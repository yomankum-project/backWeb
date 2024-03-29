package com.account.yomankum.user.service;

import com.account.yomankum.common.exception.BadRequestException;
import com.account.yomankum.common.exception.Exception;
import com.account.yomankum.common.exception.InternalErrorException;
import com.account.yomankum.user.domain.type.MailType;
import com.account.yomankum.util.RedisUtil;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;

import static com.account.yomankum.user.dto.MailDto.EmailRequestDto;
import static jakarta.mail.Message.RecipientType;
import static java.util.UUID.*;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final RedisUtil redisUtil;

    private final String CODE = "code";
    private final String UUID = "uuid";
    private final int CODE_LENGTH = 5;
    private final String CODE_VALUES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final long EXPIRE_CODE_TIME = 60 * 5L;


    @Value("${mail.id}")
    private String fromEmail;

    @Override
    public String mailSend(EmailRequestDto emailRequestDto) {
        String userEmail = emailRequestDto.email();

        if (redisUtil.existData(userEmail)) {
            redisUtil.deleteData(userEmail);
        }

        String result = "";
        String templateCode = "";
        MailType mailType = emailRequestDto.mailType();

        if (mailType.equals(MailType.JOIN)) {
            templateCode = createCode();

            JsonObject code = new JsonObject();
            code.addProperty(CODE, templateCode);

            result = new Gson().toJson(code);
        }

        if (mailType.equals(MailType.PASSWORD)) {
            templateCode = randomUUID().toString();

            JsonObject uuid = new JsonObject();
            uuid.addProperty(UUID, templateCode);

            result = new Gson().toJson(uuid);
        }

        MimeMessage template = setTemplate(mailType, userEmail, templateCode);
        sendMail(template);

        return result;
    }

    @Override
    public String createCode() {

        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CODE_VALUES.length());
            builder.append(CODE_VALUES.charAt(randomIndex));
        }
        return builder.toString();
    }
    @Override
    public MimeMessage setTemplate(MailType type, String userEmail, String templateCode) {

        String key = "";
        String value = "";
        String title = "";
        String template = "";


        if (type.equals(MailType.JOIN)) {
            title = "YOMANKUM * 가입 코드 전송";
            template = "email/joinMailForm";
            key = CODE;
            value = templateCode;
            redisUtil.setDataExpire(userEmail, templateCode, EXPIRE_CODE_TIME);
        }

        if (type.equals(MailType.PASSWORD)) {
            title = "YOMANKUM * 비밀번호 재설정";
            template = "email/passwordResetMailForm";
            key = UUID;
            value = templateCode;
            redisUtil.setDataExpire(templateCode, userEmail, EXPIRE_CODE_TIME);
        }

        MimeMessage message = setMimeMessage(userEmail, key, value, title, template);


        return message;
    }

    @Override
    public String getContext(String key, String value, String template) {
        Context context = setContext(key, value);
        return templateEngine.process(template, context);
    }

    @Override
    public void sendMail(MimeMessage message) {
        mailSender.send(message);
    }

    @Override
    public void verifyEmailCode(String userEmail, String templateCode) {
        String randomCodeByEmail = redisUtil.getData(userEmail);

        if (randomCodeByEmail == null) {
            log.error("입력한 이메일이 일치하지 않음 : {}", userEmail);
            throw new BadRequestException(Exception.EMAIL_NOT_FOUND);
        }

        if (!randomCodeByEmail.matches(templateCode)) {
            log.error("입력한 코드가 일치하지 않음 : {}", templateCode);
            throw new BadRequestException(Exception.EMAIL_CODE_UN_MATCHED);
        }
        redisUtil.deleteData(userEmail);
    }

    private MimeMessage setMimeMessage(String userEmail, String key, String value, String title, String template) {
        String charset = "UTF-8";
        String html = "html";

        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.addRecipients(RecipientType.TO, userEmail);
            message.setSubject(title);
            message.setFrom(fromEmail);
            message.setText(getContext(key, value, template), charset, html);
        } catch (MessagingException e) {
            throw new InternalErrorException(Exception.SERVER_ERROR);
        }
        return message;
    }

    private Context setContext(String key, String value) {
        Context context = new Context();
        context.setVariable(key, value);
        return context;
    }
}

package com.account.yomankum.kafka;

import com.account.yomankum.kafka.dto.AccountBookInputNotice;
import com.account.yomankum.socket.common.CustomWebSocketHandler;
import com.account.yomankum.socket.dto.AccountBookWebSocketNotice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaService {

    private final CustomWebSocketHandler customWebSocketHandler;

    @KafkaListener(topics = "input", groupId = "accountBook")
    public void inputAccountBookNotification(AccountBookInputNotice notice) {
        customWebSocketHandler.sendAccountBookMessage(AccountBookWebSocketNotice.from(notice));
        log.info("[Kafka] input 메시지 수신 - accountBookId : {}", notice.accountBookId());
    }

}


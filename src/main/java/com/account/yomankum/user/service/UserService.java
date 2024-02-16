package com.account.yomankum.user.service;

import com.account.yomankum.security.oauth.type.Tokens;
import com.account.yomankum.security.service.CustomUserDetails;
import com.account.yomankum.user.dto.request.FirstLoginUserInfoSaveDto;
import com.account.yomankum.user.dto.request.UserInfoUpdateDto;
import com.account.yomankum.user.dto.response.UserInfoDto;

import java.util.Map;

import static com.account.yomankum.user.dto.UserDto.UserLoginDto;
import static com.account.yomankum.user.dto.UserDto.UserSignUpDto;

public interface UserService {
    void signUp(UserSignUpDto userSignUpDto);
    Map<Tokens, String> login(UserLoginDto userLoginDto);
    UserInfoDto getUserInfo(CustomUserDetails userDetails);
    void updatePassword(String uuid, String password);
    void saveFirstLoginUserInfo(FirstLoginUserInfoSaveDto firstLoginUserInfoSaveDto, CustomUserDetails userDetails);
    void updateUserInfo(CustomUserDetails userDetails, UserInfoUpdateDto dto);
}

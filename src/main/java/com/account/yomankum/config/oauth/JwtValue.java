package com.account.yomankum.config.oauth;

public interface JwtValue {

    String getFirstKid();
    String getSecondKid();
    String getKid();
    String getKty();
    String getAlg();
    String getUse();
    String getN();
    String getE();
    void jwkSetting(String number);
}
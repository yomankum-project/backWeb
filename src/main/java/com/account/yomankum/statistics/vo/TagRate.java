package com.account.yomankum.statistics.vo;

import lombok.Getter;

@Getter
public class TagRate {

    private final String tag;
    private final float rate;
    private final long money;

    public TagRate(String tag, Long totalAmount, Long amountOfTag){
        this.tag = tag;
        this.money = amountOfTag;
        this.rate = (float) amountOfTag / totalAmount;
    }

}
package com.account.yomankum.sharedAccountBook.domain;

import lombok.Getter;

@Getter
public enum SharedType {
    FAMILY("가족"),
    LOVER("연인"),
    CLUB("모임"),
    FRIENDS("친구"),
    ETC("기타");

    private final String title;

    SharedType(String title) {
        this.title = title;
    }
}

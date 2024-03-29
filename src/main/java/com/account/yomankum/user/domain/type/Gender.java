package com.account.yomankum.user.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("남자"), FEMALE("여자"), NONE("없음");

    private final String genderName;

}

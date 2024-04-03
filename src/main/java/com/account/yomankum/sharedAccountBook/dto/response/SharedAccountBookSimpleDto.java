package com.account.yomankum.sharedAccountBook.dto.response;

public record SharedAccountBookSimpleDto(
        Long id,
        String name,
        String type,
        String createdDateTime
) {
}

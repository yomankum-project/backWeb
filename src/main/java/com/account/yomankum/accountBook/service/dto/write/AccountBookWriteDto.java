package com.account.yomankum.accountBook.service.dto.write;

import com.account.yomankum.accountBook.domain.AccountBook;
import com.account.yomankum.accountBook.domain.AccountBookType;
import lombok.Data;

@Data
public class AccountBookWriteDto {

    private String name;
    private AccountBookType type;

    public AccountBook toEntity(){
        return AccountBook.builder()
                .name(name)
                .type(type)
                .build();
    }

}

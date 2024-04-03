package com.account.yomankum.sharedAccountBook.controller;

import com.account.yomankum.sharedAccountBook.dto.response.SharedAccountBookSimpleDto;
import com.account.yomankum.sharedAccountBook.SharedAccountBookFinder;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/shared")
@Tag(name = "SharedAccountBook", description = "가계부 api")
public class SharedAccountBookController {

    private final SharedAccountBookFinder sharedAccountBookFinder;

    @GetMapping("/{id}")
    public List<SharedAccountBookSimpleDto> getSharedList(
            @PathVariable Long id
    ) {
        return sharedAccountBookFinder.findBySharedId(id);
    }
}

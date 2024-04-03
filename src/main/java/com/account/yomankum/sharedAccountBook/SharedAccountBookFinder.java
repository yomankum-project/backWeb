package com.account.yomankum.sharedAccountBook;

import com.account.yomankum.sharedAccountBook.dto.response.SharedAccountBookSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharedAccountBookFinder {
    @Transactional(readOnly = true)
    public List<SharedAccountBookSimpleDto> findBySharedId(Long id) {
        // dsadsa
        return null;
    }
}

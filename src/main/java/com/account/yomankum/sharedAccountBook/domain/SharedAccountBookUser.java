package com.account.yomankum.sharedAccountBook.domain;

import com.account.yomankum.common.domain.TimeBaseEntity;
import com.account.yomankum.user.domain.User;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SharedAccountBookUser extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("sharedAccountBookUser_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sharedInfo_id")
    private SharedInfo sharedInfo;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private SharedRole sharedRole;
}

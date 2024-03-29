package com.account.yomankum.user.domain;

import com.account.yomankum.user.domain.type.Gender;
import com.account.yomankum.security.oauth.type.Sns;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SnsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuidKey;
    private String email;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Role role;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Sns sns;

    private Date birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String job;

    private Instant joinDatetime;
    private Instant lastLoginDatetime;
    private Instant pwdChangeDatetime;
    private Instant stopDatetime;
    private Instant removeDatetime;
    private LocalDateTime token;
}

package com.account.yomankum.sharedAccountBook.domain;

import com.account.yomankum.common.domain.TimeBaseEntity;
import com.account.yomankum.user.domain.User;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SharedInfo extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("sharedInfo_id")
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masterUser_id")
    private User masterUser;
    @Enumerated(EnumType.STRING)
    private SharedType sharedType;
    @OneToMany(
            mappedBy = "sharedInfo",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<SharedAccountBookUser> members;
}

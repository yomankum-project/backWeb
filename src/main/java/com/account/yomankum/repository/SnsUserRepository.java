package com.account.yomankum.repository;

import com.account.yomankum.config.oauth.Sns;
import com.account.yomankum.domain.SnsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnsUserRepository extends JpaRepository<SnsUser, Long> {
    Optional<SnsUser> findByEmailAndUAndUuidKeyAndSns(Sns sns, String email, String uuidKey);
}
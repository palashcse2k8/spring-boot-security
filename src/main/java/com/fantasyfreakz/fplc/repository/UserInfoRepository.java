package com.fantasyfreakz.fplc.repository;

import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Id> {
    UserInfo findByName(String name);

    @Override
    Optional<UserInfo> findById(Id id);
}

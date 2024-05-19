package com.fantasyfreakz.fplc.repository;

import com.fantasyfreakz.fplc.domain.entites.UserCredential;
import com.fantasyfreakz.fplc.domain.entites.UserInfo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository <UserCredential, Id>{
    @Override
    Optional<UserCredential> findById(Id id);

    UserCredential findByUsername(String name);
}

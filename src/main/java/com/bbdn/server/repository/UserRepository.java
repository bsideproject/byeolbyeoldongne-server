package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    boolean findByNickname(String nickName);
}

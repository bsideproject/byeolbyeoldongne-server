package com.bbdn.server.service;

import com.bbdn.server.domain.entity.UserEntity;
import com.bbdn.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserAccountService {

    private final UserRepository userRepository;

    public UserAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> checkExistsEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity signUpGoogleUserAccount(String email) {
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .createdAt(LocalDateTime.now())
                .createdBy("SYSYTEM")
                .loginType("Google")
                .build();

        return userRepository.save(userEntity);
    }
}

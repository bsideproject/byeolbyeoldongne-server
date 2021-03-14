package com.bbdn.server.service;

import com.bbdn.server.domain.entity.UserEntity;
import com.bbdn.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@Transactional
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

    public UserEntity modifyUserAccountNickName(String email, String nickName) {

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isPresent()) {
            UserEntity saveUserEntity = UserEntity.builder()
                    .id(userEntity.get().getId())
                    .email(email)
                    .nickname(nickName)
                    .build();
            return userRepository.save(saveUserEntity);
        } else {
            return new UserEntity();
        }
    }

    @Transactional(readOnly = true)
    public boolean checkNickNameAlreadyExsits(String nickName) {
        return userRepository.findByNickname(nickName);
    }
}

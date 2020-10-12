package com.chdlsp.datapractice.service;

import com.chdlsp.datapractice.domain.entity.UserEntity;
import com.chdlsp.datapractice.domain.interfaces.request.CreateUserInfoRequestVO;
import com.chdlsp.datapractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // 4가지 기능 제공 : propagation, isolation, rollback, readOnly
/*
 * propagation : REQUIRED(default), REQUIRES_NEW
 * isolation : default 가 DBMS 마다 다름 => 대부분은 commit 된 것만 처리 가능함
 * rollback : 특정 exception 이 발생할 때 rollback 처리
 * readOnly : 읽기전용 여부 (default : false)
 * */
public class TransactionDemoService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserEntity saveNewUserInfoWithDefault(CreateUserInfoRequestVO requestVO) {

        UserEntity saveInfo = UserEntity.builder()
                .email(requestVO.getEmail())
                .password(requestVO.getPassword())
                .build();

        return userRepository.save(saveInfo);
    }

    @Transactional(readOnly = true)
    public UserEntity saveNewUserInfoWithReadOnlyTrue(CreateUserInfoRequestVO requestVO) {

        UserEntity saveInfo = UserEntity.builder()
                .email(requestVO.getEmail())
                .password(requestVO.getPassword())
                .build();

        return userRepository.save(saveInfo);
    }

    @Transactional(readOnly = true)
    public UserEntity getUserInfoById(String email) {

        Optional<UserEntity> userInfoById = userRepository.findById(email);

        if(userInfoById.isPresent()) {
            return userInfoById.get();
        } else {
            // TODO: 적절한 Exception 처리 필요
            throw new IllegalArgumentException("요청받은 email 존재하지 않습니다." + email);
        }
    }

    // TODO: 구현 필요
    public UserEntity putUserInfo(CreateUserInfoRequestVO createUserInfoRequestVO) {
        return null;
    }

    @Transactional
    public int deleteUserInfoById(String email) {

        UserEntity delUserInfo = UserEntity.builder()
                .email(email)
                .build();

        try {
            userRepository.delete(delUserInfo);
            return 1;
        } catch(Exception e) {
            // TODO: 적절한 Exception 처리 필요
            return 0;
        }
    }
}

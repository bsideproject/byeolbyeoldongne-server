package com.bbdn.server.application.controller;

import com.bbdn.server.domain.entity.UserEntity;
import com.bbdn.server.domain.interfaces.request.UserAccountNickNameRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.service.UserAccountService;
import com.bbdn.server.domain.interfaces.request.UserAccountGoogleRequest;
import com.bbdn.server.domain.interfaces.response.UserAccountExistsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private UserAccountService userAccountService;

    @PostMapping("/account/google")
    public UserAccountExistsResponse processUserAccountInfo(UserAccountGoogleRequest userAccountGoogleRequest) {

        UserAccountExistsResponse userAccountExistsResponse = new UserAccountExistsResponse();
        String email = userAccountGoogleRequest.getGoogleUserInfoVO().getEmail();
        Optional<UserEntity> userAccount = userAccountService.checkExistsEmail(email);

        if(userAccount.isPresent()) {
            userAccountExistsResponse.setCode("02");
            userAccountExistsResponse.setMessage("기존 회원 입니다.");
            userAccountExistsResponse.setNickName(userAccount.get().getNickname());
        } else {
            userAccountExistsResponse.setCode("01");
            userAccountExistsResponse.setMessage("신규 회원 입니다.");

            UserEntity userEntity = userAccountService.signUpGoogleUserAccount(email);

            if(userEntity.getEmail() == null) {
                userAccountExistsResponse.setCode("99");
                userAccountExistsResponse.setMessage("신규 회원 처리 중 오류가 발생했습니다.");
            }
        }

        return userAccountExistsResponse;
    }

    @PutMapping("/account/nickname")
    public CommonNotificationResponse modifyUserAccountNickName(UserAccountNickNameRequest userAccountNickNameRequest) {

        String email = userAccountNickNameRequest.getEmail();
        String nickName = userAccountNickNameRequest.getNickName();

        UserEntity userEntity = userAccountService.modifyUserAccountNickName(email, nickName);

        CommonNotificationResponse commonNotificationResponse = new CommonNotificationResponse();

        if(userEntity.getNickname().equals(nickName)) {
            commonNotificationResponse.setCode("01");
            commonNotificationResponse.setMessage("닉네임 수정에 성공했습니다.");
        } else {
            commonNotificationResponse.setCode("99");
            commonNotificationResponse.setMessage("닉네임 수정에 실패했습니다.");
        }
        return commonNotificationResponse;
    }
}

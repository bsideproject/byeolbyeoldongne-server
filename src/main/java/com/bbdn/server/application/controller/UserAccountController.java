package com.bbdn.server.application.controller;

import com.bbdn.server.domain.entity.UserEntity;
import com.bbdn.server.domain.interfaces.request.UserAccountNickNameRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.domain.interfaces.response.NickNameProcessResponse;
import com.bbdn.server.service.UserAccountService;
import com.bbdn.server.domain.interfaces.request.UserAccountGoogleRequest;
import com.bbdn.server.domain.interfaces.response.UserAccountExistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserAccountController {

    private UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/account/google")
    public UserAccountExistsResponse processUserAccountInfo(@RequestBody UserAccountGoogleRequest userAccountGoogleRequest) {

        log.info("processUserAccountInfo: " + userAccountGoogleRequest.toString());
        UserAccountExistsResponse userAccountExistsResponse = new UserAccountExistsResponse();
        String email = userAccountGoogleRequest.getUser().getEmail();
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
    public NickNameProcessResponse modifyUserAccountNickName(@RequestBody UserAccountNickNameRequest userAccountNickNameRequest) {

        String email = userAccountNickNameRequest.getEmail();
        String nickName = userAccountNickNameRequest.getNickName();

        NickNameProcessResponse nickNameProcessResponse = new NickNameProcessResponse();

        boolean isExistNickName = userAccountService.checkNickNameAlreadyExsits(nickName);

        if(isExistNickName) {
            nickNameProcessResponse.setCode("02");
            nickNameProcessResponse.setExistsNickName(true);
            nickNameProcessResponse.setMessage("기존에 사용중인 닉네임 입니다.");

        } else {
            UserEntity userEntity = userAccountService.modifyUserAccountNickName(email, nickName);

            if(userEntity.getNickname().equals(nickName)) {
                nickNameProcessResponse.setCode("01");
                nickNameProcessResponse.setMessage("닉네임 수정에 성공했습니다.");
            } else {
                nickNameProcessResponse.setCode("99");
                nickNameProcessResponse.setMessage("닉네임 수정에 실패했습니다.");
            }
            nickNameProcessResponse.setExistsNickName(false);
        }
        return nickNameProcessResponse;
    }
}

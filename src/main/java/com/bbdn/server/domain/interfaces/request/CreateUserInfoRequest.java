package com.bbdn.server.domain.interfaces.request;

import lombok.*;

@NoArgsConstructor
@Data
public class CreateUserInfoRequest {

    String email;
    String password;
    String nickName;

    @Builder
    public CreateUserInfoRequest(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

}

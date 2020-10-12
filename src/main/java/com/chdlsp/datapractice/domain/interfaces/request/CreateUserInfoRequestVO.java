package com.chdlsp.datapractice.domain.interfaces.request;

import lombok.*;

@NoArgsConstructor
@Data
public class CreateUserInfoRequestVO {

    String email;
    String password;
    String nickName;

    @Builder
    public CreateUserInfoRequestVO(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

}

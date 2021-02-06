package com.bbdn.server.domain.interfaces.vo.google;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class GoogleUserInfoVO {
    @Email
    String email;
    String id;
    String givenName;
    String familyName;
    String photo;
    String name;

}

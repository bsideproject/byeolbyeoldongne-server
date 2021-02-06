package com.bbdn.server.domain.interfaces.request;

import com.bbdn.server.domain.interfaces.vo.google.GoogleUserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserAccountNickNameRequest {
    String email;
    String nickName;
}

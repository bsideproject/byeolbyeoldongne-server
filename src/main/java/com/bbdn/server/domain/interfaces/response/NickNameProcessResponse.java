package com.bbdn.server.domain.interfaces.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NickNameProcessResponse extends CommonNotificationResponse {
    boolean isExistsNickName;
}

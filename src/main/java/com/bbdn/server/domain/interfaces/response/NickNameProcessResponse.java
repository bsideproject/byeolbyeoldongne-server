package com.bbdn.server.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NickNameProcessResponse extends CommonNotificationResponse {
    boolean isExistsNickName;
}

package com.chdlsp.datapractice.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInfoResponse {
    String code;
    String message;
}

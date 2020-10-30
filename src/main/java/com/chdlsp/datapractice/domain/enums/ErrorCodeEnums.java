package com.chdlsp.datapractice.domain.enums;


import org.springframework.http.HttpStatus;

public enum ErrorCodeEnums {
    // Account Context
    PASSWORD_MISMATCH(4001,"아이디 또는 비밀번호를 확인하여 주세요.", HttpStatus.UNAUTHORIZED),
    NON_EXISTENCE_ACCOUNT(4004, "계정이 존재 하지 않습니다.", HttpStatus.NOT_FOUND),
    ALREADY_EXISTENCE_USERNAME(4009, "이미 존재 하는 아이디 입니다.", HttpStatus.CONFLICT),

    // keyword
    REQUIRED_KEY_WORD(4201, "키워드는 필수로 입력하셔야 합니다.", HttpStatus.BAD_REQUEST),


    BAD_REQUEST(400, "잘못 된 요청 입니다.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(403, "접근 권한이 없습니다.", HttpStatus.FORBIDDEN)
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCodeEnums(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus toHttpStatus() {
        return this.httpStatus;
    }
}

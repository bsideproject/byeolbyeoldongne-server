package com.bbdn.server.application.constant;

public class ErrorConstant {

    public final String VALIDATOR_USER_NAME = "아이디는 필수로 입력 하셔야 합니다.";
    public final String VALIDATOR_PASSWORD = "비밀번호는 필수로 입력 하셔야 합니다.";
    public final String VALIDATOR_QUERY = "검색을 원하는 질의어는 필수로 입력하셔야 합니다.";
    public final String VALIDATOR_PLACE_SERVICE_TYPE = "존재하지 않는 서비스 타입 입니다. 사용 가능한 서비스 타입(KAKAO_MAP)";
    public final String VALIDATOR_REQUIRED_PLACE_SERVICE_TYPE = "서비스 타입은 필수로 입력하셔야 합니다.";
    public final String VALIDATOR_NOT_SUPPORT_CATEGORY_GROUP_CODE = "지원 하지 않는 카테고리 그룹 코드 입니다.(  MT1(\"대형마트\"),\n"
            + "  CS2(\"편의점\"),\n"
            + "  PS3(\"어린이집,유치원\"),\n"
            + "  SC4(\"학교\"),\n"
            + "  AC5(\"학원\"),\n"
            + "  PK6(\"주차장\"),\n"
            + "  OL7(\"주유소,충전소\"),\n"
            + "  SW8(\"지하철역\"),\n"
            + "  BK9(\"은행\"),\n"
            + "  CT1(\"문화시설\"),\n"
            + "  AG2(\"중개업소\"),\n"
            + "  PO3(\"공공기관\"),\n"
            + "  AT4(\"관광명소\"),\n"
            + "  AD5(\"숙박\"),\n"
            + "  FD6(\"음식점\"),\n"
            + "  CE7(\"카페\"),\n"
            + "  HP8(\"병원\"),\n"
            + "  PM9(\"약국\")";
    public final String VALIDATOR_REQUIRED_QUERY_PARAMETER = "쿼리 파라미터 값은 필수 입니다.";
    public final String VALIDATOR_NOT_SUPPORT_SORT_KEY = "지원하지 않는 SORT KEY 입니다.";
    public final String VALIDATOR_REQUIRED_X = "X 좌표 값이 필요합니다.";
    public final String VALIDATOR_REQUIRED_Y = "Y 좌표 값이 필요합니다.";
}

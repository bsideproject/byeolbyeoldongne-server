package com.bbdn.server.domain.interfaces.vo.kakao;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.util.List;

@ToString
@Getter
public class KakaoPlaceVO {
    private final MetaVO meta;
    private final List<DocumentVO> documents;

    @ConstructorProperties({"meta", "documents"})
    public KakaoPlaceVO(MetaVO meta, List<DocumentVO> documents) {
        this.meta = meta;
        this.documents = documents;
    }
}
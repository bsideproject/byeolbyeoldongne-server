package com.bbdn.server.domain.interfaces.vo.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@ToString
public class PaginationVO {
    private final boolean end;
    private final int pageableCount;
    private final int totalCount;

    @Builder
    @ConstructorProperties({"end", "pageableCount", "totalCount"})
    public PaginationVO(boolean end, int pageableCount, int totalCount) {
        this.end = end;
        this.pageableCount = pageableCount;
        this.totalCount = totalCount;
    }
}

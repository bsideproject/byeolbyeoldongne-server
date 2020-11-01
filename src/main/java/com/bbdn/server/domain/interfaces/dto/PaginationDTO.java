package com.bbdn.server.domain.interfaces.dto;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class PaginationDTO {
    private final boolean end;
    private final int pageableCount;
    private final int totalCount;

    @Builder
    @ConstructorProperties({"end", "pageableCount", "totalCount"})
    public PaginationDTO(boolean end, int pageableCount, int totalCount) {
        this.end = end;
        this.pageableCount = pageableCount;
        this.totalCount = totalCount;
    }
}

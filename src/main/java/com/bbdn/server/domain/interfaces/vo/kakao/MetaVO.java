package com.bbdn.server.domain.interfaces.vo.kakao;

import java.beans.ConstructorProperties;

import lombok.Getter;

@Getter
public class MetaVO {
    private final int total_count;
    private final int pageable_count;
    private final boolean is_end;
    private final RegionInfoVO same_name;

    @ConstructorProperties({"total_count", "pageable_count", "is_end", "same_name"})
    public MetaVO(int total_count, int pageable_count, boolean is_end,
                  RegionInfoVO same_name) {
        this.total_count = total_count;
        this.pageable_count = pageable_count;
        this.is_end = is_end;
        this.same_name = same_name;
    }
}

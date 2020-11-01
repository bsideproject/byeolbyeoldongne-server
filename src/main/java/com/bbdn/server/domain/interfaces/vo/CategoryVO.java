package com.bbdn.server.domain.interfaces.vo;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class CategoryVO {
    //
    private final String name;
    private final CategoryGroupVO group;

    @Builder
    @ConstructorProperties({"name", "group"})
    public CategoryVO(String name, CategoryGroupVO group) {
        this.name = name;
        this.group = group;
    }
}

package com.chdlsp.datapractice.domain.interfaces.dto;

import com.chdlsp.datapractice.domain.enums.KakaoCategoryGroupEnums;
import com.chdlsp.datapractice.handler.exception.BadRequestException;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class QueryParameterDTO {

  private final String query;
  private KakaoCategoryGroupEnums kakaoCategoryGroupEnums;
  private Double x;
  private Double y;
  private Integer radius;
  private String rect;
  private Integer page;
  private Integer size;
//  private SortKey sort;

  public QueryParameterDTO(String query) {
    Objects.requireNonNull(query, "질의어는 필수 입니다.");
    this.query = query;
  }

  public void setCategoryGroupCodeEnums(
          KakaoCategoryGroupEnums kakaoCategoryGroupEnums) {
    this.kakaoCategoryGroupEnums = kakaoCategoryGroupEnums;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public void setY(Double y) {
    this.y = y;
  }

  public void setRect(String rect) {
    this.rect = rect;
  }

//  public void setSort(SortKey sort) {
//    this.sort = sort;
//  }

  public void setRadius(Integer radius) {
    if (!(0 <= radius && radius <= 20000)) {
      throw new BadRequestException("radius는 0 ~ 2000 사이의 값만 가능 합니다.");
    }
    this.radius = radius;
  }

  public void setPage(Integer page) {
    if (Objects.isNull(page)) {
      this.page = 1;
    }
    if (!(1 <= page && page <= 45)) {
      throw new BadRequestException("page는 1 ~ 45 사이의 값만 가능 합니다.");
    }
    this.page = page;
  }

  public void setSize(Integer size) {
    if (Objects.isNull(size)) {
      this.size = 15;
    }
    if (!(1 <= size && size <= 15)) {
      throw new BadRequestException("size는 1 ~ 15 사이의 값만 가능 합니다.");
    }
    this.size = size;
  }
}

package com.bbdn.server.domain.interfaces.dto;

import com.bbdn.server.domain.enums.KakaoCategoryGroupEnums;
import com.bbdn.server.domain.enums.KakaoMapRestUrlEnums;
import com.bbdn.server.handler.exception.BadRequestException;

import java.util.Objects;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryParameterDTO {

  private String query;
  private KakaoCategoryGroupEnums kakaoCategoryGroupEnums;
  private Double x;
  private Double y;
  private Integer radius;
  private String rect;
  private Integer page;
  private Integer size;

  @Setter
  private String addressName;

  @Setter
  private KakaoMapRestUrlEnums kakaoMapRestUrlEnums;

  public QueryParameterDTO(String query) {
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

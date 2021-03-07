package com.bbdn.server.domain.interfaces.vo.kakao;

import java.beans.ConstructorProperties;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class RegionInfoVO {

  private List<String> region;
  private String keyword;
  private String selectedRegion;

  @Builder
  @ConstructorProperties({"region", "keyword", "selectedRegion"})
  public RegionInfoVO(List<String> region, String keyword, String selectedRegion) {
    this.region = region;
    this.keyword = keyword;
    this.selectedRegion = selectedRegion;
  }
}

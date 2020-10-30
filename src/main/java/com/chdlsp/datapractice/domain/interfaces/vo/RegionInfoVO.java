package com.chdlsp.datapractice.domain.interfaces.vo;

import java.beans.ConstructorProperties;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RegionInfoVO {

  private final List<String> region;
  private final String keyword;
  private final String selectedRegion;

  @Builder
  @ConstructorProperties({"region", "keyword", "selectedRegion"})
  public RegionInfoVO(List<String> region, String keyword, String selectedRegion) {
    this.region = region;
    this.keyword = keyword;
    this.selectedRegion = selectedRegion;
  }
}

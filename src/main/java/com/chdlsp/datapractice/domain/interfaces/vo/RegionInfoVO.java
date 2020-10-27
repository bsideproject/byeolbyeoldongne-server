package com.chdlsp.datapractice.domain.interfaces.vo;

import java.beans.ConstructorProperties;
import java.util.List;
import lombok.Getter;

@Getter
public class RegionInfoVO {

  private final List<String> region;
  private final String keyword;
  private final String selected_region;

  @ConstructorProperties({"region", "keyword", "selected_region"})
  public RegionInfoVO(List<String> region, String keyword, String selected_region) {
    this.region = region;
    this.keyword = keyword;
    this.selected_region = selected_region;
  }
}

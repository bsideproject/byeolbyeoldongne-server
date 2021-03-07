package com.bbdn.server.domain.interfaces.vo;

import lombok.*;

import java.util.Comparator;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressLocationVO implements Comparable<AddressLocationVO> {
    Long placeId; // 장소 ID
    String roadAddressName;
    double lat; // y, 위도
    double lng; // x, 경도

    @Override
    public int compareTo(AddressLocationVO addressLocationVO) {
        return this.placeId.compareTo(addressLocationVO.getPlaceId());
    }
}

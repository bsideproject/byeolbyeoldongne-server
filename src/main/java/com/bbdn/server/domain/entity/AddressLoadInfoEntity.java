package com.bbdn.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class AddressLoadInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String roadName; // 도로명
    String mainBuildingNo; // 건물본번
    String zoneNo; // Zone 번호

    String addressName; // 전체 도로명주소
    String region1depthName; // 지역1Depth
    String region2depthName; // 지역2Depth
    String region3depthName; // 지역3Depth
    String undergroundYn; // 지하여부
    String subBuildingNo; // 건물부번
    String buildingName; // 건물명

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}

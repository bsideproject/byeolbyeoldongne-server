package com.chdlsp.datapractice.domain.entity;

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
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String email;

    private String password;
    private String nickname;
    private boolean emailExpired; // 탈퇴여부
    private int emailLocked; // 5회잠금 카운트
    private String ip; // 가입 시 client ip

    private LocalDateTime lassPasswordChanged; // 패스워드 변경 시점
    private String loginType; // 로그인 타입

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // UserEntity 1 : N ContentEntity
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ContentEntity> contentEntityList;
}

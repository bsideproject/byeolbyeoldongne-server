package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.ContentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentInfoRepository extends JpaRepository<ContentInfoEntity, String> {

}

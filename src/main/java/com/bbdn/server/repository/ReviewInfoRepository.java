package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.CommentInfoEntity;
import com.bbdn.server.domain.entity.ReviewInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewInfoRepository extends JpaRepository<ReviewInfoEntity, Long> {

}

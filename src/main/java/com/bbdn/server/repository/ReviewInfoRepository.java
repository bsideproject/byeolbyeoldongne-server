package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.CommentInfoEntity;
import com.bbdn.server.domain.entity.ReviewInfoEntity;
import com.bbdn.server.domain.interfaces.response.PlaceReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewInfoRepository extends JpaRepository<ReviewInfoEntity, Long> {
    List<ReviewInfoEntity> findByPlaceId(Long placeId);
}

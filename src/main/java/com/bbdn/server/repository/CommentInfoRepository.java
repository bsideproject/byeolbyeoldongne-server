package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.CommentInfoEntity;
import com.bbdn.server.domain.interfaces.response.PlaceCommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfoEntity, String> {

    List<CommentInfoEntity> findByPlaceIdAndReviewSequence(Long placeId, Long reviewSequence);
}

package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.CommentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfoEntity, String> {

}

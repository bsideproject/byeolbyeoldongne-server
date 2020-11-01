package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.CommentReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReplyEntity, String> {

}

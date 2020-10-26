package com.chdlsp.datapractice.repository;

import com.chdlsp.datapractice.domain.entity.CommentReplyEntity;
import com.chdlsp.datapractice.domain.entity.ContentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReplyEntity, String> {

}

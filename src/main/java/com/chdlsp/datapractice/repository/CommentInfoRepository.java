package com.chdlsp.datapractice.repository;

import com.chdlsp.datapractice.domain.entity.CommentInfoEntity;
import com.chdlsp.datapractice.domain.entity.CommentReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfoEntity, String> {

}

package com.chdlsp.datapractice.repository;

import com.chdlsp.datapractice.domain.entity.ContentInfoEntity;
import com.chdlsp.datapractice.domain.entity.UserConcernInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentInfoRepository extends JpaRepository<ContentInfoEntity, String> {

}

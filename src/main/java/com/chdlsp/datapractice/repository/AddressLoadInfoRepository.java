package com.chdlsp.datapractice.repository;

import com.chdlsp.datapractice.domain.entity.AddressLoadInfoEntity;
import com.chdlsp.datapractice.domain.entity.CommentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressLoadInfoRepository extends JpaRepository<AddressLoadInfoEntity, String> {

}

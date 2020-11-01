package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.AddressLoadInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressLoadInfoRepository extends JpaRepository<AddressLoadInfoEntity, String> {

}

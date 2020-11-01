package com.bbdn.server.repository;

import com.bbdn.server.domain.entity.UserConcernInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConcernRepository extends JpaRepository<UserConcernInfoEntity, String> {

}

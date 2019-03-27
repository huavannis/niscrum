package com.hvnis.niscrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.UserRoleEntity;

/**
 * @author hvnis
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}

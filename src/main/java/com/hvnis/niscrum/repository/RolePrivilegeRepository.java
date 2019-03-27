package com.hvnis.niscrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.RolePrivilegeEntity;

/**
 * @author hvnis
 */
@Repository
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilegeEntity, Long> {

}

package com.hvnis.niscrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.PrivilegeEntity;

/**
 * @author hvnis
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {

}

package com.hvnis.niscrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.RoleEntity;

/**
 * @author hvnis
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}

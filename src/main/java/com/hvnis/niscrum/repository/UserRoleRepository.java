package com.hvnis.niscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.UserRoleEntity;

/**
 * @author hvnis
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserId(Long userId);
}

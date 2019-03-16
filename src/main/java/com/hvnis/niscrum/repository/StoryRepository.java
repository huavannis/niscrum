package com.hvnis.niscrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hvnis.niscrum.entity.StoryEntity;

/**
 * @author hvnis
 */
@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, String> {

}

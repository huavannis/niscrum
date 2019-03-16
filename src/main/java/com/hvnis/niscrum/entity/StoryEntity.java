package com.hvnis.niscrum.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hvnis
 */
@Entity
@NoArgsConstructor
@Getter
public class StoryEntity extends AbstractEntity {

	private String name;
	
	public StoryEntity(final String name) 
	{
		this.name = name;
	}
}

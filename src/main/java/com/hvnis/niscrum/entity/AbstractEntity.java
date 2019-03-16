package com.hvnis.niscrum.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author hvnis
 */
public abstract class AbstractEntity {

	@Id
	@GeneratedValue
	private long id;
}

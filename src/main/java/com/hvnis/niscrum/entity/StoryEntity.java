package com.hvnis.niscrum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author hvnis
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "story")
public class StoryEntity extends AbstractEntity {

    @Column
    private String name;

    public StoryEntity(final String name) {
        this.name = name;
    }
}

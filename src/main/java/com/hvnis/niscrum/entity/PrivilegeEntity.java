package com.hvnis.niscrum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hvnis
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "privilege")
public class PrivilegeEntity extends AbstractEntity {

    @Column(unique = true)
    private String name;
}

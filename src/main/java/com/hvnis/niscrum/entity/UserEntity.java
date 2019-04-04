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
@Table(name = "user")
public class UserEntity extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @Column
    private Boolean enabled;

    @Column
    private Boolean buildin;

    @Column
    private String description;
}

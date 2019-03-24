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

    @Column(unique = true)
    private String username;

    @Column
    private String password;
    
    @Column
    private String salt;

    @Column
    private Boolean enabled;
}

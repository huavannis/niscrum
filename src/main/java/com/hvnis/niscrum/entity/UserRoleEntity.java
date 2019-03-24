package com.hvnis.niscrum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "user_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "roleId" }) })
public class UserRoleEntity extends AbstractEntity {

    @Column
    private Long userId;

    @Column
    private Long roleId;
}

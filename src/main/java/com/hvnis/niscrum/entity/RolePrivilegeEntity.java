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
@Table(name = "role_privilege", uniqueConstraints = { @UniqueConstraint(columnNames = { "roleId", "privilegeId" }) })
public class RolePrivilegeEntity extends AbstractEntity {

    @Column
    private Long roleId;

    @Column
    private Long privilegeId;
}

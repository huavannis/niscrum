package com.hvnis.niscrum.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hvnis
 */
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends AbstractDto {

    private static final long serialVersionUID = -5453327329223500982L;

    private String name;
}

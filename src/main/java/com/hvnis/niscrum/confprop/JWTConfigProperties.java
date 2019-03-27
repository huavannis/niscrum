package com.hvnis.niscrum.confprop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hvnis
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "niscrum.jwt")
public class JWTConfigProperties {

    private Long expirationTime;

    private String secret;
}

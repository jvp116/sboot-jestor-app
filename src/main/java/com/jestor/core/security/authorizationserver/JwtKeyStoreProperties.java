package com.jestor.core.security.authorizationserver;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@Component
@ConfigurationProperties("jestor.jwt.keystore")
public class JwtKeyStoreProperties {

    @NotNull
    private Resource jksLocation;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;

}

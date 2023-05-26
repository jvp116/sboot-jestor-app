package com.jestor.core.security;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Validated
public class JestorSecurityProperties {
    
    @NotBlank
    private String providerUrl;
}

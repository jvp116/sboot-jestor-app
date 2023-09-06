package com.jestor.core.security.config.redis;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@Getter
public class CacheConfigurationProperties {

    @Value("${spring.cache.redis.time-to-live}")
    private long timeToLive;

    @Value("${spring.cache.redis.connect-timeout-seconds}")
    private long connectTimeout;

    @Value("${spring.cache.redis.command-timeout-seconds}")
    private long commandTimeout;

    @Value("${spring.cache.redis.host}")
    private String host;

    @Value("${spring.cache.redis.port}")
    private Integer port;

    @Value("${spring.cache.redis.password}")
    private String password;
}
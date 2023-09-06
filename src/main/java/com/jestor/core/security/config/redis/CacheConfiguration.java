package com.jestor.core.security.config.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CacheConfiguration implements CachingConfigurer {

    private final CacheConfigurationProperties properties;

    @Override
    @Bean
    public CacheErrorHandler errorHandler() {
        return new AppCacheErrorHandler();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        log.info("Abrindo conexao com Redis...");
        try {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(properties.getHost(), properties.getPort());
            redisStandaloneConfiguration.setPassword(RedisPassword.of(properties.getPassword()));
            redisStandaloneConfiguration.setDatabase(0);
            log.info("Redis conectado!");

            return new LettuceConnectionFactory(redisStandaloneConfiguration, getLettuceClientConfiguration());
        } catch (Exception e) {
            log.error("Falha ao abrir conexao com Redis!");
            throw e;
        }
    }

    @Bean
    public AppRedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var redisTemplate = new AppRedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(createCacheConfiguration(properties.getTimeToLive()))
                .build();

        redisCacheManager.setTransactionAware(true);
        return redisCacheManager;
    }

    @NotNull
    private LettuceClientConfiguration getLettuceClientConfiguration() {
        SocketOptions socketOptions = SocketOptions.builder()
                .connectTimeout(Duration.ofSeconds(properties.getConnectTimeout()))
                .build();

        TimeoutOptions timeoutOptions = TimeoutOptions.builder()
                .fixedTimeout(Duration.ofSeconds(properties.getCommandTimeout()))
                .timeoutCommands(true)
                .build();

        ClientOptions clientOptions = ClientOptions.builder()
                .socketOptions(socketOptions)
                .timeoutOptions(timeoutOptions)
                .build();
        return LettuceClientConfiguration.builder().clientOptions(clientOptions).build();
    }

    @NotNull
    private RedisCacheConfiguration createCacheConfiguration(long ttl) {
        var redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(ttl))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new JdkSerializationRedisSerializer(getClass().getClassLoader())));
        redisCacheConfiguration.usePrefix();
        return redisCacheConfiguration;
    }

    public class AppCacheErrorHandler implements CacheErrorHandler {
        @Override
        public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
            log.error("Erro na operação get do Redis", e);
        }

        @Override
        public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
            log.error("Erro na operação put do Redis", e);
        }

        @Override
        public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
            log.error("Erro na operação evict do Redis", e);
        }

        @Override
        public void handleCacheClearError(RuntimeException e, Cache cache) {
            log.error("Erro na operação clear do Redis", e);
        }
    }

    public class AppRedisTemplate<K, V> extends RedisTemplate<K, V> {
        private static final String MSG_ERRO_OPERACAO_REDIS = "Erro na operação do Redis :: ";

        @Override
        public <T> T execute(RedisCallback<T> action) {
            try {
                return super.execute(action);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public <T> T execute(RedisCallback<T> action, boolean exposeConnection) {
            try {
                return super.execute(action, exposeConnection);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public <T> T execute(RedisCallback<T> action, boolean exposeConnection, boolean pipeline) {
            try {
                return super.execute(action, exposeConnection, pipeline);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public <T> T execute(SessionCallback<T> session) {
            try {
                return super.execute(session);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public List<Object> executePipelined(SessionCallback<?> session) {
            try {
                return super.executePipelined(session);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return Collections.emptyList();
            }
        }

        @Override
        public List<Object> executePipelined(SessionCallback<?> session, RedisSerializer<?> resultSerializer) {
            try {
                return super.executePipelined(session, resultSerializer);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return Collections.emptyList();
            }
        }

        @Override
        public List<Object> executePipelined(RedisCallback<?> action) {
            try {
                return super.executePipelined(action);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return Collections.emptyList();
            }
        }

        @Override
        public List<Object> executePipelined(RedisCallback<?> action, RedisSerializer<?> resultSerializer) {
            try {
                return super.executePipelined(action, resultSerializer);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return Collections.emptyList();
            }
        }

        @Override
        public <T> T execute(RedisScript<T> script, List<K> keys, Object... args) {
            try {
                return super.execute(script, keys, args);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public <T> T execute(RedisScript<T> script, RedisSerializer<?> argsSerializer, RedisSerializer<T> resultSerializer, List<K> keys, Object... args) {
            try {
                return super.execute(script, argsSerializer, resultSerializer, keys, args);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }

        @Override
        public <T extends Closeable> T executeWithStickyConnection(RedisCallback<T> callback) {
            try {
                return super.executeWithStickyConnection(callback);
            } catch (Exception e) {
                log.error(MSG_ERRO_OPERACAO_REDIS, e);
                return null;
            }
        }
    }
}
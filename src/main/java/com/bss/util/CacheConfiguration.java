package com.bss.util;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.serializer.RedisSerializer;

//@Configuration
//@ConditionalOnProperty(value="resolved.cache.enabled", havingValue = "true", matchIfMissing = true)
public class CacheConfiguration {
//    @Value("${resolved.cache.ttl:30}")
//    private long ttlInMinutes;
//    
//    @Value("${ENV:env}")
//    private String environment;
//    
//    private static final String API_PREFIX = "auth";
//    private static final String SEPARATOR = ":";
//
//    @Bean(value = "cacheManager")
//    public CacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
//            
//            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                  .disableCachingNullValues()
//                  .entryTtl(Duration.ofMinutes(ttlInMinutes))
//                  .computePrefixWith(cacheName -> API_PREFIX.concat(SEPARATOR).concat(environment).concat(SEPARATOR)
//                        .concat(cacheName).concat(SEPARATOR)) // cache key prefix with the environment
//                  .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
//            redisCacheConfiguration.usePrefix();
//
//            return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
//                  .cacheDefaults(redisCacheConfiguration).build();
//    }
}
package com.ddbullfrog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

    private static final String URL_UNIQUE_COUNTER = "URL_UNIQUE_COUNTER";

    @Autowired
    RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisProperties.getHost());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        jedisConnectionFactory.setTimeout(redisProperties.getTimeout());
        jedisConnectionFactory.setUsePool(true);

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisAtomicInteger redisAtomicInteger() {
        return new RedisAtomicInteger(URL_UNIQUE_COUNTER, jedisConnectionFactory());
    }
}

package com.b2mark.priceDiscovery.config;

//import com.b2mark.priceDiscovery.entity.Price;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisOperations;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
//@ComponentScan
public class PriceConfiguration {

//    @Bean
//    ReactiveRedisOperations<String, Price> redisOperations(ReactiveRedisConnectionFactory factory) {
//        Jackson2JsonRedisSerializer<Price> serializer = new Jackson2JsonRedisSerializer<>(Price.class);
//        RedisSerializationContext.RedisSerializationContextBuilder<String, Price> builder =
//                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//        RedisSerializationContext<String, Price> context = builder.value(serializer).build();
//        return new ReactiveRedisTemplate<>(factory, context);
//    }
}
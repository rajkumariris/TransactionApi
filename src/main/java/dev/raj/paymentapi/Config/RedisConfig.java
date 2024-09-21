package dev.raj.paymentapi.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.raj.paymentapi.DTOs.ResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
   public RedisTemplate redisTemplate(RedisConnectionFactory factory){
       RedisTemplate redisTemplate = new RedisTemplate();
       redisTemplate.setConnectionFactory(factory);
       redisTemplate.setKeySerializer(new StringRedisSerializer());
       Jackson2JsonRedisSerializer<ResponseDto> serializer = new Jackson2JsonRedisSerializer<>(ResponseDto.class);
       ObjectMapper objectMapper = new ObjectMapper();
       serializer.setObjectMapper(objectMapper);
       redisTemplate.setValueSerializer(serializer);
       return redisTemplate;
   }
}

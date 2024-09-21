package dev.raj.paymentapi.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;
    public <T> T get(String key, Class<T> tClass) throws JsonProcessingException {
     try {
         Object o = redisTemplate.opsForValue().get(key);
         ObjectMapper objectMapper = new ObjectMapper();
         return  tClass.cast(o);
     }
     catch (Exception e){
         log.error("Error in RedisService.get: "+e.getMessage());
         return null;
     }
    }
    public void set(String key, Object tClass, Long ttl) throws JsonProcessingException {
        try {
            redisTemplate.opsForValue().set(key, tClass, ttl, TimeUnit.MINUTES);
        }
        catch (Exception e){
            log.error("Error in RedisService.get: "+e.getMessage());
        }
    }

}

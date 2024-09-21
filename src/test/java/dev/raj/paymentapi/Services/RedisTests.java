package dev.raj.paymentapi.Services;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {


    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    void testSendMail(){
       // redisTemplate.opsForValue().set("email", "raj@email.com");
        String email = (String) redisTemplate.opsForValue().get("salary");
        System.out.println(email);
    }
}

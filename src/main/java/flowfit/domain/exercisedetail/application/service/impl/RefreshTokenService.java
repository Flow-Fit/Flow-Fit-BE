package flowfit.domain.exercisedetail.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final StringRedisTemplate redisTemplate;

    public void storeRefreshToken(String email, String refreshToken, long ttl) {
        redisTemplate.opsForValue().set(refreshToken, email, ttl, TimeUnit.MILLISECONDS);
    }

    public void checkRefreshTokenExists(String refreshToken) {
        redisTemplate.hasKey(refreshToken);
    }

    public void removeRefreshToken(String refreshToken) {
        redisTemplate.delete(refreshToken);
    }

    public void findEmailByRefreshToken(String refreshToken) {
        redisTemplate.opsForValue().get(refreshToken);
    }
}
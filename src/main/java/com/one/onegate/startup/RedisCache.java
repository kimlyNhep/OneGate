package com.one.onegate.startup;

import com.one.onegate.mapper.ResponseCodeMapper;
import com.one.onegate.model.ResponseCode;
import com.one.onegate.utils.Constants;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisCache {
    private static final String KEY = "responseCacheKey";

    private final ResponseCodeMapper responseCodeMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    private static HashOperations<String, String, ResponseCode> hashOperations;

    @Autowired
    public RedisCache(
            ResponseCodeMapper responseCodeMapper,
            RedisTemplate<String, Object> redisTemplate) {
        this.responseCodeMapper = responseCodeMapper;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    private void initRespCodeCache(List<ResponseCode> respCodes) {
        for (ResponseCode responseCode : respCodes) {
            hashOperations.put(KEY, responseCode.getCode(), responseCode);
        }
    }

    public static void addRespCodeCache(final ResponseCode respCode) {
        hashOperations.put(KEY, respCode.getId().toString(), respCode);
    }

    public static void reloadRespCode(final ResponseCode respCode) {
        hashOperations.put(KEY, respCode.getCode(), respCode);
    }

    public static ResponseCode getRespCode(final String code) {
        return hashOperations.get(KEY, code);
    }

    @Bean
    public void loadResponseCodeToCache() {
        List<ResponseCode> responseCodes = responseCodeMapper.findAllByStatus(Constants.STATUS_ACTIVE);
        initRespCodeCache(responseCodes);
    }
}

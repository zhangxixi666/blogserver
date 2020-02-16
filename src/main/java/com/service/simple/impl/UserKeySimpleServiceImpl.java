package com.service.simple.impl;

import com.common.BaseConstants;
import com.service.simple.api.UserKeySimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

@Slf4j
public class UserKeySimpleServiceImpl implements UserKeySimpleService {
    @Autowired(required = false)
    private Jedis redisClient;
    @Override
    public String getCurrentTableKey(){
        try{
            String key = BaseConstants.REFORM_USER_CURRENT_KEY;
            String val = redisClient.get(key);
            log.info("-----user.getCurrentTableKey:" + val);
            return val;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

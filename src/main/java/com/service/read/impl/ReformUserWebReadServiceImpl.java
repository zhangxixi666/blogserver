package com.service.read.impl;

import com.alibaba.fastjson.JSON;
import com.common.BaseConstants;
import com.service.read.api.ReformUserWebReadService;
import com.service.simple.api.UserKeySimpleService;
import com.wizard.dto.ReformUserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
@Slf4j
public class ReformUserWebReadServiceImpl implements ReformUserWebReadService {
    @Autowired(required = false)
    private Jedis redisClient;
    @Resource
    UserKeySimpleService userKeySimpleService;
    @Override
    public ReformUserDto getUserByLoginName(String loginName, int status){
        try{
            log.info("-----ReformUserWebReadServiceImpl.getUserByLoginName.param:loginName:" + loginName + ",status:" + status);
            String key = BaseConstants.SECOND_LEVEL_KEY_READ_USER + loginName;
            String tempStr = redisClient.get(key);
            log.info("-----ReformUserWebReadServiceImpl.getUserByLoginName.resp:" + tempStr);
            if(StringUtils.isNoneBlank(tempStr)){
                return JSON.parseObject(tempStr, ReformUserDto.class);
            }

            /**
             * 二级缓存不存在，去表中取，填充第三方接口返回获取动态表名
             * */
            String table = userKeySimpleService.getCurrentTableKey();
            String field = status+":"+loginName;
            String userStr = redisClient.hget(table, field);
            if(StringUtils.isBlank(userStr)){
                log.info("-----ReformUserWebReadServiceImpl.getUserByLoginName:缓存底表不存在该用户,"+loginName);
                return null;
            }
            ReformUserDto userDto = JSON.parseObject(userStr, ReformUserDto.class);
            String timeout = "600";
            redisClient.setex(key, Integer.parseInt(timeout), JSON.toJSONString(userDto));
            log.info("-----ReformUserWebReadServiceImpl.getUserByLoginName:二级缓存缓存该用户,"+loginName);
            return userDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

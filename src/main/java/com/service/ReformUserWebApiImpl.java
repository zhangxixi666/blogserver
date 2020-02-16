package com.service;

import com.service.read.api.ReformUserWebReadService;
import com.wizard.UserStatus;
import com.wizard.api.ReformUserWeb;
import com.wizard.dto.ReformUserDto;

import javax.annotation.Resource;

public class ReformUserWebApiImpl implements ReformUserWeb {
    @Resource
    ReformUserWebReadService reformUserWebReadService;
    @Override
    public ReformUserDto getUserByLoginName(String loginName){
        try{
            ReformUserDto user = reformUserWebReadService.getUserByLoginName(loginName, UserStatus.VALID.getValue());
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

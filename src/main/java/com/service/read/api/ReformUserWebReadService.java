package com.service.read.api;

import com.wizard.dto.ReformUserDto;

public interface ReformUserWebReadService {
    public ReformUserDto getUserByLoginName(String loginName, int status);
}

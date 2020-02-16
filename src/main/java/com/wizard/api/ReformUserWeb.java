package com.wizard.api;

import com.wizard.dto.ReformUserDto;

public interface ReformUserWeb {
    public ReformUserDto getUserByLoginName(String loginName);
}

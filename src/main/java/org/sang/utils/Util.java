package org.sang.utils;

import org.sang.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by sang on 2017/12/20.
 */
public class Util {
    /*
    * 获取当前用户
    * */
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}

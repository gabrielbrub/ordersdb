package com.brub.ordersdb.config;

import com.brub.ordersdb.model.User;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {

    public Object generate(Object target, Method method, Object... params) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + user.getId() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }
}
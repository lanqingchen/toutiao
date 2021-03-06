package com.nowcoder.configuration;

import com.nowcoder.interceptor.LoginInterceptor;
import com.nowcoder.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by BlueFish on 2016/7/6.
 */
@Component
public class ToutiaoWebConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private PassportInterceptor passportInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginInterceptor).addPathPatterns("/setting");
        super.addInterceptors(registry);
    }
}

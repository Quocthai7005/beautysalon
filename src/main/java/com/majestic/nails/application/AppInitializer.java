package com.majestic.nails.application;

import com.majestic.nails.configuration.*;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
        	MainDBConfiguration.class,
        	AmazonConfiguration.class,
        	AsyncConfiguration.class,
                RedisConfiguration.class,
                WebSecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/*" };
    } 
}

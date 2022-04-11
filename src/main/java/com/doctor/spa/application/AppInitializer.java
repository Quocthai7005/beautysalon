package com.doctor.spa.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.doctor.spa.configuration.AmazonConfiguration;
import com.doctor.spa.configuration.AsyncConfiguration;
import com.doctor.spa.configuration.MainDBConfiguration;
import com.doctor.spa.configuration.MvcConfiguration;
import com.doctor.spa.configuration.RedisConfiguration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
        	MainDBConfiguration.class,
        	AmazonConfiguration.class,
        	AsyncConfiguration.class, RedisConfiguration.class};
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

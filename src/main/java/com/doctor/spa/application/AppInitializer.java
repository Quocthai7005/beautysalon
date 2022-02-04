package com.doctor.spa.application;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.doctor.spa.configuration.AmazonConfiguration;
import com.doctor.spa.configuration.DbConfiguration;
import com.doctor.spa.configuration.MvcConfiguration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { DbConfiguration.class, AmazonConfiguration.class };
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

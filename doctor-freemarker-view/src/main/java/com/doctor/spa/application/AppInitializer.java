package com.doctor.spa.application;

import com.doctor.spa.configuration.*;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
        	AsyncConfiguration.class,
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

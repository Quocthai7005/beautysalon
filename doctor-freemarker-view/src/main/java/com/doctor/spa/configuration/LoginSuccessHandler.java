package com.doctor.spa.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginSuccessHandler extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null) {
    		SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach((role) -> {
        		if (role.toString().equals("ADMIN")) {
        			request.getSession().setMaxInactiveInterval(30*60);
        			return;
        		}
        	});
    	}
    }
}
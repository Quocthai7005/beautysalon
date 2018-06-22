package com.doctor.spa.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doctor.spa.dto.ImageDto;
import com.doctor.spa.service.PageTextService;

public class CaptionInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	PageTextService pageService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {		
	    List<ImageDto> images = pageService.getShownImage();	    
	    modelAndView.addObject("headerImages", images);
	}
}

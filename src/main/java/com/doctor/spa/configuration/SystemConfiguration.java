package com.doctor.spa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class SystemConfiguration {

	@Value("${paging.default_page}")
	private int defaultPage;
	
	@Value("${paging.default_size}")
	private int defaultSize;

	@Value("${attachment.base_path}")
	private String basePath;

	public int getDefaultPage() {
		return defaultPage;
	}

	public int getDefaultSize() {
		return defaultSize;
	}

	public String getBasePath() {
		return basePath;
	}

}

package com.doctor.spa.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class SystemConfiguration {

	@Value("0")
	private int defaultPage;
	
	@Value("${paging.default_size}")
	private int defaultSize;

	public int getDefaultPage() {
		return defaultPage;
	}

	public int getDefaultSize() {
		return defaultSize;
	}

}

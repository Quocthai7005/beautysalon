package com.majestic.nails.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SystemConfiguration {

	@Value("0")
	private int defaultPage;
	
	@Value("5")
	private int defaultSize;

	public int getDefaultPage() {
		return defaultPage;
	}

	public int getDefaultSize() {
		return defaultSize;
	}

}

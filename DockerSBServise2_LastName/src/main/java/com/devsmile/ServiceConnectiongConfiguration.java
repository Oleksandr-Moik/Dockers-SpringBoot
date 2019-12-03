package com.devsmile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "service.age")
public class ServiceConnectiongConfiguration {

	private String host;
	private String port;
	
}

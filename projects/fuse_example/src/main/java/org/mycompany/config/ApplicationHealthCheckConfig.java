package org.mycompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.mycompany.health.ApplicationHealthCheck;

@Configuration
public class ApplicationHealthCheckConfig {

	@Bean(name = "app-health-check")
    public ApplicationHealthCheck applicationHealthCheck() {
		return new ApplicationHealthCheck("global", "app-health-check");
    }
	
}

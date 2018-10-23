package org.shyu.springboot.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.shyu.springboot.endpoint.TopicEndPoint;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig extends ResourceConfig {
	public TopicConfig() {
		register(TopicEndPoint.class);
	}
}

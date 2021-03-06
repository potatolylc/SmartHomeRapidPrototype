package ioedata.socket.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@ComponentScan(
		basePackages = "ioedata.socket",
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.ANNOTATION, 
				value = Configuration.class))
@EnableWebSocketMessageBroker
public class SocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		super.configureMessageBroker(registry);
		registry.enableSimpleBroker("/queue/", "/topic/");
		registry.setApplicationDestinationPrefixes("/app");
		System.out.println("configureMessageBroker...");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/socket");
		registry.addEndpoint("/webSocket").withSockJS();
		System.out.println("registerStompEndpoints...");
	}

}

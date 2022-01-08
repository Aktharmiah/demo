package payroll.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import payroll.models.User;

@Configuration
public class AppConfig {
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){

		return builder.build();
	}

    @Bean
    public User person(RestTemplate restTemplate){

        User user = new User();

        return user;
    }
}

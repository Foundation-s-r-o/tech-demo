package sk.foundation.techdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.vladmihalcea.spring.repository",
		"sk.foundation.techdemo"
})
public class TechDemoApplicationConfiguration {

}

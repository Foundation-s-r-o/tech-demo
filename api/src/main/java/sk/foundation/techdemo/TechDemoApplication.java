package sk.foundation.techdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.vladmihalcea.spring.repository",
		"sk.foundation.techdemo"
})
public class TechDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechDemoApplication.class, args);
	}

}

package br.com.hotmart.challenge;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.hotmart.libs.repository")
@EntityScan(basePackages = "br.com.hotmart.libs.domain")
@ConfigurationProperties("hotmart.default")
@Configuration
@EnableScheduling
public class ChallengeApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Value("${hotmart.default.timezone}")
	private String timezone;

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone(timezone));
	}

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

}

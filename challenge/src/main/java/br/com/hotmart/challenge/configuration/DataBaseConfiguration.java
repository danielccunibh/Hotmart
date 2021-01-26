package br.com.hotmart.challenge.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.hotmart.libs.repository")
@EntityScan(basePackages = "br.com.hotmart.libs.domain")
@Configuration
public class DataBaseConfiguration {

}

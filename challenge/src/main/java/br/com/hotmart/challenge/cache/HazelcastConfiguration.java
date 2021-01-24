package br.com.hotmart.challenge.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
@EnableCaching
public class HazelcastConfiguration {

	@Bean
	public Config hazelCastConfig() {
		return new Config().setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("hotmart").setTimeToLiveSeconds(60));
	}

}
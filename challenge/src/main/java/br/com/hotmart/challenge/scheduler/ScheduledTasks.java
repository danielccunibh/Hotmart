package br.com.hotmart.challenge.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.hotmart.challenge.service.NewsApiService;
import br.com.hotmart.libs.util.DateUtil;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private Date startDate;

	@Autowired
	private NewsApiService service;

	@Scheduled(fixedDelay = 500)
	// @Scheduled(cron = "0 0 */6 ? * *")
	public void reportCurrentTime() {
		startDate = new Date();
		log.info("Iniciando a pesquisa por noticias de categoria de produtos: {}", dateFormat.format(startDate));

		service.updateNewsApi();

		log.info("Concluido a pesquisa por noticias de categoria de produtos: {}", dateFormat.format(new Date()));
		log.info("Tempo decorrido: {}", DateUtil.getDaysBetweenDates(startDate, new Date()));
	}

}

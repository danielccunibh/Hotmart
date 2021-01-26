package br.com.hotmart.challenge.scheduler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.hotmart.challenge.service.NewsApiService;
import br.com.hotmart.challenge.service.ProductService;
import br.com.hotmart.libs.util.DateUtil;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private Date startDate;

	@Autowired
	private NewsApiService newsApiService;

	@Autowired
	private ProductService productService;

	@Scheduled(cron = "0 0 */6 ? * *")
	public void updateProdctCategoryNewsJob() {
		startDate = new Date();
		log.info("Iniciando a pesquisa por noticias de categoria de produtos: {}", getDateForLog(startDate));

		newsApiService.updateNewsApi();

		log.info("Concluido a pesquisa por noticias de categoria de produtos: {}", getDateForLog(new Date()));
		log.info("Tempo decorrido: {}", DateUtil.getDaysBetweenDates(startDate, new Date()));
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void updateScoreProduct() {
		startDate = new Date();
		log.info("Iniciando o calculo de score dos produtos: {}", getDateForLog(startDate));

		productService.updateScoreProduct();

		log.info("Concluido o calculo de score dos produtos: {}", getDateForLog(new Date()));
		log.info("Tempo decorrido: {}", DateUtil.getDaysBetweenDates(startDate, new Date()));
	}

	private String getDateForLog(Date date) {
		return DateUtil.getDateFormated(date, DateUtil.HHMMSS);
	}

}

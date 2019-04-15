package br.com.alexandre;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import br.com.alexandre.repositories.PlanetRepository;
import br.com.alexandre.services.SwapiService;

@SpringBootApplication(exclude=EmbeddedMongoAutoConfiguration.class)
//@EnableCaching
public class SwapiApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SwapiApplication.class, args);
	}
	
//	@Bean
//	public CacheManager cacheManager() {
//		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
//	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
//	@Bean
//	public EhCacheManagerFactoryBean ehCacheCacheManager() {
//		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
//		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
//		cmfb.setShared(true);
//		return cmfb;
//	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.getInterceptors().add(addRequestHeader("User-agent", "curl/7.59.0"));
		return restTemplate;
	}
	
	@Bean
	CommandLineRunner init(PlanetRepository planetRepository, RepositoryRestConfiguration repositoryRestConfiguration, MongoTemplate mongoTemplate) {
		return (evt) -> {
			// disabling all the REST mappings automatically exposed by the repositories.
			//repositoryRestConfiguration.disableDefaultExposure();
			
			log.info("Using mongodb database with name: " +  mongoTemplate.getDb().getName());
			
		};
	}
	
	@Bean
	public CommandLineRunner run(SwapiService swapiService) throws Exception {
		return args -> {
			// run arbitrary code at application boot
		};
	}
	
	@Bean
	public ClientHttpRequestInterceptor addRequestHeader(String name, String value) {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().set(name, value);
				return execution.execute(request, body);
			}
		};
	}

}

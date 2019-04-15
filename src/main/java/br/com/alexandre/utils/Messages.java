package br.com.alexandre.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	@Autowired
	private MessageSource messageSource;
	
	private static Locale locale = Locale.US;
	
	public String planetIdNotFound(String id) {
		Object[] args = new Object[] {id};
		return messageSource.getMessage("planetIdNotFound", args, locale);
	}
	
	public String planetNameNotFound(String name) {
		Object[] args = new Object[] {name};
		return messageSource.getMessage("planetNameNotFound", args, locale);
	}
	
	public String planetNameConflict(String name, String id) {
		Object[] args = new Object[] {name,id};
		return messageSource.getMessage("planetNameConflict", args, locale);
	}
	
	public String noPlanetInSwapi(String name) {
		Object[] args = new Object[] {name};
		return messageSource.getMessage("noPlanetInSwapi", args, locale);
	}
	
	public String emptyDatabase() {
		return messageSource.getMessage("emptyDatabase",null, locale);
	}
}

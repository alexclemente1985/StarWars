package br.com.alexandre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlanetNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 188790403191120671L;
	
	public PlanetNotFoundException(String message) {
		super(message);
	}
}

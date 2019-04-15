package br.com.alexandre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoPlanetInSwapiException extends Exception {
	private static final long serialVersionUID = -6098667240417451070L;

	public NoPlanetInSwapiException (String msg) {
		super(msg);
	}
}

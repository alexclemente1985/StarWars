package br.com.alexandre.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlanetNameConflictException extends RuntimeException{
	private static final long serialVersionUID = -6098667240417451040L;

	public PlanetNameConflictException (String msg) {
		super(msg);
	}
}

package br.com.quemateria.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException{

	private static final long serialVersionUID = -2647212333569346058L;
	
	private String message;
	
	public ApiException(String message) {
		super(message);
		this.message = message;
	}

}

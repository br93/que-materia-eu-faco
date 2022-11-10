package br.com.quemateria.exceptions;

public class CustomNotFoundException extends ApiException{
	
	private static final long serialVersionUID = 2557088997991404375L;
	
	public CustomNotFoundException(String message) {
		super(message);
	}

}

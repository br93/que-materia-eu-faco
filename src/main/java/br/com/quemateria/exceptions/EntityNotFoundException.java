package br.com.quemateria.exceptions;

public class EntityNotFoundException extends ApiException{
	
	private static final long serialVersionUID = 2557088997991404375L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}

}

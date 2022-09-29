package br.com.quemateria.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDTO {
	
	private String message;
	private List<String> errors;
	private HttpStatus status;
	
	public ApiErrorDTO(String message, List<String> errors, HttpStatus status) {
		this.message = message;
		this.errors = errors;
		this.status = status;
	}

	public ApiErrorDTO(String message, String error, HttpStatus status) {
		this.message = message;
		this.errors = Arrays.asList(error);
		this.status = status;
	}

}

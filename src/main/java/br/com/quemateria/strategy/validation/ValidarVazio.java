package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarVazio extends StrategyValidation{
	
	public final Logger logger = LoggerFactory.getLogger(ValidarBranco.class);
	
	@Override
	public void validar(String parametro, String nome) {
		logger.info("Validando se " + nome + " não é vazio...");
		if(parametro.isEmpty()) {
			logger.info("Inválido: " + nome + " não pode estar vazio");
			throw new CustomBadRequestException(nome + " não pode estar vazio");
		}
			
	}

}

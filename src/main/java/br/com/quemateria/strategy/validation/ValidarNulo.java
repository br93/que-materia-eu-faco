package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarNulo extends StrategyValidation{
	
	public final Logger logger = LoggerFactory.getLogger(ValidarNulo.class);
	
	@Override
	public void validar(String parametro, String nome) {
		logger.info("Validando se " + nome + " não é nulo...");
		if(parametro == null) {
			logger.info("Inválido: " + nome + " não pode ser nulo");
			throw new CustomBadRequestException(nome + "não pode ser nulo");
		}
			
	}

}

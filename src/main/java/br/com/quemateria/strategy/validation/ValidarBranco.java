package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarBranco extends StrategyValidation {
	
	public final Logger logger = LoggerFactory.getLogger(ValidarBranco.class);

	@Override
	public void validar(String parametro, String nome) {
		logger.info("Validando se " + nome + " não é branco...");
		if (parametro.isBlank()) {
			logger.info("Inválido: " + nome + " não pode estar em branco");
			throw new CustomBadRequestException(nome + " não pode estar em branco");
		}
			
	}

}

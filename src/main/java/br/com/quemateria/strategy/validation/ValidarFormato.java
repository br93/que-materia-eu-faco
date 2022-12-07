package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarFormato extends StrategyValidation{
	
	public final Logger logger = LoggerFactory.getLogger(ValidarFormato.class);
	
	@Override
	public void validar(String parametro, String nome, String regex, String formato) {
		logger.info("Validando se " + nome + " está no formato correto...");
		if(!parametro.matches(regex)) {
			logger.info("Inválido: " + nome + " com formato inválido. Formato aceito: " + formato);
			throw new CustomBadRequestException(nome + " com formato inválido. Formato aceito: " + formato);
		}
			
	}

}

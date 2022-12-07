package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarTamanho extends StrategyValidation {

	public final Logger logger = LoggerFactory.getLogger(ValidarTamanho.class);

	@Override
	public void validar(String parametro, String nome, Integer tamanho) {
		logger.info("Validando se " + nome + " tem o número de caracteres corretos...");
		if (parametro.length() != tamanho) {
			logger.info("Inválido: " + nome + " com " + parametro.length() + " caracteres" + " mas deveria ter " + tamanho + " caracteres");
			throw new CustomBadRequestException(nome + "com " + parametro.length() + "caracteres " + "mas deveria ter " + tamanho + " caracteres");
		}

	}
}

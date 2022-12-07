package br.com.quemateria.strategy.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quemateria.exceptions.CustomBadRequestException;
import br.com.quemateria.strategy.StrategyValidation;

public class ValidarIntervalo extends StrategyValidation {

	public final Logger logger = LoggerFactory.getLogger(ValidarIntervalo.class);

	@Override
	public void validar(Integer parametro, String nome, Integer min, Integer max) {
		logger.info("Validando se " + nome + " está no intervalo correto...");
		if (parametro < min) {
			logger.info("Inválido: " + nome + " menor que o valor minimo de " + min);
			throw new CustomBadRequestException(nome + "menor que o valor minimo de " + min);
		}

		if (parametro > max) {
			logger.info("Inválido: " + nome + " maior que o valor maximo de " + max);
			throw new CustomBadRequestException(nome + "maior que o valor maximo de " + max);
		}

	}

}

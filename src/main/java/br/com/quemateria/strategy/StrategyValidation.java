package br.com.quemateria.strategy;

public abstract class StrategyValidation implements Strategy{
	
	@Override
	public void validar(String parametro, String nome) {
	
	}
	
	@Override
	public void validar(String parametro, String nome, Integer tamanho) {
		
	}
	
	@Override
	public void validar(String parametro, String nome, String regex, String formato) {
		
	}
	
	@Override
	public void validar(Integer parametro, String nome, Integer min, Integer max) {
		
	}

}

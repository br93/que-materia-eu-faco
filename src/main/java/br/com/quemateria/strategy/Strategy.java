package br.com.quemateria.strategy;

public interface Strategy {

	public void validar(String parametro, String nome);
	public void validar(String parametro, String nome, Integer tamanho);
	public void validar(String parametro, String nome, String regex, String formato);
		
	public void validar(Integer parametro, String nome, Integer min, Integer max);
	
	

}

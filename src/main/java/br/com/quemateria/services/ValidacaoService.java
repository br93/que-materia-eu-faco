package br.com.quemateria.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.quemateria.strategy.StrategyValidation;
import br.com.quemateria.strategy.validation.ValidarBranco;
import br.com.quemateria.strategy.validation.ValidarFormato;
import br.com.quemateria.strategy.validation.ValidarIntervalo;
import br.com.quemateria.strategy.validation.ValidarNulo;
import br.com.quemateria.strategy.validation.ValidarTamanho;
import br.com.quemateria.strategy.validation.ValidarVazio;

@Service
public class ValidacaoService {
	
	private StrategyValidation validacao;
	public final Logger logger = LoggerFactory.getLogger(ValidacaoService.class);
	
	public void validarId(Long id) {
		logger.info("Validando id...");
		this.validarString(id.toString(), "id");
		this.validarNumero(id.toString(), "id");
		
		logger.info("Id validado!");
	}
	
	public void validarNome(String string, String nome) {
		logger.info("Validando nome...");
		this.validarString(string, nome);
		
		logger.info("Nome validado!");
	}
	
	public void validarCodigoAluno(String registro) {
		logger.info("Validando registro de aluno...");
		this.validarString(registro, "Registro");
		
		logger.info("Validando quantidade de digitos do registro de aluno...");
		this.validarTamanho(registro, "Registro", 8);
		
		logger.info("Validando formato do registro de aluno...");
		this.validarFormato(registro, "Registro", "^[a][0-9]{7}", "Formato a0000000");
		
		logger.info("Registro de aluno validado!");
	}
	
	public void validarCodigoCurso(String codigo) {
		logger.info("Validando código de curso...");
		this.validarString(codigo, "Código de curso");
		
		logger.info("Validando quantidade de digitos de código de curso...");
		this.validarTamanho(codigo, "Código de curso", 3);
		
		logger.info("Validando formato do código de curso...");
		this.validarFormato(codigo, "Código de curso", "^[0-9]{3}$", "Formato 000");
		
		logger.info("Código de curso validado!");
	}
	
	public void validarCodigoDisciplina(String codigo) {
		logger.info("Validando código de disciplina...");
		this.validarString(codigo, "Código de disciplina");
		
		logger.info("Validando quantidade de dígitos do código de disciplina...");
		this.validarIntervalo(codigo.length(), "Código de disciplina", 5, 6);
		
		logger.info("Validando formato do código de disciplina...");
		this.validarFormato(codigo, "Código de disciplina", 
				"(^[A-Z0-9]{5}|^[A-Z0-9]{6})$", "Formato XXXXX ou XXXXXX");
		
		logger.info("Código de disciplina validado!");
	}
	
	public void validarCodigoTurma(String codigo) {
		logger.info("Validando código de turma...");
		this.validarString(codigo, "Código de turma");
		
		logger.info("Validando quantidade de dígitos do código de turma...");
		this.validarIntervalo(codigo.length(), "codigo", 9, 10);
		
		logger.info("Validando formato do código de turma...");
		this.validarFormato(codigo, "Código de turma", 
				"(^[A-Z0-9]{5}|^[A-Z0-9]{6})-[A-Z]{1}[0-9]{2}$", "Formato XXXXX-A00 ou XXXXXX-A00");
		
		logger.info("Código de turma validado!");
	}
	
	public void validarPeriodo(Integer periodo) {
		logger.info("Validando período...");
		this.validarString(periodo.toString(), "periodo");
		this.validarNumero(periodo.toString(), "periodo");
		
		logger.info("Validando intervalo permitido para período...");
		this.validarIntervalo(periodo, "periodo", 1, 10);
		
		logger.info("Período validado!");
	}
	
	public void validarCargaHoraria(Integer cargaHoraria) {
		logger.info("Validando carga horária...");
		this.validarString(cargaHoraria.toString(), "carga horária");
		this.validarNumero(cargaHoraria.toString(), "carga horária");
		
		logger.info("Validando intervalo permitido para carga horária...");
		this.validarIntervalo(cargaHoraria, "carga horária", 15, 150);
		
		logger.info("Carga horária validada!");
	}
	
	public void validarTipoDeDisciplina(Integer tipo) {
		logger.info("Validando tipo de disciplina...");
		this.validarString(tipo.toString(), "tipo de disciplina");
		this.validarNumero(tipo.toString(), "tipo de disciplina");
		
		logger.info("Validando intervalo permitido para tipo de disciplina...");
		this.validarIntervalo(tipo, "tipo de disciplina", 1, 4);
	}
	
	public void validarQuantidadePreRequisitos(Integer preRequisitos) {
		logger.info("Validando quantidade de pré-requisitos...");
		this.validarString(preRequisitos.toString(), "quantidade de pré-requisitos");
		this.validarNumero(preRequisitos.toString(), "quantidade de pré-requisitos");
		
		logger.info("Validando intervalo permitido para quantidade de pre-requisitos...");
		this.validarIntervalo(preRequisitos, "carga horária", 0, 50);
		
		logger.info("Quantidade de pré-requisitos validada");
	}
	
	public void validarDia(Integer dia) {
		logger.info("Validando dia...");
		this.validarString(dia.toString(), "dia");
		this.validarNumero(dia.toString(), "dia");
		
		logger.info("Validando intervalo permitido para dia...");
		this.validarIntervalo(dia, "dia", 2, 6);
		
		logger.info("Dia validado");
	}
	
	public void validarHorario(String horario) {
		logger.info("Validando horário...");
		this.validarString(horario, "horario");
		
		logger.info("Validando formato permitido para horário...");
		this.validarFormato(horario, "horario", "(^[MT][1-6]|^[N][1-5])$", "Formato X0");
		
		logger.info("Horário validado");
	}
	
	private void validarBranco(String parametro, String nome) {
		validacao = new ValidarBranco();
		validacao.validar(parametro, nome);
	}
	
	private void validarFormato(String parametro, String nome, String regex, String formato) {
		validacao = new ValidarFormato();
		validacao.validar(parametro, nome, regex, formato);
	}
	
	private void validarIntervalo(Integer parametro, String nome, Integer min, Integer max) {
		validacao = new ValidarIntervalo();
		validacao.validar(parametro, nome, min, max);
	}
	
	private void validarNulo(String parametro, String nome) {
		validacao = new ValidarNulo();
		validacao.validar(parametro, nome);
	}
	
	private void validarNumero(String parametro, String nome) {
		logger.info("Validando número...");
		this.validarFormato(parametro, nome, "^[0-9]+$", "numero");
	}
	
	private void validarTamanho(String parametro, String nome, Integer tamanho) {
		validacao = new ValidarTamanho();
		validacao.validar(parametro, nome, tamanho);
	}
	
	private void validarVazio(String parametro, String nome) {
		validacao = new ValidarVazio();
		validacao.validar(parametro, nome);
	}
	
	private void validarString(String parametro, String nome) {
		this.validarNulo(parametro, nome);
		this.validarVazio(parametro, nome);
		this.validarBranco(parametro, nome);
	}
	

}

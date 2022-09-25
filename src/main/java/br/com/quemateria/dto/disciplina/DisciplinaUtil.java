package br.com.quemateria.dto.disciplina;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.MatrizCurricular;
import br.com.quemateria.entities.Turma;
import br.com.quemateria.services.DisciplinaService;

@Component
public class DisciplinaUtil {
	
	private final DisciplinaService disciplinaService;
	
	public DisciplinaUtil(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetId{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetNome{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetRequisitos{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetCargaHoraria{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetMatrizCurricular{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetTurmas{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetEquivalencias{
		
	}
	
	@GetId
	public Long getId(String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getId();
	}
	
	@GetNome
	public String getNome (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getNome();
	}
	
	@GetRequisitos
	public Set<Disciplina> getRequisitos (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getRequisitos();
	}
	
	@GetCargaHoraria
	public Integer getCargaHoraria (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getCargaHoraria();
	}
	
	@GetMatrizCurricular
	public Set<MatrizCurricular> getMatrizCurricular (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getMatrizCurricular();
	}
	
	@GetTurmas
	public Set<Turma> getTurmas (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getTurmas();
	}
	
	@GetEquivalencias
	public Set<Disciplina> getEquivalencias (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getEquivalencias();
	}
	
	
}

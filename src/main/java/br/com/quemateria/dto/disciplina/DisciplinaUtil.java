package br.com.quemateria.dto.disciplina;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import br.com.quemateria.entities.Curso;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.Horario;
import br.com.quemateria.entities.TipoDeDisciplina;
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
	public @interface GetCurso{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetHorario{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetNome{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetPeriodo{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetRequisitos{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetTipoDeDisciplina{
		
	}
	
	@GetId
	public Long getId(String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getId();
	}
	
	@GetCurso
	public Curso getCurso (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getCurso();
	}
	
	@GetHorario
	public Horario getHorario (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getHorario();
	}
	
	@GetNome
	public String getNome (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getNome();
	}
	
	@GetPeriodo
	public Integer getPeriodo (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getPeriodo();
	}
	
	@GetRequisitos
	public Set<Disciplina> getRequisitos (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getRequisitos();
	}
	
	@GetTipoDeDisciplina
	public TipoDeDisciplina getTipoDeDisciplina (String codigo) {
		return disciplinaService.buscarDisciplinaPorCodigo(codigo).getTipoDeDisciplina();
	}
	
	
}

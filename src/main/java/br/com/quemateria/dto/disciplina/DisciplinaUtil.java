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
import br.com.quemateria.entities.HorarioAula;
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
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetCargaHoraria{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetHorarios{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetCodigo{
		
	}
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface GetPreRequisito{
		
	}
	
	@GetId
	public Long getId(String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getId();
	}
	
	@GetCurso
	public Curso getCurso (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getCurso();
	}
	
	@GetNome
	public String getNome (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getNome();
	}
	
	@GetPeriodo
	public Integer getPeriodo (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getPeriodo();
	}
	
	@GetRequisitos
	public Set<Disciplina> getRequisitos (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getRequisitos();
	}
	
	@GetTipoDeDisciplina
	public TipoDeDisciplina getTipoDeDisciplina (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getTipoDeDisciplina();
	}
	
	@GetCargaHoraria
	public Integer getCargaHoraria (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getCargaHoraria();
	}
	
	@GetHorarios
	public Set<HorarioAula> getHorarios (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getHorarios();
	}
	
	@GetCodigo
	public String getCodigo (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getCodigo();
	}
	
	@GetPreRequisito
	public Integer getPreRequisito (String turma) {
		return disciplinaService.buscarDisciplinaPorTurma(turma).getPreRequisito();
	}
	
	
}

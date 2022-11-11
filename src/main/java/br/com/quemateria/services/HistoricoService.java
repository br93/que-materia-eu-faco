package br.com.quemateria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.DisciplinaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoricoService {

	private AlunoService alunoService;
	private DisciplinaRepository disciplinaRepository;

	private Long getCursoIdAluno(Long alunoId) {
		return alunoService.getUsuario(alunoId).getCurso().getId();
	}

	public Set<Disciplina> getDisciplinasCursadas(Long alunoId) {
		return alunoService.getUsuario(alunoId).getDisciplinasCursadas();
	}

	public List<Disciplina> listarDisciplinasObrigatorias(Long alunoId) {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdAluno(alunoId), 1L);
	}

	public List<Disciplina> listarDisciplinasSegundoEstrato(Long alunoId) {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdAluno(alunoId), 2L);
	}

	public List<Disciplina> listarDisciplinasTrilha(Long alunoId) {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdAluno(alunoId), 3L);
	}

	public List<Disciplina> listarDisciplinasOpcionais(Long alunoId) {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdAluno(alunoId), 4L);
	}

	public List<Disciplina> getDisciplinasObrigatoriasFaltantes(Long alunoId) {

		List<Disciplina> disciplinasObrigatorias = this.listarDisciplinasObrigatorias(alunoId);
		List<Disciplina> obrigatoriasFaltantes = disciplinasObrigatorias;

		obrigatoriasFaltantes.removeAll(this.getDisciplinasCursadas(alunoId));

		return obrigatoriasFaltantes;
	}

	public List<Disciplina> getDisciplinasFaltantes(Long alunoId) {
		List<Disciplina> disciplinasFaltantes = new ArrayList<>();
		disciplinasFaltantes.addAll(this.listarDisciplinasObrigatorias(alunoId));
		disciplinasFaltantes.addAll(this.listarDisciplinasSegundoEstrato(alunoId));
		disciplinasFaltantes.removeAll(this.getDisciplinasCursadas(alunoId));

		return disciplinasFaltantes;
	}

	public List<Disciplina> getOpcionaisFaltantes(Long alunoId) {
		List<Disciplina> disciplinasFaltantes = new ArrayList<>();
		disciplinasFaltantes.addAll(this.listarDisciplinasTrilha(alunoId));
		disciplinasFaltantes.addAll(this.listarDisciplinasOpcionais(alunoId));
		disciplinasFaltantes.removeAll(this.getDisciplinasCursadas(alunoId));

		return disciplinasFaltantes;
	}

}

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

	private Long getCursoIdUsuario() {
		return alunoService.getUsuario().getCurso().getId();
	}

	public Set<Disciplina> getDisciplinasCursadas() {
		return alunoService.getUsuario().getDisciplinasCursadas();
	}

	public List<Disciplina> listarDisciplinasObrigatorias() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdUsuario(), 1L);
	}

	public List<Disciplina> listarDisciplinasSegundoEstrato() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdUsuario(), 2L);
	}

	public List<Disciplina> listarDisciplinasTrilha() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdUsuario(), 3L);
	}

	public List<Disciplina> listarDisciplinasOpcionais() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(
						getCursoIdUsuario(), 4L);
	}

	public List<Disciplina> getDisciplinasObrigatoriasFaltantes() {

		List<Disciplina> disciplinasObrigatorias = this.listarDisciplinasObrigatorias();
		List<Disciplina> obrigatoriasFaltantes = disciplinasObrigatorias;

		obrigatoriasFaltantes.removeAll(this.getDisciplinasCursadas());

		return obrigatoriasFaltantes;
	}

	public List<Disciplina> getDisciplinasFaltantes() {
		List<Disciplina> disciplinasFaltantes = new ArrayList<>();
		disciplinasFaltantes.addAll(this.listarDisciplinasObrigatorias());
		disciplinasFaltantes.addAll(this.listarDisciplinasSegundoEstrato());
		disciplinasFaltantes.removeAll(this.getDisciplinasCursadas());

		return disciplinasFaltantes;
	}

	public List<Disciplina> getOpcionaisFaltantes() {
		List<Disciplina> disciplinasFaltantes = new ArrayList<>();
		disciplinasFaltantes.addAll(this.listarDisciplinasTrilha());
		disciplinasFaltantes.addAll(this.listarDisciplinasOpcionais());
		disciplinasFaltantes.removeAll(this.getDisciplinasCursadas());

		return disciplinasFaltantes;
	}

}

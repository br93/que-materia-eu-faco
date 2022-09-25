package br.com.quemateria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.repositories.AlunoRepository;
import br.com.quemateria.repositories.DisciplinaRepository;

@Service
public class HistoricoService {

	private AlunoRepository alunoRepository;
	private DisciplinaRepository disciplinaRepository;

	public HistoricoService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
	}

	private Aluno getUltimoAlunoCadastrado() {
		List<Aluno> listaAlunos = alunoRepository.findAll();
		return listaAlunos.get(listaAlunos.size() - 1);
	}
	
	private Long getIdCursoUltimoAlunoCadastrado() {
		return this.getUltimoAlunoCadastrado().getCurso().getId();
	}

	public Set<Disciplina> getDisciplinasCursadas() {
		return this.getUltimoAlunoCadastrado().getDisciplinasCursadas();
	}

	public List<Disciplina> listarDisciplinasObrigatorias() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(getIdCursoUltimoAlunoCadastrado(), 1L);
	}

	public List<Disciplina> listarDisciplinasSegundoEstrato() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(getIdCursoUltimoAlunoCadastrado(), 2L);
	}

	public List<Disciplina> listarDisciplinasTrilha() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(getIdCursoUltimoAlunoCadastrado(), 3L);
	}
	
	public List<Disciplina> listarDisciplinasOpcionais() {
		return disciplinaRepository
				.findByMatrizCurricular_Curso_IdAndMatrizCurricular_TipoDeDisciplina_IdOrderByMatrizCurricular_PeriodoAsc(getIdCursoUltimoAlunoCadastrado(), 4L);
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

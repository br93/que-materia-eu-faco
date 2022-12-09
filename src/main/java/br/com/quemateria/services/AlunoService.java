package br.com.quemateria.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Curso;
import br.com.quemateria.entities.Disciplina;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.exceptions.CustomNotFoundException;
import br.com.quemateria.repositories.AlunoRepository;
import br.com.quemateria.services.auth.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

	private final AlunoRepository alunoRepository;
	private final UsuarioService loginService;
	private final CursoService cursoService;
	private final ValidacaoService validacaoService;

	public final Logger logger = LoggerFactory.getLogger(AlunoService.class);

	private String gerarCodigo() {
		logger.info("Gerando código de aluno...");
		Random rand = new Random();
		Integer num = rand.nextInt(9000000) + 1000000;

		String codigo = "a" + num.toString();
		Boolean codigoUnico = false;

		while (!codigoUnico) {
			try {
				this.buscarAlunoPorRegistro(codigo);
			} catch (Exception ex) {
				logger.info("Código é único!");
				codigoUnico = true;
			}
		}

		return codigo;

	}

	private Aluno gerarInformacoesAluno(Usuario loginAluno) {
		logger.info("Gerando informações de aluno...");
		Curso curso = cursoService.buscarCursoPorCodigo("236");
		Aluno aluno = new Aluno(null, loginAluno.getUsuario(), this.gerarCodigo(), 1, curso, null, loginAluno);

		return aluno;
	}

	public Aluno atualizarDisciplinasCursadas(Aluno aluno, Long userId) {
		logger.info("Acessando método atualizarDisciplinasCursadas...");
		Aluno alunoOriginal = this.buscarAluno(userId);
		
		logger.info("Atualizando período do aluno...");
		alunoOriginal.setPeriodo(aluno.getPeriodo());

		logger.info("Adicionando as novas disciplinas ao cadastro do aluno...");
		Set<Disciplina> disciplinasOriginal = alunoOriginal.getDisciplinasCursadas();
		Set<Disciplina> novasDisciplinas = aluno.getDisciplinasCursadas();
		novasDisciplinas.addAll(disciplinasOriginal);

		alunoOriginal.setDisciplinasCursadas(novasDisciplinas);
		logger.info("Disciplinas atualizadas com sucesso!");

		return alunoRepository.save(alunoOriginal);

	}

	public Aluno salvarAluno(Long id) {
		logger.info("Acessando método salvarAluno...");

		Usuario loginAluno = loginService.criarCadastroAluno(id);
		Aluno aluno = gerarInformacoesAluno(loginAluno);

		logger.info("Retornando aluno " + aluno.getNome() + " criado");
		return alunoRepository.save(aluno);
	}

	public Aluno salvarAluno(Aluno aluno, Long id) {
		logger.info("Acessando método salvarAluno...");

		Usuario loginAluno = loginService.criarCadastroAluno(id);
		aluno.setLogin(loginAluno);

		logger.info("Retornando aluno " + aluno.getNome() + " criado");
		return alunoRepository.save(aluno);
	}

	public Page<Aluno> listarAlunos(Pageable pageable) {
		logger.info("Acessando método listarAlunos...");

		logger.info("Listando todos os alunos cadastrados");
		return alunoRepository.findAll(pageable);
	}

	public List<Aluno> listarAlunos() {
		logger.info("Acessando método listarAlunos...");

		logger.info("Listando todos os alunos cadastrados");
		return alunoRepository.findAll();
	}

	public Aluno buscarAluno(Long id) {
		logger.info("Acessando método buscarAluno...");

		Optional<Aluno> buscarPorId = alunoRepository.findById(id);

		if (!buscarPorId.isPresent())
			logger.info("Aluno não encontrado");

		logger.info("Aluno " + buscarPorId.get().getNome() + " encontrado");
		return buscarPorId.orElseThrow(() -> new CustomNotFoundException("Aluno não encontrado"));
	}

	public Aluno buscarAlunoPorRegistro(String registro) {
		logger.info("Acessando método buscarAlunoPorRegistro...");

		Optional<Aluno> buscarPorRegistro = alunoRepository.findByRegistro(registro);

		if (!buscarPorRegistro.isPresent())
			logger.info("Aluno não encontrado");

		logger.info("Aluno " + buscarPorRegistro.get().getNome() + " encontrado");
		return buscarPorRegistro.orElseThrow(() -> new CustomNotFoundException("Aluno não encontrado"));
	}

	public Aluno atualizarAluno(Aluno aluno, Long id) {
		logger.info("Acessando método atualizarAluno...");

		Aluno alunoOriginal = this.buscarAluno(id);
		aluno.setId(alunoOriginal.getId());

		logger.info("Retornando aluno " + aluno.getNome() + " atualizado");
		return alunoRepository.save(aluno);
	}

	public void excluirAluno(String registro) {
		logger.info("Acessando método excluirAluno...");

		Aluno aluno = this.buscarAlunoPorRegistro(registro);

		if (aluno != null)
			logger.info("Aluno " + aluno.getNome() + " deletado com sucesso");
		alunoRepository.delete(aluno);

	}

	public Aluno getUsuarioPorId(Long id) {
		logger.info("Acessando método getUsuario...");

		return this.buscarAluno(id);
	}

	public void validarParametros(String registro) {
		logger.info("Acessando método validarParametros...");

		validacaoService.validarCodigoAluno(registro);

		logger.info("Parâmetros validados!");
	}

	public void validarParametros(Integer periodo, List<String> disciplinas) {
		logger.info("Acessando método validarParametros...");

		validacaoService.validarPeriodo(periodo);
		disciplinas.forEach(x -> validacaoService.validarCodigoDisciplina(x));

		logger.info("Parâmetros validados!");
	}

	public void validarParametros(String nome, String registro, Integer periodo, String curso) {
		logger.info("Acessando método validarParametros...");

		validacaoService.validarNome(nome, "Nome de aluno");
		validacaoService.validarCodigoAluno(registro);
		validacaoService.validarPeriodo(periodo);
		validacaoService.validarCodigoCurso(curso);

		logger.info("Parâmetros validados!");
	}

}
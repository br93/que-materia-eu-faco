package br.com.quemateria.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.aluno.AlunoCadastroDisciplinasDTO;
import br.com.quemateria.dto.aluno.AlunoMapper;
import br.com.quemateria.dto.aluno.ConsultaAlunoDTO;
import br.com.quemateria.dto.disciplina.RegistroDisciplinaSimplesDTO;
import br.com.quemateria.entities.Aluno;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.AlunoService;
import br.com.quemateria.services.auth.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/alunos")
@AllArgsConstructor
public class AlunoController {

	private final AlunoService alunoService;
	private final UsuarioService usuarioService;
	private final AlunoMapper alunoMapper;

	@GetMapping
	public ResponseEntity<ConsultaAlunoDTO> buscarAluno(@RequestParam String registro) {

		alunoService.validarParametros(registro);
		return new ResponseEntity<>(alunoMapper.toDTO(alunoService.buscarAlunoPorRegistro(registro)), HttpStatus.OK);
	}

	@GetMapping("list")
	public ResponseEntity<Page<ConsultaAlunoDTO>> listarAlunos(@PageableDefault Pageable pageable,
			@RequestParam(required = false) Integer page) {
		return new ResponseEntity<>(alunoService.listarAlunos(pageable).map(alunoMapper::toDTO),
				HttpStatus.PARTIAL_CONTENT);
	}

	@PatchMapping("cadastrar-disciplina")
	public ResponseEntity<ConsultaAlunoDTO> salvarDisciplinasCursadas(Authentication auth,
			@RequestBody AlunoCadastroDisciplinasDTO dto) {

		List<String> disciplinas = dto.getDisciplinas().stream().map(RegistroDisciplinaSimplesDTO::getCodigo)
				.collect(Collectors.toList());

		alunoService.validarParametros(dto.getPeriodo(), disciplinas);
		Usuario login = (Usuario) auth.getPrincipal();

		Aluno aluno = alunoMapper.toEntity(dto);
		Aluno alunoAtualizado = alunoService.atualizarDisciplinasCursadas(aluno, login.getId());

		return new ResponseEntity<>(alunoMapper.toDTO(alunoAtualizado), HttpStatus.OK);
	}

	@DeleteMapping("delete/{registro}")
	public ResponseEntity<ConsultaAlunoDTO> deletarAluno(@PathVariable String registro, Authentication auth) {

		alunoService.validarParametros(registro);

		Usuario login = (Usuario) auth.getPrincipal();
		String permissao = login.getPerfil().getPermissao();
		Aluno aluno = alunoService.buscarAluno(login.getId());

		if (permissao.equals("ADMIN") || aluno.getRegistro().equals(registro)) {
			alunoService.excluirAluno(registro);
			usuarioService.excluirUsuario(login.getId());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

}

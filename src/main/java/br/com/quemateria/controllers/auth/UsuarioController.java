package br.com.quemateria.controllers.auth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.quemateria.dto.auth.AutenticacaoDTO;
import br.com.quemateria.dto.auth.ConsultaUsuarioDTO;
import br.com.quemateria.dto.auth.LoginMapper;
import br.com.quemateria.entities.Usuario;
import br.com.quemateria.services.AlunoService;
import br.com.quemateria.services.auth.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/usuarios")
@AllArgsConstructor
public class UsuarioController {
	
	private final LoginMapper loginMapper;
	private final UsuarioService loginService;
	private final AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario (@RequestParam Long id){
		return new ResponseEntity<ConsultaUsuarioDTO>(loginMapper.toDTO(loginService.buscarUsuario(id)), HttpStatus.OK);
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<ConsultaUsuarioDTO>> listarUsuarios(@PageableDefault Pageable pageable){
		Page<ConsultaUsuarioDTO> pageUsuarios = loginService.listarUsuarios(pageable).map(loginMapper::toDTO);
		return new ResponseEntity<Page<ConsultaUsuarioDTO>>(pageUsuarios, HttpStatus.PARTIAL_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<ConsultaUsuarioDTO> cadastrarUsuario (@RequestBody AutenticacaoDTO dto){
		Usuario login = loginService.salvarUsuario(loginMapper.toEntity(dto));
		alunoService.salvarAluno(login.getId());
		
		return new ResponseEntity<ConsultaUsuarioDTO>(loginMapper.toDTO(login), HttpStatus.CREATED);
	}
	
	@PutMapping("edit/{id}")
	public ResponseEntity<ConsultaUsuarioDTO> atualizarUsuario(@PathVariable Long id, @RequestBody AutenticacaoDTO dto){
		Usuario login = loginService.atualizarUsuario(loginMapper.toEntity(dto), id);
		return new ResponseEntity<ConsultaUsuarioDTO>(loginMapper.toDTO(login), HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("admin/{id}")
	public ResponseEntity<ConsultaUsuarioDTO> promoverUsuario(@PathVariable Long id){
		Usuario login = loginService.promoverUsuario(id);
		return new ResponseEntity<ConsultaUsuarioDTO>(loginMapper.toDTO(login), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaUsuarioDTO> deletarUsuario(@PathVariable Long id){
		loginService.excluirUsuario(id);
		return new ResponseEntity<ConsultaUsuarioDTO>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
}

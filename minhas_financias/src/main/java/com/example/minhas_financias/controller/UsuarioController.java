package com.example.minhas_financias.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minhas_financias.controller.form.AutenticarForm;
import com.example.minhas_financias.controller.form.UsuarioForm;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.ResponseStatusEnum;
import com.example.minhas_financias.response.Response;
import com.example.minhas_financias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;
	
	@PostMapping("/salvar")
	public ResponseEntity<Response<Usuario>> salvar(@RequestBody UsuarioForm usuario ) {
		Response<Usuario> response = new Response<>(); 
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.salvarUsuario(usuario));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
			
	}
	
	@PostMapping
	public ResponseEntity<Response<Usuario>> autenticacao(@RequestBody AutenticarForm form){
		Response<Usuario> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.autenticar(form));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Response<Usuario>> atualizarUsuario(@PathVariable Long id,
			@RequestBody UsuarioForm form){
		Response<Usuario> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.alterarUsuario(id, form));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Usuario>> excluirPerfil(@PathVariable Long id) {
		Response<Usuario> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			service.deletarUsuario(id);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
        
	
	}

}

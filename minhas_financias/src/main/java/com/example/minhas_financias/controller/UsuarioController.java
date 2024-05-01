package com.example.minhas_financias.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	

}

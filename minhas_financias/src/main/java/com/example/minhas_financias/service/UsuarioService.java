package com.example.minhas_financias.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.minhas_financias.controller.form.UsuarioForm;
import com.example.minhas_financias.exception.ErroAutenticationException;
import com.example.minhas_financias.exception.RegraNegocioException;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository repository;

	public Usuario autenticar(UsuarioForm form) {
		
		Optional<Usuario> usuario = repository.findByEmail(form.getEmail());
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticationException("Usuario não Encontrado");
		}
		
		if(!usuario.get().getSenha().equals(form.getSenha())) {
			throw new ErroAutenticationException("Senha inválida");
		}
		
		return usuario.get();
		
	}
	
	public Usuario salvarUsuario(UsuarioForm form) {
		validarEmail(form.getEmail());
		
		Usuario usuario = Usuario.builder()
				.nome(form.getNome())
				.email(form.getEmail())
				.Senha(form.getSenha())
				.build();
		
		return repository.save(usuario);
	}
	
	public Usuario validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Email já cadastrado");
		}
		return null;
	}
	
	
}

package com.example.minhas_financias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.minhas_financias.controller.form.AutenticarForm;
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

	public Usuario autenticar(AutenticarForm form) {
		
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
	
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Email já cadastrado");
		}
		return;
	}
	
	public List<Usuario> listaTodos() {
		return repository.findAll();
	}
	
	public Usuario alterarUsuario(Long id, UsuarioForm atualizar) {
		Optional<Usuario> procurar = repository.findById(id); 
		if(procurar.isPresent()) {
			Usuario usuario = procurar.get();
			usuario.setNome(atualizar.getNome());
			usuario.setEmail(atualizar.getEmail());
			usuario.setSenha(atualizar.getSenha());
			return repository.save(usuario);
		} 
		else {
            throw new RegraNegocioException("Usuario não encontrado");

		}
	}
	
	public void deletarUsuario(Long id) {
		repository.deleteById(id);
	}


	
	
}

package com.example.minhas_financias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder encoder;

	
	public Usuario salvarUsuario(UsuarioForm form) {
		validarEmail(form.getEmail());
        String senhaCriptografada = encoder.encode(form.getSenha());

		
		Usuario usuario = Usuario.builder()
				.login(form.getNome())
				.email(form.getEmail())
				.senha(senhaCriptografada)
				.role(form.getRole())
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
	
	public Usuario alterarUsuario(Integer id, UsuarioForm atualizar) {
		Optional<Usuario> procurar = repository.findById(id); 
		if(procurar.isPresent()) {
			Usuario usuario = procurar.get();
			usuario.setLogin(atualizar.getNome());
			usuario.setEmail(atualizar.getEmail());
			usuario.setSenha(atualizar.getSenha());
			return repository.save(usuario);
		} 
		else {
            throw new RegraNegocioException("Usuario não encontrado");

		}
	}
	
	public void deletarUsuario(Integer id) {
		repository.deleteById(id);
	}
	
	public Optional<Usuario > obterPorId(Integer id) {
		return repository.findById(id);
	}


	
	
}

package com.example.minhas_financias.service;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.minhas_financias.controller.form.AutenticarForm;
import com.example.minhas_financias.controller.form.UsuarioForm;
import com.example.minhas_financias.exception.ErroAutenticationException;
import com.example.minhas_financias.exception.RegraNegocioException;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.repository.UsuarioRepository;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;//Classe responsável para fazer operações na base de dados
	
	
	
	@Test
	public void deveValidarEmail() {
		 Mockito.when(repository.existsByEmail("email@email.com")).thenReturn(false);      
	        Assertions.assertDoesNotThrow(() -> {
	            service.validarEmail("email@email.com");
	        });
	}
	
	@Test
	public void deveLancaErroAoValidarEmailQuandoExistirEmailCadastrado() {
		  	Usuario usuario = criarUsuario();
	        Mockito.when(repository.existsByEmail(usuario.getEmail())).thenReturn(true);
	        
	        Assertions.assertThrows(RegraNegocioException.class, () -> {
	            service.validarEmail(usuario.getEmail());
	        });
	}
	
	@Test
	public void deveAutenticarUsuariocomSucesso() {
		//Cenário
		String email = "email@email.com";
		String senha = "admin";
		
		Usuario usuario = Usuario.builder()
				.email(email)
				.Senha(senha)
				.id(1L)
				.build();
		
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//Ação
		AutenticarForm form = new AutenticarForm(email,senha);
		Usuario result = service.autenticar(form);
		
		//verificação
		Assertions.assertNotNull(result);
		
	}
	
	@Test
	public void deveLancaErrorParaEmailUsuarioNaoAutenticado() {
		String email = "email@email.com";
		String senha = "admin";
		
		Usuario usuario = Usuario.builder()
				.email(email)
				.Senha(senha)
				.id(1L)
				.build();
		
		//Cénario
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//Ação
		
			//Verificando se a menssagem de erro esta indo corretamente
		ErroAutenticationException exception = Assertions.assertThrows(ErroAutenticationException.class,() ->{
			AutenticarForm form = new AutenticarForm(usuario.getEmail(),usuario.getSenha());
			service.autenticar(form);
		});
		
		Assertions.assertEquals("Usuario não Encontrado", exception.getMessage());
			
		/*Assertions.assertThrows(ErroAutenticationException.class, () -> {
			AutenticarForm form = new AutenticarForm(usuario.getEmail(),usuario.getSenha());
			service.autenticar(form);
        });*/
		
		
	}
		
	@Test
	public void deveLancaErrorQuandoSenhaNaoBater() {
		String email = "email@email.com";
		String senha = "admin";
		
		Usuario usuario = Usuario.builder()
				.email(email)
				.Senha(senha)
				.id(1L)
				.build();
		
		//Cénario
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		//Ação
		
			//Verificando se a menssagem de erro esta indo corretamente
			ErroAutenticationException exception = Assertions.assertThrows(ErroAutenticationException.class,() ->{
				AutenticarForm form = new AutenticarForm(usuario.getEmail(),"senha");
				service.autenticar(form);
			});
			
			Assertions.assertEquals("Senha inválida", exception.getMessage());

		
		/*Assertions.assertThrows(ErroAutenticationException.class, () -> {
			AutenticarForm form = new AutenticarForm(usuario.getEmail(),"senha");
			service.autenticar(form);
        });*/
	}
	
	@Test
	public void deveSalvaUsuario() {
		//Cénario
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());//Não vai fazer nada(Não lança erro)
		Usuario usuario = criarUsuario();
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		//Ação
		UsuarioForm form = new UsuarioForm(
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getSenha());
		
		Usuario salvar = service.salvarUsuario(form);
		
		//Verificação
		Assertions.assertNotNull(salvar.getNome());
		Assertions.assertNotNull(salvar.getEmail());
		Assertions.assertNotNull(salvar.getSenha());

	}
	
	public void naoDeveCadastrarUsuarioComEmailCadastrado() {
		//Cenario
		Usuario usuario = criarUsuario();
		String email = "email@email.com";
		
		Mockito.doThrow(RegraNegocioException.class)
		.when(service)
		.validarEmail(email);
		
		//Ação
		UsuarioForm form = new UsuarioForm(
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getSenha());
		
		service.salvarUsuario(form);

		//Verificação
		Mockito.verify(repository,Mockito.never()).save(usuario);
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("nome")
				.email("email@email.com")
				.Senha("admin")
				.build();
	}
}

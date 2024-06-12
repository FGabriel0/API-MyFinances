package com.example.minhas_financias.model.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.minhas_financias.model.entity.Usuario;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;//Classe responsável fazer operações na base de dados
	
	@Test
	public void deveVerificarAExistenciaDeumEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		
		entityManager.persist(usuario);
		
		//Ação
		boolean result = repository.existsByEmail("usuario@gmail.com");
		
		//Verificação
		Assertions.assertTrue(result);
	}
	
	@Test
	public void deveRetornaFalsoQuandoNaoHouverUsuarioCadastrado() {
		//Ação
		boolean result = repository.existsByEmail("joao@gmail.com");
		
		//Verificação
		Assertions.assertFalse(result);
	}
	
	@Test
	public void devePersistirUmUsuarionaBasedeDados(){
		//Cenário
		Usuario usuario =criarUsuario();
		
		//Ação
		Usuario usuarioSalvo = repository.save(usuario);
		
		//Verificação
		Assertions.assertNotNull(usuarioSalvo.getId());
	}
	
	@Test
	public void deveProcurarUsuarioPorEmail() {
		
		 UserDetails userDetails = User.builder()
	                .username("usuario@gmail.com")
	                .password("senha")
	                .roles("USER")
	                .build();
		
		// Ação
	        entityManager.persist(userDetails);

	        // Verificação
	     UserDetails result = repository.findByLogin("usuario@gmail.com");

	        // Asserção
	        Assertions.assertNotNull(result);
	        Assertions.assertEquals("usuario@gmail.com", result.getUsername());
	    
	}
	
	@Test
	public void deveRetornaVazioAoBuscarUsuarioPorEmailQuandoNaoExiste() {
					
		//Verificação
		UserDetails result = repository.findByLogin("usuario@gmail.com");		
		
        Assertions.assertNotNull(result);
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.login("usuario")
				.email("usuario@gmail.com")
				.senha("admin")
				.build();
	}
	
}

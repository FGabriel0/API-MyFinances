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
		//Cénario
		Usuario usuario = criarUsuario();
		
		//Ação
		entityManager.persist(usuario);
		
		 //Verificação
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");
		
		Assertions.assertTrue(result.isPresent());
		
		
	}
	@Test
	public void deveRetornaVazioAoBuscarUsuarioPorEmailQuandoNaoExiste() {
					
		//Verificação
		Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");		
		
		Assertions.assertFalse(result.isPresent());
	}
	
	
	
	
	
	
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("usuario")
				.email("usuario@gmail.com")
				.Senha("admin")
				.build();
	}
	
}

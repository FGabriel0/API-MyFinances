package com.example.minhas_financias.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.minhas_financias.exception.RegraNegocioException;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.repository.UsuarioRepository;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@InjectMocks
	UsuarioService service;
	
	@Mock
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;//Classe responsável para fazer operações na base de dados
	
	
	@BeforeEach
	public void setUp() {
        MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void deveValidarEmail() {
			when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);		
		Assertions.assertDoesNotThrow(() -> {
			service.validarEmail("email@email.com");
        });
	}
	
	
	@Test
	public void deveLancaErroAoValidarEmailQuandoExistirEmailCadastrado() {
		  	Usuario usuario = criarUsuario();
	        when(repository.existsByEmail(usuario.getEmail())).thenReturn(true);
	        
	        Assertions.assertThrows(RegraNegocioException.class, () -> {
	            service.validarEmail(usuario.getEmail());
	        });
	}
	
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nome("gabriel")
				.email("email@email.com")
				.Senha("admin")
				.build();
	}

}

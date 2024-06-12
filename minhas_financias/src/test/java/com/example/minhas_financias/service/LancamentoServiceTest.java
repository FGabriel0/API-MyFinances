package com.example.minhas_financias.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import com.example.minhas_financias.controller.dto.InforLancamento;
import com.example.minhas_financias.controller.form.LancamentoForm;
import com.example.minhas_financias.model.entity.Lancamento;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.StatusLancamento;
import com.example.minhas_financias.model.enuns.TipoLancamento;
import com.example.minhas_financias.model.repository.LancamentoRepository;
import com.example.minhas_financias.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class LancamentoServiceTest {

	@MockBean
	LancamentoRepository repository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@SpyBean	
	LancamentoService service;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
    public void deveSalvaLancamento() {
		  LancamentoForm form = criarLancamentoForm();
		  Mockito.when(repository.save(Mockito.any(Lancamento.class))).thenReturn(null);

		    // Act
		    InforLancamento salvar = service.salvar(form);

		    // Assert
		    Assertions.assertNotNull(salvar);
    }

	

	public static LancamentoForm criarLancamentoForm() {
	    return LancamentoForm.builder()
	    		.descricao("casa")
	    		.ano(2024)
	    		.mes(11)
	    		.usuario_id(1)
	    		.valor(BigDecimal.TEN)
	    		.tipoLancamento(TipoLancamento.DESPENSA)
	    		.status(StatusLancamento.PENDENTE)
	    		.build();
	      
	}

}

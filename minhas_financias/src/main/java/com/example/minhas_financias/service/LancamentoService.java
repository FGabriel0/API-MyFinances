package com.example.minhas_financias.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.minhas_financias.controller.dto.InforLancamento;
import com.example.minhas_financias.controller.form.LancamentoForm;
import com.example.minhas_financias.controller.form.buscarPorFiltro;
import com.example.minhas_financias.exception.RegraNegocioException;
import com.example.minhas_financias.model.entity.Lancamento;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.StatusLancamento;
import com.example.minhas_financias.model.repository.LancamentoRepository;
import com.example.minhas_financias.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LancamentoService {
	
	private final LancamentoRepository repository;
	private final UsuarioRepository usuarioRepository;

	 public InforLancamento salvar(LancamentoForm form) {
	        Usuario usuario = usuarioRepository.findById(form.usuario_id)
	                .orElseThrow(() -> new RegraNegocioException("Usuario inválido"));
	        
	        Lancamento lancamento = Lancamento.builder()
	                .descricao(form.getDescricao())
	                .ano(form.getAno())
	                .mes(form.getMes())
	                .usuario(usuario)
	                .valor(form.getValor())
	                .tipo(form.getTipoLancamento())
	                .status(StatusLancamento.PENDENTE)
	                .datacadastro(LocalDate.now())
	                .build();    

	        Lancamento lancamentoSalvo = repository.save(lancamento);
	        return converter(lancamentoSalvo);
	    }
	
	public InforLancamento atualizar(Long id,LancamentoForm form) {
		Objects.requireNonNull(id);
		validarLancamento(form);
		Optional<Lancamento> procurar = repository.findById(id);
		if(procurar.isPresent()) {
			Lancamento lancamento = procurar.get();
			lancamento.setDescricao(form.getDescricao());
			lancamento.setMes(form.getMes());
			lancamento.setAno(form.getAno());
			lancamento.setValor(form.getValor());
			lancamento.setTipo(form.getTipoLancamento());
			lancamento.setStatus(form.getStatus());
			
			Lancamento converter = repository.save(lancamento);
			return converter(converter);
		}
		else {
			throw new RegraNegocioException("Lançamento não encontrado");
		}
	
	}
	
	public void deletarLancamento(Long id) {
		Objects.requireNonNull(id);
		repository.deleteById(id);
	}
	
    @Transactional(readOnly = true)
	public List<InforLancamento> buscar(buscarPorFiltro form){
		
		Lancamento lancamento = Lancamento.builder()
				.descricao(form.getDescricao())
				.ano(form.getAno())
				.mes(form.getMes())
				.build();		
		
		Example example = Example.of(lancamento, ExampleMatcher
				.matching()
				.withIgnoreCase()//Não importa como a String vai vim se caixa alto ou não mas ainda vai buscar
				.withStringMatcher(StringMatcher.CONTAINING));//Forma de como ele vai vai buscar 
		
	    List<Lancamento> lancamentos = repository.findAll(example);
		 return converter(lancamentos);
	}
	
	public List<Lancamento> buscarPorUsuario(Long idUsuario){
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
		return repository.findByUsuario(usuario);
	}
	
	
	public void atualizarStatus(Long id,
			StatusLancamento statusLancamento ) {
		
		repository.findById(id).map(p -> {
			p.setStatus(statusLancamento);
			return repository.save(p);
		}).orElseThrow(() -> new RegraNegocioException("Lançamento inválido"));
	}
	
	private void validarLancamento(LancamentoForm form) {
		if(form.getDescricao() == null || form.descricao.trim().equals("")) {
			throw new RegraNegocioException("Digite uma Descrição válida");
		}
		
		if(form.getAno() == null || form.getAno() < 0 || form.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Digite um Ano válido");
		}
		
		if(form.getMes() == null || form.getMes() < 0 || form.getMes() > 12) {
			throw new RegraNegocioException("Digite um Més válido");
		}
		
		if(form.valor == null || form.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Digite um valor válido");
		}
		if(form.getTipoLancamento() == null) {
			throw new RegraNegocioException("Digite tipo de Lançamento");
		}
	}
	
	private InforLancamento converter(Lancamento lancamento) {
        String nomeUsuario = lancamento.getUsuario().getNome(); 
        return InforLancamento.builder()
                .id(lancamento.getId())
                .descricao(lancamento.getDescricao())
                .ano(lancamento.getAno())
                .mes(lancamento.getMes())
                .id_usuario(nomeUsuario)
                .valor(lancamento.getValor())
                .tipoLancamento(lancamento.getTipo())
                .status(lancamento.getStatus())
                .build();
    }
	
	
	private List<InforLancamento> converter(List<Lancamento> lancamentos) {
	    List<InforLancamento> infoLancamentos = new ArrayList<>();

	    for (Lancamento lancamento : lancamentos) {
	        String nomeUsuario = lancamento.getUsuario().getNome(); 
	        InforLancamento infoLancamento = InforLancamento.builder()
	        		.id(lancamento.getId())
	        		.id_usuario(nomeUsuario)
	                .descricao(lancamento.getDescricao())
	                .ano(lancamento.getAno())
	                .mes(lancamento.getMes())
	                .valor(lancamento.getValor())
	                .tipoLancamento(lancamento.getTipo())
	                .status(lancamento.getStatus())
	                .build();
	        infoLancamentos.add(infoLancamento);
	    }
	    return infoLancamentos;
	}
}

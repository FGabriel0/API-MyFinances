package com.example.minhas_financias.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minhas_financias.controller.dto.InforLancamento;
import com.example.minhas_financias.controller.form.LancamentoForm;
import com.example.minhas_financias.controller.form.updateStatusForm;
import com.example.minhas_financias.model.entity.Lancamento;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.ResponseStatusEnum;
import com.example.minhas_financias.model.enuns.StatusLancamento;
import com.example.minhas_financias.response.Response;
import com.example.minhas_financias.service.LancamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lancamento")
@RequiredArgsConstructor
public class LancamentoController {
	
	private final LancamentoService service;
	
	@GetMapping
	public ResponseEntity<Response<List<Lancamento>>> buscar(@RequestBody LancamentoForm form){
		Response<List<Lancamento>> response = new Response<>();
		try {
			
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.buscar(form));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Response<List<Lancamento>>> buscarPorUsuario(@PathVariable Long idUsuario){
		Response<List<Lancamento>> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.buscarPorUsuario(idUsuario));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Response<InforLancamento>> salvar(@RequestBody LancamentoForm form){		
		Response<InforLancamento> response = new Response<>();
        InforLancamento inforLancamentoSalvo = service.salvar(form);
		try {        
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(inforLancamentoSalvo);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<InforLancamento>> atualizar(@PathVariable Long id,
			@RequestBody LancamentoForm form){
		
		Response<InforLancamento> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.atualizar(id, form));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Response<Lancamento>> atualizarStatus(@PathVariable Long id,
			@RequestBody updateStatusForm form){
		Response<Lancamento> response= new Response<>();
		String novoStatus = form.getNovostatus();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			service.atualizarStatus(id, StatusLancamento.valueOf(novoStatus));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Lancamento>> deletarLancamento(@PathVariable Long id){
		Response<Lancamento> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			service.deletarLancamento(id);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	
}

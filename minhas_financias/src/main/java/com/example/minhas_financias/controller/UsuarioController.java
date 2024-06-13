package com.example.minhas_financias.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minhas_financias.controller.dto.inforSaldoDTO;
import com.example.minhas_financias.controller.dto.loginResponseDTO;
import com.example.minhas_financias.controller.form.AutenticarForm;
import com.example.minhas_financias.controller.form.UsuarioForm;
import com.example.minhas_financias.exception.RegraNegocioException;
import com.example.minhas_financias.infra.security.tokenService;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.ResponseStatusEnum;
import com.example.minhas_financias.response.Response;
import com.example.minhas_financias.service.LancamentoService;
import com.example.minhas_financias.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final AuthenticationManager authenticationManager;
	private final UsuarioService service;
	private final tokenService tokenservice;
	private final LancamentoService lancamentoService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Response<Usuario>> salvar(@RequestBody UsuarioForm usuario ) {
		Response<Usuario> response = new Response<>(); 
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.salvarUsuario(usuario));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
			
	}
	
	@PostMapping("/login")
	public ResponseEntity login (@RequestBody AutenticarForm form) {
	try {
		 var userNamePassword = new UsernamePasswordAuthenticationToken(form.getLogin(), form.getSenha());            
         var authentication = authenticationManager.authenticate(userNamePassword);
         
         String token = tokenservice.generateToken((Usuario)authentication.getPrincipal());
         
         loginResponseDTO response = new loginResponseDTO(token);

         return ResponseEntity.ok(response);

	} catch (Exception e) {
		 throw new RegraNegocioException("Erro no login" + e);
	}
		 
}

	
	@PatchMapping("/{id}")
	public ResponseEntity<Response<Usuario>> atualizarUsuario(@PathVariable Integer id,
			@RequestBody UsuarioForm form){
		Response<Usuario> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(service.alterarUsuario(id, form));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Usuario>> excluirPerfil(@PathVariable Integer id) {
		Response<Usuario> response = new Response<>();
		try {
			response.setStatus(ResponseStatusEnum.SUCCESS);
			service.deletarUsuario(id);
	        return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
        
	
	}
	
	@GetMapping("/{id}/saldo")
	public ResponseEntity<Response<inforSaldoDTO>> obterSaldo(@PathVariable("id") Integer id){
		Response<inforSaldoDTO> response = new Response<>();
		try {
			Optional<Usuario> existe = service.obterPorId(id);
			
			if(!existe.isPresent()) {
				 response.setStatus(ResponseStatusEnum.ERROR);
				 return ResponseEntity.ok(response);
			}
			
			BigDecimal saldo = lancamentoService.obterSaldoUsuario(id);
			inforSaldoDTO dto = new inforSaldoDTO();
			dto.setSaldo(saldo);
			
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setData(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseStatusEnum.ERROR);
			response.setMessage("Ocorreu um erro inesperado. Entre em contato com o administrador do sistema.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}

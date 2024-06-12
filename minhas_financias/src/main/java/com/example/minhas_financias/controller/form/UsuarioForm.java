package com.example.minhas_financias.controller.form;

import com.example.minhas_financias.model.enuns.UseRoles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioForm {
	
	private String nome;
	private String email;
	private String senha;
	private UseRoles role;
}

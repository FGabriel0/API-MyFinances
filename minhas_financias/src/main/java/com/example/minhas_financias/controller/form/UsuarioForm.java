package com.example.minhas_financias.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
	
	private String nome;
	private String email;
	private String senha;
}

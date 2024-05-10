package com.example.minhas_financias.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class buscarPorFiltro {

	private String descricao;
	private Integer ano;
	private Integer mes;
}

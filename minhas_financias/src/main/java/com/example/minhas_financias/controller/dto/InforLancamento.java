package com.example.minhas_financias.controller.dto;

import java.math.BigDecimal;

import com.example.minhas_financias.model.enuns.StatusLancamento;
import com.example.minhas_financias.model.enuns.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InforLancamento {
	private Long id;
	private String descricao;
	private Integer ano;
	private Integer mes;
	private String id_usuario;
	private BigDecimal valor;
	public TipoLancamento tipoLancamento;
	public StatusLancamento status;
}

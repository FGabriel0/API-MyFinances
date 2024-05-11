package com.example.minhas_financias.controller.form;

import java.math.BigDecimal;

import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.StatusLancamento;
import com.example.minhas_financias.model.enuns.TipoLancamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoForm {

	public String descricao;
	public Integer ano;
	public Integer mes;
	public Long usuario_id;
	public BigDecimal valor;
	public TipoLancamento tipoLancamento;
	public StatusLancamento status;
	

}

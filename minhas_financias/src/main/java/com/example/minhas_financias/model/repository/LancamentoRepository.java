package com.example.minhas_financias.model.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.minhas_financias.model.entity.Lancamento;
import com.example.minhas_financias.model.entity.Usuario;
import com.example.minhas_financias.model.enuns.TipoLancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {

    List<Lancamento> findByUsuario(Usuario usuario);
    
    @Query(value = "SELECT SUM(l.valor) FROM Lancamento l JOIN l.usuario u " +
            "WHERE u.id = :idUsuario AND l.tipo = :tipo")
    BigDecimal obterSaldoLancamentoUsuario(@Param("idUsuario") Long idUsuario,@Param("tipo") TipoLancamento tipo);

}

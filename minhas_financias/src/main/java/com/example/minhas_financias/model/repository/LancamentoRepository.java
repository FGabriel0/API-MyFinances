package com.example.minhas_financias.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minhas_financias.model.entity.Lancamento;
import com.example.minhas_financias.model.entity.Usuario;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {

    List<Lancamento> findByUsuario(Usuario usuario);

}

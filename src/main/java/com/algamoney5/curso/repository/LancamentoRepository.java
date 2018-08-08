package com.algamoney5.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney5.curso.model.Lancamento;
import com.algamoney5.curso.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}

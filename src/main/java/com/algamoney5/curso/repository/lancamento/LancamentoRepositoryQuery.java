package com.algamoney5.curso.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algamoney5.curso.repository.filter.LancamentoFilter;
import com.algamoney5.curso.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	 
}

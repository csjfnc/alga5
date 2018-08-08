package com.algamoney5.curso.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algamoney5.curso.model.Lancamento;
import com.algamoney5.curso.repository.LancamentoRepository;
import com.algamoney5.curso.repository.filter.LancamentoFilter;
import com.algamoney5.curso.repository.projection.ResumoLancamento;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public List<Lancamento> listarService(){
		return lancamentoRepository.findAll();
	}
	
	public Page<ResumoLancamento> resumirService(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}
	
	public Lancamento criarService(Lancamento lancamento){
		return lancamentoRepository.save(lancamento);
	}
	
	
	public Lancamento atualizarService(Long codigo, Lancamento lancamento){
		Lancamento lancamentoBD = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoBD, "codigo");
		return lancamentoRepository.save(lancamentoBD);
	}

	public Lancamento buscarPeloCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo).get();
		if(lancamento == null){
			throw new EmptyResultDataAccessException(1);
		}
		return lancamento;
	}
	
	public void removerService(Long codigo){
		lancamentoRepository.deleteById(codigo);
	}
	 
}

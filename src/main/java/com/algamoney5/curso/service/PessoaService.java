package com.algamoney5.curso.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney5.curso.model.Pessoa;
import com.algamoney5.curso.repository.PessoaRespository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRespository pessoaRespository;
	
	
	public List<Pessoa> litarService(){
		return pessoaRespository.findAll();
	}
	
	
	public Pessoa criarService(Pessoa pessoa){
		return pessoaRespository.save(pessoa);		
	}
	
	
	public Pessoa atualizarService(Long codigo, Pessoa pessoa){
		Pessoa pessoaBd = buscarpeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaBd, "codigo");		
		return pessoaRespository.save(pessoaBd);
	}


	private Pessoa buscarpeloCodigo(Long codigo) {
		Pessoa pessoa = pessoaRespository.findById(codigo).get();
		if(pessoa == null){
			throw new EmptyResultDataAccessException(1);
		}
		return pessoa;
	}
	

	public void remover(Long codigo){
		pessoaRespository.deleteById(codigo);
	}
}

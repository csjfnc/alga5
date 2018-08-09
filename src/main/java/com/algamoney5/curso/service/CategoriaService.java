package com.algamoney5.curso.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney5.curso.model.Categoria;
import com.algamoney5.curso.repository.CategoriaRepository;

@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//ATUALIZAR CATEGORIA RECEBIDA
	public Categoria atualizar(Long codigo, Categoria categoria){
		Categoria categoriaBD = buscarPeloCodigo(codigo);		
		BeanUtils.copyProperties(categoria, categoriaBD, "codigo");
		return categoriaRepository.save(categoriaBD);		
	}
	
	//BUSCA CATEGORIA PELO CODIGO
	public Categoria buscarPeloCodigo(Long codigo){
		Categoria categoria = categoriaRepository.getOne(codigo);
		if(categoria == null){
			throw new EmptyResultDataAccessException(1);
		}		
		return categoria;
	}
}

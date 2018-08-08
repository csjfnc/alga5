package com.algamoney5.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney5.curso.model.Pessoa;

public interface PessoaRespository extends JpaRepository<Pessoa, Long> {

}

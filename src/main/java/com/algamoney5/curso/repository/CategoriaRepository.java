package com.algamoney5.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney5.curso.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

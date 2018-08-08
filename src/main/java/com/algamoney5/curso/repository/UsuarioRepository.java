package com.algamoney5.curso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney5.curso.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
}

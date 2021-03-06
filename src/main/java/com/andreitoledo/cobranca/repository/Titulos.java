package com.andreitoledo.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andreitoledo.cobranca.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long>{
	
	public List<Titulo> findByDescricaoLike(String descricao);

}

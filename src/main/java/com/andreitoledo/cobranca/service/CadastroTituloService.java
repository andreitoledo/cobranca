package com.andreitoledo.cobranca.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.andreitoledo.cobranca.model.StatusTitulo;
import com.andreitoledo.cobranca.model.Titulo;
import com.andreitoledo.cobranca.repository.Titulos;
import com.andreitoledo.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {
	
	@Autowired
	public Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido.");			
		}
		
	}

	public void excluir(Long codigo) {
		titulos.deleteById(codigo);
	}

//	botão receber em ajax
	@SuppressWarnings("deprecation")
	public String receber(Long codigo) {
		Titulo titulo = titulos.getOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}

//	filtro para pesquisar titulos
	
	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoLike(descricao);
	}
	

}

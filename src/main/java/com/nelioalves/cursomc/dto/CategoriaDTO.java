package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Categoria;
/*
 * objeto de transferencia de dados 
 * usada para exibir todos os dados que você precisa para facilitar e simplificar algumas operaçoes
 */
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID =1L;
	
	private Integer id;
	private  String nome;
	
	// construtor vazio
	public CategoriaDTO() {
		
	}
	
	// instanciando um objeto do tipo categoria
	public CategoriaDTO(Categoria obj) {
		id= obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

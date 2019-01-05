package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Produto;
/*
 * objeto de transferencia de dados 
 * usada para exibir todos os dados que você precisa para facilitar e simplificar algumas operaçoes
 * Otimizar o tráfego (trafegando menos dados
 * O DTO tem essencialmente esse papel de "view" que você mencionou. Isso é importante para você controlar quais dados (inclusive dados relacionados de outras entidades) deverão trafegar entre as requisições. 
 */
public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID =1L;
	
	private Integer id;

	private String nome;
	private Double Preco;

	
	// construtor vazio
	public ProdutoDTO() {
		
	}
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		Preco= obj.getPreco();
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


	public Double getPreço() {
		return Preco;
	}


	public void setPreço(Double preco) {
		Preco = preco;
	}
	
	
	
}

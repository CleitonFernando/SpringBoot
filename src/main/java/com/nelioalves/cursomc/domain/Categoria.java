package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

// CAMADA DE DOMINIO OU DOMAIN Model
@Entity
public class Categoria  implements Serializable{

	
	private static final long serialVersionUID = 1L; //versao da classe serializer que éa numero um
	// e um interface onde o objetos pode convertido e byte para que objetos pode gravado em arquivos e trafegado em rede
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//DEFINIDO A ESTRATEGIA DE GERAÇÃO AUTOMATICA DE ID 
	private Integer id;
	
	 /// tamanho maximo
	private  String nome;
	
	@ManyToMany(mappedBy = "categorias")// mapeamento  da categoria ja feito do outra lado na classe produto em cima de categorias que a lista
	private List<Produto> produto = new ArrayList<>(); // muitos para muitos dos dois lados
	
	
	public Categoria() {
		
	}
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}

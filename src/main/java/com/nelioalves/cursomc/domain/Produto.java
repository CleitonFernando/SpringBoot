package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity //indica que uma entidade jpa
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L; //versao da classe serializer que éa numero um
	// e um interface onde o objetos pode convertido e byte para que objetos pode gravado em arquivos e trafegado em rede
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//DEFINIDO A ESTRATEGIA DE GERAÇÃO AUTOMATICA DE ID 
	private Integer id;
	private String nome;
	private Double Preço;
	/*
	 * um relacionanmento de muitos para muitos uma categoria pode ter varios produto
	 * um produto pode ter varias categorias então cria-se uma tela nova  com id das duas tabelas
	 */
	@JsonBackReference // ele faz do outra lado da associação foi buscando objetos eu nao busco mais  ele ira omitar uma lista de categoria a cada produto evitar referencia ciclica
	@ManyToMany // notacao para criar-se a tabela entre categoria e produto (muitos para muitos)
	@JoinTable(// essa tabela faz muitas para muitos
			name = "PRODUTO_CATEGORIA", // nome da tabela
			joinColumns = @JoinColumn(name="produto_id"),// id do produto chave estrangeira foreign key
			inverseJoinColumns = @JoinColumn(name = "categoria_id")// outra chave estrangeira foreign key
			)
	
	private List<Categoria> categorias = new ArrayList<>();
	
	@OneToMany(mappedBy="id.produto")
	private Set<ItemPedido>itens = new HashSet<>();
	
	public Produto() {
		
	}

	public Produto(Integer id, String nome, Double preço) {
		this.id = id;
		this.nome = nome;
		Preço = preço;
	}
	public List<Pedido> getpedidos(){
		List<Pedido>lista = new ArrayList<>();
		for(ItemPedido x :itens) {
			lista.add(x.getPedido());
		}
		return lista;
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
		return Preço;
	}

	public void setPreço(Double preço) {
		Preço = preço;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}

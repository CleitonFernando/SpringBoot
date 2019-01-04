package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;
/*
 * objeto de transferencia de dados 
 * usada para exibir todos os dados que você precisa para facilitar e simplificar algumas operaçoes
 * Otimizar o tráfego (trafegando menos dados
 * O DTO tem essencialmente esse papel de "view" que você mencionou. Isso é importante para você controlar quais dados (inclusive dados relacionados de outras entidades) deverão trafegar entre as requisições. 
 */
@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID =1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=4, max=120, message="O tamanho deve ser entre 5 e 80 caracteres")
	private  String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email invalido")
	private String email;
	
	// construtor vazio
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome =obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}

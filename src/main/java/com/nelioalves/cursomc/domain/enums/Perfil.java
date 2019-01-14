package com.nelioalves.cursomc.domain.enums;

public enum Perfil {
	
	ADMIN(1,"ROLE_ADMIN"), ///PREFIXO É EXIGENCIA DO SRING SEGURITY
	CLIENTE(2,"ROLE_CLIENTE");

	
	private int cod;
	private String descricao;
	
	// construtor do tipo enum é sempre privado
	private Perfil(int cod,String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	// retorna um objeto atraves do codigo 
	public static Perfil ToEnum(Integer cod) {
		
		// se o codigo for nullo retorna null
		if(cod==null) {
			return null;
		}
		/// percorrendo o enumeradores do tipo cliente para achar através do codigo.
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido:"+cod); // lanca um exceção do tipo illegalargumentException
	}

	
}

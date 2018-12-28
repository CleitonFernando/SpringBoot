package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {
	
	Pendente(1,"Pendente"),
	QUITADO(2,"Quitado"),
	CANCELADO(3,"Cancelado");
	
	private int cod;
	private String descricao;
	
	// construtor do tipo enum é sempre privado
	private EstadoPagamento(int cod,String descricao) {
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
	public static EstadoPagamento ToEnum(Integer cod) {
		
		// se o codigo for nullo retorna null
		if(cod==null) {
			return null;
		}
		/// percorrendo o enumeradores do tipo cliente para achar através do codigo.
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido:"+cod); // lanca um exceção do tipo illegalargumentException
	}

	
}

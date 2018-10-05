package com.nelioalves.cursomc.services.exceptions;
/*
 * objeto nao encontrado
 */
public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	//construtor que recebe um string na exceção
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	//construtor 2 que recebe a mensagem mais outra exceção que aconteceu antes
	public ObjectNotFoundException(String msg,Throwable cause) {
		super(msg,cause);
	}
	
	
}

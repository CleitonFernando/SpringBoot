package com.nelioalves.cursomc.resources.exception;

import java.io.Serializable;
/*
 * Erro padrao
 */
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status; // status http do erro
	private String msg;//  a mensagem de erro
	private Long TimeStamp; //o instante que aconteceu o erro o milisegundo
	
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		TimeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		TimeStamp = timeStamp;
	}
	
}

package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;
import com.nelioalves.cursomc.resources.exception.StandardError;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 * esta classe intercepta as exceções
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	/*
	 * este metodo vai receber a exceção e as informações da requisão
	 */
	@ExceptionHandler(ObjectNotFoundException.class)// essa noação indica que esse metodo é tratador de exceção desse tipo de exceção objectNotFoundException
	public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis()); // instanciar um erro padrao passando erro de objeto nao encontrando que not found messagem da exceção, tempo ou milissegundos da exceção
		
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); // esse metodo ira retorna um responseEntity com o metodo status http juntamento com objecto instanciado err  
	}

}

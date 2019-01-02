package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.ClienteService;

// CAMADA CONTROLADORES REST costuma-se ter metodos pequenos

@RestController // QUE RECEBE A NOTAÇÃO REST CONTROLLER
@RequestMapping(value="/clientes") //E NOTACAO REQUEST MAPPING AO INICIALIZAR AO SPRING BOOT ACESSAR NA UM LOCALHOST:8080/CATEGORIAS
public class ClienteResources {
	
	/*
	 * ESTE METODO RECEBE UM ID DA BARRA URL OU SEJA localhost:8080/categorias/1
	 * que recebe a notação pathvariable
	 */

	@Autowired // notacao para a classe instanciar sozinha
	private ClienteService service;  // acessando o serviço
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { 	// esse reponse é um tipo especial do spring boot que ele ja encapsula e armazena varias informações de uma respostas http para um serviço rest <?> pode ser varias tipos pode encontrar ou não encontrar
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj); //retorna uma respostas que ocorreu com sucesso e juntamente com obj
	}
	
	
}

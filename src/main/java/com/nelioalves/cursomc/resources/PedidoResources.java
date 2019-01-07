package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;

// CAMADA CONTROLADORES REST costuma-se ter metodos pequenos

@RestController // QUE RECEBE A NOTAÇÃO REST CONTROLLER
@RequestMapping(value="/pedidos") //E NOTACAO REQUEST MAPPING AO INICIALIZAR AO SPRING BOOT ACESSAR NA UM LOCALHOST:8080/PedidoS
public class PedidoResources {
	
	/*
	 * ESTE METODO RECEBE UM ID DA BARRA URL OU SEJA localhost:8080/Pedidos/1
	 * que recebe a notação pathvariable
	 */

	@Autowired // notacao para a classe instanciar sozinha
	private PedidoService service;  // acessando o serviço
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) { 	// esse reponse é um tipo especial do spring boot que ele ja encapsula e armazena varias informações de uma respostas http para um serviço rest <?> pode ser varias tipos pode encontrar ou não encontrar
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj); //retorna uma respostas que ocorreu com sucesso e juntamente com obj
	}
	
	@RequestMapping(method=RequestMethod.POST) /// declarando o metodo post
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){ // essa anotação faz o json ser convertido em java automaticamente
	
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); // return a uri  201
	}
	
}

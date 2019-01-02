package com.nelioalves.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

// CAMADA CONTROLADORES REST costuma-se ter metodos pequenos

@RestController // QUE RECEBE A NOTAÇÃO REST CONTROLLER
@RequestMapping(value="/categorias") //E NOTACAO REQUEST MAPPING AO INICIALIZAR AO SPRING BOOT ACESSAR NA UM LOCALHOST:8080/CATEGORIAS
public class CategoriaResources {
	
	/*
	 * ESTE METODO RECEBE UM ID DA BARRA URL OU SEJA localhost:8080/categorias/1
	 * que recebe a notação pathvariable
	 */

	@Autowired // notacao para a classe instanciar sozinha
	private CategoriaService service;  // acessando o serviço
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) { 	// esse reponse é um tipo especial do spring boot que ele ja encapsula e armazena varias informações de uma respostas http para um serviço rest <?> pode ser varias tipos pode encontrar ou não encontrar
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj); //retorna uma respostas que ocorreu com sucesso e juntamente com obj
	}
	
	// postar criar
	@RequestMapping(method=RequestMethod.POST) /// declarando o metodo post
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ // essa anotação faz o json ser convertido em java automaticamente
		
		obj = service.Insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); // return a uri  201
	}

	/// atualizar dados
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void>update(@RequestBody Categoria obj ,@PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build(); /// noContent 204 não retorna nada
	}
	
	
}

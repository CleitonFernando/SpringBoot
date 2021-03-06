package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){ // essa anotação faz o json ser convertido em java automaticamente
		
		Categoria obj = service.fromDTO(objDTO);
		
		obj = service.Insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build(); // return a uri  201
	}

	/// atualizar dados
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void>update(@Valid @RequestBody CategoriaDTO objDTO ,@PathVariable Integer id){
		
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build(); /// noContent 204 não retorna nada
	}
	
	//deletar id
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void>Delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>>findAll(){ // retorna uma lista de categoria dto
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj->new CategoriaDTO(obj)).collect((Collectors.toList())); // convertando uma lista para outra lista
		return ResponseEntity.ok().body(listDto);
	}
	
	//paginação
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>>findPage(
			@RequestParam(value="page",defaultValue="0")Integer page, /// NUMERO DA PAGINA
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, /// QUANTIDADE DE LINHA
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, ///ORDENAR POR NOME
			@RequestParam(value="direction",defaultValue="ASC")String direction){ //  DIREÇÃO DE PAGINA DESCENTE OU ASCENDENTE
		
		Page<Categoria> list =service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto =  list.map(obj->new CategoriaDTO(obj)); // convertando uma lista para outra lista
		return ResponseEntity.ok().body(listDto);
	}
	
}

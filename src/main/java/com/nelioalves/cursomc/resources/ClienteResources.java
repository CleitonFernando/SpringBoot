package com.nelioalves.cursomc.resources;

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

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
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
	
	/// atualizar dados
		@RequestMapping(value="/{id}",method=RequestMethod.PUT)
		public ResponseEntity<Void>update(@Valid @RequestBody ClienteDTO objDTO ,@PathVariable Integer id){
			
			Cliente obj = service.fromDTO(objDTO);
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
		public ResponseEntity<List<ClienteDTO>>findAll(){ // retorna uma lista de categoria dto
			List<Cliente> list = service.findAll();
			List<ClienteDTO> listDto = list.stream().map(obj->new ClienteDTO(obj)).collect((Collectors.toList())); // convertando uma lista para outra lista
			return ResponseEntity.ok().body(listDto);
		}
		
		//paginação
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>>findPage(
				@RequestParam(value="page",defaultValue="0")Integer page, /// NUMERO DA PAGINA
				@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, /// QUANTIDADE DE LINHA
				@RequestParam(value="orderBy",defaultValue="nome")String orderBy, ///ORDENAR POR NOME
				@RequestParam(value="direction",defaultValue="ASC")String direction){ //  DIREÇÃO DE PAGINA DESCENTE OU ASCENDENTE
			
			Page<Cliente> list =service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDTO> listDto =  list.map(obj->new ClienteDTO(obj)); // convertando uma lista para outra lista
			return ResponseEntity.ok().body(listDto);
		}
	
	
}

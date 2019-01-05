package com.nelioalves.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.utils.URL;
import com.nelioalves.cursomc.services.ProdutoService;

// CAMADA CONTROLADORES REST costuma-se ter metodos pequenos

@RestController // QUE RECEBE A NOTAÇÃO REST CONTROLLER
@RequestMapping(value="/produtos") //E NOTACAO REQUEST MAPPING AO INICIALIZAR AO SPRING BOOT ACESSAR NA UM LOCALHOST:8080/ProdutoS
public class ProdutoResources {
	
	/*
	 * ESTE METODO RECEBE UM ID DA BARRA URL OU SEJA localhost:8080/Produtos/1
	 * que recebe a notação pathvariable
	 */

	@Autowired // notacao para a classe instanciar sozinha
	private ProdutoService service;  // acessando o serviço
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) { 	// esse reponse é um tipo especial do spring boot que ele ja encapsula e armazena varias informações de uma respostas http para um serviço rest <?> pode ser varias tipos pode encontrar ou não encontrar
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj); //retorna uma respostas que ocorreu com sucesso e juntamente com obj
	}
	
	//paginação
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<Page<ProdutoDTO>>findPage(
				@RequestParam(value="nome",defaultValue="")String nome, /// NUMERO DA PAGINA
				@RequestParam(value="categorias",defaultValue="")String categorias, /// NUMERO DA PAGINA
				@RequestParam(value="page",defaultValue="0")Integer page, /// NUMERO DA PAGINA
				@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, /// QUANTIDADE DE LINHA
				@RequestParam(value="orderBy",defaultValue="nome")String orderBy, ///ORDENAR POR NOME
				@RequestParam(value="direction",defaultValue="ASC")String direction){ //  DIREÇÃO DE PAGINA DESCENTE OU ASCENDENTE
			String nomeDecoded = URL.decodeParam(nome);
			List<Integer> ids = URL.decodeIntList(categorias);
			Page<Produto> list =service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
			Page<ProdutoDTO> listDto =  list.map(obj->new ProdutoDTO(obj)); // convertando uma lista para outra lista
			return ResponseEntity.ok().body(listDto);
		}
}

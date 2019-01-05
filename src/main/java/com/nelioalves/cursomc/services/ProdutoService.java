package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


//CAMADA DE SERVIÇO
/*
 *  Esta camada efetua uma chamada a ProdutoRepository que acessa ao banco de dados 
 */

@Service // notação necessaria
public class ProdutoService {
	
	// para instanciar o repo que recebe a notaçao Autowired com essa notação a dependencia seria auto instanciada
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
		/*
	 * Este metodo vai no banco de dados  retorna a Produto com este id
	 */
	
	public Produto find(Integer id) {
		
		Optional<Produto> obj = repo.findById(id); // buscando por id
								
		// caso não encontre o id ele devera retornar  um mensagem com o id não encontrado caso ocorra essa exceção a camada rest resources recebera essa exceção
		return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado!id:"+ id + ",tipo:"+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage,String orderBy,String direction){ // parametros numero da pagina,quantidade de linhas,ordenar por id,direção descente ou descente
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);// busca todas as categorias a partir do  id
		return repo.search(nome,categorias,pagerequest);
	}
}

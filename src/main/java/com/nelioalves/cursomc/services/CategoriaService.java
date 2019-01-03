package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


//CAMADA DE SERVIÇO
/*
 *  Esta camada efetua uma chamada a categoriaRepository que acessa ao banco de dados 
 */

@Service // notação necessaria
public class CategoriaService {
	
	// para instanciar o repo que recebe a notaçao Autowired com essa notação a dependencia seria auto instanciada
	@Autowired
	private CategoriaRepository repo;
	
		/*
	 * Este metodo vai no banco de dados  retorna a categoria com este id
	 */
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id); // buscando por id
								
		// caso não encontre o id ele devera retornar  um mensagem com o id não encontrado caso ocorra essa exceção a camada rest resources recebera essa exceção
		return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado!id:"+ id + ",tipo:"+ Categoria.class.getName()));
	}
	
	// inserir categoria
	public Categoria Insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// atualizar categoria
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	///deletando categoria
	public void delete(Integer id) {
		
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) { //captura ex ceção do banco de dados com a associação que nao pode deletar
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	
	//pegando todas as categorias
	public List<Categoria> findAll(){
		
		return repo.findAll();
	}
	
	
	//paginação
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){ // parametros numero da pagina,quantidade de linhas,ordenar por id,direção descente ou descente
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pagerequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(),objDTO.getNome());
	}
}

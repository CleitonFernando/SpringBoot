package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;

//CAMADA DE SERVIÇO
/*
 *  Esta camada efetua uma chamada a categoriaRepositry que acessa ao banco de dados 
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
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
}

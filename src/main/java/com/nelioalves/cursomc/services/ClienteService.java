package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.*;


//CAMADA DE SERVIÇO
/*
 *  Esta camada efetua uma chamada a categoriaRepository que acessa ao banco de dados 
 */

@Service // notação necessaria
public class ClienteService {
	
	// para instanciar o repo que recebe a notaçao Autowired com essa notação a dependencia seria auto instanciada
	@Autowired
	private ClienteRepository repo;
	
		/*
	 * Este metodo vai no banco de dados  retorna a categoria com este id
	 */
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id); // buscando por id
								
		// caso não encontre o id ele devera retornar  um mensagem com o id não encontrado caso ocorra essa exceção a camada rest resources recebera essa exceção
		return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado!id:"+ id + ",tipo:"+ Cliente.class.getName()));
	}
	
}

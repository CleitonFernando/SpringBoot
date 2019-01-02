package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.*;


//CAMADA DE SERVIÇO
/*
 *  Esta camada efetua uma chamada a PedidoRepository que acessa ao banco de dados 
 */

@Service // notação necessaria
public class PedidoService {
	
	// para instanciar o repo que recebe a notaçao Autowired com essa notação a dependencia seria auto instanciada
	@Autowired
	private PedidoRepository repo;
	
		/*
	 * Este metodo vai no banco de dados  retorna a Pedido com este id
	 */
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id); // buscando por id
								
		// caso não encontre o id ele devera retornar  um mensagem com o id não encontrado caso ocorra essa exceção a camada rest resources recebera essa exceção
		return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado!id:"+ id + ",tipo:"+ Pedido.class.getName()));
	}
	
}

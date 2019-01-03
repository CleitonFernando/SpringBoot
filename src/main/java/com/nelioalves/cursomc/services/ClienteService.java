package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
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
	
	
	// inserir categoria
	public Cliente Insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	// atualizar categoria
	public Cliente update(Cliente obj) {
		
		Cliente newOBJ = find(obj.getId()); //pegando cliente com dados antigos
		
		updateData(newOBJ, obj);// atualizo o cliente com dados novos
		return repo.save(newOBJ);
	}
	
	///deletando categoria
	public void delete(Integer id) {
		
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) { //captura ex ceção do banco de dados com a associação que nao pode deletar
			throw new DataIntegrityException("Não é possivel excluir uma cliente que possui pedidos");
		}
		
	}
	
	//pegando todas as categorias
	public List<Cliente> findAll(){
		
		return repo.findAll();
	}
	
	
	//paginação
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){ // parametros numero da pagina,quantidade de linhas,ordenar por id,direção descente ou descente
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pagerequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
	}
	
	///atualizado cliente antigo para o novo dados
	private void updateData(Cliente newOBJ, Cliente obj) {
		 newOBJ.setNome(obj.getNome());
		 newOBJ.setEmail(obj.getEmail());
	}
}

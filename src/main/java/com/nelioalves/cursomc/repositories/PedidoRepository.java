package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pedido;

//ESTA CAMADA É DE ACESSO AO DADOS (REPOSITORY)

//  a categoria repository sera uma INTERFACE que é capaz de acessar os dados com base em que voce passar com parametro
// que recebera um objeto e o tipo da categoria para a busca que é do tipo integer ou seja pelo id chave primaria 
// BUSCA DE ACESSO A DADOS ,SALVAR,ALTERAR..ETC
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	
}

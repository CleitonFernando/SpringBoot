package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

/*
 * o CommandLineRunner permite implementa um metodo  auxiliar quando a iniciar
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// Instaciando categoria
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		//instanciando produto 
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2  = new Produto(null,"Impressoura",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		// categoria não reconhece o produto que ela tem portanto agora ela ira conhecer
		cat1.getProduto().addAll(Arrays.asList(p1,p2,p3)); // a categoria 1 tem o produto computador,impressoura,mouse == adicionando a lista de produtos
		cat2.getProduto().addAll(Arrays.asList(p2)); /// categoria 2 tem apenas a impressoura == adcionando a lista de produtos
		
		// produto não reconhece o produto que ela tem portanto agora ela ira conhecer
		p1.getCategorias().addAll(Arrays.asList(cat1));   //  o produto 1 pertence a categoria 1 == adicionando a lista de categorias  
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2)); //  o produto 2 pertence a categoria 1  e  2 == adicionando a lista de categorias
		p3.getCategorias().addAll(Arrays.asList(cat1)); // //  o produto 3 pertence a categoria 1 == adicionando a lista de categorias
		
		// Instaciando estado 
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		// instanciando cidade
		Cidade c1 =  new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		// adicionando uma lista de cidade em um estado
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		
		
		// Salvando a categoria no banco de dados no repository 
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		//salvando produto  no banco de dados
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		// salvando o estado no banco de dados
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		// salvando a cidade no banco de dados 
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}
}

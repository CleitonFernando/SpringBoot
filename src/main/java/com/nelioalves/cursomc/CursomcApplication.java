package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		//instanciando cliente
		Cliente cli1 = new Cliente(null,"maria","maria@gmail.com","123",TipoCliente.PESSOAFISICA);
		// adicionando telefones a lista
		cli1.getTelefones().addAll(Arrays.asList("1885","8555"));
		// instancia endereços
		Endereco e1 = new Endereco(null,"Rua flores","300","apto 303","Jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","sala 800","centro","387777012",cli1,c2);
		// adicionando endereços a lista
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		// Formatando a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		
		// instanciando pedido
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2= new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);
		
		// instanciando o pagamento do tipo com cartao
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1); /// associando pagamento ao pedido
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.Pendente,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);// associando pagamento ao pedido
		
		// adicionando pedidos a lista de pedidos
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		// Salvando a categoria no banco de dados no repository 
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		//salvando produto  no banco de dados
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		// salvando o estado no banco de dados
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		// salvando a cidade no banco de dados 
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		/// salvando cliente no banco de dados
		clienteRepository.saveAll(Arrays.asList(cli1));
		// salvando endereços no banco de dados
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		// salvando pedido no banco de dadoos
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}
}

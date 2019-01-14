package com.nelioalves.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.Perfil;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;/// instanciando o metodo que criptografa a senha
	
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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		// Instaciando categoria
				Categoria cat1 = new Categoria(null,"Informatica");
				Categoria cat2 = new Categoria(null,"Escritório");
				Categoria cat3 = new Categoria(null,"Cama mesa e banho");
				Categoria cat4 = new Categoria(null,"Eletrônicos");
				Categoria cat5 = new Categoria(null,"Jardinagem");
				Categoria cat6= new Categoria (null,"Decoração");
				Categoria cat7 = new Categoria(null,"Perfumaria");
				
				
				//instanciando produto 
				Produto p1 = new Produto(null,"computador",2000.00);
				Produto p2  = new Produto(null,"Impressoura",800.00);
				Produto p3 = new Produto(null,"Mouse",80.00);
				Produto p4 = new Produto(null,"Mesa de escritoria",300.00);
				Produto p5 = new Produto(null,"Toalha",50.00);
				Produto p6= new Produto(null,"colcha",200.00);
				Produto p7= new Produto(null,"TV true color",1200.00);
				Produto p8 = new Produto(null,"Roçadeira",800.00);
				Produto p9 = new Produto(null,"Abajour",100.00);
				Produto p10 = new Produto(null,"Pendente",180.00);
				Produto p11 = new Produto(null,"Shampo",90.00);
				
				// categoria não reconhece o produto que ela tem portanto agora ela ira conhecer
				cat1.getProduto().addAll(Arrays.asList(p1,p2,p3)); // a categoria 1 tem o produto computador,impressoura,mouse == adicionando a lista de produtos
				cat2.getProduto().addAll(Arrays.asList(p2,p4)); /// categoria 2 tem apenas a impressoura == adcionando a lista de produtos
				cat3.getProduto().addAll(Arrays.asList(p5,p6));
				cat4.getProduto().addAll(Arrays.asList(p1,p2,p3,p7));
				cat5.getProduto().addAll(Arrays.asList(p8));
				cat6.getProduto().addAll(Arrays.asList(p9,p10));
				cat7.getProduto().addAll(Arrays.asList(p11));
				
				// produto não reconhece o produto que ela tem portanto agora ela ira conhecer
				p1.getCategorias().addAll(Arrays.asList(cat1,cat4));   //  o produto 1 pertence a categoria 1 == adicionando a lista de categorias  
				p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4)); //  o produto 2 pertence a categoria 1  e  2 == adicionando a lista de categorias
				p3.getCategorias().addAll(Arrays.asList(cat1,cat4)); // //  o produto 3 pertence a categoria 1 == adicionando a lista de categorias
				p4.getCategorias().addAll(Arrays.asList(cat2));
				p5.getCategorias().addAll(Arrays.asList(cat3));
				p6.getCategorias().addAll(Arrays.asList(cat3));
				p7.getCategorias().addAll(Arrays.asList(cat4));
				p8.getCategorias().addAll(Arrays.asList(cat5));
				p9.getCategorias().addAll(Arrays.asList(cat6));
				p10.getCategorias().addAll(Arrays.asList(cat6));
				p11.getCategorias().addAll(Arrays.asList(cat7));		
				
				
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
				Cliente cli1 = new Cliente(null,"maria Silva","cleitontexas@gmail.com","69154782058",TipoCliente.PESSOAFISICA,pe.encode("123"));
				cli1.getTelefones().addAll(Arrays.asList("1885","8555"));
				
				Cliente cli2 = new Cliente(null,"Ana Costa","cleitontexas@hotmail.com","04354841096",TipoCliente.PESSOAFISICA,pe.encode("123"));
				cli2.addPerfil(Perfil.ADMIN);
				cli2.getTelefones().addAll(Arrays.asList("999999","11111111"));

				// adicionando telefones a lista
				
				// instancia endereços
				Endereco e1 = new Endereco(null,"Rua flores","300","apto 303","Jardim","38220834",cli1,c1);
				Endereco e2 = new Endereco(null,"Avenida Matos","105","sala 800","centro","387777012",cli1,c2);
				
				Endereco e3 = new Endereco(null,"Avenida Flores","1051",null,"centro","14920000",cli1,c2);

				// adicionando endereços a lista
				cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
				cli2.getEnderecos().addAll(Arrays.asList(e3));
				
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
				
				
				//instanciando itempedido
				ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
				ItemPedido ip2 = new ItemPedido(ped2,p3,0.00,2,80.00);
				ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
				
				ped1.getItens().addAll(Arrays.asList(ip1,ip2));
				ped2.getItens().addAll(Arrays.asList(ip3));
				
				p1.getItens().addAll(Arrays.asList(ip1));
				p2.getItens().addAll(Arrays.asList(ip3));
				p3.getItens().addAll(Arrays.asList(ip2));
			
				// Salvando a categoria no banco de dados no repository 
				categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
				//salvando produto  no banco de dados
				produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
				// salvando o estado no banco de dados
				estadoRepository.saveAll(Arrays.asList(est1,est2));
				// salvando a cidade no banco de dados 
				cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
				/// salvando cliente no banco de dados
				clienteRepository.saveAll(Arrays.asList(cli1,cli2));
				// salvando endereços no banco de dados
				enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
				// salvando pedido no banco de dadoos
				pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
				pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
				
				itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
				
	}
}

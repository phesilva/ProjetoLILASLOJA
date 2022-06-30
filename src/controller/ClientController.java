package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dao.impl.ClienteDAOImp;
import dao.impl.ProdutoDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import model.entities.Cliente;
import model.entities.Pedidos;
import model.entities.Produto;
import service.ClienteService;
import service.PedidoService;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ClientController implements Initializable {
	@FXML
	private AnchorPane tela_clientes;
	@FXML
	private Button btnCadastrar;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtCpf;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnBuscar;
	@FXML
	private TextField txtContato;
	@FXML
	private TableView<Cliente> tableview_clientes;
	@FXML
	private TableColumn<Cliente, String> nome;
	@FXML
	private TableColumn<Cliente, String> cpf;
	@FXML
	private TableColumn<Cliente, String> email;
	@FXML
	private TableColumn<Cliente, String> contato;
	@FXML
	private TableColumn<Cliente, String> endereco;
	@FXML
	private TableColumn<Cliente, Integer>codigo;
	@FXML
	private TableColumn<Cliente, String>cep;
	@FXML
	private TextField txtCEP;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtCodigo;
	@FXML
	private AnchorPane tela_produtos;
	@FXML
	private Button btnCadastrar1;
	@FXML
	private TextField txtCodigoProduto;
	@FXML
	private TextField txtNomeProduto;
	@FXML
	private TextField txtMarca;
	@FXML
	private Button btnAtualizar1;
	@FXML
	private Button btnExcluir1;
	@FXML
	private Button btnBuscar1;
	@FXML
	private TextField txtValor;
	@FXML
	private TableView<Produto> tableview_produtos;
	@FXML
	private TableColumn<Produto, Integer> codigoProduto;
	@FXML
	private TableColumn<Produto, String> nomeProduto;
	@FXML
	private TableColumn<Produto, String> modeloProduto;
	@FXML
	private TableColumn<Produto, String> marcaProduto;
	@FXML
	private TableColumn<Produto, Double> valorProduto;
	@FXML
	private TableColumn<Produto, Integer> unidadeProduto;
	@FXML
	private TableColumn<Produto, String> tamanhoProduto;
	@FXML
	private TableColumn<Produto, Integer> numeracaoProduto;
	@FXML
	private TextField txtPeso;
	@FXML
	private TextField txtModelo;
	@FXML
	private TextField txtUnidade;
	@FXML
	private TextField txtTamanho;
	@FXML
	private TextField txtNumeracao;
	@FXML
	private Button btnListar1;
	@FXML
	private Button btnListarTodos;
	@FXML
	private TableView<Pedidos> tableview_pedidos;
	@FXML
	private Button btnCadastrarPedido;
	@FXML
	private Button btnAtualizarPedido;
	@FXML
	private Button btnExcluirPedido;
	@FXML
	private Button btnBuscarPedido;
	@FXML
	private Button btnListarPedidos;
	@FXML
	private TextField txtCodigoPedido;
	@FXML
	private TextField txtCodigoCliente;
	@FXML
	private TextField txtCodigoProduto1;
	@FXML
	private TextField txtValorPedido;
	@FXML
	private TextField txtDataPedido;
	@FXML
	private TableColumn<Pedidos,Integer> codigopedido;
	@FXML
	private TableColumn<Pedidos,Integer> codigocliente;
	@FXML
	private TableColumn<Pedidos,Integer> codigoproduto;
	@FXML
	private TableColumn<Pedidos,Double> valorpedido;
	@FXML
	private TableColumn<Pedidos,Date> datapedido;
	@FXML
	private DatePicker datapicker;

	
    private static ClienteService servico = new ClienteService();
    private static PedidoService pedidoservico = new PedidoService();
	
	private static ProdutoDAOImp daop = new ProdutoDAOImp();
	
	private ObservableList<Cliente> lista;
	
	private ObservableList<Produto> listap;
	
	private ObservableList<Pedidos> listapp;
	
	// Event Listener on Button[#btnCadastrar].onAction
	@FXML
	public void acaoCadastrar(ActionEvent event) {
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setCep(txtCEP.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setContato(txtContato.getText());
		
		servico.criar(cliente);
		
		carregartabelaview();
		
		LimparCampo();
		
	}
	// Event Listener on Button[#btnAtualizar].onAction
	@FXML
	public void acaoAtualizar(ActionEvent event) {
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(txtCodigo.getText()));
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		cliente.setEmail(txtEmail.getText());
		cliente.setCep(txtCEP.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setContato(txtContato.getText());
		
		servico.alterar(cliente);
		
		carregartabelaview();
		
		LimparCampo();
	}
	// Event Listener on Button[#btnExcluir].onAction
	@FXML
	public void acaoExcluir(ActionEvent event) {
		Cliente cliente = new Cliente();
		
		cliente.setId(Integer.parseInt(txtCodigo.getText()));
		
		servico.excluir(cliente);
		
		carregartabelaview();
		
		LimparCampo();
	}
	// Event Listener on Button[#btnBuscar].onAction
	@FXML
	public void AcaoBuscar(ActionEvent event) {
		Cliente cliente = new Cliente();
		
		if(txtCodigo.getText().isEmpty() && !txtNome.getText().isEmpty()) {
			
			List<Cliente> listaclientes = servico.buscar(txtNome.getText());
			lista = FXCollections.observableArrayList(listaclientes);
			tableview_clientes.setItems(lista);
			
		}else if(!txtCodigo.getText().isEmpty() && txtNome.getText().isEmpty()) {
			cliente.setId(Integer.parseInt(txtCodigo.getText()));		
			Cliente clientebuscado = servico.busca_id(cliente);
			lista = FXCollections.observableArrayList(clientebuscado);
			tableview_clientes.setItems(lista);
		}					
			
			LimparCampo();
		
	}
	@FXML
	public void AcaoListartodos(ActionEvent event) {
		
		carregartabelaview();
		
	}
	private void carregartabelaview() {
		lista = FXCollections.observableArrayList(servico.listar());
		tableview_clientes.setItems(lista);
	}

	// Event Listener on Button[#btnCadastrar1].onAction
	@FXML
	public void acaoCadastrarProduto(ActionEvent event) {
		
		Produto produto = new Produto();
		
		produto.setNome(txtNomeProduto.getText());
		produto.setMarca(txtMarca.getText());
		produto.setModelo(txtModelo.getText());
		produto.setValor(Double.parseDouble(txtValor.getText()));
		produto.setNumeracao(Integer.parseInt(txtNumeracao.getText()));
		produto.setTamanho(txtTamanho.getText());
		produto.setUnidade(Integer.parseInt(txtUnidade.getText()));
		
		daop.criar(produto);
		
		carregartabelaviewP();
		
		LimparCampoP();
		
	}
	// Event Listener on Button[#btnAtualizar1].onAction
	@FXML
	public void acaoAtualizarProduto(ActionEvent event) {
		Produto produto = new Produto();
		produto.setCodigo(Integer.parseInt(txtCodigoProduto.getText()));
		produto.setNome(txtNomeProduto.getText());
		produto.setMarca(txtMarca.getText());
		produto.setModelo(txtModelo.getText());
		produto.setValor(Double.parseDouble(txtValor.getText()));
		produto.setNumeracao(Integer.parseInt(txtNumeracao.getText()));
		produto.setTamanho(txtTamanho.getText());
		produto.setUnidade(Integer.parseInt(txtUnidade.getText()));
		
		daop.alterar(produto);
		
		carregartabelaviewP();
		
		LimparCampoP();
	}
	// Event Listener on Button[#btnExcluir1].onAction
	@FXML
	public void acaoExcluirProduto(ActionEvent event) {
		Produto produto = new Produto();
		
		produto.setCodigo(Integer.parseInt(txtCodigoProduto.getText()));
		
		daop.excluir(produto);
		
		carregartabelaviewP();
		
		LimparCampoP();
	}
	// Event Listener on Button[#btnBuscar1].onAction
	@FXML
	public void AcaoBuscarProduto(ActionEvent event) {
		// TODO Autogenerated
		Produto produto = new Produto();
		
		produto.setCodigo(Integer.parseInt(txtCodigoProduto.getText()));		
		Produto produtobuscado = daop.busca_id(produto);
		
		listap = FXCollections.observableArrayList(produtobuscado);
		tableview_produtos.setItems(listap);
		
		
	}
	// Event Listener on Button[#btnListar1].onAction
	@FXML
	public void acaoBuscarListarProduto(ActionEvent event) {
		
		carregartabelaviewP();
		
	}
	
	private void carregartabelaviewP() {
		listap = FXCollections.observableArrayList(daop.listar());
		tableview_produtos.setItems(listap);
	}
		
	
	@FXML
	public void acaoCadastrarPedido() {
		Pedidos pedido = new Pedidos();
		pedido.setCodigocliente(Integer.parseInt(txtCodigoCliente.getText()));
		pedido.setCodigoproduto(Integer.parseInt(txtCodigoProduto1.getText()));
		pedido.setValorpedido(Double.valueOf(txtValorPedido.getText()));
		
		pedidoservico.criar(pedido);
		
		carregartableviewpp();
		
		LimparCampoPP();
		
	}
	@FXML 
	public void acaoAtualizarPedido() {
		Pedidos pedido = new Pedidos();
		pedido.setCodigopedido(Integer.parseInt(txtCodigoPedido.getText()));
		pedido.setCodigocliente(Integer.parseInt(txtCodigoCliente.getText()));
		pedido.setCodigoproduto(Integer.parseInt(txtCodigoProduto1.getText()));
		pedido.setValorpedido(Double.valueOf(txtValorPedido.getText()));
		
		pedidoservico.alterar(pedido);
		
		carregartableviewpp();
		
		LimparCampoPP();
	}
	@FXML
	public void acaoExcluirPedido() {
		Pedidos pedido = new Pedidos();
		pedido.setCodigopedido(Integer.parseInt(txtCodigoPedido.getText()));
		pedidoservico.excluir(pedido);
		
		carregartableviewpp();
		
		LimparCampoPP();
	}
	@FXML
	public void AcaoBuscarPedido() {
		Pedidos pedido = new Pedidos();
		pedido.setCodigopedido(Integer.parseInt(txtCodigoPedido.getText()));
		Pedidos pedidobuscado = pedidoservico.buscar(pedido);
		listapp = FXCollections.observableArrayList(pedidobuscado);
		tableview_pedidos.setItems(listapp);
		
		LimparCampoPP();
	}
	@FXML
	public void acaoBuscarListarPedidos() {
		
		carregartableviewpp();
		
	}
	private void carregartableviewpp() {
		listapp = FXCollections.observableArrayList(pedidoservico.listar());
		tableview_pedidos.setItems(listapp);
	}
	private void LimparCampo() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtContato.setText("");
		txtCEP.setText("");
	}
	private void LimparCampoP() {
		
		txtCodigoProduto.setText("");
		txtNomeProduto.setText("");
		txtModelo.setText("");
		txtMarca.setText("");
		txtValor.setText("");
		txtTamanho.setText("");
		txtNumeracao.setText("");
		txtUnidade.setText("");
		
	}
	private void LimparCampoPP() {	
		txtCodigoPedido.setText("");
		txtCodigoCliente.setText("");
		txtCodigoProduto1.setText("");
		txtValorPedido.setText("");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carregarcellValueClientes();
		carregarcellValueProdutos();
		carregarcellValuePedidos();
	}
	private void carregarcellValuePedidos() {
		codigopedido.setCellValueFactory(new PropertyValueFactory<>("codigopedido"));
		codigocliente.setCellValueFactory(new PropertyValueFactory<>("codigocliente"));
		codigoproduto.setCellValueFactory(new PropertyValueFactory<>("codigoproduto"));
		valorpedido.setCellValueFactory(new PropertyValueFactory<>("valorpedido"));
	}
	private void carregarcellValueProdutos() {
		 codigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		 nomeProduto         .setCellValueFactory(new PropertyValueFactory<>("nome"));
		 modeloProduto       .setCellValueFactory(new PropertyValueFactory<>("modelo"));
		 marcaProduto        .setCellValueFactory(new PropertyValueFactory<>("marca"));
		 valorProduto        .setCellValueFactory(new PropertyValueFactory<>("valor"));
		 unidadeProduto      .setCellValueFactory(new PropertyValueFactory<>("unidade"));
		 tamanhoProduto      .setCellValueFactory(new PropertyValueFactory<>("tamanho"));
		 numeracaoProduto    .setCellValueFactory(new PropertyValueFactory<>("numeracao"));
	}
	
	private void carregarcellValueClientes() {
		codigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		contato.setCellValueFactory(new PropertyValueFactory<>("contato"));
		endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		cep.setCellValueFactory(new PropertyValueFactory<>("cep"));
	}
}

package service;

import java.util.List;

import dao.impl.PedidoDAOImp;
import model.entities.Pedidos;

public class PedidoService {
	
	private static PedidoDAOImp pedidosdao = new PedidoDAOImp();

	public void criar(Pedidos pedido) {
		pedidosdao.criar(pedido);
	}
	public void alterar(Pedidos pedido) {
		pedidosdao.alterar(pedido);
	}
	public void excluir(Pedidos pedido) {
		pedidosdao.excluir(pedido);
	}
	public List<Pedidos>listar(){
		return pedidosdao.listar();
	}
	public Pedidos buscar(Pedidos pedido) {
		return pedidosdao.buscar(pedido);
	}
	
}

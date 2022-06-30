package dao;

import java.util.List;

import model.entities.Pedidos;

public interface InterfacePedido {
	public void criar(Pedidos pedido);
	public void alterar(Pedidos pedido);
	public void excluir(Pedidos pedido);
	public List<Pedidos>listar();
	public Pedidos buscar(Pedidos pedido);
	
}

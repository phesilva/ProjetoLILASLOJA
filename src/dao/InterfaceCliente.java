package dao;

import java.util.ArrayList;

import model.entities.Cliente;

public interface InterfaceCliente {
	public void criar(Cliente cliente);
	public void alterar(Cliente cliente);
	public void excluir(Cliente cliente);
	public Cliente busca_id(Cliente cliente);
	public ArrayList<Cliente> listar();
	public ArrayList<Cliente>buscar(String busca);
}

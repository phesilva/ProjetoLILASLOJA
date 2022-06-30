package service;

import java.util.ArrayList;

import dao.impl.ClienteDAOImp;
import model.entities.Cliente;

public class ClienteService {
	
	private static ClienteDAOImp clientedao = new ClienteDAOImp();
	
	public void criar(Cliente cliente) {
		clientedao.criar(cliente);
	}
	public void alterar(Cliente cliente) {
		clientedao.alterar(cliente);
	}
	public void excluir(Cliente cliente) {
		clientedao.excluir(cliente);
	}
	public ArrayList<Cliente>listar(){
		return clientedao.listar();
	}
	public Cliente busca_id(Cliente cliente) {
		return clientedao.busca_id(cliente);
	}
	
	public static ArrayList<Cliente> buscar(String busca) {
		return clientedao.buscar(busca);
	}

}

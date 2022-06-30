package dao;

import java.util.ArrayList;

import model.entities.Produto;

public interface InterfaceProduto {
	public void criar(Produto produto);
	public void alterar(Produto produto);
	public void excluir(Produto produto);
	public Produto busca_id(Produto produto);
	public ArrayList<Produto> listar();
}

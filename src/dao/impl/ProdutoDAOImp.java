package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.InterfaceProduto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.entities.Cliente;
import model.entities.Produto;
import util.HibernateUtil;

public class ProdutoDAOImp implements InterfaceProduto{

	private SessionFactory sf;

	public ProdutoDAOImp() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void criar(Produto produto) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(produto);
		transaction.commit();
		entityManager.close();
		
	}
	@Override
	public void alterar(Produto produto) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(produto);
		transaction.commit();
	}
	@Override
	public void excluir(Produto produto) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Produto buscado = entityManager.find(Produto.class, produto.getCodigo());
		transaction.begin();
		entityManager.remove(buscado);
		transaction.commit();
		
	}
	@Override
	public Produto busca_id(Produto produto) {
		EntityManager entityManager = sf.createEntityManager();
		Produto produtobuscado = entityManager.find(Produto.class, produto.getCodigo());
		
		return produtobuscado;
	}
	@Override
	public ArrayList<Produto> listar() {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}
	private String montarQuery(String busca) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT codigo,nome, modelo, marca, valor, tamanho,numeracao,unidade FROM Tabela_produtos ");
		if (busca != null) {
			buffer.append("WHERE  LIKE '%");
			buffer.append(busca);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY codigo");
		return buffer.toString();
	}
	private ArrayList<Produto> gerarLista(Query query) {
		ArrayList<Produto> lista = new ArrayList<Produto>();
		List<Object[]> listaresult = query.getResultList();
		for (Object[] obj : listaresult) {
			Produto produto = new Produto();
			
			produto.setCodigo(Integer.parseInt(obj[0].toString()));
			produto.setNome(obj[1].toString());
			produto.setModelo(obj[2].toString());
			produto.setMarca(obj[3].toString());
			produto.setValor(Double.parseDouble(obj[4].toString()));
			produto.setTamanho(obj[5].toString());
			produto.setNumeracao(Integer.parseInt(obj[6].toString()));
			produto.setUnidade(Integer.parseInt(obj[7].toString()));
			
			lista.add(produto);
		}
		return lista;
	}
}

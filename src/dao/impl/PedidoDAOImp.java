package dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.InterfacePedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.entities.Pedidos;
import util.HibernateUtil;

public class PedidoDAOImp implements InterfacePedido{
	
	private SessionFactory sf;
	
	public PedidoDAOImp() {
		this.sf = HibernateUtil.getSessionFactory();
		
	}
	
	@Override
	public void criar(Pedidos pedido) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(pedido);
		transaction.commit();
		
	}
	@Override
	public void alterar(Pedidos pedido) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(pedido);
		transaction.commit();
		
	}
	@Override
	public void excluir(Pedidos pedido) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Pedidos buscado = entityManager.find(Pedidos.class, pedido.getCodigopedido());
		transaction.begin();
		entityManager.remove(buscado);
		transaction.commit();
	}
	@Override
	public Pedidos buscar(Pedidos pedido) {
		EntityManager entityManager = sf.createEntityManager();
		Pedidos buscado = entityManager.find(Pedidos.class, pedido.getCodigopedido());
		
		return buscado;
	}
	@Override
	public List<Pedidos>listar(){
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}
	private String montarQuery(String busca) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT codigopedido, codigocliente, codigoproduto, valorpedido FROM Tabela_pedidos ");
		if (busca != null) {
			buffer.append("WHERE codigopedido LIKE '%");
			buffer.append(busca);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY codigopedido");
		return buffer.toString();
	}
	
	
	private ArrayList<Pedidos> gerarLista(Query query) {
		ArrayList<Pedidos> lista = new ArrayList<Pedidos>();
		List<Object[]> listaresult = query.getResultList();
		for (Object[] obj : listaresult) {
			Pedidos pedido = new Pedidos();
			
			pedido.setCodigopedido(Integer.parseInt(obj[0].toString()));
			pedido.setCodigocliente(Integer.parseInt(obj[1].toString()));
			pedido.setCodigoproduto(Integer.parseInt(obj[2].toString()));
			pedido.setValorpedido(Double.parseDouble(obj[3].toString()));
			
			lista.add(pedido);
		}
		return lista;
	}
}

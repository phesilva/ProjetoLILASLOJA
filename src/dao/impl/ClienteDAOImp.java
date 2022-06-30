package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.InterfaceCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.entities.Cliente;
import util.HibernateUtil;

public class ClienteDAOImp implements InterfaceCliente{
private SessionFactory sf;
	
	public ClienteDAOImp() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	@Override
	public void criar(Cliente cliente) {
		if(sf.isClosed()) {
			sf.openSession();
		}
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(cliente);
		transaction.commit();
		
		entityManager.close();
		
		sf.isClosed();
	}
	@Override
	public void alterar(Cliente cliente) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(cliente);
		transaction.commit();
	}
	@Override
	public void excluir(Cliente cliente) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		Cliente buscado = entityManager.find(Cliente.class, cliente.getId());
		transaction.begin();
		entityManager.remove(buscado);
		transaction.commit();
		
	}
	@Override
	public Cliente busca_id(Cliente cliente) {
		EntityManager entityManager = sf.createEntityManager();
		Cliente clientebuscado = entityManager.find(Cliente.class, cliente.getId());
		
		return clientebuscado;
	}
	
	//-----------------------------------------------------------------
	@Override
	public ArrayList<Cliente> listar() {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}
	@Override
	public ArrayList<Cliente>buscar(String busca){
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(busca));
		return gerarLista(query);
	}
	
	
	private String montarQuery(String busca) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT cln_id_cliente, cln_nome_cliente, cln_email_cliente, cln_cpf_cliente, cln_cep_cliente,cln_endereco_cliente,cln_contato_cliente FROM Tabela_Cliente ");
		if (busca != null) {
			buffer.append("WHERE cln_nome_cliente LIKE '%");
			buffer.append(busca);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY cln_id_cliente");
		return buffer.toString();
	}
	
	
	private ArrayList<Cliente> gerarLista(Query query) {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		List<Object[]> listaresult = query.getResultList();
		for (Object[] obj : listaresult) {
			Cliente cliente = new Cliente();
			
			cliente.setId(Integer.parseInt(obj[0].toString()));
			cliente.setNome(obj[1].toString());
			cliente.setEmail(obj[2].toString());
			cliente.setCpf(obj[3].toString());
			cliente.setCep(obj[4].toString());
			cliente.setEndereco(obj[5].toString());
			cliente.setContato(obj[6].toString());
			
			lista.add(cliente);
		}
		return lista;
	}
}

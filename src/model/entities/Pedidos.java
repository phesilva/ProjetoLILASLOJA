package model.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tabela_pedidos")
public class Pedidos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigopedido;
	
	private Integer codigocliente;
	
	private Integer codigoproduto;
	
	private Double valorpedido;

	
	public Pedidos(Integer codigopedido, Integer codigocliente, Integer codigoproduto, Double valorpedido) {
		super();
		this.codigopedido = codigopedido;
		this.codigocliente = codigocliente;
		this.codigoproduto = codigoproduto;
		this.valorpedido = valorpedido;
	}

	public Pedidos() {
	}

	public Integer getCodigopedido() {
		return codigopedido;
	}

	public void setCodigopedido(Integer codigopedido) {
		this.codigopedido = codigopedido;
	}

	public Integer getCodigocliente() {
		return codigocliente;
	}

	public void setCodigocliente(Integer codigocliente) {
		this.codigocliente = codigocliente;
	}

	public Integer getCodigoproduto() {
		return codigoproduto;
	}

	public void setCodigoproduto(Integer codigoproduto) {
		this.codigoproduto = codigoproduto;
	}

	public Double getValorpedido() {
		return valorpedido;
	}

	public void setValorpedido(Double valorpedido) {
		this.valorpedido = valorpedido;
	}	

}

package model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tabela_produtos")
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codigo;
	
	private String nome;
	
	private String modelo;
	
	private String marca;
	
	private Double valor;
	
	private String tamanho;
	
	private Integer numeracao;
	
	private Integer unidade;
	
	
	

	public Produto(Integer codigo, String nome, String modelo, String marca, Double valor, String tamanho,
			Integer numeracao, Integer unidade) {
		this.codigo = codigo;
		this.nome = nome;
		this.modelo = modelo;
		this.marca = marca;
		this.valor = valor;
		this.tamanho = tamanho;
		this.numeracao = numeracao;
		this.unidade = unidade;
	}
	public Produto() {
		
	}

	@Override
	public String toString() {
		return "Produtos [codigo=" + codigo + ", nome=" + nome + ", modelo=" + modelo + ", marca=" + marca + ", valor="
				+ valor + ", tamanho=" + tamanho + ", numeracao=" + numeracao + ", unidade=" + unidade + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getNumeracao() {
		return numeracao;
	}

	public void setNumeracao(Integer numeracao) {
		this.numeracao = numeracao;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}
	

	
	
}

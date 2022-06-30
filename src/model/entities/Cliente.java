package model.entities;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Tabela_Cliente")
public class Cliente implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="cln_id_cliente")
		private Integer id;
		
		@Column(name="cln_nome_cliente",length=50)
		private String nome;
		
		
		@Column(name="cln_email_cliente",length=50)
		private String email;
		
		@Column(name="cln_cpf_cliente",length=11)
		private String cpf;
		
		@Column(name="cln_cep_cliente", length=8)
		private String cep;
		
		@Column(name="cln_endereco_cliente", length=70)
		private String endereco;
		
		@Column(name="cln_contato_cliente",length=9)
		private String contato;

		public Cliente(Integer id,String nome, String email, String cpf, String cep,String endereco,String contato) {
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.cpf = cpf;
			this.cep = cep;
			this.endereco = endereco;
			this.contato = contato;
		}
		
		public Cliente() {
			
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getContato() {
			return contato;
		}
		public void setContato(String contato) {
			this.contato = contato;
		}

		@Override
		public String toString() {
			return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", cep=" + cep
					+ ", endereco=" + endereco + ", contato=" + contato + "]";
		}
		
}

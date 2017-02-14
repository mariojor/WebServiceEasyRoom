package br.com.droid.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Oferta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int Id;
	private String descricao;
	private String titulo;
	private String telefone;
	private String endereco;
	private double valor;
	public int idUsuario;

	public Oferta(int id, String descricao, String titulo,String telefone,String endereco, double valor) {
		super();
		Id = id;
		this.descricao = descricao;
		this.titulo = titulo;
		this.telefone = telefone;
		this.endereco = endereco;
		this.valor = valor;
	}
	
	public Oferta(){}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}

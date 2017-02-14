package br.com.droid.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Usuario implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String email;
	private String senha;
	
	public Usuario(){
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

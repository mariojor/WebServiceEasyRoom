package br.com.droid.interfaces;

import br.com.droid.dao.UsuarioDAO;
import br.com.droid.model.Usuario;

public interface InterfaceUsuarioDAO {

	public UsuarioDAO getUsuarioDAOInstance();
	
	public String cadastrarUsuario(Usuario usuario);
	
	public Usuario buscarUsuarioID(String usuario);
	
	public String validarLogin(Usuario usuario);
	
	public boolean verificaLogin(Usuario usuario);
	
	public String esqueciMinhaSenha(Usuario emailUsuario);
}

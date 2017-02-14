package br.com.droid.fachada;

import br.com.droid.dao.UsuarioDAO;
import br.com.droid.interfaces.InterfaceUsuarioDAO;
import br.com.droid.model.Usuario;

public class FachadaUsuario implements InterfaceUsuarioDAO {

	@Override
	public UsuarioDAO getUsuarioDAOInstance() {
		return UsuarioDAO.getUsuarioDAOInstance();
	}

	@Override
	public String cadastrarUsuario(Usuario usuario) {
		String cadastro = null;
		try {
			cadastro = getUsuarioDAOInstance().cadastrarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadastro;
	}

	@Override
	public Usuario buscarUsuarioID(String usuario) {
		return getUsuarioDAOInstance().buscarUsuarioID(usuario);
	}

	@Override
	public String validarLogin(Usuario usuario) {
		return getUsuarioDAOInstance().validarLogin(usuario);
	}

	@Override
	public boolean verificaLogin(Usuario usuario) {
		return getUsuarioDAOInstance().verificaLogin(usuario);
	}

	public String esqueciMinhaSenha(Usuario emailUsuario) {
		// TODO Auto-generated method stub
		return getUsuarioDAOInstance().esqueciMinhaSenha(emailUsuario);
	}

}

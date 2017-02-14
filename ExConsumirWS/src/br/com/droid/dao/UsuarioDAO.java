package br.com.droid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.droid.model.Anuncio;
import br.com.droid.model.Oferta;
import br.com.droid.model.Usuario;
import br.com.droid.util.ConectarBanco;
import br.com.droid.util.SendEmail;
import br.com.droid.util.ValidadorUtil;

public class UsuarioDAO {

	private static UsuarioDAO instance;

	public static UsuarioDAO getUsuarioDAOInstance() {
		if (instance == null)
			instance = new UsuarioDAO();
		return instance;
	}

	public String cadastrarUsuario(Usuario usuario) throws Exception {

		String retornoCadastro = null;
		if (!ValidadorUtil.isValidateEmail(usuario.getEmail())) {
			return retornoCadastro = "emailInvalido";
		}

		try {
			if (verificaLogin(usuario)) {
				Connection com = ConectarBanco.GetConnection();
				String queryUsuario = "Insert into Usuario values(null,?,?)";
				PreparedStatement prepareUsuario = com
						.prepareStatement(queryUsuario);
				prepareUsuario.setString(1, usuario.getEmail());
				prepareUsuario.setString(2, usuario.getSenha());
				prepareUsuario.executeUpdate();
				retornoCadastro = "Cadastrado com Sucesso";
				com.close();
			} else {
				retornoCadastro = "false";
			}
		} catch (Exception ex) {
			throw new Exception("Erro");
		}
		return retornoCadastro;
	}

	public List<Oferta> buscarOfertas() {

		List<Oferta> anuncios = new ArrayList<Oferta>();
		try {
			Connection com = ConectarBanco.GetConnection();
			String query = "select * from oferta";
			PreparedStatement pre = com.prepareStatement(query);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				Oferta ofertas = new Oferta();
				ofertas.setDescricao(rs.getString("descricao"));
				ofertas.setTitulo(rs.getString("titulo"));
				ofertas.setTelefone(rs.getString("telefone"));
				ofertas.setEndereco(rs.getString("endereco"));
				ofertas.setValor(rs.getDouble("valor"));
				anuncios.add(ofertas);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar Ofertas");
			e.printStackTrace();
		}
		return anuncios;
	}

	public Usuario buscarUsuarioID(String usuario) {
		Usuario use = null;
		try {
			Connection com = ConectarBanco.GetConnection();
			String query = "select * from usuario where email=?";
			PreparedStatement pre = com.prepareStatement(query);
			pre.setString(1, usuario);
			ResultSet rsult = pre.executeQuery();
			if (rsult.next()) {
				use = new Usuario();
				use.setId(rsult.getInt(1));
				use.setEmail(rsult.getString(2));
				use.setSenha(rsult.getString(3));
			} else {

				return use;
			}
			com.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return use;
	}

	public boolean cadastrarAnuncio(Anuncio anuncio, Oferta oferta) {

		try {
			Connection com = ConectarBanco.GetConnection();
			// salva oferta
			String queryOferta = "insert into oferta values(null,?,?,?)";
			PreparedStatement preOferta = com.prepareStatement(queryOferta);
			preOferta.setString(1, oferta.getDescricao());
			preOferta.setString(2, oferta.getTitulo());
			preOferta.setDouble(3, oferta.getValor());
			preOferta.executeUpdate();
			// buscar oferta salva
			String buscar = "select * from oferta where descricao =?";
			PreparedStatement prebuscar = com.prepareStatement(buscar);
			prebuscar.setString(1, oferta.getDescricao());
			ResultSet resultadoOferta = prebuscar.executeQuery();
			Oferta of = new Oferta();

			if (resultadoOferta.next()) {
				of.setId(resultadoOferta.getInt(1));
			}
			// salva Anuncio
			String queryAnuncio = "insert into anuncio values(null,?,?)";
			PreparedStatement preAnuncio = com.prepareStatement(queryAnuncio);
			preAnuncio.setInt(1, of.getId());
			preAnuncio.setInt(2, anuncio.getIdUsuario());
			preAnuncio.executeUpdate();
			com.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String validarLogin(Usuario usuario) {

		String valid = "true";
		try {
			Connection com = ConectarBanco.GetConnection();
			String buscar = "select * from usuario where email =? and senha=?";
			PreparedStatement prebuscar = com.prepareStatement(buscar);
			prebuscar.setString(1, usuario.getEmail());
			prebuscar.setString(2, usuario.getSenha());
			ResultSet resultadoLogin = prebuscar.executeQuery();
			Usuario user = new Usuario();
			while (resultadoLogin.next()) {
				user.setEmail(resultadoLogin.getString(1));
				user.setSenha(resultadoLogin.getString(2));
			}
			if ((user.getEmail() == null || user.getEmail().isEmpty() || user
					.getEmail().equals(""))
					|| (user.getSenha() == null || user.getSenha().isEmpty() || user
							.getSenha().equals(""))) {
				valid = "false";
			}
			com.close();
		} catch (SQLException e) {
			valid = "false";
			e.printStackTrace();
		}
		return valid;
	}

	public boolean verificaLogin(Usuario usuario) {

		boolean valid = false;

		try {

			Connection com = ConectarBanco.GetConnection();

			String buscar = "select * from usuario where email =?";
			PreparedStatement prebuscar = com.prepareStatement(buscar);
			prebuscar.setString(1, usuario.getEmail());
			ResultSet resultadoLogin = prebuscar.executeQuery();

			Usuario user = new Usuario();

			while (resultadoLogin.next()) {
				user.setEmail(resultadoLogin.getString(1));
			}
			if (user.getEmail() == null || user.getEmail().isEmpty()
					|| user.getEmail().equals("")) {

				valid = true;
			}
			com.close();
		} catch (SQLException e) {
			valid = false;
			e.printStackTrace();
		}

		return valid;
	}

	public String esqueciMinhaSenha(Usuario emailUsuario) {

		String valid = "false";

		try {

			Connection com = ConectarBanco.GetConnection();

			String buscar = "select * from usuario where email =?";
			PreparedStatement prebuscar = com.prepareStatement(buscar);
			prebuscar.setString(1, emailUsuario.getEmail());

			ResultSet resultadoLogin = prebuscar.executeQuery();

			Usuario user = new Usuario();

			while (resultadoLogin.next()) {
				user.setEmail(resultadoLogin.getString(1));
			}
			if (user.getEmail() == null || user.getEmail().equals("")
					|| user.getEmail().isEmpty()) {
				return valid;
			} else {
				updateTable(emailUsuario.getEmail());
			}
			valid = "true";

			com.close();

		} catch (SQLException e) {
			valid = "false";
			e.printStackTrace();
		}
		return valid;
	}

	public void updateTable(String email) {
		String novaSenha = SendEmail.gerarSenhaAleatoria();

		try {

			Connection conn = ConectarBanco.GetConnection();

			String sql = "UPDATE usuario SET senha=? WHERE email=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, novaSenha);
			statement.setString(2, email);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				SendEmail.enviarEmail(email,novaSenha);
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

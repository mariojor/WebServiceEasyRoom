 package br.com.droid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.droid.model.Oferta;
import br.com.droid.util.ConectarBanco;

public class AnuncioDAO {
	
private static AnuncioDAO instance;
	
	public AnuncioDAO getAnuncioDAOInstance() {
		if (instance == null)
			instance = new AnuncioDAO();
		return instance;
	}
	
	public String CadastrarOferta(Oferta ofe){
		try {
			
			//salvando oferta
			Connection com = ConectarBanco.GetConnection();
			String query = "insert into Oferta values(null,?,?,?,?,?)";
			PreparedStatement pre  = com.prepareStatement(query);
			pre.setString(1, ofe.getDescricao());
			pre.setString(2, ofe.getTitulo());
			pre.setString(3, ofe.getTelefone());
			pre.setString(4, ofe.getEndereco());
			pre.setDouble(5, ofe.getValor());
			pre.executeUpdate();
			com.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erro anuncio não cadastrado";
		}
	
		return "Cadastrado";
	}
	
	public List<Oferta> buscarAnuncios() {

		List<Oferta> anuncios = new ArrayList<Oferta>();

		try {
			Connection com = ConectarBanco.GetConnection();
			String query = "select * from Oferta order by id_oferta desc";
			PreparedStatement pre = com.prepareStatement(query);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				Oferta anuncio = new Oferta();
				anuncio.setId(rs.getInt("ID_OFERTA"));
				anuncio.setDescricao(rs.getString("DESCRICAO"));
				anuncio.setTitulo(rs.getString("TITULO"));
				anuncio.setTelefone(rs.getString("TELEFONE"));
				anuncio.setEndereco(rs.getString("ENDERECO"));
				anuncio.setValor(rs.getDouble("VALOR"));
				anuncios.add(anuncio);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar anuncios");
			e.printStackTrace();
		}
		return anuncios;
	}

}

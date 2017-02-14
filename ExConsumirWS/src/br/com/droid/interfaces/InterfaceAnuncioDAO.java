package br.com.droid.interfaces;

import java.util.List;

import br.com.droid.dao.AnuncioDAO;
import br.com.droid.model.Oferta;

public interface InterfaceAnuncioDAO {

	public AnuncioDAO getAnuncioDAOInstance();
	
	public String CadastrarOferta(Oferta ofe);

	public List<Oferta> buscarAnuncios();
}

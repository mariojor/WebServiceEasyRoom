package br.com.droid.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.droid.fachada.FachadaAnuncio;
import br.com.droid.fachada.FachadaUsuario;
import br.com.droid.model.Oferta;
import br.com.droid.model.Usuario;

/**
 * 
 * @author Mario
 *
 */

@Path("/cliente")
public class ClienteResource {

	/**
	 * Parte dos metodos de Anuncio
	 * 
	 */
	@GET
	@Path("/buscarAnuncios")
	@Produces("application/json")
	public List<Oferta> buscarAnuncios(){
		return  new FachadaAnuncio().buscarAnuncios();
	}
	
	@POST 
	@Path ( "CadastrarOferta/" ) 
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})  
    @Produces("application/json")
	public String CadastrarOferta(Oferta oferta){
		return new FachadaAnuncio().CadastrarOferta(oferta);
	}

	/**
	 * Parte dos metodos de usuario
	 * 
	 */
	@POST 
	@Path ( "cadastrarUsuario/" ) 
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})  
    @Produces("application/json")
	public String cadastrarUsuario(Usuario usuario) throws Exception {
	       return new FachadaUsuario().cadastrarUsuario(usuario);
	}
	
	@POST 
	@Path ( "validarLogin/" ) 
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})  
    @Produces("application/json")
	public String validarLogin(Usuario usuario){
		return new FachadaUsuario().validarLogin(usuario);
	}

	
	@POST 
	@Path ( "esqueciMinhaSenha/" ) 
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})  
    @Produces("application/json")
	public String esqueciMinhaSenha(Usuario emailUsuario){
		return new FachadaUsuario().esqueciMinhaSenha(emailUsuario);
	}
	
}


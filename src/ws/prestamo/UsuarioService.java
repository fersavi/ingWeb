package ws.prestamo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import logicaNegocio.UsuarioBL;
import dto.Usuario;
import dto.UsuarioKey;
import exception.HibernateGettingException;
import ws.dto.UsuarioWS;

@Path("Usuario")
@Component
public class UsuarioService {
	
	 /**
	  * Esta variable será inyectada por spring para accedes a los metodos disponibles para manejar el usuario
	  * */
	@Autowired
	private UsuarioBL usuarioBL;
	 
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<UsuarioWS> obtenerUsuarios () throws RemoteException{
		
		/**
		 * Esta lista cotendra los usuarios existetes pero solo con los atributos que utiliza el servicio web
		 * */
		List<UsuarioWS> listaUsuarios = new ArrayList<UsuarioWS>();
		/**
		 * variable que obtiene los cliente existentes
		 * */
		List<Usuario> usuarios = null;     
		try {
			usuarios = usuarioBL.obtenerUsuarios();
			for(Usuario usuario : usuarios){
				UsuarioWS usuarioWS = new UsuarioWS();
				usuarioWS.setId(usuario.getId());
				usuarioWS.setNombre(usuario.getNombre());
				usuarioWS.setApellido(usuario.getApellido());
				usuarioWS.setCorreo(usuario.getCorreo());
				usuarioWS.setDireccion(usuario.getDireccion());
				usuarioWS.setTelefono(usuario.getTelefono());
				usuarioWS.setCelular(usuario.getCelular());
				
				listaUsuarios.add(usuarioWS);
			}
		} catch (HibernateGettingException e) {
			throw new RemoteException(e.getMessage());
		}
		return listaUsuarios;
	}
}

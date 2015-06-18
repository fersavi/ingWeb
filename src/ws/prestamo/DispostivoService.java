package ws.prestamo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import logicaNegocio.CategoriaDispositivoBL;
import logicaNegocio.DispositivoBL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ws.dto.DispositivoWS;
import dto.CategoriaDispositivo;
import dto.Dispositivo;
import exception.HibernateDeletingException;
import exception.HibernateGettingException;
import exception.HibernateSavingException;
import exception.HibernateUpdatingException;

/**
 * 
 * @author Camilo Rivera
 *
 */
@Path("dispositivo")
@Component
public class DispostivoService {

	/**
	 * Bean inyectado por Spring con el que accedemos a los métodos de los
	 * dispositivos declarados en la lógica del negocio
	 */
	@Autowired
	private DispositivoBL dispositivoBL;
	@Autowired
	private CategoriaDispositivoBL categoriaDispositivoBL;
	/**
	 * Método que retorna todos los dispositivos.
	 * Se puede acceder a traves de http://localhost:8080/ingWeb/rest/dispositivo/obtenerDispositivos
	 * @return
	 * @throws RemoteException
	 */
	@GET
	@Path("obtenerDispositivos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWS> obtener() throws RemoteException {
		List<Dispositivo> dispositivos = null;
		List<DispositivoWS> listaDispositivos = new ArrayList<DispositivoWS>();

		try {
			dispositivos = dispositivoBL.obtenerDispositivos();
			for (Dispositivo dispositivo : dispositivos) {
				DispositivoWS dispositivoWS = new DispositivoWS();
				dispositivoWS.setId(dispositivo.getId());
				dispositivoWS.setCategoria(dispositivo.getCategoria());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setEstado(dispositivo.getEstado());
				dispositivoWS.setMarca(dispositivo.getMarca());
				listaDispositivos.add(dispositivoWS);
			}
		} catch (HibernateGettingException ex) {
			throw new RemoteException(ex.getMessage());
		}
		return listaDispositivos;
	}

	/**
	 * Método para obtener un solo dispositivo basado en el id
	 * Se puede acceder a traves de http://localhost:8080/ingWeb/rest/dispositivo/obtenerDispositivo/{id}
	 * @param id
	 * @return
	 */
	@GET
	@Path("obtenerDispositivo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DispositivoWS obtenerDispositivo(@PathParam("id") String id) {
		DispositivoWS dispositivo = new DispositivoWS();
		Dispositivo disp;
		try {
			disp = dispositivoBL.obtenerDispositivo(Integer.parseInt(id));
			dispositivo.setId(disp.getId());
			dispositivo.setCategoria(disp.getCategoria());
			dispositivo.setDescripcion(disp.getDescripcion());
			dispositivo.setEstado(disp.getEstado());
			dispositivo.setMarca(disp.getMarca());
		} catch (HibernateGettingException e) {
			e.printStackTrace();
		}
		return dispositivo;
	}
	
	/**
	 * Metodo para guardar dispositivos
	 * Se puede acceder a traves de http://localhost:8080/ingWeb/rest/dispositivo/guardar/{categoria}/{estado}/{descripcion}/{marca}
	 * @param categoria
	 * @param estado
	 * @param descripcion
	 * @param marca
	 * @return
	 */
	@POST
	@Path("guardar/{categoria}/{estado}/{descripcion}/{marca}")
	@Produces(MediaType.APPLICATION_JSON)
	public String guardar(@PathParam("categoria") String categoria, @PathParam("estado") String estado, 
			@PathParam("descripcion") String descripcion, @PathParam("marca") String marca){
		CategoriaDispositivo categoriaDispositivo = null;
		try {
			categoriaDispositivo = categoriaDispositivoBL.obtenerCategoria(categoria);
			dispositivoBL.guardar(categoriaDispositivo, estado, descripcion, marca);
			return("El dispositivo se ha guardado correctamente.");
		} catch (HibernateGettingException e) {
			e.printStackTrace();
		} catch (HibernateSavingException e) {
			e.printStackTrace();
		}
		return("Hubo un error al guardar el dispositivo.");
	}
	
	/**
	 * Metodo para actualizar dispositivos
	 * @param id
	 * @param categoria
	 * @param estado
	 * @param descripcion
	 * @param marca
	 * @return
	 */
	@PUT
	@Path("actualizar/{id}/{categoria}/{estado}/{descripcion}/{marca}")
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizar(@PathParam("id")String id, @PathParam("categoria") String categoria, @PathParam("estado") String estado, 
			@PathParam("descripcion") String descripcion, @PathParam("marca") String marca){
		CategoriaDispositivo categoriaDispositivo = null;
		try {
			categoriaDispositivo = categoriaDispositivoBL.obtenerCategoria(categoria);
			dispositivoBL.actualizar(Integer.parseInt(id), categoriaDispositivo, estado, descripcion, marca);
			return("El dispositivo se ha actualizado correctamente.");
		} catch (HibernateGettingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateUpdatingException e) {
			e.printStackTrace();
		}
		return("Hubo un error al actualizar el dispositivo.");
	}
	
	/**
	 * Metodo para eliminar dispositivos
	 * Se puede acceder a traves de 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("eliminar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String eliminar(@PathParam("id")String id){
		try {
			dispositivoBL.eliminar(Integer.parseInt(id));
			return("El dispositivo se eliminó correctamente.");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HibernateDeletingException e) {
			e.printStackTrace();
		} catch (HibernateGettingException e) {
			e.printStackTrace();
		}
		return("Hubo un error al eliminar el dispositivo.");
	}
}

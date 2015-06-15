package ws.prestamo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.Dispositivo;
import exception.HibernateGettingException;
import logicaNegocio.DispositivoBL;
import ws.dto.DispositivoWS;

/**
 * 
 * @author Camilo Rivera
 *
 */
@Path("dispositivo")
@Component
public class DispostivoService {

	/**
	 * Bean inyectado por Spring con el que accedemos a los métodos de los dispositivos declarados en la lógica del negocio
	 */
	@Autowired
	private DispositivoBL dispositivoBL;
	
	
	/**
	 * Método que retorna todos los usuarios.
	 * @return 
	 * @throws RemoteException
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoWS> obtener() throws RemoteException{
		List<Dispositivo> dispositivos = null;
		List<DispositivoWS> listaDispositivos = new ArrayList<DispositivoWS>();
		
		try{
			dispositivos = dispositivoBL.obtenerDispositivos();
			
			for(Dispositivo dispositivo : dispositivos){
				DispositivoWS dispositivoWS = new DispositivoWS();
				dispositivoWS.setCategoria(dispositivo.getCategoria());
				dispositivoWS.setDescripcion(dispositivo.getDescripcion());
				dispositivoWS.setEstado(dispositivo.getEstado());
				dispositivoWS.setMarca(dispositivo.getMarca());
				dispositivoWS.setId(dispositivo.getId());
				listaDispositivos.add(dispositivoWS);
			}
		}catch(HibernateGettingException ex){
			throw new RemoteException(ex.getMessage());
		}
		return listaDispositivos;
		
	}
}

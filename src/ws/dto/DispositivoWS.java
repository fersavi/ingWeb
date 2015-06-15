package ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

import dto.CategoriaDispositivo;

/*
 * DTO en el cual guardamos solo la informaci�n que queremos retornar a traves del WS
 */
@XmlRootElement
public class DispositivoWS {
	private int id; //Id del dispositivo
	private CategoriaDispositivo categoria; //Categor�a del dispositivo
	private String estado; //prestado o disponibles
	private String descripcion; //Descripci�n del dispositivo
	private String marca; //Marca del dispositivo
	
	/*
	 * Obtener id del dispositivo
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Asignar id al dispositivo
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Obtener categoria del dispositivo
	 */
	public CategoriaDispositivo getCategoria() {
		return categoria;
	}
	
	/*
	 * Asignar categor�a al dispositivo
	 */
	public void setCategoria(CategoriaDispositivo categoria) {
		this.categoria = categoria;
	}
	
	/*
	 * Obtener estado del dispositivo
	 */
	public String getEstado() {
		return estado;
	}
	
	/*
	 * Asignar estado al dispositivo
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/*
	 * Obtener descripci�n del dispositivo
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/*
	 * Asignar descripci�n al dispositivo
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/*
	 * Obtener marca del dispositivo
	 */
	public String getMarca() {
		return marca;
	}
	
	/*
	 * Asignar marca al dispositivo
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
}

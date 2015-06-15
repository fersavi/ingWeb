package ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

import dto.UsuarioKey;
/**
 * Anotación que nos permite convertir  este DTO en un objeto JSON
 * */


/**
 * Este DTO  encapsula  unicamente los datos  del usuario que maneja el servicio web
 * @author Fernando Sanabria V correo: fer.savi1326@gmail.com
 * */
@XmlRootElement
public class UsuarioWS {
	/**
	 * Contiene tipo y número de cedula del usuario
	 * */
	private UsuarioKey id;
	
	/**
	 * Nombre del usuario
	 * */
	private String nombre;
	/**
	 * Apellido del usuario
	 * */
	private String apellido;
	
	/**
	 * Dirección de correo electrónico del usuario
	 * */
	private String correo;
	
	
	/**
	 * Dirección residencia del usuario
	 * */
	private String direccion;
	

	/**
	 * Telefono fijo  del usuario
	 * */
	private String telefono;
	
	/**
	 * Telefono movil  del usuario
	 * */
	private String celular;
	
	
	public UsuarioKey getId() {
		return id;
	}
	public void setId(UsuarioKey id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
}

package modelo;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable { 

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	
	private String nombre;
	private String apellido;
	private String email;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(Usuario u) {
		this.email = u.getEmail();
		this.apellido = u.getApellido();
		this.id = u.getId();
		this.nombre = u.getNombre();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString () {
	   return nombre + " " + apellido + " − " + email;
	}

}



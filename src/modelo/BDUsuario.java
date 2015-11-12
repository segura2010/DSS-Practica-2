package modelo;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BDUsuario {

	private static final String PERSISTENCE_UNIT_NAME = "usuario";
	
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	
	public void insertar(Usuario u)
	{}
	
	public void eliminar(Usuario u)
	{}
	
	public void actualizar(Usuario u)
	{}
	
	public Usuario seleccionarUsuario(String email)
	{
		Usuario u = null;
		
		return u;
	}
	
	public boolean existeEmail(String email)
	{
		return seleccionarUsuario(email) != null;
	}
	
	public List<Usuario> listarUsuarios(String email)
	{
		List<Usuario> usuarios = null;
		
		return usuarios;
	}
	
}

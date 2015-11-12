package modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {

	private static final String PERSISTENCE_UNIT_NAME = "Usuario";
	
	private static EntityManagerFactory factoria;
	
	public BDUsuario() {
		// TODO Auto-generated constructor stub
		try{
			factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void insertar(Usuario u)
	{
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void eliminar(Usuario u)
	{
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();
		em.remove(u);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void actualizar(Usuario u)
	{}
	
	public Usuario seleccionarUsuario(String email)
	{
		EntityManager em = factoria.createEntityManager();
		
		Usuario u = null;
		
		Query q = em.createQuery("select u from Usuario u where u.email =:arg1");
		q.setParameter("arg1", email);
		
		if(q.getResultList().size() > 0)
		{
			u = (Usuario) q.getResultList().get(0);
		}
		
		return u;
	}
	
	public boolean existeEmail(String email)
	{
		return seleccionarUsuario(email) != null;
	}
	
	public List<Usuario> listarUsuarios()
	{
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("select u from Usuario u");
		
		return q.getResultList();
	}
	
}

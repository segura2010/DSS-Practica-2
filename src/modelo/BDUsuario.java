package modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BDUsuario {

	private static final String PERSISTENCE_UNIT_NAME = "practica2";
	
	private EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	
	public BDUsuario() {
		// TODO Auto-generated constructor stub
		//factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public boolean insertar(Usuario u)
	{
		EntityManager em = factoria.createEntityManager();
		
		if(existeEmail(u.getEmail()))
		{
			em.close();
			return false;
		}
		
		Usuario mergeUsuario = em.merge(u);
		em.getTransaction().begin();
		em.persist(mergeUsuario);
		em.flush();
		em.getTransaction().commit();
		em.close();
		
		return true;
	}
	
	public void eliminar(Usuario u)
	{
		EntityManager em = factoria.createEntityManager();
		Usuario mergeUsuario = em.merge(u);
		em.getTransaction().begin();
		em.remove(mergeUsuario);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void actualizar(Usuario u)
	{
		EntityManager em = factoria.createEntityManager();
		Usuario mergeUsuario = em.merge(u);
		em.getTransaction().begin();
		em.persist(mergeUsuario);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
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
		try{
			EntityManager em = factoria.createEntityManager();
			em.getTransaction().begin();
			Query q = em.createQuery("select u from Usuario u");
			em.getTransaction().commit();
			
			List<Usuario> usuarios = (List<Usuario>) q.getResultList();
			
			em.close();
			
			return usuarios;
		}catch(Exception e){
			
			EntityManager em = factoria.createEntityManager();
			Query q = em.createQuery("select u from Usuario u");
			
			List<Usuario> usuarios = (List<Usuario>) q.getResultList();
			
			return usuarios;
		}
	}
	
}

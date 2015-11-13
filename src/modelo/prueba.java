package modelo;



import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.BDUsuario;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BDUsuario bd = new BDUsuario();
		
		Usuario u = new Usuario();
		u.setApellido("jj");
		u.setNombre("bb");
		u.setEmail("bbb");
		bd.insertar(u);
		
		List<Usuario> l = bd.listarUsuarios();
		for(int i=0;i<l.size();i++)
		{
			System.out.println(l.get(i));
		}

	}

}

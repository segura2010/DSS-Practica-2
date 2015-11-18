package comunicacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.BDUsuario;
import modelo.Usuario;

public class ListaCorreosServlet extends HttpServlet {
	
	// crea el objeto que controla la BD
	private BDUsuario usuarioControlador = new BDUsuario();
	
	public ListaCorreosServlet() {
		// TODO Auto-generated constructor stub
		//usuarioControlador = new BDUsuario();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		// Set response content type
		
		
		List<Usuario> usuarios = usuarioControlador.listarUsuarios();
		
		  resp.setContentType("text/html");
		
		  // Actual logic goes here.
		  PrintWriter out = resp.getWriter();
		  out.println("<h1>Lista de correo: </h1>");
		  
		  for (int i = 0; i < usuarios.size(); i++)
		  {
			  out.println(usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellido() + " " + usuarios.get(i).getEmail() + "<br>");
		  }
		  
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		
		String action = req.getParameter("action");
		//System.out.println("Accion: " + action);
		
		ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
		
		boolean ok = true;
		
		switch (action) {
		case "actualizarUsuario":
			if(usuarioControlador.existeEmail(req.getParameter("email")))
			{
				actualizaUsuario(req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("email"));
				
				out.writeObject("0");
				out.flush();
				out.close();
			}
			else
			{
				out.writeObject("El usuario no existe!");
				out.flush();
				out.close();
			}
			break;
			
		case "aniadirUsuario":
			ok = aniadirUsuario(req.getParameter("nombre"), req.getParameter("apellido"), req.getParameter("email"));
			
			if(ok)
			{
				out.writeObject("0");
			}
			else
			{
				out.writeObject("Ya existe un usuario con ese email!");
			}
			
			out.flush();
			out.close();
			break;
			
		case "listarUsuarios":
			// llamo a listar para que me escriba los usuarios
			
			List<Usuario> usuarios = usuarioControlador.listarUsuarios();
			out.writeObject(usuarios);
			out.flush();
			out.close();
			break;
			
		case "eliminarUsuario":
			if(usuarioControlador.existeEmail(req.getParameter("email")))
			{
				Usuario u = usuarioControlador.seleccionarUsuario(req.getParameter("email"));
				usuarioControlador.eliminar(u);
				
				out.writeObject("0");
				out.flush();
				out.close();
			}
			else
			{
				out.writeObject("El usuario no existe!");
				out.flush();
				out.close();
			}
			break;

		default:
			break;
		}
		
	}
	
	
	private void actualizaUsuario(String nombre, String apellido, String email)
	{
		Usuario u = usuarioControlador.seleccionarUsuario(email);
		u.setApellido(apellido);
		u.setNombre(nombre);
		u.setEmail(email);
		usuarioControlador.actualizar(u);
	}
	
	private boolean aniadirUsuario(String nombre, String apellido, String email)
	{
		Usuario u = new Usuario();
		u.setApellido(apellido);
		u.setNombre(nombre);
		u.setEmail(email);
		return usuarioControlador.insertar(u);
	}
	
	private void listarUsuarios(HttpServletResponse resp) throws IOException
	{
		List<Usuario> usuarios = usuarioControlador.listarUsuarios();
		ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
		out.writeObject(usuarios);
		out.flush();
		out.close();
	}
	
}

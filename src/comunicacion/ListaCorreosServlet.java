package comunicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.*;

public class ListaCorreosServlet extends HttpServlet {
	
	// crea el objeto que controla la BD
	BDUsuario usuarioControlador = new BDUsuario();

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
			  out.println(usuarios.get(i).getEmail());
		  }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		resp.getWriter().println("Hola");
	}
	
}

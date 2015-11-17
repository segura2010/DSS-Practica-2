package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Usuario;

public class ClienteTexto {
	
	// url a mi servlet
	public static String servletURL = "http://localhost:8080/Practica2_LuisAlbertoSeguraDelgado/ListaCorreosServlet";

	// Buffers de lectura desde teclado
	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader (isr);
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String entrada = "";
		do{
			try {
				mostrarMenu();
				entrada = br.readLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch (entrada) {
			case "0":
				System.out.println("Adios!");
				System.exit(0);
				break;
				
			case "1":
				listarUsuarios();
				break;
			
			case "2":
				solicitarDatosUsuarioAniadir();
				break;
				
			case "3":
				solicitarDatosUsuarioEditar();
				break;
			
			case "4":
				solicitarDatosUsuarioEliminar();
				break;

			default:
				break;
			}
			
		}while(!entrada.equals("0"));
		
	}

	
	public static void mostrarMenu()
	{
		System.out.println("\t1. Lista Usuarios");
		System.out.println("\t2. Añadir Usuario");
		System.out.println("\t3. Modificar Usuario");
		System.out.println("\t4. Eliminar Usuario");
		System.out.println("\t0. SALIR");
		System.out.print("Elige la opción deseada:");
	}
	
	public static void solicitarDatosUsuarioAniadir()
	{
		try {
			System.out.println("Nombre:");
			String nombre = br.readLine();
			
			System.out.println("Apellido:");
			String apellido = br.readLine();
			
			System.out.println("Email:");
			String email = br.readLine();
			
			aniadirUsuario(nombre, apellido, email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void solicitarDatosUsuarioEditar()
	{
		try {
			System.out.println("Nombre:");
			String nombre = br.readLine();
			
			System.out.println("Apellido:");
			String apellido = br.readLine();
			
			System.out.println("Email:");
			String email = br.readLine();
			
			editarUsuario(nombre, apellido, email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void solicitarDatosUsuarioEliminar()
	{
		try {
			
			System.out.println("Email:");
			String email = br.readLine();
			
			eliminarUsuario(email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void listarUsuarios()
	{
		// preparamos los parametros necesarios
		Map<String,String> parametros = new HashMap<String, String>();
		parametros.put("action", "listarUsuarios");
		
		try {
			// hacemos la peticion
			ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(servletURL, parametros));
			
			// me quedo con la lista de usuarios que me devuelve
			List<Usuario> usuarios = (List<Usuario>) respuesta.readObject();
			
			// Muestro los usuarios del sistema
			System.out.println("----- LISTA USUARIOS -----");
			for (Usuario usuario : usuarios)
			{
				System.out.println(usuario.getNombre() + "\t\t\t" +usuario.getApellido() + "\t\t\t" + usuario.getEmail());
			}
			System.out.println("----- -------------- -----");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void aniadirUsuario(String nombre, String apellido, String email)
	{
		// preparamos los parametros necesarios
		Map<String,String> parametros = new HashMap<String, String>();
		parametros.put("action", "aniadirUsuario");
		parametros.put("nombre", nombre);
		parametros.put("apellido", apellido);
		parametros.put("email", email);
		
		try {
			// hacemos la peticion
			ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(servletURL, parametros));
			
			// me quedo con la respuesta que me devuelve
			String mensaje = (String) respuesta.readObject();
			
			switch (mensaje) {
			case "0":
				// todo ha salido bien
				System.out.println("Usuario creado satisfactoriamente!");
				break;

			default:
				// algo ha salido mal..
				System.out.println(mensaje);
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void editarUsuario(String nombre, String apellido, String email)
	{
		// preparamos los parametros necesarios
		Map<String,String> parametros = new HashMap<String, String>();
		parametros.put("action", "actualizarUsuario");
		parametros.put("nombre", nombre);
		parametros.put("apellido", apellido);
		parametros.put("email", email);
		
		try {
			// hacemos la peticion
			ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(servletURL, parametros));
			
			// me quedo con la respuesta que me devuelve
			String mensaje = (String) respuesta.readObject();
			
			switch (mensaje) {
			case "0":
				// todo ha salido bien
				System.out.println("Usuario editado satisfactoriamente!");
				break;

			default:
				// algo ha salido mal..
				System.out.println(mensaje);
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void eliminarUsuario(String email)
	{
		// preparamos los parametros necesarios
		Map<String,String> parametros = new HashMap<String, String>();
		parametros.put("action", "eliminarUsuario");
		parametros.put("email", email);
		
		try {
			// hacemos la peticion
			ObjectInputStream respuesta = new ObjectInputStream(realizarPeticionPost(servletURL, parametros));
			
			// me quedo con la respuesta que me devuelve
			String mensaje = (String) respuesta.readObject();
			
			switch (mensaje) {
			case "0":
				// todo ha salido bien
				System.out.println("Usuario eliminado satisfactoriamente!");
				break;

			default:
				// algo ha salido mal..
				System.out.println(mensaje);
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static InputStream realizarPeticionPost(String urlString, Map<String,String> parametros)
	{
		String cadenaParametros = "";
		boolean primerPar = true;
		for (Map.Entry<String, String> entry : parametros.entrySet())
		{
			// Para cada parametro, me quedo con el nombre del parametro y su valor y lo
			// pongo en el formato correcto para realizar la peticion
			// parametro1=valor1&parametro2=valor2&...
			if (!primerPar)
			{
				cadenaParametros += "&";
			}
			else
			{
				primerPar = false;
			}
		    String parDeParametro = URLEncoder.encode(entry.getKey()) + "=" +URLEncoder.encode(entry.getValue()); //String.format("%s=%s", URLEncoder.encode(entry.getKey()), URLEncoder.encode(entry.getValue()));
		    cadenaParametros += parDeParametro;
		}
		try
		{
			URL url = new URL(urlString); // peticion a la url que me indican
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setUseCaches(false);
			conexion.setRequestMethod("POST"); // mediante POST
			conexion.setDoOutput(true);
			
			// preparamos el outputstream
			OutputStream output = conexion.getOutputStream();
			
			// envio los datos
			output.write(cadenaParametros.getBytes()); // envio los datos
			output.flush();
			output.close();
			
			// Devuelvo el input stream para que se utilice para recuperar la respuesta del servidor
			return conexion.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

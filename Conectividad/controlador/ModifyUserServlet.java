package controlador;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.FaccadeCL;
import modelo.datos.*;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModifyUserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String error = "";
		
		String nick = req.getParameter("nick");
		if (nick != null){
			if (!nick.trim().equals(new String("")))
				nick = nick.trim();
			else
				error += "\tEl nick no tiene valor\n";
		} else
			error += "\tFalta el parametro nick\n";
		
		
		String password = req.getParameter("password");
		if (password != null){
			if (!password.trim().equals(new String("")))
				password = password.trim();
			else
				error += "\tLa contrasenya no tiene valor\n";
		} else
			error += "\tFalta el parametro password\n";
		
		
		String new_password = req.getParameter("new_password");
		if (new_password != null){
			if (!new_password.trim().equals(new String("")))
				new_password = new_password.trim();
			else
				error += "\tLa nueva contrasenya no tiene valor\n";
		}
		
		String nombre = req.getParameter("nombre");
		if (nombre != null){
			if (!nombre.trim().equals(new String("")))
				nombre = nombre.trim();
			else
				error += "\tEl nombre no tiene valor\n";
		}
		
		String apellidos = req.getParameter("apellidos");
		if (apellidos != null){
			if (!apellidos.trim().equals(new String("")))
				apellidos = apellidos.trim();
			else
				error += "\tLos apellidos no tienen valor\n";
		}
		
		
		String valoracion = req.getParameter("valoration");
		if (valoracion != null){
			if (!valoracion.trim().equals(new String("")))
				valoracion = valoracion.trim();
			else
				error += "\tLa valoracion no tienen valor\n";
		}	
		
		
		if (error.equals("")){
			UsuariosVO usuario = new UsuariosVO(nick, null, null, new BigDecimal(0),
												password, null, null, null);
			UsuariosVO usr_destino = new UsuariosVO(nick, nombre, apellidos,
													new BigDecimal(valoracion),
													new_password, null, null, null);
			try{
				FaccadeCL fachada = new FaccadeCL ();
				fachada.updateUser(usuario, usr_destino);
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				e.printStackTrace(System.err);
			}
			resp.sendError(100);
		}else{
			resp.sendError(400, "Errores encontrados:\n" + error);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}

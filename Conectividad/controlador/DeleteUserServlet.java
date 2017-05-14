package cambia_libros.controlador;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cambia_libros.modelo.FaccadeCL;
import cambia_libros.modelo.datos.*;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DeleteUserServlet() {
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
		
		
		if (error.equals("")){
			System.err.println("[DeletUser]: No se hay errores en los parametros");
			UsuariosVO usuario = new UsuariosVO(nick, "", "", new BigDecimal(0),
												password, null, null, null);
			try{
				FaccadeCL fachada = new FaccadeCL ();
				fachada.deleteUser(usuario);
				resp.sendError(100);
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				System.err.print("[DeletUser]: ");
				e.printStackTrace(System.err);
			}
		}else{
			System.err.println("[DeletUser]: Errores encontrados:\n" + error);
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

package controlador;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.FaccadeCL;
import datos.*;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsertBookServlet() {
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
		
		
		String tittle = req.getParameter("tittle");
		if (tittle != null){
			if (!tittle.trim().equals(new String("")))
				tittle = tittle.trim();
			else
				error += "\tEl titulo no tiene valor\n";
		} else
			error += "\tFalta el parametro titulo\n";
		
		
		String author = req.getParameter("author");
		if (author != null){
			if (!author.trim().equals(new String("")))
				author = author.trim();
			else
				error += "\tEl author no tiene valor\n";
		} else
			error += "\tFalta el parametro author\n";
		
		
		String location = req.getParameter("location");
		if (location != null){
			if (!location.trim().equals(new String("")))
				location = location.trim();
			else
				error += "\tLa localizacion no tiene valor\n";
		} else
			error += "\tFalta el parametro location\n";
		
		
		String description = req.getParameter("description");
		if (description != null){
			description = description.trim();
		} else
			error += "\tFalta el parametro description\n";
		
		
		if (error.equals("")){
			UsuariosVO usuario = new UsuariosVO(nick, null, null, new BigDecimal(0),
												password, null, null, null);
			LibrosVO libro = new LibrosVO(-1, usuario, tittle, author, description, location);
			try{
				FaccadeCL fachada = new FaccadeCL ();
				fachada.insertBook(libro);
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
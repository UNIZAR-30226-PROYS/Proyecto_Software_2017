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
public class AddFavUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddFavUserServlet() {
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
		
		
		String fav_nick = req.getParameter("fav_nick");
		if (fav_nick != null){
			if (!fav_nick.trim().equals(new String("")))
				fav_nick = fav_nick.trim();
			else
				error += "\tEl usuario favorito no tiene valor\n";
		} else
			error += "\tFalta el parametro fav_nick\n";
		
		
		if (error.equals("")){
			System.err.println("[AddFavUser]: No hay error en los parametros");
			UsuariosVO usuario = new UsuariosVO(nick, null, null, new BigDecimal(0),
												null, null, null, null);
			UsuariosVO usr_favorito = new UsuariosVO(fav_nick, null, null,
													new BigDecimal(0),
													null, null, null, null);
			Lista_usuariosVO fav = new Lista_usuariosVO(0, usuario, usr_favorito);
			try{
				FaccadeCL fachada = new FaccadeCL ();
				fachada.addFavUser(fav);
				resp.sendError(100);
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				System.err.print("[AddFavUser]: ");
				e.printStackTrace(System.err);
			}
		}else{
			System.err.println("[AddFavUser]: Errores encontrados:\n" + error);
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
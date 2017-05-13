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
public class AddFavBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddFavBookServlet() {
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
		
		
		String id_book_s = req.getParameter("id_book");
		int id_book = -1;
		if (id_book_s != null){
			if (!id_book_s.trim().equals(new String(""))) {
				id_book_s = id_book_s.trim();
				id_book = Integer.parseInt(id_book_s);
			} else
				error += "\tEl libro favorito no tiene valor\n";
		} else
			error += "\tFalta el parametro id_book\n";
		
		
		if (error.equals("")){
			System.err.println("[AddFavBook]: No hay error en los parametros");
			UsuariosVO usuario = new UsuariosVO(nick, null, null, new BigDecimal(0),
												null, null, null, null);
			LibrosVO libro_fav = new LibrosVO(id_book, null, null, null, null, null);
			Lista_librosVO fav = new Lista_librosVO(0, libro_fav, usuario);
			try{
				FaccadeCL fachada = new FaccadeCL ();
				fachada.addFavBook(fav);
				resp.sendError(100);
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				System.err.print("[AddFavBook]: ");
				e.printStackTrace(System.err);
			}
		}else{
			System.err.println("[AddFavBook]: Errores encontrados:\n" + error);
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
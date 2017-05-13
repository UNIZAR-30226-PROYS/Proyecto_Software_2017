package cambia_libros.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cambia_libros.controlador.datos.ListaLibros;
import cambia_libros.modelo.FaccadeCL;
import cambia_libros.modelo.datos.LibrosVO;
import cambia_libros.modelo.datos.UsuariosVO;

public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchBookServlet() {
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String error = "";
		
		String nick = req.getParameter("nick");
		if (nick != null){
			if (!nick.trim().equals(new String("")))
				nick = nick.trim();
			else
				error += "\tEl nick no tiene valor\n";
		} else
			error += "\tFalta el parametro nick\n";
		
		String tittle = req.getParameter("tittle");
		if (tittle != null){
			if (!tittle.trim().equals(new String("")))
				tittle = tittle.trim();
			else
				error += "\tEl titulo no tiene valor\n";
		}
		
		String author = req.getParameter("author");
		if (author != null){
			if (!author.trim().equals(new String("")))
				author = author.trim();
			else
				error += "\tEl autor no tiene valor\n";
		}
		
		String location = req.getParameter("location");
		if (location != null){
			if (!location.trim().equals(new String("")))
				location = location.trim();
			else
				error += "\tLa localizacion no tiene valor\n";
		}
		
		String nick_b = req.getParameter("nick_b");
		if (nick_b != null){
			if (!nick_b.trim().equals(new String("")))
				nick_b = nick_b.trim();
			else
				error += "\tEl duenyo no tiene valor\n";
		}
		
		if (error.equals("")){
			UsuariosVO usuario = new UsuariosVO(nick_b, null, null, new BigDecimal(0),
												null, null, null, null);
			UsuariosVO asker = new UsuariosVO(nick, null, null, new BigDecimal(0),
												null, null, null, null);
			
			LibrosVO libro_buscado = new LibrosVO(-1, usuario, tittle, author, null, location );
			
			try{
				System.err.println("[SearchBook]: No hay error en parametros");
				FaccadeCL fachada = new FaccadeCL ();
				ListaLibros busqueda = fachada.searchBook(asker, libro_buscado);
				
				resp.setContentType("text/xml");
				PrintWriter out = resp.getWriter();
				out.println("<busqueda nick=" + nick + ">");
				out.print(busqueda.toXML());
				out.println("</busqueda>");
				resp.sendError(100);
				
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				System.err.print("[SearchBook]: ");
				e.printStackTrace(System.err);
			}
		}else{
			System.err.println("[SearchBook]: Errores encontrados:\n" + error);
			resp.sendError(400, "Errores encontrados:\n" + error);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

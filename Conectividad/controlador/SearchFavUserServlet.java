package cambia_libros.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cambia_libros.controlador.datos.ListaUsuarios;
import cambia_libros.modelo.FaccadeCL;
import cambia_libros.modelo.datos.UsuariosVO;

public class SearchFavUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchFavUserServlet() {
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
		
		if (error.equals("")){
			System.err.println("[SearchFavUser]: No hay error en parametros");
			UsuariosVO usuario = new UsuariosVO(nick, null, null, new BigDecimal(0),
												null, null, null, null);
			
			try{
				FaccadeCL fachada = new FaccadeCL ();
				ListaUsuarios busqueda = fachada.searchFavUser(usuario);
				
				resp.setContentType("text/xml");
				PrintWriter out = resp.getWriter();
				out.println("<busqueda nick=" + nick + ">");
				out.print(busqueda.toXML());
				out.println("</busqueda>");
				resp.sendError(100);
				
			}catch (Exception e){
				resp.sendError(500, e.getMessage());
				System.err.print("[SearchFavUser]: ");
				e.printStackTrace(System.err);
			}
		}else{
			System.err.println("[SearchFavUser]: Errores encontrados:\n" + error);
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

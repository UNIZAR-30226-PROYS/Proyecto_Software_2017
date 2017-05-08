package controlador.datos;

import java.util.ArrayList;

public class ListaLibros {
	private ArrayList<Libros> libros;
	
	public ListaLibros(ArrayList<Libros> libros, boolean fav){
		this.libros = new ArrayList<Libros>(libros);
	}
	
	public String toXML(){
		String resultado = "<libreria>";
		for (Libros l : libros){
			resultado += "<libro>\n"
					+ "\t<id_libro>"+ l.getIdLibros() +"</id_libro>\n"
					+ "\t<titulo>" + l.getTitulo() + "</titulo>\n"
					+ "\t<autor>" + l.getAutor() + "</autor>\n"
					+ "\t<usuario>" + l.getNick_duenyo() + "</usuario>\n"
					+ "\t<localizacion>" + l.getLocalizacion()
					+ "</localizacion>\n"
					+ "\t<favorito>" + l.getFavorito() + "</favorito>\n"
					+ "</libro>\n";
		}
		resultado += "</libreria>";
		return resultado;
	}
}

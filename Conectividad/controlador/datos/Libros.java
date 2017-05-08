package controlador.datos;

import modelo.datos.LibrosVO;

public class Libros{
	private boolean fav;
	private LibrosVO libro;
	
	public Libros(LibrosVO libro, boolean fav){
		this.libro = libro;
		this.fav = fav;
	}
	
	public int getIdLibros(){
		return libro.getIdLibros();
	}
	
	public String getTitulo(){
		return libro.getTitulo();
	}
	
	public String getAutor(){
		return libro.getAutor();
	}
	
	public String getLocalizacion(){
		return libro.getLocalizacion();
	}
	
	public String getNick_duenyo(){
		return libro.getNick_duenyo().getNickname();
	}
	
	public boolean getFavorito(){
		return fav;
	}
	public String toXML(){
		return "<libro>\n"
			+ "\t<id_libro>"+ libro.getIdLibros() +"</id_libro>\n"
			+ "\t<titulo>" + libro.getTitulo() + "</titulo>\n"
			+ "\t<autor>" + libro.getAutor() + "</autor>\n"
			+ "\t<usuario>" + libro.getNick_duenyo().getNickname() + "</usuario>\n"
			+ "\t<localizacion>" + libro.getLocalizacion()
			+ "</localizacion>\n"
			+ "\t<descripcion>" + libro.getDescripcion()
			+ "</descripcion>\n"
			+ "\t<favorito>" + fav + "</favorito>\n"
			+ "</libro>\n";
	}
}
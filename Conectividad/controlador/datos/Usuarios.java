package controlador.datos;

import modelo.datos.UsuariosVO;

public class Usuarios{
	private boolean fav;
	private UsuariosVO user;
	
	public Usuarios(UsuariosVO user, boolean fav){
		this.user = user;
		this.fav = fav;
	}
	
	public String toXML(){
		return "<usuario>\n"
			+ "\t<nick>"+ user.getNickname() +"</nick>\n"
			+ "\t<nombre>" + user.getNombre() + "</nombre>\n"
			+ "\t<apellidos>" + user.getApellido() + "</apellidos>\n"
			+ "\t<valoracion>" + user.getValoracion().toString() 
			+ "</valoracion>\n"
			+ "\t<favorito>" + fav + "</favorito>\n"
			+ "</usuario>\n";
	}
}
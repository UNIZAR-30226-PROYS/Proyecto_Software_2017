package controlador.datos;

import java.util.ArrayList;

public class ListaUsuarios {
	private ArrayList<Usuarios> usuarios;
	
	public ListaUsuarios(ArrayList<Usuarios> usuarios, boolean fav){
		this.usuarios = new ArrayList<Usuarios>(usuarios);
	}
	
	public String toXML(){
		String resultado = "<gente>";
		for (Usuarios u : usuarios){
			resultado += u.toXML();
		}
		resultado += "</gente>";
		return resultado;
	}
}

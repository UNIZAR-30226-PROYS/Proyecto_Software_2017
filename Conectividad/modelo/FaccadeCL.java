package modelo;

import modelo.datos.*;
import controlador.datos.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Clase faccade para el proyecto de cambia libros 
 * (Proyecto Software Unizar 2017)
 */
public class FaccadeCL{
	
	/*
	 * Inserta un nuevo usuario en la base de datos con valor
	 * user
	 */
	public void insertUser(UsuariosVO user) throws SQLException {
		/*Connection connection = null;
		try{
		   connection = GestorDeConexionesBD.getConnection();
	       UsuarioDAO usuarioDAO = new UsuarioDAO();
		   usuarioDAO.insertarUsuario(user,connection);  
	       connection.close();  
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    } finally{
			connection.close();
		}  */
	}
	
	/*
	 * Actualiza un usuario existente en la base de datos, 
	 * acorde al valor de user
	 */
	public void updateUser(UsuariosVO user, UsuariosVO updated_user) throws SQLException {}
	
	/*
	 * Devuelve un usuario de la base de datos que concida con
	 * user
	 */
	public Usuarios getUser(UsuariosVO user, UsuariosVO user_s) throws SQLException{
		return null;
	}
	
	/* 
	 * Borra de la base de datos cuyo valor corresponda con user
	 */
	public void deleteUser(UsuariosVO user) throws SQLException{}
	
	/*
	 * Inserta un libro en la base de datos cuyo valor corresponda
	 * con el de book
	 */
	public void insertBook(LibrosVO book) throws SQLException{}
	
	/*
	 * Borra un libro de la base de datos cuyo valor corresponda 
	 * con el de book
	 */
	public void deleteBook(LibrosVO book) throws SQLException{}
	
	/*
	 * Devuelve un libro de la base de datos cuyo valor
	 * corresponda con el de book
	 */
	public Libros getBook(UsuariosVO user, LibrosVO book) throws SQLException{
		return null;
	}
	
	/*
	 * Devuelve una lista de libros de la base de datos 
	 * cuyos valores correspondan con el de book
	 */
	public ListaLibros searchBook(LibrosVO book) throws SQLException{
		return null;
	}
	
	/*
	 * Devuelve una lista de libros favoritos para el
	 * usuario user
	 */
	public ListaLibros searchFavBook(UsuariosVO user) throws SQLException{
		return null;
	}
	
	/*
	 * Devuelve una lista de usuarios favoritos para el
	 * usuario user
	 */
	public ListaUsuarios searchFavUser(UsuariosVO user) throws SQLException{
		return null;
	}
	
	/*
	 * Inserta un nuevo usuario favorito que corresponda con 
	 * favUsr
	 */
	public void addFavUser(Lista_usuariosVO favUsr) throws SQLException{}
	
	/*
	 * Borra un nuevo usuario favorito que corresponda con 
	 * favUsr
	 */
	public void deleteFavUser(Lista_usuariosVO favUsr) throws SQLException{}
	
	/*
	 * Inserta un nuevo libro favorito que corresoponda con 
	 * favBook
	 */
	public void addFavBook(Lista_librosVO favBook) throws SQLException{}
	
	/*
	 * Borra un nuevo libro favorito que corresoponda con 
	 * favBook
	 */
	public void deleteFavBook(Lista_librosVO favBook) throws SQLException{}
}
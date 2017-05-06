package modelo;

import datos.*;
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
	public void updateUser(UsuariosVO user) throws SQLException {}
	
	/*
	 * Devuelve un usuario de la base de datos que concida con
	 * user
	 */
	public UsuariosVO getUser(UsuariosVO user) throws SQLException{
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
	public LibrosVO getBook(LibrosVO book) throws SQLException{
		return null;
	}
	
	/*
	 * Devuelve una lista de libros de la base de datos 
	 * cuyos valores correspondan con el de book
	 */
	public ArrayList<LibrosVO> searchBook(LibrosVO book) throws SQLException{
		return new ArrayList<LibrosVO>();
	}
	
	/*
	 * Devuelve una lista de usuarios favoritos para el
	 * usuario user
	 */
	public ArrayList<UsuariosVO> searchFavUser(UsuariosVO user) throws SQLException{
		return new ArrayList<UsuariosVO>();
	}
	
	/*
	 * Inserta un nuevo usuario favorito que corresponda con 
	 * favUsr
	 */
	public void addFavUser(Lista_usuariosVO favUsr) throws SQLException{}
	
	/*
	 * Inserta un nuevo libro favorito que corresoponda con 
	 * favBook
	 */
	public void addFavBook(Lista_librosVO favBook) throws SQLException{}
}
package cambia_libros.modelo;

import java.sql.SQLException;

import cambia_libros.controlador.datos.*;
import cambia_libros.modelo.datos.*;

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
		System.out.println("Usuario insertado:\n" + user.getNickname()
							+ " nombre: " + user.getNombre() + " appel: " + user.getApellido()
							+ " contrasenya: " + user.getContrasenya());
		try{
	       UsuariosDAO usuarioDAO = new UsuariosDAO();
		   usuarioDAO.addUsuario(user);    
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Actualiza un usuario existente en la base de datos, 
	 * acorde al valor de user
	 */
	public void updateUser(UsuariosVO user, UsuariosVO updated_user) throws SQLException {
		System.out.println("Usuario cambiado:\n" + user.getNickname()
							+ " nombre: " + user.getNombre() + " appel: " + user.getApellido()
							+ " contrasenya: " + user.getContrasenya()
							+ "\n A: \n" + updated_user.getNickname()
							+ " nombre: " + updated_user.getNombre() + " appel: " + updated_user.getApellido()
							+ " contrasenya: " + updated_user.getContrasenya());
		try{
	       UsuariosDAO usuarioDAO = new UsuariosDAO();
	       UsuariosVO user_aux = usuarioDAO.getUsuario(user.getNickname());
	       if (user_aux.getContrasenya().equals(user.getContrasenya())){
	    	   usuarioDAO.updateUsuario(updated_user);  
	       }
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Devuelve un usuario de la base de datos que concida con
	 * user
	 */
	public Usuarios getUser(UsuariosVO user, UsuariosVO user_s) throws SQLException{
		System.out.println("Usuario buscado por " + user.getNickname() + ":\n" + user_s.getNickname()
		+ " nombre: " + user_s.getNombre() + " appel: " + user_s.getApellido());
		return null;
	}
	
	/* 
	 * Borra de la base de datos cuyo valor corresponda con user
	 */
	public void deleteUser(UsuariosVO user) throws SQLException{
		System.out.println("Borrado usuario: " + user.getNickname() + " pass: " + user.getContrasenya());
		try{
	       UsuariosDAO usuarioDAO = new UsuariosDAO();
	       UsuariosVO user_aux = usuarioDAO.getUsuario(user.getNickname());
	       if (user_aux.getContrasenya().equals(user.getContrasenya())){
	    	   usuarioDAO.deleteUsuario(user.getNickname());    
	       }
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Inserta un libro en la base de datos cuyo valor corresponda
	 * con el de book
	 */
	public void insertBook(UsuariosVO user, LibrosVO book) throws SQLException{
		System.out.println("Insertado libro: \n"
							+ "Titulo: " + book.getTitulo()
							+ " autor: " + book.getAutor()
							+ " loc: " + book.getLocalizacion()
							+ " duenyo: " + book.getNick_duenyo().getNickname()
							+ " descripcion: " + book.getDescripcion());
		try{
	       UsuariosDAO usuarioDAO = new UsuariosDAO();
	       LibrosDAO librosDAO = new LibrosDAO();
	       
	       UsuariosVO user_aux = usuarioDAO.getUsuario(user.getNickname());
	       if (user_aux.getContrasenya().equals(user.getContrasenya())){
	    	   librosDAO.addLibro(book); 
	       }
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Borra un libro de la base de datos cuyo valor corresponda 
	 * con el de book
	 */
	public void deleteBook(UsuariosVO user, LibrosVO book) throws SQLException{
		System.out.println("Borrado libro: " + book.getIdLibros());
		try{
	       UsuariosDAO usuarioDAO = new UsuariosDAO();
	       LibrosDAO librosDAO = new LibrosDAO();
	       
	       UsuariosVO user_aux = usuarioDAO.getUsuario(user.getNickname());
	       if (user_aux.getContrasenya().equals(user.getContrasenya())){
	    	   librosDAO.deleteLibro(book.getIdLibros()); 
	       }
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
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
	public ListaLibros searchBook(UsuariosVO user, LibrosVO book) throws SQLException{
		System.out.println(user.getNickname() + " busca libro " + book.getTitulo()
							+ " de " + book.getAutor() + " duenyo " 
							+ book.getNick_duenyo().getNickname() + " descripcion "
							+ book.getDescripcion() + " loc " + book.getLocalizacion());
		return null;
	}
	
	/*
	 * Devuelve una lista de libros favoritos para el
	 * usuario user
	 */
	public ListaLibros searchFavBook(UsuariosVO user) throws SQLException{
		System.out.println(user.getNickname() + " busca libros favoritos");
		return null;
	}
	
	/*
	 * Devuelve una lista de usuarios favoritos para el
	 * usuario user
	 */
	public ListaUsuarios searchFavUser(UsuariosVO user) throws SQLException{
		System.out.println(user.getNickname() + " busca usuarios favoritos");
		return null;
	}
	
	/*
	 * Inserta un nuevo usuario favorito que corresponda con 
	 * favUsr
	 */
	public void addFavUser(Lista_usuariosVO favUsr) throws SQLException{
		System.out.println(favUsr.getNickname_users().getNickname() + " anyade a favs a "
							+ favUsr.getUser_fav().getNickname());
		try{
	       Lista_usuariosDAO lista_usuariosDAO = new Lista_usuariosDAO();
	       lista_usuariosDAO.addLista_usuarios(favUsr); 
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Borra un nuevo usuario favorito que corresponda con 
	 * favUsr
	 */
	public void deleteFavUser(Lista_usuariosVO favUsr) throws SQLException{
		System.out.println(favUsr.getNickname_users().getNickname() + " borra de favs al user "
							+ favUsr.getUser_fav().getNickname());
		try{
	       Lista_usuariosDAO lista_usuariosDAO = new Lista_usuariosDAO();
	       lista_usuariosDAO.deleteLista_usuarios(favUsr.getId_list_users()); 
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}	
	
	/*
	 * Inserta un nuevo libro favorito que corresoponda con 
	 * favBook
	 */
	public void addFavBook(Lista_librosVO favBook) throws SQLException{
		System.out.println(favBook.getNickname() + " anyade a favs a "
							+ favBook.getId_libro().getIdLibros());
		try{
	       Lista_librosDAO lista_librosDAO = new Lista_librosDAO();
	       lista_librosDAO.addLista_libros(favBook); 
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
	
	/*
	 * Borra un nuevo libro favorito que corresoponda con 
	 * favBook
	 */
	public void deleteFavBook(Lista_librosVO favBook) throws SQLException{
		System.out.println(favBook.getNickname() + " borra de favs a "
				+ favBook.getId_libro().getIdLibros());
		try{
	       Lista_librosDAO lista_librosDAO = new Lista_librosDAO();
	       lista_librosDAO.deleteLista_libros(favBook.getId_list_libros());
	    } catch (Exception e) {
	       e.printStackTrace(System.err);
	    }
	}
}
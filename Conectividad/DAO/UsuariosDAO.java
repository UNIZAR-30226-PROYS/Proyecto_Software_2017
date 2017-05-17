package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import VO.Usuarios;


public class UsuariosDAO {
	public void addUsuario(Usuarios tra){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addUsuario(session,tra);
        tx.commit();
        session.close();
	}
	
	public void addUsuario(Session session, Usuarios usu){
		Usuarios usuario= new Usuarios(
				usu.getNickname(),usu.getNombre(),
				usu.getApellido(),usu.getValoracion(),
				usu.getContrasenya(),usu.getLibros(),
				usu.getUsuarios_fav(),usu.getTransacciones_comprador());
		session.save(usuario);
	}
	
	public Usuarios getUsuario(int id){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Usuarios usuario = session.get(Usuarios.class, id);
        tx.commit();
        session.close();
        return usuario;
	}
	
	public int deleteUsuario(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Usuarios WHERE nickname = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();

        return rowCount;
	}
	
	public int updateUsuario(Usuarios usu){
		Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    String hql = "UPDATE Usuarios SET "
	    		+ "nombre = :nombre,"
	        	+ "Apellido = :apellido, "
	       		+ "valoracion = :valor, "
	       		+ "contrasenya = :cont"
	       		+ " WHERE nickname = :id";
	    Query query = session.createQuery(hql);
	    query.setParameter("id",usu.getNickname());
	    query.setParameter("nombre",usu.getNombre());
	    query.setParameter("apellido",usu.getApellido());
	    query.setParameter("valor",usu.getValoracion());
	    query.setParameter("cont",usu.getContrasenya());
	    int rowCount = query.executeUpdate();
	    System.out.println("Rows affected: " + rowCount);
	    tx.commit();
	    session.close();

	    return rowCount;
	}
	
	public List<Usuarios> searchUsuarios(Usuarios obj){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Usuarios> lista = session.createCriteria(Usuarios.class).add(Example.create(obj).excludeZeroes()).list();
        tx.commit();
        session.close();
        return lista;
	}
}

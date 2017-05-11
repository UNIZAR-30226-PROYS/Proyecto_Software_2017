package DAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import VO.Lista_libros;

public class Lista_librosDAO {
	public void addLista_libros(Lista_libros tra){
		Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    addLista_libros(session,tra);
	    tx.commit();
        session.close();
	}
		
	public void addLista_libros(Session session, Lista_libros lis){
		Lista_libros lista= new Lista_libros(
			lis.getId_lista_libros(),lis.getId_libro(),
			lis.getNickname());
		session.save(lista);
	}
		
	public Lista_libros getLista_libros(int id){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Lista_libros lista= session.get(Lista_libros.class, id);
        tx.commit();
        session.close();
        return lista;
	}
	
	public int deleteLista_libros(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Lista_libros WHERE id_lista_libros = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
	
	public int updateLista_libros(Lista_libros lis){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "UPDATE Lista_libros SET "
       		+ "id_libro = :idLibro,"
       		+ "nickname = :nick, "
      		+ " WHERE id_lista_users = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",lis.getId_lista_libros());
        query.setParameter("nick",lis.getNickname());
        query.setParameter("idLibro",lis.getId_libro());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}

}

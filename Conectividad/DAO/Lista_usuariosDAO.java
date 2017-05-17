package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import VO.Lista_usuarios;


public class Lista_usuariosDAO {
	public void addLista_usuarios(Lista_usuarios tra){
		Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    addLista_usuarios(session,tra);
	    tx.commit();
        session.close();
	}
		
	public void addLista_usuarios(Session session, Lista_usuarios lis){
		Lista_usuarios lista= new Lista_usuarios(
			lis.getId_lista_users(),lis.getNickname_users()
			,lis.getUser_fav());
		session.save(lista);
	}
		
	public Lista_usuarios getLista_usuarios(int id){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Lista_usuarios lista= session.get(Lista_usuarios.class, id);
        tx.commit();
        session.close();
        return lista;
	}
	
	public int deleteLista_usuarios(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Lista_usuarios WHERE id_lista_users = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
	
	public int updateLista_usuarios(Lista_usuarios lis){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "UPDATE Lista_usuarios SET "
       		+ "nickname_users = :nick,"
       		+ "user_fav = :user, "
      		+ " WHERE id_lista_users = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",lis.getId_lista_users());
        query.setParameter("nick",lis.getNickname_users());
        query.setParameter("user",lis.getUser_fav());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
	
	public List<Lista_usuarios> searchLista_usuarios(Lista_usuarios obj){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Lista_usuarios> lista = session.createCriteria(Lista_usuarios.class).add(Example.create(obj).excludeZeroes()).list();
        tx.commit();
        session.close();
        return lista;
	}
}

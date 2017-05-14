package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import VO.Transacciones;

public class TransaccionesDAO {
	public void addTransaccion(Transacciones tra){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addTransacciones(session,tra);
        tx.commit();
        session.close();
	}
	
	public void addTransaccion(Session session, Transacciones tran){
		Transacciones transacc= new Transacciones(
				tran.getIdtransacciones(),tran.getTitulo_libro(),
				tran.getNick_comprador(),tran.getNick_vendedor());
		session.save(transacc);
	}
	
	public Transacciones getTransaccion(int id){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Transacciones transacc = session.get(Transacciones.class, id);
        tx.commit();
        session.close();
        return transacc;
	}
	
	public int deleteTransaccion(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Transacciones WHERE idtransacciones = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();

        return rowCount;
	}
	
	public int updateTransaccion(Transacciones tran){
		Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    String hql = "UPDATE Transacciones SET "
	       + "titulo_libro = :titulo,"
	       + " WHERE idtransacciones = :id";
	    Query query = session.createQuery(hql);
	    query.setParameter("id",tran.getIdtransacciones());
	    query.setParameter("titulo",tran.getTitulo_libro());
	    int rowCount = query.executeUpdate();
	    System.out.println("Rows affected: " + rowCount);
	    tx.commit();
	    session.close();
	    return rowCount;
	}
}

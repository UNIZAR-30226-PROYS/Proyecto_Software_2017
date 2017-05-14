package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import VO.Libros;

public class LibrosDAO {
	public void addLibro(Libros lib){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addLibro(session,lib);
        tx.commit();
        session.close();
	}
	
	public void addLibro(Session session, Libros lib){
		Libros libro= new Libros(lib.getIdLibros(),lib.getNick_duenyo(),lib.getTitulo(), lib.getAutor(),
				lib.getDescripcion(), lib.getLocalizacion());
		session.save(libro);
	}
	
	public Libros getLibro(int id){
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Libros libro = session.get(Libros.class, id);
        tx.commit();
        session.close();
        return libro;
	}
	
	public int deleteLibro(int id){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Libros WHERE idLibros = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();

        return rowCount;
	}
	
	public int updateLibro(Libros lib){
	        Session session = SessionUtil.getSession();
	        Transaction tx = session.beginTransaction();
	        String hql = "UPDATE Libros SET nick_duenyo = :nick,"
	        		+ "titulo = :titulo, "
	        		+ "autor = :autor, "
	        		+ "descripcion = :desc, "
	        		+ "localizacion = :loc"
	        		+ " WHERE idLibros = :id";
	        Query query = session.createQuery(hql);
	        query.setParameter("id",lib.getIdLibros());
	        query.setParameter("nick",lib.getNick_duenyo());
	        query.setParameter("titulo",lib.getTitulo());
	        query.setParameter("autor",lib.getAutor());
	        query.setParameter("desc",lib.getDescripcion());
	        query.setParameter("loc",lib.getLocalizacion());
	        int rowCount = query.executeUpdate();
	        System.out.println("Rows affected: " + rowCount);
	        tx.commit();
	        session.close();

	        return rowCount;
	}
}

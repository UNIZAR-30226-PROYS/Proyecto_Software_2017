import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Lista_libros")
@Table(name = "Lista_libros")
public class Lista_libros implements Serializable {
	@Id
	@Column(name = "id_list_libros",nullable=false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id_list_libros;
	
	@Column(name = "id_libro",nullable=false)
	private int id_libro;
	
	@ManyToOne
	@JoinColumn(name = "nickname",nullable=false)
	private Usuarios nickname;
	
	public Lista_libros(){}
	
	public Lista_libros(int id_list_libros, int id_libro, Usuarios nickname) {
		super();
		this.id_list_libros = id_list_libros;
		this.id_libro = id_libro;
		this.nickname = nickname;
	}

	public int getId_list_libros() {
		return id_list_libros;
	}

	public void setId_list_libros(int id_list_libros) {
		this.id_list_libros = id_list_libros;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public Usuarios getNickname() {
		return nickname;
	}

	public void setNickname(Usuarios nickname) {
		this.nickname = nickname;
	}
	
}

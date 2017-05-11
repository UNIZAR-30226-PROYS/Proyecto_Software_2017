package VO;
import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Lista_usuarios")
@Table(name = "Lista_libros")
public class Lista_usuarios implements Serializable {
	@Id
	@Column(name = "id_lista_users",nullable=false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id_lista_users;
	
	@ManyToOne
	@JoinColumn(name = "nickname",nullable=false)
	private Usuarios nickname_users;
	
	@Column(name = "user_fav",nullable=false)
	private int user_fav;

	public Lista_usuarios(){}
	
	public Lista_usuarios(int id_list_users, Usuarios nickname_users, int user_fav) {
		super();
		this.id_lista_users = id_list_users;
		this.nickname_users = nickname_users;
		this.user_fav = user_fav;
	}

	public int getId_lista_users() {
		return id_lista_users;
	}

	public void setId_lista_users(int id_list_users) {
		this.id_lista_users = id_list_users;
	}

	public Usuarios getNickname_users() {
		return nickname_users;
	}

	public void setNickname_users(Usuarios nickname_users) {
		this.nickname_users = nickname_users;
	}

	public int getUser_fav() {
		return user_fav;
	}

	public void setUser_fav(int user_fav) {
		this.user_fav = user_fav;
	}
	
	
}

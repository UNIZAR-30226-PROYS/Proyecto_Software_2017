package VO;
import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Libros")
@Table(name = "Libros")
public class Libros implements Serializable {
	@Id
	@Column(name = "idLibros",nullable=false)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idLibros; 

	@ManyToOne
	@JoinColumn(name = "nick_duenyo",nullable=false)
	private Usuarios nick_duenyo;
	
	@Column(name = "titulo",nullable=false)
	private String titulo;
	
	@Column(name = "autor",nullable=false)
	private String autor;
	
	@Column(name = "descripcion",nullable=false)
	private String descripcion;
	
	@Column(name = "localizacion",nullable=false)
	private String localizacion;
	
	public Libros(){}
	
	public Libros(int idLibros, Usuarios nick_duenyo, String titulo, String autor, String descripcion,
			String localizacion) {
		super();
		this.idLibros = idLibros;
		this.nick_duenyo = nick_duenyo;
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
	}

	public int getIdLibros() {
		return idLibros;
	}

	public Usuarios getNick_duenyo() {
		return nick_duenyo;
	}

	public void setNick_duenyo(Usuarios nick_duenyo) {
		this.nick_duenyo = nick_duenyo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	
}

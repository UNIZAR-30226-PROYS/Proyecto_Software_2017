package VO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

@Entity(name = "Usuarios")
@Table(name = "Usuarios")
public class Usuarios implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7695133847485595607L;

	@Id
	@Column(name = "nickname",nullable=false)
	private String nickname;
	
	@Column(name = "nombre",nullable=false)
	private String nombre;
	
	@Column(name = "Apellido",nullable=false)
	private String apellido;
	
	@Column(name = "valoracion")
	private BigDecimal valoracion; 
	
	@Column(name = "contrasenya",nullable=false)
	private String contrasenya;
	
	@OneToMany(mappedBy = "nickname_libros")
	private List<Libros> libros;

	@OneToMany(mappedBy = "nickname_users")
	private List<Usuarios> usuarios_fav;
	
	@OneToMany(mappedBy = "nick_comprador")
	private List<Transacciones> transacciones_comprador;
	
	@OneToMany(mappedBy = "nick_vendedor")
	private List<Transacciones> transacciones_vendedor;
	
	public Usuarios(){}
	
	public Usuarios(String nickname, String nombre, String apellido, BigDecimal valoracion, String contrasenya,
			List<Libros> libros, List<Usuarios> usuarios_fav, List<Transacciones> transacciones_comprador) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.valoracion = valoracion;
		this.contrasenya = contrasenya;
		this.libros = libros;
		this.usuarios_fav = usuarios_fav;
		this.transacciones_comprador = transacciones_comprador;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public BigDecimal getValoracion() {
		return valoracion;
	}

	public void setValoracion(BigDecimal valoracion) {
		this.valoracion = valoracion;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public List<Libros> getLibros() {
		return libros;
	}

	public void setLibros(List<Libros> libros) {
		this.libros = libros;
	}

	public List<Usuarios> getUsuarios_fav() {
		return usuarios_fav;
	}

	public void setUsuarios_fav(List<Usuarios> usuarios_fav) {
		this.usuarios_fav = usuarios_fav;
	}

	public List<Transacciones> getTransacciones_comprador() {
		return transacciones_comprador;
	}

	public void setTransacciones_comprador(List<Transacciones> transacciones_comprador) {
		this.transacciones_comprador = transacciones_comprador;
	}

	public List<Transacciones> getTransacciones_vendedor() {
		return transacciones_vendedor;
	}

	public void setTransacciones_vendedor(List<Transacciones> transacciones_vendedor) {
		this.transacciones_vendedor = transacciones_vendedor;
	}
	
	
}

package VO;
import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Transacciones")
@Table(name = "Transacciones")
public class Transacciones implements Serializable{
	@Id
	@Column(name = "idtransacciones",nullable=false)
	private int idtransacciones;
	
	@Column(name = "titulo_libro",nullable=false)
	private String titulo_libro;

	@ManyToOne
	@JoinColumn(name = "nick_comprador",nullable=false)
	private Usuarios nick_comprador;
	
	@ManyToOne
	@JoinColumn(name = "nick_vendedor",nullable=false)
	private Usuarios nick_vendedor;
	
	public Transacciones(){}
	
	public Transacciones(int idtransacciones, String titulo_libro, Usuarios nick_comprador, Usuarios nick_vendedor) {
		super();
		this.idtransacciones = idtransacciones;
		this.titulo_libro = titulo_libro;
		this.nick_comprador = nick_comprador;
		this.nick_vendedor = nick_vendedor;
	}

	public int getIdtransacciones() {
		return idtransacciones;
	}

	public String getTitulo_libro() {
		return titulo_libro;
	}

	public void setTitulo_libro(String titulo_libro) {
		this.titulo_libro = titulo_libro;
	}

	public Usuarios getNick_comprador() {
		return nick_comprador;
	}

	public void setNick_comprador(Usuarios nick_comprador) {
		this.nick_comprador = nick_comprador;
	}

	public Usuarios getNick_vendedor() {
		return nick_vendedor;
	}

	public void setNick_vendedor(Usuarios nick_vendedor) {
		this.nick_vendedor = nick_vendedor;
	}
	
	
}

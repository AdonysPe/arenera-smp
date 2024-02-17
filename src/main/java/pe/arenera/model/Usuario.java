package pe.arenera.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "usuario_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usuarioid;
	
	@Column(name = "usuario_nombre")
	private String usuarionombre;
	
	@Column(name = "usuario_apellido")
	private String usuarioapellido;
	
	@Column(name = "usuario_dni")
	private String usuariodni;
	
	@Column(name = "usuario_telefono")
	private String usuariotelefono;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "clave")
	private String clave;

	// CONSTRUCTOR, GETTERS AND SETTERS Y TOSTRING
	public Usuario() {
		super();
	}

	public Usuario(int usuarioid, String usuarionombre, String usuarioapellido, String usuariodni,
			String usuariotelefono, String correo, String clave) {
		super();
		this.usuarioid = usuarioid;
		this.usuarionombre = usuarionombre;
		this.usuarioapellido = usuarioapellido;
		this.usuariodni = usuariodni;
		this.usuariotelefono = usuariotelefono;
		this.correo = correo;
		this.clave = clave;
	}

	public int getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(int usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getUsuarionombre() {
		return usuarionombre;
	}

	public void setUsuarionombre(String usuarionombre) {
		this.usuarionombre = usuarionombre;
	}

	public String getUsuarioapellido() {
		return usuarioapellido;
	}

	public void setUsuarioapellido(String usuarioapellido) {
		this.usuarioapellido = usuarioapellido;
	}

	public String getUsuariodni() {
		return usuariodni;
	}

	public void setUsuariodni(String usuariodni) {
		this.usuariodni = usuariodni;
	}

	public String getUsuariotelefono() {
		return usuariotelefono;
	}

	public void setUsuariotelefono(String usuariotelefono) {
		this.usuariotelefono = usuariotelefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [usuarioid=" + usuarioid + ", usuarionombre=" + usuarionombre + ", usuarioapellido="
				+ usuarioapellido + ", usuariodni=" + usuariodni + ", usuariotelefono=" + usuariotelefono + ", correo="
				+ correo + ", clave=" + clave + "]";
	}
	
	
	
}

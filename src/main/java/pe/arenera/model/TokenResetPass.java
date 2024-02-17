package pe.arenera.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reset_token_pass")
public class TokenResetPass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private int tokenid;
	
	@Column(unique = true, name = "token")
	private String token;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaExpiracion;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	// CONSTRUCTOR VACIO, GETTERS Y SETTERS Y TOSTRING
	public TokenResetPass() {
		super();
	}

	public TokenResetPass(int tokenid, String token, LocalDateTime fechaExpiracion, Usuario usuario) {
		super();
		this.tokenid = tokenid;
		this.token = token;
		this.fechaExpiracion = fechaExpiracion;
		this.usuario = usuario;
	}

	public int getTokenid() {
		return tokenid;
	}

	public void setTokenid(int tokenid) {
		this.tokenid = tokenid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "TokenResetPass [tokenid=" + tokenid + ", token=" + token + ", fechaExpiracion=" + fechaExpiracion
				+ ", usuario=" + usuario + "]";
	}

	
	
}

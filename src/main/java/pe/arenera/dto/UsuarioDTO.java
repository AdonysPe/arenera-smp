package pe.arenera.dto;

public class UsuarioDTO {

	private int usuarioid;	
	private String usuarionombre;
	private String usuarioapellido;
	private String usuariodni;	
	private String usuariotelefono;	
	private String correo;	
	private String clave;

	// CONSTRUCTORES, GETTERS AND SETTERS Y TO STRING
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(int usuarioid, String usuarionombre, String usuarioapellido, String usuariodni,
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
		return "UsuarioDTO [usuarioid=" + usuarioid + ", usuarionombre=" + usuarionombre + ", usuarioapellido="
				+ usuarioapellido + ", usuariodni=" + usuariodni + ", usuariotelefono=" + usuariotelefono + ", correo="
				+ correo + ", clave=" + clave + "]";
	}
	
}

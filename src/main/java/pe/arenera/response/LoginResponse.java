package pe.arenera.response;

public class LoginResponse {

	String mensaje;
	Boolean estado;
	
	// CONSTRUCTORES, G AND S Y TO STRING
	public LoginResponse(String mensaje, Boolean estado) {
		super();
		this.mensaje = mensaje;
		this.estado = estado;
	}

	public LoginResponse() {
		super();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "LoginResponse [mensaje=" + mensaje + ", estado=" + estado + "]";
	}
	
}

package pe.arenera.dto;

public class LoginDTO {

	private String correo;
	private String clave;
	
	// CONSTRUCTORES, GETTERS AND SETTERS Y TO STRING
	public LoginDTO(String correo, String clave) {
		super();
		this.correo = correo;
		this.clave = clave;
	}

	public LoginDTO() {
		super();
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
		return "LoginDTO [correo=" + correo + ", clave=" + clave + "]";
	}
	
}

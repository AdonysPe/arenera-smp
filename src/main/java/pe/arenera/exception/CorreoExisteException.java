package pe.arenera.exception;

public class CorreoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CorreoExisteException(String mensaje) {
		super(mensaje);
	}
}

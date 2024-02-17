package pe.arenera.exception;

public class VehiculoNoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public VehiculoNoEncontradoException(String message) {
		super(message);
	}
	
	public VehiculoNoEncontradoException(String message, Throwable causa) {
		super(message, causa);
	}
}

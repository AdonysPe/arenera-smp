package pe.arenera.exception;

public class UsuarioNoEncontradoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoExcepcion(Integer id) {
		super("No se pudo encontrar al usuario con el id " + id);
	}

}

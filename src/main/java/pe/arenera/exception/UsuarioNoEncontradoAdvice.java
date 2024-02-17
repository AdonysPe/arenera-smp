package pe.arenera.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UsuarioNoEncontradoAdvice {
	
	@ResponseBody
	@ExceptionHandler(UsuarioNoEncontradoExcepcion.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> manejoException(UsuarioNoEncontradoExcepcion exception){
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("mensajeError", exception.getMessage());
		
		return errorMap;
	}
	
	@ExceptionHandler(CorreoExisteException.class)
	public ResponseEntity<String> manejoCorreoExiste(CorreoExisteException ex){
		
		String mensaje = "El correo proporcionado ya está registrado BRODA!";
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(mensaje);
	}

}

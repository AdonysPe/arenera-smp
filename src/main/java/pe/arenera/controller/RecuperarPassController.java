package pe.arenera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.arenera.model.Usuario;
import pe.arenera.repository.UsuarioRepository;
import pe.arenera.service.RecuperarPassService;
import pe.arenera.service.VerificacionTokenService;

@RestController
@CrossOrigin
@RequestMapping("/api/recuperacionpass")
public class RecuperarPassController {

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private RecuperarPassService recuperarPassRepo;
	
	@Autowired
	private VerificacionTokenService veriToken;
	
	@PostMapping("/generartoken")
	public ResponseEntity<String> generarTokenRecuperacion(@RequestParam String correo){
		
		Usuario usuario = userRepo.findByCorreo(correo);
		
		if(usuario != null) {
			
			String token = recuperarPassRepo.generarTokenRecuperacion(usuario);
			return ResponseEntity.ok(token);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro al usuario");
		}
	}
	
	@PostMapping("/verificar")
	public ResponseEntity<String> verificarTokenRecuperacion(@RequestParam String token) {

        boolean tokenValido = veriToken.verificarTokenRecuperacion(token);

        if (tokenValido) {
            // El token es válido y no ha expirado
            return ResponseEntity.ok("Token verificado correctamente");
        } else {
            // El token no es válido o ha expirado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o expirado");
        }
    }
	
	@PostMapping("/restablecer")
	public ResponseEntity<String> restablecerContraseña(@RequestParam String token ,@RequestParam String nuevoPass){
		
		boolean tokenValido = veriToken.verificarTokenRecuperacion(token);
		
		if(tokenValido) {
			boolean exitoReset = recuperarPassRepo.restablecerContraseña(token, nuevoPass);
			
			if(exitoReset) {
				return ResponseEntity.ok("Contraseña restablecida correctamente!!!"); 
			}else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error para restablecer el Pass");
			}
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TOKEN EXPIRADO!");
		}
	}
}

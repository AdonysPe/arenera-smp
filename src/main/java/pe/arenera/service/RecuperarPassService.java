package pe.arenera.service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import pe.arenera.model.TokenResetPass;
import pe.arenera.model.Usuario;
import pe.arenera.repository.ResetPassRepository;
import pe.arenera.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class RecuperarPassService {

	@Autowired
	private ResetPassRepository recuPassRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository userRepo;

	// GENERACION DE TOKEN Y ENVIO AL CORREO ELECTRONICO <-
	public String generarTokenRecuperacion(Usuario usuario){
		
		// GENERAR TOKEN UNICO
		String token = UUID.randomUUID().toString();
		
		// CALCULACION DE LA FECHA DE EXPIRACION
		LocalDateTime fechaExpiracion = LocalDateTime.now().plusHours(24);
		
		// CREACION DEL REGISTRO DE TOKEN DE RECUPERACION Y ESTABLECER LA REFERENCIA AL USUARIO
		TokenResetPass tokenRecuperacion = new TokenResetPass();
		
		tokenRecuperacion.setToken(token);
		tokenRecuperacion.setFechaExpiracion(fechaExpiracion);
		tokenRecuperacion.setUsuario(usuario);
		
		// GUARDAR EL REGISTRO A LA BASE DE DATOS
		recuPassRepo.save(tokenRecuperacion);
		
		
		// ASUNTO
		String asunto = "RECUPERACION DE CONTRASEÑA - ASMP";
		
		// CUERPO DE CORREO
		String cuerpo = "Hola " + usuario.getUsuarionombre() + ",\n\n" +
                "Recibimos una solicitud para restablecer tu contraseña. " +
                "Ingresa el siguiente token en la página de restablecimiento de contraseña: " + "\n\n" + token + "\n\n" +
                "Si no solicitaste esta acción, ignora este mensaje. ATTE: ARENERA SMP";
		
			
			SimpleMailMessage mensaje = new SimpleMailMessage();
			mensaje.setTo(usuario.getCorreo());
			mensaje.setSubject(asunto);
			mensaje.setText(cuerpo);
			
			javaMailSender.send(mensaje);
			
			return token;
			
		
	}
	
	// RESTABLECIMIENTO DE CONTRASEÑA EN EL FORMULARIO PARA RESTABLACER CONTRASEÑA
	public boolean restablecerContraseña(String token, String nuevaContraseña) {
		// Buscar el registro del token en la base de datos
	    TokenResetPass tokenRecuperacion = recuPassRepo.findByToken(token);
	    
	    if (tokenRecuperacion == null) {
	        // El token no fue encontrado en la base de datos
	        return false;
	    }

	    if (tokenRecuperacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
	        // El token ha expirado
	        recuPassRepo.delete(tokenRecuperacion); // Eliminar el token expirado
	        return false;
	    }

	    // El token es válido y no ha expirado, proceder con el restablecimiento de contraseña

	    Usuario usuario = tokenRecuperacion.getUsuario();

	    // Actualizar la contraseña del usuario
	    String contraseñaEncriptada = passwordEncoder.encode(nuevaContraseña);
	    usuario.setClave(contraseñaEncriptada);
	    userRepo.save(usuario);

	    // Eliminar el registro del token de recuperación, ya que ya fue utilizado
	    recuPassRepo.delete(tokenRecuperacion);

	    return true;
    }


}

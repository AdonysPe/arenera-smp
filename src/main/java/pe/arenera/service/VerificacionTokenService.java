package pe.arenera.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.arenera.model.TokenResetPass;
import pe.arenera.repository.ResetPassRepository;

@Service
public class VerificacionTokenService {
	
	@Autowired
	private ResetPassRepository resetPassRepo;
	
	@Transactional
	public boolean verificarTokenRecuperacion(String token) {
		
		TokenResetPass optionalToken = resetPassRepo.findByToken(token);
		
		if(optionalToken == null) {
			return false;
		}
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fechaExpiracion = optionalToken.getFechaExpiracion();
		
		if(now.isAfter(fechaExpiracion)) {
			return false;
		}
		
		//resetPassRepo.delete(optionalToken);
		
		return true;
	}
	
	
}

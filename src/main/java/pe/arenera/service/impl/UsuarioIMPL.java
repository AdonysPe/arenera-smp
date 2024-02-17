package pe.arenera.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.arenera.dto.LoginDTO;
import pe.arenera.dto.UsuarioDTO;
import pe.arenera.exception.CorreoExisteException;
import pe.arenera.model.Usuario;
import pe.arenera.repository.UsuarioRepository;
import pe.arenera.response.LoginResponse;
import pe.arenera.service.UsuarioService;

@Service
public class UsuarioIMPL implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// AGREGAR USUARIO <---
	@Override
	public String addUsuario(UsuarioDTO usuarioDTO) {
		
		String correo = usuarioDTO.getCorreo();
		
		if(existeCorreo(correo)) {
			throw new CorreoExisteException("EL CORREO YA ESTA REGISTRADO!");
		}
		
		Usuario usuario = new Usuario(
				usuarioDTO.getUsuarioid(),
				usuarioDTO.getUsuarionombre(),
				usuarioDTO.getUsuarioapellido(),
				usuarioDTO.getUsuariodni(),
				usuarioDTO.getUsuariotelefono(),
				usuarioDTO.getCorreo(),
	
				this.passwordEncoder.encode(usuarioDTO.getClave())
				);
		
		usuarioRepo.save(usuario);
		
		return usuario.getUsuarionombre();
	}
	
	// INICIAR SESION <---
	@Override
	public LoginResponse login(LoginDTO loginDTO) {
		
		//String msj = "";
		Usuario usuario1 = usuarioRepo.findByCorreo(loginDTO.getCorreo());
		
		if(usuario1 != null) {
			String clave = loginDTO.getClave();
			String encodedPassword = usuario1.getClave();
			Boolean isClaveBien = passwordEncoder.matches(clave, encodedPassword);
			
			if(isClaveBien) {
				Optional<Usuario> usuario = usuarioRepo.findOneByCorreoAndClave(loginDTO.getCorreo(), encodedPassword);
				if(usuario.isPresent()) {
					return new LoginResponse("Ingreso Exitoso",true);
				}else {
					return new LoginResponse("Ingreso Fallido", false);
				}
				
			}else {
				return new LoginResponse("Contraseña no coincide", false);
			}
			
		}else {
			return new LoginResponse("Correo no existe", false);
		}
	}

	@Override
	public String actualizarUser(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeCorreo(String correo) {
		return usuarioRepo.existsByCorreo(correo);
	}

	@Override
	public void registrarUsuario(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
}

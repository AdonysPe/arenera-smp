package pe.arenera.service;

import org.springframework.stereotype.Service;

import pe.arenera.dto.LoginDTO;
import pe.arenera.dto.UsuarioDTO;
import pe.arenera.model.Usuario;
import pe.arenera.response.LoginResponse;

@Service
public interface UsuarioService {

	String addUsuario(UsuarioDTO usuarioDTO);

	LoginResponse login(LoginDTO loginDTO);

	String actualizarUser(UsuarioDTO usuarioDTO);

	boolean existeCorreo(String correo);

	void registrarUsuario(UsuarioDTO usuarioDTO);

	void registrarUsuario(Usuario usuario);

}

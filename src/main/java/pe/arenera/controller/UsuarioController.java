package pe.arenera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.arenera.dto.LoginDTO;
import pe.arenera.dto.UsuarioDTO;
import pe.arenera.exception.UsuarioNoEncontradoExcepcion;
import pe.arenera.model.Usuario;
import pe.arenera.repository.UsuarioRepository;
import pe.arenera.response.LoginResponse;
import pe.arenera.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	// REGISTRAR USUARIO <---
	@PostMapping(path = "/save")
	public String guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		
		String id = usuarioService.addUsuario(usuarioDTO);
		return id;
	}
	// REGISTRARM USUARIO V2 (PS) <---
	@PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
	
	// LOGIN DE USUARIO <---
	@PostMapping(path = "/login")
	public ResponseEntity<?> logeoUsuario(@RequestBody LoginDTO loginDTO){
		
		LoginResponse loginResponse = usuarioService.login(loginDTO);
		return ResponseEntity.ok(loginResponse);
	}
	
	// LISTADO DE USUARIOS <---
	@GetMapping(path = "/listar")
	List<Usuario> listarUsuarios(){
		return usuRepo.findAll();
	}
	
	// LISTADO DE USUARIO CON PAGINACION
	@GetMapping(path = "/listadopag")
	public ResponseEntity<List<Usuario>> listarUsersPagin(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Usuario> usuarioPage = usuRepo.findAll(pageable);
		List<Usuario> usuarios = usuarioPage.getContent();
		
		return ResponseEntity.ok(usuarios);
	}
	
	// RECUPERAR USUARIO POR ID <---
	@GetMapping(path = "/user/{id}")
	Usuario getUsuarioById(@PathVariable Integer id) {
		return usuRepo.findById(id)
				.orElseThrow( () -> new UsuarioNoEncontradoExcepcion(id));
	}
	
	// ACTUALIZAR USUARIO <---
	@PutMapping(path = "/user/{id}")
	Usuario actualizarUsuario(@RequestBody Usuario newUsuario, @PathVariable Integer id) {
		return usuRepo.findById(id)
				.map( usuario -> {
					usuario.setUsuarionombre(newUsuario.getUsuarionombre());
					usuario.setUsuarioapellido(newUsuario.getUsuarioapellido());
					usuario.setUsuariotelefono(newUsuario.getUsuariotelefono());
					usuario.setCorreo(newUsuario.getCorreo());	
					
					return usuRepo.save(usuario);
					
				}).orElseThrow( () -> new UsuarioNoEncontradoExcepcion(id));
	 }
	
	// ELIMINAR USUARIO <---
	@DeleteMapping(path = "user/{id}")
	String eliminarUsuario(@PathVariable Integer id) {
		if(!usuRepo.existsById(id)) {
			throw new UsuarioNoEncontradoExcepcion(id);
		}
		usuRepo.deleteById(id);
		return "El usuario con el id: " + id + " fue eliminado correctamente";
	}
	
	// VERIFICAR SI EL CORREO ELECTRONICO EXISTE O NO <---
	@GetMapping(path = "/validate")
	public ResponseEntity<String> validarCorreo(@RequestParam String correo){
		boolean existsCorreo = usuarioService.existeCorreo(correo);
		
		if(existsCorreo) {
			return ResponseEntity.ok("EL CORREO YA EXISTE!");
		}else {
			try {
				UsuarioDTO usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCorreo(correo);
				
				usuarioService.addUsuario(usuarioDTO);
				
				return ResponseEntity.ok("EL CORREO SE HA REGISTRADO!");
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OCURRIO UN ERROR!");
			}
		}
	}
}
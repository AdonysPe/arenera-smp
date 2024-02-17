package pe.arenera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;

import pe.arenera.model.Usuario;

//@Repository
@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findOneByCorreoAndClave(String correo, String encodedPassword);

	Usuario findByCorreo(String correo);

	boolean existsByCorreo(String correo);

}

package pe.arenera.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import pe.arenera.model.TokenResetPass;

public interface ResetPassRepository extends JpaRepository<TokenResetPass, Integer>{

	TokenResetPass findByToken(String token);

}

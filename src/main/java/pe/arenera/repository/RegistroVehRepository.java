package pe.arenera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;

import pe.arenera.model.Vehiculo;
import pe.arenera.model.VehiculoEntryExit;

@EnableJpaRepositories
//@Repository
public interface RegistroVehRepository extends JpaRepository<VehiculoEntryExit, Integer>{

	@Query("SELECT ve FROM VehiculoEntryExit ve "
			+ "WHERE ve.vehiculo = ?1 AND ve.fechaSalida IS NULL ORDER BY ve.fechaEntrada DESC")
	Optional<VehiculoEntryExit> findLastEntryByVehiculo(Vehiculo vehiculo);


}

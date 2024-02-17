package pe.arenera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.arenera.exception.*;
import pe.arenera.model.Vehiculo;
import pe.arenera.repository.VehiculoRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/vehiculos")
public class VehiculoController {
	
	@Autowired
	private VehiculoRepository vehiculoRepo;
	
	// LISTAR VEHICULOS <---
	@GetMapping(path = "/listarvehi")
	public ResponseEntity<List<Vehiculo>> obtenerVehiculos(){
		List<Vehiculo> vehiculos = vehiculoRepo.findAll();
		return ResponseEntity.ok(vehiculos);
	}
	
	// AGREGAR VEHICULO <---
	@PostMapping(path = "/save")
	public ResponseEntity<Vehiculo> agregarVehiculo(@RequestBody Vehiculo vehiculo){
		Vehiculo vehiculoNuevo = vehiculoRepo.save(vehiculo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoNuevo);
	}
	
	// ACTUALIZAR VEHICULO <---
	@PutMapping("/car")
	public ResponseEntity<Vehiculo> actualizarVehiculo(@RequestBody Vehiculo vehiculo){
				
		vehiculoRepo.findById(vehiculo.getVehiculoid())
				.orElseThrow( () -> new VehiculoNoEncontradoException
						("Vehiculo no enonctrado con ID: " + vehiculo.getVehiculoid()) );
		
		Vehiculo vehiculoUpdate = vehiculoRepo.save(vehiculo);
		return ResponseEntity.ok(vehiculoUpdate);
	}
	
	// RECUPERAR VEHICULO POR ID <---
	@GetMapping(path = "/car/{id}")
	Vehiculo getVehiculoById(@PathVariable Integer id) {
		return vehiculoRepo.findById(id)
				.orElseThrow( () -> new VehiculoNoEncontradoException("Vehiculo no encontrado con ID: " + id));
	}
	
	@DeleteMapping(path = "/car/{id}")
	public ResponseEntity<Void> eliminarVehiculo(@PathVariable Integer id){
		Vehiculo vehiculoExists = vehiculoRepo.findById(id)
				.orElseThrow( () -> new VehiculoNoEncontradoException("Vehiculo no enonctrado con ID: " + id) );
		
		vehiculoRepo.delete(vehiculoExists);
		return ResponseEntity.noContent().build();
	}
}

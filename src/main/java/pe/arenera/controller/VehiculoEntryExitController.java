package pe.arenera.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.arenera.dto.RegistroVehiculoDTO;
import pe.arenera.model.Vehiculo;
import pe.arenera.model.VehiculoEntryExit;
import pe.arenera.service.VehiculoEntryExitService;

@RestController
@CrossOrigin
@RequestMapping("/api2/vehiculo")
public class VehiculoEntryExitController {

	@Autowired
	private VehiculoEntryExitService entryExitService;
	
	// REGISTRAR ENTRADA DEL VEHICULO REGISTRADO
	@PostMapping("/entrada")
	public ResponseEntity<String> registrarEntradaVehiculo(@RequestParam String placa) {
		VehiculoEntryExit entradaSalida = entryExitService.registrarEntradaVehiculo(placa);

		if (entradaSalida != null) {
			return ResponseEntity.ok("Entrada de vehículo registrada");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el vehículo");
		}
	}

	// REGISTRAR SALIDA DEL VEHICULO REGISTRADO
	@PostMapping("/salida")
	public ResponseEntity<String> registrarSalidaVehiculo(@RequestParam String placa) {
		VehiculoEntryExit entradaSalida = entryExitService.registrarSalidaVehiculo(placa);

		if (entradaSalida != null) {
			return ResponseEntity.ok("SE REGISTRO LA SALIDA DEL VEHICULO CORRECTAMENTE!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontró el vehículo o no se registró una entrada previa");
		}
	}

	// LISTAR REGISTROS REALIZADOS
	@GetMapping("/listar")
	public ResponseEntity<List<RegistroVehiculoDTO>> listarRegistros() {
	    List<VehiculoEntryExit> registros = entryExitService.obtenerRegistros();

	    List<RegistroVehiculoDTO> dtos = registros.stream()
	            .map(registro -> new RegistroVehiculoDTO(
	                    registro.getRegistroveid(),
	                    registro.getVehiculo().getPlaca(),
	                    registro.getFechaEntrada(),
	                    registro.getFechaSalida()))
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(dtos);
	}
	
	// BUSCAR VEHICULO EXISTENTE
	@GetMapping("/buscar")
	public ResponseEntity<Vehiculo> buscarVehiculoPorPlaca(@RequestParam String placa){
		Vehiculo vehiculo = entryExitService.buscarVehiculoPorPlaca(placa);
		
		if(vehiculo != null) {
			return ResponseEntity.ok(vehiculo);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}

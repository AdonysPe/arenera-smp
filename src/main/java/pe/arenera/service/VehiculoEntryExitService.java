package pe.arenera.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.arenera.model.Vehiculo;
import pe.arenera.model.VehiculoEntryExit;
import pe.arenera.repository.RegistroVehRepository;
import pe.arenera.repository.VehiculoRepository;

@Service
public class VehiculoEntryExitService {

	@Autowired
	private RegistroVehRepository entryExitRepo;
	
	@Autowired
	private VehiculoRepository vehiculoRepo;
	
	// REGISTRAR ENTRADA VEHICULO
	public VehiculoEntryExit registrarEntradaVehiculo(String placa) {
		Optional<Vehiculo> optionalVehiculo = vehiculoRepo.findByPlaca(placa);
		
		if (optionalVehiculo.isPresent()) {
			Vehiculo vehiculo = optionalVehiculo.get();
			
			LocalDateTime fechaEntrada = LocalDateTime.now();
			
			VehiculoEntryExit entradaSalida = new VehiculoEntryExit();
			entradaSalida.setVehiculo(vehiculo);
			entradaSalida.setFechaEntrada(fechaEntrada);
			
			return entryExitRepo.save(entradaSalida);
		}
		
		return null;
	}
	
	// REGISTRAR SALIDA VEHICULO
	public VehiculoEntryExit registrarSalidaVehiculo(String placa) {
		Optional<Vehiculo> optionalVehiculo = vehiculoRepo.findByPlaca(placa);
		
		if (optionalVehiculo.isPresent()) {
			Vehiculo vehiculo = optionalVehiculo.get();
			
			LocalDateTime fechaSalida = LocalDateTime.now();
			
			Optional<VehiculoEntryExit> optionalEntrada = entryExitRepo.findLastEntryByVehiculo(vehiculo);
			
			if (optionalEntrada.isPresent()) {	
				VehiculoEntryExit entradaSalida = optionalEntrada.get();
				entradaSalida.setFechaSalida(fechaSalida);
				
				return entryExitRepo.save(entradaSalida);
			}
		}
		
		return null;
	}
	
	
	// LISTAR REGISTROS DE ENTRADA Y SALIDA
	public List<VehiculoEntryExit> obtenerRegistros(){
		
		return entryExitRepo.findAll();
	}
	
	// BUSCAR AL VEHICULO POR PLACA
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		Optional<Vehiculo> optionalVehiculo = vehiculoRepo.findByPlaca(placa);
		return optionalVehiculo.orElse(null);
	}

}

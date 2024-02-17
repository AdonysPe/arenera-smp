package pe.arenera.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "registro_vehiculo" ) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VehiculoEntryExit {
	
	@Id
	@Column(name = "id_registro_vehiculo", length = 255)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registroveid;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;
	
	@Column(name = "fecha_entrada")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaEntrada;
	
	@Column(name = "fecha_salida")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime fechaSalida;
	
	// CONSTRUCTORES, GETTERS AND SETTERS Y TOSTRING 
	public VehiculoEntryExit() {
		super();
	}

	public VehiculoEntryExit(int registroveid, Vehiculo vehiculo, LocalDateTime fechaEntrada,
			LocalDateTime fechaSalida) {
		super();
		this.registroveid = registroveid;
		this.vehiculo = vehiculo;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public int getRegistroveid() {
		return registroveid;
	}

	public void setRegistroveid(int registroveid) {
		this.registroveid = registroveid;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Override
	public String toString() {
		return "VehiculoEntryExit [registroveid=" + registroveid + ", vehiculo=" + vehiculo + ", fechaEntrada="
				+ fechaEntrada + ", fechaSalida=" + fechaSalida + "]";
	}

	
}

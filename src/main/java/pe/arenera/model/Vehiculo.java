package pe.arenera.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
	
	@Id
	@Column(name = "vehiculo_id", length = 45)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehiculoid;
	
	@Column(name = "vehiculo_placa", length = 255)
	private String placa;
	
	@Column(name = "vehiculo_color", length = 255)
	private String color;
	
	@Column(name = "vehiculo_modelo", length = 255)
	private String modelo;
	
	@OneToMany(mappedBy = "vehiculo")
	private List<VehiculoEntryExit> registros;
	
	// CONSTRUCTORES, GETTERS AND SETTERS Y TOSTRING
	public Vehiculo() {
		super();
	}

	public Vehiculo(int vehiculoid, String placa, String color, String modelo, Usuario usuario,
			List<VehiculoEntryExit> registros) {
		super();
		this.vehiculoid = vehiculoid;
		this.placa = placa;
		this.color = color;
		this.modelo = modelo;
		this.registros = registros;
	}

	public int getVehiculoid() {
		return vehiculoid;
	}

	public void setVehiculoid(int vehiculoid) {
		this.vehiculoid = vehiculoid;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<VehiculoEntryExit> getRegistros() {
		return registros;
	}

	public void setRegistros(List<VehiculoEntryExit> registros) {
		this.registros = registros;
	}

	@Override
	public String toString() {
		return "Vehiculo [vehiculoid=" + vehiculoid + ", placa=" + placa + ", color=" + color + ", modelo=" + modelo
				+ ", usuario=" + ", registros=" + registros + "]";
	}
}

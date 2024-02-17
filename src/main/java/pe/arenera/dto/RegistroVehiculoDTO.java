package pe.arenera.dto;

import java.time.LocalDateTime;

public class RegistroVehiculoDTO {
    private int registroveid;
    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    // Constructor, getters y setters

    public RegistroVehiculoDTO(int registroveid, String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        this.registroveid = registroveid;
        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getRegistroveid() {
        return registroveid;
    }

    public void setRegistroveid(int registroveid) {
        this.registroveid = registroveid;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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
}

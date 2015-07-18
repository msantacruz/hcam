package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class EstadisticaMovimientoDieselDTO {

	private Date fecha;
	private Double acumuladoTanque1;
	private Double acumuladoTanque2;
	private Double total;
	private Double descarga;
	private Double temperatura;
	private Double salida;
	private String alarma;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getAcumuladoTanque1() {
		return acumuladoTanque1;
	}
	public void setAcumuladoTanque1(Double acumuladoTanque1) {
		this.acumuladoTanque1 = acumuladoTanque1;
	}
	public Double getAcumuladoTanque2() {
		return acumuladoTanque2;
	}
	public void setAcumuladoTanque2(Double acumuladoTanque2) {
		this.acumuladoTanque2 = acumuladoTanque2;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getDescarga() {
		return descarga;
	}
	public void setDescarga(Double descarga) {
		this.descarga = descarga;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public Double getSalida() {
		return salida;
	}
	public void setSalida(Double salida) {
		this.salida = salida;
	}
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
}

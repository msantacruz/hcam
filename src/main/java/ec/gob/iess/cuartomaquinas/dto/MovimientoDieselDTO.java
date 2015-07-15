package ec.gob.iess.cuartomaquinas.dto;

public class MovimientoDieselDTO {
	private String alarma;
	private Double descarga;
	private Double salida;
	private Double temperatura;
	private Double acumuladoTanque1;
	private Double acumuladoTanque2;
	
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
	public Double getDescarga() {
		return descarga;
	}
	public void setDescarga(Double descarga) {
		this.descarga = descarga;
	}
	public Double getSalida() {
		return salida;
	}
	public void setSalida(Double salida) {
		this.salida = salida;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
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
}

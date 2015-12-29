package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class VaporDTO {

	private Date fecha;
	private String valvula;
	private String estado;
	private Double flujo;
	private Double presion;
	private Double temperatura;
	private Boolean alarma_alta_presion;
	private Boolean alarma_baja_presion;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getValvula() {
		return valvula;
	}
	public void setValvula(String valvula) {
		this.valvula = valvula;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getFlujo() {
		return flujo;
	}
	public void setFlujo(Double flujo) {
		this.flujo = flujo;
	}
	public Double getPresion() {
		return presion;
	}
	public void setPresion(Double presion) {
		this.presion = presion;
	}
	public Double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	public Boolean getAlarma_alta_presion() {
		return alarma_alta_presion;
	}
	public void setAlarma_alta_presion(Boolean alarma_alta_presion) {
		this.alarma_alta_presion = alarma_alta_presion;
	}
	public Boolean getAlarma_baja_presion() {
		return alarma_baja_presion;
	}
	public void setAlarma_baja_presion(Boolean alarma_baja_presion) {
		this.alarma_baja_presion = alarma_baja_presion;
	}
}

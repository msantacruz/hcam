package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class AguaDTO {
	
	
	private Double presion;
	private Double flujo;
	private String bomba_1;
	private String bomba_2;
	private String bomba_3;
	private String alarma;
	

	private Date fecha;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getPresion() {
		return presion;
	}
	public void setPresion(Double presion) {
		this.presion = presion;
	}
	public Double getFlujo() {
		return flujo;
	}
	public void setFlujo(Double flujo) {
		this.flujo = flujo;
	}
	public String getBomba_1() {
		return bomba_1;
	}
	public void setBomba_1(String bomba_1) {
		this.bomba_1 = bomba_1;
	}
	public String getBomba_2() {
		return bomba_2;
	}
	public void setBomba_2(String bomba_2) {
		this.bomba_2 = bomba_2;
	}
	public String getBomba_3() {
		return bomba_3;
	}
	public void setBomba_3(String bomba_3) {
		this.bomba_3 = bomba_3;
	}
	public String getAlarma() {
		return alarma;
	}
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}
	
	
	
	
	
	
}

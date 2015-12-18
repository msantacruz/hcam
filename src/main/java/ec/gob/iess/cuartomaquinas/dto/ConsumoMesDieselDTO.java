package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class ConsumoMesDieselDTO {
	private Double consumo_total_mes;
	private Date fecha;


	public Double getConsumo_total_mes() {
		return consumo_total_mes;
	}
	public void setConsumo_total_mes(Double consumo_total_mes) {
		this.consumo_total_mes = consumo_total_mes;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
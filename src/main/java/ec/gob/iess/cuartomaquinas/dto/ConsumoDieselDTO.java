package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class ConsumoDieselDTO {
	private Date fecha;
	private Double consumo;

	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}	
}



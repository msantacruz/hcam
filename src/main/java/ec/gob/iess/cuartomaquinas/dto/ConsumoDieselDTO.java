package ec.gob.iess.cuartomaquinas.dto;

import java.util.Date;

public class ConsumoDieselDTO {
	private Date fecha;
	private Double total;

	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}	
}



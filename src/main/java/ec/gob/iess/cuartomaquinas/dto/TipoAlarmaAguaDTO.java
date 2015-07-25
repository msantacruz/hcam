package ec.gob.iess.cuartomaquinas.dto;

public class TipoAlarmaAguaDTO {
	
	private boolean bajapresion;
	private boolean altapresion;
	
	
	
	public boolean isBajapresion() {
		return bajapresion;
	}
	public void setBajapresion(boolean bajapresion) {
		this.bajapresion = bajapresion;
	}
	public boolean isAltapresion() {
		return altapresion;
	}
	public void setAltapresion(boolean altapresion) {
		this.altapresion = altapresion;
	}
	
}

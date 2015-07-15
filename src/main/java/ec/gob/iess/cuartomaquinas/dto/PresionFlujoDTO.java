package ec.gob.iess.cuartomaquinas.dto;

public class PresionFlujoDTO {

	private String presion;
	private String flujo;
	
	public String getPresion() {
		return presion.trim();
	}
	public void setPresion(String presion) {
		this.presion = presion;
	}
	public String getFlujo() {
		return flujo.trim();
	}
	public void setFlujo(String flujo) {
		this.flujo = flujo;
	}	
}

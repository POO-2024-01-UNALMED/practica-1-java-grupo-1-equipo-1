package gestorAplicacion.constantes;

public enum Destino {
	
	COOPACABANA(20), BELLO(10), ITAGUI(15), ENVIGADO(20), LAPINTADA(50), GUARNE(23), ANGELOPOLIS(38), BARBOSA(42), RIONEGRO(34), CALDAS(24)
	,GUATAPE(82), MARINILLA(50); 
	
	private int distancia;
	
	private Destino(int distancia) {
		
		this.distancia = distancia;
		
	}
	
	public int getDistancia() {
		
		return distancia;
		
	}
	
	
	
	

}

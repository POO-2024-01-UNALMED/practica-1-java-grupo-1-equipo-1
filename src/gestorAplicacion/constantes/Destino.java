package gestorAplicacion.constantes;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Enumeración que representa los destinos asociados a la terminal y sus respectivas distancias.
 */

public enum Destino {
	
	COOPACABANA(20), BELLO(10), ITAGUI(15), ENVIGADO(20), LAPINTADA(50), GUARNE(23), ANGELOPOLIS(38), BARBOSA(42), RIONEGRO(34), CALDAS(24)
	,GUATAPE(82), MARINILLA(50); 
	
	private int distancia; // atributo distancia, el cuál se asocia a un destino específico, este número representa la distancia en KM al destino.
	
	 /**
     * Constructor para la clase Destino.
     * @param distancia, la distancia asociada al destino.
     */
	
	private Destino(int distancia) {
		
		this.distancia = distancia;
		
	}
	
	/**
	 * Método para obtener la distancia asociada a un destino.
	 * @return la distacia asociada al destino.
	 */
	
	public int getDistancia() {
		
		return distancia;
		
	}
	
	
	
	

}

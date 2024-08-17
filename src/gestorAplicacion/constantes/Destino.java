package gestorAplicacion.constantes;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Enumeración que representa los destinos asociados a la terminal y sus respectivas distancias.
 */

public enum Destino {
	
	// Destinos Cercanos
	MEDELLIN(0,0) ,COOPACABANA(0,18), BELLO(0,10), ITAGUI(0,-11), ENVIGADO(0,-10), LAPINTADA(60,-53), GUARNE(33,0), ANGELOPOLIS(-45.8,20), BARBOSA(0,42), RIONEGRO(36,-5), CALDAS(0,-22)
	,GUATAPE(79.6,-10), MARINILLA(50,0), SABANETA(0,-14), LAESTRELLA(0,-16), GIRARDOTA(-23,0), 
	
	// Destinos Lejanos
	CARTAGENA(151.24,634.2), SANTAMARTA(354.22, 750.63), BARRANQUILLA(253.54,384.56), LAGUAJIRA(523,840), BOGOTA(398.49,-126.28), CALI(-351.49, -267.65), BUENAVENTURA(-429.95, -226.75), BUCARAMANGA(325.52,229); 
	
	private double ejeX; // atributo distancia, el cuál se asocia a un destino específico, este número representa la distancia en KM al destino.
	private double ejeY; // atributo distancia, el cuál se asocia a un destino específico, este número representa la distancia en KM al destino.
	
	 /**
     * Constructor para la clase Destino.
     * @param Coordenadas, las coordenadas asociadas al destino.
     */
	
	private Destino(double distancia, double latitud) {
		this.ejeX = distancia;
		this.ejeY = latitud;
	}
	
	/**
	 * Método para obtener la coordenada en X asociada a un destino.
	 * @return  la coordenada en X asociada al destino.
	 */
	
	public double getEjeX() {
		return ejeX;
	}
	
	/**
	 * Método para obtener la coordenada en Y asociada a un destino.
	 * @return la coordenada en Y asociada al destino.
	 */
	
	public double getEjeY() {
		return ejeY;
	}
	
	
	/**
	 * metodo para ver si el string que se ingresa puede convertirse en uno de los destinos
	 * @param input
	 * @return
	 */
    public static boolean esDestinoValido(String destinoDeseado) {
        for (Destino destino : Destino.values()) {
            if (destino.name().equals(destinoDeseado)) {
                return true;
            }
        }
        return false;
    }
}

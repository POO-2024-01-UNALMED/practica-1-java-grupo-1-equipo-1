package gestorAplicacion.constantes;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Enumeración que representa los tipos de pasajeros que pueden estar asociados a la terminal y los respectivos descuentos según su tipo.
 */

public enum TipoPasajero {
	
	ESTUDIANTE(0.35), DISCAPACITADO(0.45), REGULAR(1), VIP(2);
	
	private double descuento; // Atributo descuento, el cuál hará un descuento al valor del pasaje al pasajero.
	
	 /**
     * Constructor para la clase TipoPasajero.
     * @param descuento, el descuento asociado al tipo de pasajero.
     */
	
	private TipoPasajero(double descuento) {
		
		this.descuento = descuento;
		
	}
	
	/**
	 * Método para obtener el descuento asociado a un tipo de pasajero.
	 * @return el descuento asociado al pasajero.
	 */
	
	public double getDescuento() {
		
		return descuento;
		
	}

}

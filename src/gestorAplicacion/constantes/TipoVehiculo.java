package gestorAplicacion.constantes;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Enumeración que representa los tipos de vehículos que pueden estar asociados a la terminal y la respectiva capacidad de pasajeros según su tipo.
 */

public enum TipoVehiculo {
	
	BUS(42), TAXI(4), VANS(15), ESCALERA(50);
	
	int capacidad; // Capacidad de pasajeros de cada tipo de vehículo
	
	 /**
     * Constructor para la clase TipoVehiculo.
     * @param capacidad, la capacidad asociada según el tipo de vehículo.
     */
	
	private TipoVehiculo(int capacidad) {
		
		this.capacidad = capacidad;
		
	}
	
	 /**
     * Método para obtener la capacidad en base a un vehículo.
     * @return la capacidad respectiva del vehículo.
     */
	
	public int getCapacidad() {
		
		return capacidad;
	}

}

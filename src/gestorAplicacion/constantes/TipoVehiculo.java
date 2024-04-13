package gestorAplicacion.constantes;

public enum TipoVehiculo {
	
	BUS(42), TAXI(4), VANS(15), ESCALERA(50);
	
	int capacidad;
	
	private TipoVehiculo(int capacidad) {
		
		this.capacidad = capacidad;
		
	}
	
	public int getCapacidad() {
		
		return capacidad;
	}

}

package gestorAplicacion.constantes;

public enum TipoPasajero {
	
	ESTUDIANTE(0.35f), DISCAPACITADO(0.45f), REGULAR(1), VIP(2);
	
	private float descuento;
	
	private TipoPasajero(float descuento) {
		
		this.descuento = descuento;
		
	}
	
	public float getDescuento() {
		
		return descuento;
		
	}

}

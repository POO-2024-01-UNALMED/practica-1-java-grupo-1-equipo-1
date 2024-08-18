package gestorAplicacion.constantes;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Enumeración que representa los días de la semana.
 */

public enum Dia {
	
	LUN(1), MAR(2), MIER(3), JUE(4), VIE(5), SAB(6), DOM(7);

	private int value;
	
	Dia(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}

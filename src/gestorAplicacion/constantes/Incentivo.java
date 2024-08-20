package gestorAplicacion.constantes;

import gestorAplicacion.administrativo.Viaje;

public interface Incentivo {
	
	double incentivoBase = 50; // Acordar valor, incentivo asociado a las distintas bonificaciones que pueden tener las clases que implementen la interfaz
	
	void descuento(); // Método abstacto a implementar en cada clase que implemente la interfaz, servirá para el modelado económico de la terminal
	void bonificacion(); // Método abstacto a implementar en cada clase que implemente la interfaz, servirá para el modelado económico de la terminal


}


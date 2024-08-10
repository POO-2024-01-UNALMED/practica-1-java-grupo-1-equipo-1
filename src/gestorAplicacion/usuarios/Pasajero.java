package gestorAplicacion.usuarios;
import java.util.ArrayList;
import java.util.Random;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.administrativo.Terminal;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a un pasajero, el cuál es pieza fundamental en una terminal, esta clase nos servirá para tener realizar interaciones entre 
 *  varias clases como lo son transportadora, factura, terminal y viaje, nos será de mucha utilidad ya que con esta podremos asociar pasajeros a viajes
 *  cancelar el viaje de un pasajero y rembolsar su respectivo dinero. 
 */

public class Pasajero extends Persona{

	// Atributos
	
	private TipoPasajero tipo; // Tipo de pasajero asociado al pasajero
	private static int idStatic = 0;
	private static final char[] GENEROS = {'M', 'F'};
	private Viaje viaje;
	private static final String[] NOMBRESH = {"Carlos", "Luis", "Jose", "Alejandro", "Santiago", "Jonathan","Jhonathan",
												"Juan", "jhon", "john", "David", "johan", "Fernando", "Camilo", "Daniel",
												"Jack", "Mateo","Miguel", "Andrés", "Sebastián", "Ricardo", "Francisco",
											    "Marcos", "Iván", "Manuel", "Pedro", "Roberto", "Rafael", "Mario", "Pablo",
											    "Hugo", "Sergio", "Antonio", "Javier", "Martín", "Álvaro", "Gustavo",
											    "Adrián", "Fabián", "Héctor", "Enrique", "Eduardo", "Ángel", "Cristian",
											    "Alfredo", "Gabriel", "Leonardo", "Bruno", "Armando"};

	private static final String[] NOMBRESM = {"Ana", "Maria", "Laura", "Carmen", "Juana", "Marta", "Lucía", "Sofía", "Isabel",
		    "Patricia", "Sara", "Elena", "Pilar", "Teresa", "Rosa", "Beatriz", "Adriana",
		    "Gabriela", "Victoria", "Natalia", "Daniela", "Paula", "Carolina", "Silvia",
		    "Claudia", "Lorena", "Cristina", "Julia", "Andrea", "Valeria", "Eva", "Angela",
		    "Susana", "Raquel", "Irene", "Rocío", "Alicia", "Marina", "Lourdes", "Bárbara",
		    "Sandra", "Ariana", "Verónica", "Esther", "Noelia", "Vanessa", "Nuria", "Inés"};
	
	public Pasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero) {
		super(id, edad, nombre, genero);
		
		this.tipo = tipo;
		
	}

	//contructor aleatorio para llenar viajes
		/**
		 * Para un correcto funcionamiento de esta implementación, se deberá especificar que el atributo "id" para los otros 
		 * objetos creados manualmente debera ser superior a los 6 dígitos y asi, evitar conflictos a la hora de designar viajes, etc.
		 */
	
	/**
	 * Constructor para la clase persona
     * @param tipo, tipo de pasajero.
     */
	
	//Constructor para nuevos usuarios
	
	public Pasajero(TipoPasajero tipo) {
		
		this.tipo = tipo;													
		Random random = new Random();
		this.setId(Pasajero.getIdStatic());
        this.setEdad(random.nextInt(100));
		char genero = GENEROS[random.nextInt(GENEROS.length)];
		this.setGenero(genero);
		
		if (genero == 'M') {
			
			this.setNombre(NOMBRESM[random.nextInt(NOMBRESM.length)]);
			
		}
		
		else {
			
			this.setNombre(NOMBRESH[random.nextInt(NOMBRESH.length)]);
			
		}
		
	}
	
	public Viaje masEconomico(String destinoDeseado, int cantidad){

		Viaje viajeMasBarato = null;

	   	//Suponiendo que "viajes" es una arrayList con los viajes que se 
		//encuentran disponibles en la terminal y que "finalDestino" de transportadora es hacia donde se dirige

		for (Viaje viaje : Terminal.getViajes()) {
	        // Verificar si el viaje tiene el destino deseado y suficientes asientos disponibles
			
	        if (viaje.getLlegada().name().equals(destinoDeseado) && viaje.getAsientosDisponibles() >= cantidad&&viaje.getEstado()==false) {
	        	
	            if (viajeMasBarato == null || viaje.getTarifa() < viajeMasBarato.getTarifa()) {
	                viajeMasBarato = viaje;
	                
	            }
	        }
	    }

	    return viajeMasBarato;
	}
	
	/**
	 * Este método elige el viaje con la tarifa más económica.
	 * @param destinoDeseado Nombre del destino deseado.
	 * @param cantidad Asientos solicitados.
	 * @return El viaje más barato que cumple con los criterios.
	 */

	@Override
	public String identificarse() {
		
		// Implementación pendiente
		
		return null;
	}
	
	
	public void pagarFactura() {
		
		// Implementación pendiente
		
		
	}
	
	/**
	 * Método para desvincular a un pasajero de un viaje
	 */
	
	public String desvincularViaje() {
		
		int index = this.getViaje().getPasajeros().indexOf(this);// si indexOf() retorna -1 es porque no se encontró
		
		if (index != -1) {

	        this.getViaje().getPasajeros().set(index, null);
	        this.viaje = null;
	        return"Viaje cancelado";
	        
	    } else {
	    	
	    	return "No hay viaje asignado a este pasajero";
	    	
	    }

	}
	
	public void renovarContrato(int dias) {
		//no es neceserio
	}
	
	@Override
	public void descuento(double porcentaje) {
		
	}
	
	@Override
	public void bonificacion(double premio) {
		
		
	}
	
	// Métodos getters y setters
	
	/**
	 * Método para obtener el tipo de pasajero de la pasajero.
	 * @return el tipo de pasajero de dicho pasajero.
	 */
	
	public TipoPasajero getTipo() {
		
		return tipo;
	}
	
	public void setTipo(TipoPasajero tipo) {
		
		this.tipo = tipo;
		
	}

	public Viaje getViaje() {
		
		return viaje;
		
	}

	public void setViaje(Viaje viaje) {
		
		this.viaje = viaje;
		
	}

	public static int getIdStatic() {
		
		return idStatic++;
		
	}


}

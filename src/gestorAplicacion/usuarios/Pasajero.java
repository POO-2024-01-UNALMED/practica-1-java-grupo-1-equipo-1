package gestorAplicacion.usuarios;
import java.util.ArrayList;
import java.util.Random;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.Incentivo;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.administrativo.Factura;
import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;

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
	
	public Pasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero) { // falta Inicializar varios de los atributos que hereda de persona
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
	
	/**
	 * Método que nos sirve para calcular el pequeño descuento que le hace la transportadora a un pasajero 
	 *en su pasaje
	 
	 */
	@Override
	public void descuento() {
		
		double dineroTransportadora = this.getViaje().getTransportadora().getDinero();
		 this.viaje.setTarifa(viaje.getTarifa()-50);
		 this.getViaje().getTransportadora().setDinero(dineroTransportadora - 50);
		 
	}
	
	/**
	 * Método que nos sirve para calcular la bonificación que le hace una transportadora a un pasajero por su fidelidad a la trasnportadora 
	 */
	
	@Override
	public void bonificacion() {
		
		switch (this.verificarBonificacion()) {
		
		case 0 :
			
			this.dinero += 0; // Ya que el pasajero no ha viajado ninguna vez en la misma transportadora, por ende no tendrá bonificación	
			break;
			
		case 5 : 
			
			this.dinero += Incentivo.incentivoBase;
			double dineroTrans = this.getViaje().getTransportadora().getDinero();
			double dineroTransportadoraluegoDeIncentivo = dineroTrans - Incentivo.incentivoBase;
			this.getViaje().getTransportadora().setDinero(dineroTransportadoraluegoDeIncentivo);
			break;
			
		case 10 : 
			
			this.dinero += Incentivo.incentivoBase * 2;
			dineroTrans = this.getViaje().getTransportadora().getDinero();
			dineroTransportadoraluegoDeIncentivo = dineroTrans - (Incentivo.incentivoBase*2);
			this.getViaje().getTransportadora().setDinero(dineroTransportadoraluegoDeIncentivo);
			break;
			
		default:
			
			this.dinero = Incentivo.incentivoBase * 3;
			dineroTrans = this.getViaje().getTransportadora().getDinero();
			dineroTransportadoraluegoDeIncentivo = dineroTrans - (Incentivo.incentivoBase*3);
			this.getViaje().getTransportadora().setDinero(dineroTransportadoraluegoDeIncentivo);
			break;
	
		}
			
	}
	
	/**
	 * Método que nos sirve para saber si se puede bonificar a un pasajero o no
	 * @return viajeMismaTransportadora, de esto depende la bonificacion
	 */
	
	public int verificarBonificacion() {
		
		int viajesMismaTransportadora = 0;
		
		for (Viaje v : this.getHistorial()) {
			
			if (v.getTransportadora().getNombre().equalsIgnoreCase(this.getViaje().getTransportadora().getNombre())) {
				
				viajesMismaTransportadora += 1;
				
			}
			
			}
		
		return viajesMismaTransportadora;
	}
	
	/**
	 * Método sobrecargado de la clase persona, permite aplicarle descuento al valor del pasaje que pagará el pasajero
	 * @return el Viaje que tomará el pasajero
	 */
	
	public Viaje tomarViaje(Destino destino, String fecha, Terminal terminal) {
		
		// Se llama al método elegir destino, para añadir al pasajero a la lista de pasajeros de la transportadora que hace viajes al destino que va el pasajero
				this.elegirDestino(destino, terminal);
				ArrayList <Viaje> DisponiblesATomar = this.verDisponibilidad(destino, fecha, terminal); // Se crea una lista de los posibles viajes que se pueden tomar
				Viaje viajeElegido = DisponiblesATomar.get(0); // Se inicia con el viaje ubicado en la primera posición de la lista
				
				// Se hace este for para encontar el viaje con la tarifa más barata
				for (Viaje viaje: DisponiblesATomar) {
					
					if (viaje.getTarifa() < viajeElegido.getTarifa()) {
						
						viajeElegido = viaje;
					}
				}
				
				// Se verifica que el pasajero tenga suficiente dinero para pagar el viaje
				
				if (this.dinero >= viajeElegido.getTarifa()) {
					
					// Se calcula la tarifa del viaje
					double tarifa = ((viajeElegido.getTarifa()) - (this.getTipo().getDescuento()*viajeElegido.getTarifa()));
					
					viajeElegido.getPasajeros().add((Pasajero)this); // Se añade a la lista de pasajeros del viaje, caso de especialización
					this.historial.add(viajeElegido); // Se añade el viaje a su historial 
					this.dinero = dinero - tarifa; // Se le descuenta el dinero 
					
					// Se crea el for para eliminar al pasajero de la lista de pasajeros de la transportadora a la cuál estaba asociado, esto por que ya está en un viaje
					
					for (Transportadora transportadora : terminal.getTransportadoras()) {
						
						if (transportadora.getDestinoAsignado().name().equals(viajeElegido.getLlegada().name())) {
							
							for (Pasajero pasajero: transportadora.getPasajeros()) {
								
								if (pasajero.getId() == this.getId()) {
									
									transportadora.getPasajeros().remove(pasajero);
								}
							}
							
							
						}
					}
					
					return viajeElegido;	
				}
				
				
				return null;
				
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

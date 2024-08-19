package gestorAplicacion.usuarios;
import java.util.ArrayList;
import java.util.Random;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.Incentivo;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
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
	
	private static final long serialVersionUID = 1L;
	private TipoPasajero tipo; // Tipo de pasajero asociado al pasajero
	private Viaje viaje;
	
	public Pasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero, double dinero) { // falta Inicializar varios de los atributos que hereda de persona
		super(id, edad, nombre, genero, dinero);
		
		this.tipo = tipo;
		
	}
	
	public Pasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero) { // falta Inicializar varios de los atributos que hereda de persona
		super(id, edad, nombre, genero);
		
		this.tipo = tipo;
		
	}
	
	/**
	 * Constructor para la clase persona
     * @param tipo, tipo de pasajero.
     */
	
	//Constructor para nuevos usuarios
	
	public Pasajero(TipoPasajero tipo) {
		this.tipo = tipo;
	}

	
	/**
	 * Este método elige el viaje con la tarifa más económica.
	 * @param destinoDeseado Nombre del destino deseado.
	 * @param cantidad Asientos solicitados.
	 * @return El viaje más barato que cumple con los criterios.
	 */

	
	
	/**
	 * Este método elige el viaje con la tarifa más económica.
	 * @param destinoDeseado Nombre del destino deseado.
	 * @param cantidad Asientos solicitados.
	 * @return El viaje más barato que cumple con los criterios.
	 */
	
	
	public static Pasajero nuevoPasajero(TipoPasajero tipo, int id, int edad, String nombre, char genero, double dinero) {
		return new Pasajero(tipo, id, edad, nombre, genero, dinero);
	}

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
		 double descuento = (this.viaje.getTarifa()-(viaje.getTarifa() * this.getTipo().getDescuento()));
		 this.getViaje().getTransportadora().setDinero(dineroTransportadora - (viaje.getTarifa() * this.getTipo().getDescuento()));
		 this.setDinero(this.getDinero()- descuento);
		 
	}
	
	public double obtenerValorDescontado() {
		
		double valorDescontado = (this.viaje.getTarifa()-(viaje.getTarifa() * this.getTipo().getDescuento()));
		return valorDescontado;
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


}

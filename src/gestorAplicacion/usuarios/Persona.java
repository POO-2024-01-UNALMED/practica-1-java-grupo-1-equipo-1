package gestorAplicacion.usuarios;
import java.util.ArrayList;
import java.util.Map;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.administrativo.Factura;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;


/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una persona la cual tiene un id, una edad, nombre, genero, dinero, historial, experiencia y una factura esta clase 
 *  es abstracta, su implementación principalmente es para las relaciones de herencia y métodos abstractos. 
 */

public abstract class Persona {
	
	protected int id; // Id de la persona
	protected int edad; // Edad de la persona
	protected String nombre; // Nombre de la persona
	protected char genero; // Género de la persona
	protected ArrayList <Viaje> historial = new ArrayList<>(); // Historial de viajes de la persona
	protected int experiencia; // Este atributo será utilizado por las clases que heredan de persona y tendrá diferentes implementaciones en estas
	protected int dinero; // Dinero de la persona
	protected ArrayList <Factura> facturas = new ArrayList<>(); // Facturas asociadas a la persona
	
	
	public Persona() {
		
	}
	
	/**
	 * Constructor para la clase persona
     * @param id, id de la persona.
     * @param edad, edad de la persona
     * @param nombre, nombre de la persona
     * @param genero, genero de la persona
     * @param historial, historial de viaje de la persona
     * @param experiencia, atributo que se usará en las clases hijas
     * @param dinero, dinero de la persona
     */
	
	public Persona(int id, int edad, String nombre, char genero, ArrayList<Viaje> historial, int experiencia, int dinero) {
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.genero = genero;
		this.historial = historial;
		this.experiencia = experiencia;
		this.dinero = dinero;
	}

	/**
	 * Método para obtener el historial de viajes que ha tomado una persona.
	 * @return el historial de viajes de la persona.
	 */
	
	public String verHistorialViajes() {
		
		String resultado = "";
		
		if (this.historial.size() != 0) {
			
			for (Viaje v : this.historial) {
				
				resultado += "Id : " + v.getId() + 
						"\nTarifa : " + v.getTarifa() +
						"\nOrigen : " + v.getDestino() +
						"\nDestino : " + v.getFinalDestino() + 
						"\nHora de inicio : " + v.getHora() +
						"\nDuración : " + v.getDuracion() + 
						"\nFecha : " + v.getFecha()+ "\n\n";
			}
			
			return resultado;
		}
		
		return this.nombre + "no ha viajado a ningún lado";
	}
	
	/**
	 * Método que nos sirve para mostrar un String con la información de una factura que esté asociada 
	 * con objetos que pertenezcan a las subclases de persona.
	 * @return String con la información de la factura en caso de que sea encontrada, de lo contrario se retorna que el objeto no posee la factura
	 */
	
	public String mostrarFactura(Factura factura) {
		
		// Se verifica si en la lista de facturas, se encuentre la factura que se quiere mostrar
		
		if (this.getFacturas().contains(factura)) {  
			
			// Se verifica si el objeto es una instancia de pasajero, sino se verifica si es de tipo conductor, ya que dependiendo el objeto
			// Este tiene diferente información en la factura
				
			if (this instanceof Pasajero) {
				
				return "Numero de su factura: " + factura.getNumeroFactura() + 
						"\nTotal:" + factura.getTotal() + 
						"\nPasajero: " + factura.getPasajero() + 
						"\nTerminal: " + factura.getTerminal().getNombre() +
						"\nConductor: " + factura.getConductor().getNombre() +
						"\nViaje: " + factura.getViaje().getFinalDestino() + 
						"\nVehículo: " + factura.getVehiculo().getModelo() +
						"\nTransportadora: " + factura.getTransportadora().getNombre();		
			}
			
			else if(this instanceof Conductor) {
				
				return "Numero de su factura: " + factura.getNumeroFactura() + 
						"\nTotal:" + factura.getTotal() + 
						"\nTransportadora: " + factura.getTransportadora().getNombre() +
						"\nVehículo conducido : " + factura.getVehiculo().getModelo();
							
			}
			
			// Por último, si no es instancia de persona ni de conductor, entonces será instancia de mecánico
			
			return "Numero de su factura: " + factura.getNumeroFactura() + 
					"\nTotal:" + factura.getTotal() + 
					"\nTransportadora: " + factura.getTransportadora().getNombre() +
					"\nTaller: " + factura.getTaller().getNombre();
			
		}
		
		// Si no se llega a encontrar dicha factura
		
		return this.nombre + " no tiene dicha factura";
			
	}

	// Método Abstracto, tendrá diferentes implementaciones en las subclases de persona
	
	public abstract String identificarse();
		
		
	/**
	 * Método que nos sirve para calcular el dinero que ha gastado una persona o un pasajero en base a las facturas que adquirió
	 * y con base a esto descontarlo de su dinero
	 * @return El dinero que le quedó
	 */
	
	public int consultarDinero() {
		
		int dinero_gastado = 0;
		
		// Se verifica que la lista de las facturas no esté vacia
		
		if (!this.facturas.isEmpty()) {
			
			for (Factura factura: this.getFacturas()) {
				
				dinero_gastado += factura.getTotal();
				
			}
			
			this.dinero = dinero - dinero_gastado;
			return dinero ;
				
		}else {
			
			return dinero;
		}
				
	}
	
	/**
	 * Método que nos sirve para que un pasajero pueda tomar un viaje con base a un destino, una fecha y una terminal especificada
	 * @param destino, el destino al cuál viajara el pasajero
	 * @param fecha, la fecha en la cuál viajará el pasajero
	 * @param terminal, la terminal en la cual el pasajero tomará el viaje
	 * @return el viaje que tomó el pasajero
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
			
			viajeElegido.getPasajeros().add((Pasajero)this); // Se añade a la lista de pasajeros del viaje
			this.historial.add(viajeElegido); // Se añade el viaje a su historial 
			this.dinero = dinero - (int)viajeElegido.getTarifa(); // Se le descuenta el dinero
			
			// Se crea el for para eliminar al pasajero de la lista de pasajeros de la transportadora a la cuál estaba asociado, esto por que ya está en un viaje
			
			for (Transportadora transportadora : terminal.getTransportadoras()) {
				
				if (transportadora.getDestinoAsignado().equals(viajeElegido.getFinalDestino())) {
					
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
	
	/**
	 * Método que nos sirve para que una persona pueda reservar un viaje para él y para las personas que lo acompañarán en dicho viaje
	 * es decir que el viaje se convierte en un viaje exclusivo, en el cuál solo viaja la persona que lo reservó y las que lo acompañarán
	 * @param destino, el destino al cuál viajará la persona y sus acompañantes
	 * @param fecha, la fecha en la cuál viajará la persona y sus acompañantes
	 * @param pasajeros, lista con los pasajeros que acompañaran a la persona en el viaje, esta lista lo incluye a él también
	 * @param terminal, la terminal en la cual la persona y sus acompañantes tomarán el viaje
	 * @return el viaje que reservó la persona
	 */
	
	
	public Viaje reservarViaje(Destino destino, String fecha, ArrayList <Pasajero> pasajeros, Terminal terminal) {
		
		double costo = 0;
		Viaje viajeReservado = null;
		ArrayList <Viaje> viajesReservables = new ArrayList<>(); 
		ArrayList <Viaje> viajesReservablesOtraFecha = new ArrayList<>(); // Por si no hay viajes para la fecha que el pasajeró necesita
				
		for (Viaje viaje: terminal.getViajes()) {
			
			// Se comprueba que el destino del viaje sea igual al destino al cuál viajará la persona con los acompañantes, lo mismo se hace con la fecha
			// Y al final se comprueba se el viaje cuenta con la capacidad necesaria para transportar a los pasajeros
			
			if (viaje.getFinalDestino().equals(destino) && viaje.getFecha().equals(fecha) && viaje.getVehiculo().getCapacidad() >= pasajeros.size()) {
					
				viajesReservables.add(viaje);
				
					
			}else if (viaje.getFinalDestino().equals(destino)&& !viaje.getFecha().equals(fecha) 
					&& viaje.getVehiculo().getCapacidad() >= pasajeros.size())   {
				
				viajesReservablesOtraFecha.add(viaje);
					
				
			}
				
		}
		// Se verifica que la lista no esté vacía, lo que quiere decir que si hay viaje para la fecha que especificó la persona
		if (!viajesReservables.isEmpty()) {
			
			viajeReservado = viajesReservables.get(0);
			
			// Se busca el viaje más barato
			
			for (Viaje v : viajesReservables) {
				
				if (v.getTarifa() < viajeReservado.getTarifa()) {
					
					viajeReservado = v;
					
					
				}
				
			}
			// Se calcula el costo del viaje
			costo = viajeReservado.getTarifa() * pasajeros.size();
			
			// Se verifica que la persona que va a reservar el viaje tenga suficiente dinero para pagarlo
			if (costo <= this.dinero) {
				
				viajeReservado.setPasajeros(pasajeros); 
				terminal.getViajes().remove(viajeReservado); // Se elimina el viaje, ya que esté no admitirá más pasajeros
				this.dinero = dinero - (int) costo;
			}
			
			
			
		}else {
			
			viajeReservado = viajesReservablesOtraFecha.get(0);
			
			for (Viaje v : viajesReservablesOtraFecha) {
				
				if (v.getTarifa() < viajeReservado.getTarifa()) {
					
					viajeReservado = v;
					
					
				}
				
				
			}
			
			costo = viajeReservado.getTarifa() * pasajeros.size();
			
			if (costo <= this.dinero) {
				
				viajeReservado.setPasajeros(pasajeros); 
				terminal.getViajes().remove(viajeReservado);
				this.dinero = dinero - (int) costo;
			}
		}
		
		// Se elimina el viaje de la transportadora que se encargaba de este
		
		for (Transportadora transportadora : terminal.getTransportadoras()){
			
			if (transportadora.getDestinoAsignado().equals(viajeReservado.getFinalDestino())) {
				
				for (Viaje v: transportadora.getViajesAsignados()) {
					
					if (v.getId() == viajeReservado.getId()) {
						
						transportadora.getViajesAsignados().remove(v);
					}
				}
				
				
			}
			
		}
		
		
		return viajeReservado;
	}
	
	/**
	 * Método que nos permite ver la disponibilidad de ciertos viajes, con base a un destino, fecha y una terminal específica
	 * @param destino, el destino del cual la persona quiere ver viajes disponibles
	 * @param fecha, la fecha a la cual la persona quiere ver viajes disponibles
	 * @param terminal, la terminal en la cual la persona consultará por los viajes disponibles
	 *
	 */ 
	
	
	public ArrayList <Viaje> verDisponibilidad(Destino destino, String fecha, Terminal terminal){
		
		ArrayList <Viaje> viajesDisponibles = new ArrayList<>();
		ArrayList <Viaje> viajesDisponiblesEnOtraFecha = new ArrayList<>();
		
		for (Viaje viaje : terminal.getViajes()) {
			
			// Se compara el destino, la fecha y se evalúa si la capacidad del viaje no ha llegado a su tope
			if (viaje.getFinalDestino().equals(destino) && viaje.getFecha().equals(fecha) && viaje.getPasajeros().size() < viaje.getVehiculo().getCapacidad()) {
				
				viajesDisponibles.add(viaje);
				
				
			}else if (viaje.getFinalDestino().equals(destino)&& (!viaje.getFecha().equals(fecha))&& viaje.getPasajeros().size() < viaje.getVehiculo().getCapacidad()) {
				
				viajesDisponiblesEnOtraFecha.add(viaje);
				
			}
			
			
		}
		
		if (!viajesDisponibles.isEmpty()) {
			
			return viajesDisponibles;
			
		}
		
		return viajesDisponiblesEnOtraFecha;
		
	}
	
	/**
	 * Método que nos permite agregar a una persona a la lista de pasajeros de una transportadora teniendo en cuenta el destino al que esta va
	 * @param destino, el destino al cuál viajará la persona 
	 * @param terminal, la terminal en la cual la persona tomará el viaje
	 *
	 */ 
	
	public void elegirDestino(Destino destino, Terminal terminal) {
		
		
		
		for (Destino D: terminal.getDestinos()) {
			
			// Se verifica que en la lista de destinos de la terminal este esté presente
			
			if (D.equals(destino)) {
				
				for (Transportadora transportadora : terminal.getTransportadoras()) {
					
					// Si se encuentra a una transportadora con el destino al que va la persona, esta se añade a la lista de pasajeros de dicha transportadora
					if (destino.equals(transportadora.getDestinoAsignado())){
						
						transportadora.getPasajeros().add((Pasajero)this);
					}
					
				}
				
			}
			
			
			
			
		}
		
	}

	// METODOS GETTERS Y SETTERS
	
	
	/**
	 * Establece o modifica el id de la persona.
	 * @param id, el id de la persona.
	 */
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	/**
	 * Método para obtener el id de la persona.
	 * @return el id de la persona.
	 */
	
	public int getId() {
		
		return id;
		
	}
	
	/**
	 * Establece o modifica la edad de la persona.
	 * @param edad, la edad de la persona.
	 */
	
	public void setEdad(int edad) {
		
		this.edad = edad;
		
	}
	
	/**
	 * Método para obtener la edad de la persona.
	 * @return la edad de la persona.
	 */
	
	public int getEdad() {
		
		return edad;
		
	}
	
	/**
	 * Establece o modifica el nombre de la persona.
	 * @param nombre, el nombre de la persona.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 * Método para obtener el nombre de la persona.
	 * @return el nombre de la persona.
	 */
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	/**
	 * Establece o modifica el genero de la persona.
	 * @param genero, el género de la persona.
	 */
	
	public void setGenero(char genero) {
		
		this.genero = genero;
		
	}
	
	/**
	 * Método para obtener el genero de la persona.
	 * @return el género de la persona.
	 */
	
	public char getGenero() {
		
		return genero;
		
	}
	
	/**
	 * Establece o modifica el historial viajes de la persona.
	 * @param historial, el historia de viajes de la persona.
	 */
	
	public void setHistorial(ArrayList<Viaje> historial) {
		
		this.historial = historial;
		
	}
	
	/**
	 * Método para obtener el historial de viajes de la persona.
	 * @return el historial de viajes de la persona.
	 */
	
	public ArrayList<Viaje> getHistorial() {
		
		return historial;
		
	}
	
	
	/**
	 * Establece o modifica el dinero de la persona.
	 * @param dinero, el dinero de la persona.
	 */
	
	public void setDinero(int dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método para obtener el dinero de la persona.
	 * @return el dinero de la persona.
	 */
	
	public int getDinero() {
		
		return dinero;
	
	}
	
	/**
	 * Establece o modifica las facturas de la persona.
	 * @param factura, la factura de la persona.
	 */
	
	public void setFacturas(ArrayList<Factura> facturas) {
		
		this.facturas = facturas;
		
	}
	
	/**
	 * Método para obtener las facturas de la persona.
	 * @return la lista de las facturas que tiene la persona.
	 */
	
	public ArrayList<Factura> getFacturas() {
		
		return facturas;
		
	}
	
	/**
	 * Método para obtener la experiencia de la persona.
	 * @return la experiencia de la persona.
	 */
    public int getExperiencia() {
        return experiencia;
    }

	/**
	 * Establece o modifica la experiencia de la persona.
	 * @param experiencia, la experiencia de la persona.
	 */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    
}

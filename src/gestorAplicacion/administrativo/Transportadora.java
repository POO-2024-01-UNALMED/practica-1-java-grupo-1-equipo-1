package gestorAplicacion.administrativo;
import java.util.ArrayList;
import java.io.Serializable;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.constantes.*;
import gestorAplicacion.usuarios.Persona;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una transportadora, la cuál tiene su respectivo nombre, dinero, una lista de conductores, pasajeros, vehículos, viajes,
 *  un destino asiginado y una terminal, esta clase nos servirá para organizar los distitnos viajes, asociar a los conductores, hacerle su respectivo
 *  pago y monitorear a los distintos vehículos asociados a esta.
 */

public class Transportadora implements Incentivo, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Atributos
	
	private String nombre; // Nombre de la transportadora
	private double dinero; // Dinero de la transportadora 
	private ArrayList <Conductor> conductores = new ArrayList<>(); // Lista de conductores de la transportadora
	private ArrayList <Conductor> conductoresRegistrados = new ArrayList<>(); // Lista de conductores registrados de la transportadora 
	private ArrayList <Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros de la transportadora
	private ArrayList <Vehiculo> vehiculos = new ArrayList<>(); // Lista de vehículos asociados a la transportadora
	private ArrayList <Viaje> viajesAsignados = new ArrayList<>(); // Viajes de la transportadora
	private Destino destinoAsignado; // Destino asignado a la transportadora, lugar hacia donde esta viajará
	private Terminal terminal; // Terminal en donde opera la transportadora
	private Taller taller; //Taller de la transportadora
	private ArrayList <Destino> destinos = new ArrayList<>(); // ???
	private ArrayList <Viaje> viajesTerminados = new ArrayList<>(); // Viajes que ha concluido la terminal
	private Persona dueño; // Dueño de la transportadora
	private final double estrellas; // Permite calcular la tarifa de los buses
	private static ArrayList <Transportadora> transportadoras = new ArrayList <Transportadora>();
	private String fechaPago; // Fecha en la cual la transportadora le paga a la termina
	private int numeropagosRealizados; 
	private ArrayList <Conductor> conductoresDespedidos = new ArrayList<>(); // Lista de conductores registrados de la transportadora

	
	//Constructor con todos los parámetros
	
	public Transportadora(String nombre, double dinero, ArrayList<Conductor> conductores, ArrayList<Pasajero> pasajeros, ArrayList<Vehiculo> vehiculos, ArrayList<Viaje> viajesAsignados, Destino destinoAsignado, Terminal terminal,
						  ArrayList<Destino> destinos, ArrayList<Viaje> viajesTerminados,Persona dueño, double estrellas) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.conductores = conductores;
		this.pasajeros = pasajeros;
		this.vehiculos = vehiculos;
		this.viajesAsignados = viajesAsignados;
		this.destinoAsignado = destinoAsignado;
		this.terminal = terminal;
		this.destinos = destinos;
		this.viajesTerminados = viajesTerminados;
		this.estrellas = estrellas;
		this.dueño = dueño;
		transportadoras.add(this);
		

		
	}
	
	public Transportadora(String nombre, double dinero, ArrayList<Conductor> conductores, ArrayList<Pasajero> pasajeros, ArrayList<Vehiculo> vehiculos, ArrayList<Viaje> viajesAsignados, Destino destinoAsignado, Terminal terminal, Taller taller,
			  ArrayList<Destino> destinos, ArrayList<Viaje> viajesTerminados,Persona dueño, double estrellas) {
		
		this (nombre, dinero, conductores, pasajeros, vehiculos, viajesAsignados, destinoAsignado, terminal, destinos, viajesTerminados, dueño, estrellas);
				  



	}
	
	//Dejarlo mientras las Pruebas 
	public Transportadora() {
		this.estrellas = 1;		
		transportadoras.add(this);
	}
	
	
	public Viaje elegirViajeTransportadora(ArrayList<Viaje> viajes) {
		
		for (Viaje viaje: viajes) {
			if(viaje.getVehiculo().getTransportadora()==this) {
				
				return viaje;
			}
		}
		return null;
	}

	/**
	 * Método que nos sirve para mostrar un String con la información de una factura que esté asociada 
	 * con el dueño de una transportadora.
	 * @param factura, la factura que se quiere mostar 
	 * @return String con la información de la factura en caso de que sea encontrada, de lo contrario se retorna que el objeto no posee la factura
	 */

	public String mostrarFactura(Factura factura) {
				
				// hablar para que la transportadora tenga dueño y ese dueño sea el encargado de las facturas
			if (dueño.getFacturas().contains(factura)) {
					
					return "Numero de su factura: " + factura.getNumeroFactura() + 
							"\nTotal:" + factura.getTotal() +
							"\nTerminal: " + factura.getTerminal();
					
				}
				
				return this.dueño.getNombre() + "no tiene dicha factura";
			}
		
	
	/**
	 * Metodo para contratar un conductor el cual se agregara
	 * a la lista de conductores de la transportadora.
	 * @param Conductor a contratar
	 */
	
	public String contratarConductor(Conductor conductor) {
		
		if (conductor == null) {
			return "No se ha encontrado el conductor";
		} else {
			if (conductor.getExperiencia() >= 5) {
				if (conductor.getEstadoLicencia()) {
					conductor.reinicioAtributos();
					this.getConductores().add(conductor);
					this.getconductoresRegistrados().remove(conductor);
					return "Se contrato a " + conductor.getNombre() + " exitosamente.";
				} else {
					return "No se pudo contratar a " + conductor.getNombre() + "porque no tiene licencia activa";
				}
			} else {
				return "No se pudo contratar a " + conductor.getNombre() + " porque tiene menos de cinco años de experiencia";
			}
		}
		
	}
	
	public String contratarConductor(int cedula) {
		Conductor conductor = null;
		for (Conductor conduc:conductoresRegistrados) {
			if (conduc.getId() == cedula)
				conductor = conductoresRegistrados.get(conductoresRegistrados.indexOf(conduc));
				continue;
		}
		return contratarConductor(conductor);
				
	}
	
	/**
	 * Metodo para despedir conductor al cual se le remueve el vehiculo,
	 *  se le remueve de la lista de conductores de la transportadora y 
	 * se agrega a la lista de conductores despedidos elimina el conductor.
	 * Para esto, primero se verifica que no tenga viajes programados y 
	 * que su vehiculo tenga almenos 2 conductores. Si existe algun inconveniente
	 * el metodo devolvera un valor diferente para cada caso.
	 * @param Conductor a despedir*/
	
	public String despedirConductor(int id) {
		Conductor conductor =  encontrarConductor(id);
		
		if (conductor.getHorario().size() == 0) {
			if (conductor.getVehiculo() == null) {
				conductor.quitarVehiculo();
				conductor.getTransportadora().getConductores().remove(conductor);
				conductor.indemnizar();
				conductor.reinicioAtributos();
				return "Se ha despedido a " + conductor.getNombre();
				}
			if (conductor.getVehiculo().getConductores().size() >= 2 ) {
				conductor.quitarVehiculo();
				conductor.getTransportadora().getConductores().remove(conductor);
				conductor.indemnizar();
				conductor.reinicioAtributos();
				return "Se ha despedido a " + conductor.getNombre();
				} else return "No es posible porque no hay mas conductores asigandos al vehiculo asociado al conductor";
			} else return "No es posible porque el conductor tiene viajes programados";
				
			}
	
	/**
	 * Metodo que nos permite saber si una transportadora puede efectuar el respectivo pago a la terminal
	 * dicha terminal cobra cierta comisión por cada viaje que realiza la transportadora
	 * por ende se contarán los viajes que realizó exitosamente la terminal 
	 * y ese valor se multiplicará por la comisión para calcular el valor y así usar ese valor para saber si es posible dicho pago
	 */
	
	public boolean verificarPagoTerminal() {
		
		boolean pago = false; // Nos servirá para saber si el pago es posible o no
		
		double calcularValorAPagar = this.terminal.getComision()*this.getViajesTerminados().size();
		
		if (this.getDinero() > calcularValorAPagar) {
			
			pago = true;
			
			return pago;
		}
		
		else {
			
			 
			return false;
			
		}
				
		}
	
	/**
	 * Método que nos sirve para obtener el valor que le tiene que pagar la transportadora a la terminal  
	 * donde esta presta sus servicios  
	 * @return calcularValoraPagar, el valor que le tiene que pagar la transportadora a la terminal
	 */

	public double RetornarValorAPagarTerminal() {
		
		double calcularValoraPagar = this.terminal.getComision()*this.getViajesTerminados().size(); // La terminal cobra una comisión por cada viaje hecho por la transportadora
		
		if(this.verificarPagoTerminal()) { // se llama al método verificarPagoTerminal para comprobar si la transportadora cuenta con suficiente dinero
		
			return calcularValoraPagar;
			
		}else { return 0.0;}
		
	
		
		
	}
		
	/**
	 * Verifica la capacidad de la terminal y el dinero de la transportadora para poder agregar un vehiculo
	 * @param vehiculo a comprar
	 */
	
	public void agregarVehiculo(Vehiculo vehiculo) {
		
		if (vehiculo.getPrecio() <= this.dinero && this.terminal.getCapacidadVehiculos() > this.terminal.getCantidadVehiculos()) {
			
			this.vehiculos.add(vehiculo);
			this.terminal.agregarVehiculoTerminal(vehiculo);
			this.reducirDinero(vehiculo.getPrecio());
			this.terminal.setCantidadVehiculos(this.terminal.getCantidadVehiculos() + 1);
			
		}
		
	}
	
	/**
	 * Remueve un vehiculo de la lista de vehiculos de la transportadora
	 * @param vehiculo a remover
	 */
	
	public void removerVehiculo(Vehiculo vehiculo) {
			
		vehiculos.remove(vehiculo);
		this.terminal.removerVehiculoTerminal(vehiculo);
		
	}
	
	
	public void realizarMantenimiento() {
		
		// Implementación pendiente
		
		
	}
	
	public ArrayList <Vehiculo> disponibilidadVehiculos(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public ArrayList <Factura> reporteFinanciero(){
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	/**
	 * Paga x cantidad de dinero a una persona
	 * @param persona a pagar
	 * @param cantidad de dinero a pagar
	 */
	
	public void pagarPersona(Persona persona, int cantidad) {
		
		
		this.reducirDinero(cantidad);
		persona.aumentarDinero(cantidad);
		
		
	}
	
	public boolean realizarRevision() {
		
		// Implementación pendiente
		
		return false;
	
		
	}
	
	public boolean estadoVehiculo() {
		
		// Implementación pendiente
		
		return false;
		
		
	}
	
	public void asignarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public void designarVehiculo() {
		
		// Implementación pendiente
		
		
	}
	
	public Factura ventaViaje() {
		
		// Implementación pendiente
		
		return null;
		
		
	}
	
	public void pagarConductores() {
		
			for(Conductor c: this.getConductores()) {
				
				if (c.getHorario().size() == 0) { // O no tiene viajes asignados o ya los completó, tenemos en cuenta que si los tiene asignados
					
					c.bonificacion();
					c.descuento();
					c.aumentarDinero(800000);
				
				
			}
				
		}
			
			numeropagosRealizados++;
		
		
		
	}
	
	public void pagarConductor(Conductor c) {
		
		
		if (verificarCumplimientoHorario(c)) { // O no tiene viajes asignados o ya los completó, tenemos en cuenta que si los tiene asignados se le paga al conductor por su cumplimiento de horario
			
			c.bonificacion();
			c.descuento();
			c.aumentarDinero(800000);

	}
	
	
	
}
	
	public boolean verificarCumplimientoHorario(Conductor c) {
		
		if (c.getHorario().size() == 0) {
			
			return true;
		}
		
		return false;
		
		
	}
	
	/**
	 * Aumenta el dinero de la transportadora
	 * @param dinero a aumentar
	 */
	
	public void aumentarDinero (double dinero) {
		
		this.dinero = this.dinero + dinero;
	}
	
	/**
	 * Reduce el dinero de la terminal
	 * @param dinero a reducir
	 */
	
	public void reducirDinero (double dinero) {
		
		this.dinero = this.dinero - dinero;
	}
	
	/**
	 * Metodo para calcular el dinero de la transportadora
	 * teniendo en cuenta la cantidad de pasajeros transportados y la tarifa asociada al viaje
	 * 
	 */
	
	public void calcularDineroTransportadora() { 
		
		int cantPasajerosTransportados = 0; 
		double tarifaViajes = 0; 
		
		for (Viaje v: viajesTerminados) {
			
			if (v.getPasajeros().size()>0) { 
				
			cantPasajerosTransportados += v.getPasajeros().size(); 
			tarifaViajes += v.getTarifa(); 
			
			} 
		} 
		
		this.dinero = cantPasajerosTransportados * tarifaViajes; 
		
	}
	
	/**
	 * Método que nos sirve para calcular el descuento que le hace la terminal a la transportadora por el cobro que esta le efectúa y a su vez 
	 * se cancela dicho cobro
	 * 
	 */
	
	@Override
	public void descuento () {
		
		double valorPagar = this.RetornarValorAPagarTerminal(); // Se llama al método RetornarValorApagarTerminal para saber cuánto se le tiene que pagar
		int verificacionViajes = 0;
		
		if (this.getViajesAsignados().size() == this.getViajesTerminados().size()) { // La transportadora cumple con su horario
			
			verificacionViajes = 1; // se le descuenta por cumplir con sus viajes
			
		}
		
		if (valorPagar > 0) {
			
			switch(verificacionViajes) {
			
			case 0:
				
				this.dinero -= valorPagar;
				this.terminal.setDinero(this.getTerminal().getDinero()+ valorPagar);
				break;
				
			case 1:
				
				this.dinero -= (valorPagar - (valorPagar*0.05));
				this.terminal.setDinero(this.getTerminal().getDinero()+ valorPagar);
				break;
			
			
			}
			
			
		}
		
		
		
		
	}
	
	public String mostrarConductRegistrados() {
		String mensaje = "";
		for (Persona conductor : this.conductoresRegistrados) {
			mensaje += "Nombre: " + conductor.getNombre()+ "  #ID: " + conductor.getId() + "\n";
		}
		return mensaje;
	}
	
	/**
	 * Método que nos sirve para calcular la bonificación que le hace la terminal a la transportadora
	 * teninedo en cuenta que la transportadora ha sabido mantener al los clientes, los cuales usan mucho su servicio
	 
	 */
	@Override
	public void bonificacion() {
		
		double dineroTerminal = this.getTerminal().getDinero();
		double dineroaRestarTerminal = 0; /** Ya que esta bonificará a la transportadora 
		por cada pasajero que dicha transportadora haya bonificado, ya que el cliente está viajando mucho en la transportadora, por ende el incentivo
		*/
		for (Pasajero p: this.getPasajeros()) {
			
			if (p.verificarBonificacion()!= 0) {
				
				dineroaRestarTerminal += Incentivo.incentivoBase;
				this.dinero += dineroaRestarTerminal;
				
				
			}

		}
		
		this.getTerminal().setDinero(dineroTerminal-dineroaRestarTerminal);
		
	}
	
	/**
	 * Metodo para que devolvera un string con la lista de
	 * conductores activos de una determinada trasportadora
	 * @return string con la lista de conductores activos*/
	
	public String mostrarConductActivos() {
		String mensaje = "";
		for (Conductor conductor : this.conductores) {
			mensaje += "Nombre: " + conductor.getNombre()+ "  #ID: " + conductor.getId() + "\n";
		}
		return mensaje;
	}
	
	/**
	 * Metodo para despedir un conductor de la lista de conductores
	 * activos con su id.
	 * @param id , id del conductor a despedir*/

	public void despedirConductDesdeLista(int id) {
		for (Conductor conductor : this.conductores) {
			if (conductor.getId() == id) {
				despedirConductor(conductor.getId());
				return;
			}
		}
	}
	
	public Conductor encontrarConductor(int cedula) {
		for (Conductor driver:conductores) {
			if (driver.getId() == cedula) {
				return driver;
			} 
		} 
		return null;
	}
	
	public Vehiculo encontrarVehiculo(String placa) {
		String placaVehiculo = placa.toUpperCase();
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getPlaca().equals(placaVehiculo)) {
				return vehiculo;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que muestra los conductores disponibles de la terminal
	 * que no tengan un viaje asignado el mismo dia del viaje pasado como
	 * parametro y que los otros conductores asociados al vehiculo de cada
	 * conductor no tengan viajes asignados el mismo dia del pasado como parametro
	 * @return string con la lista de conductores disponibles*/
	
	public String conductoresDisponibles(Viaje v) {
		ArrayList<Conductor> conductoresLibres= new ArrayList<>();
		String mensaje = "";
		
		/*for (Conductor conductor: conductores) {
			
			for (Conductor driver: conductor.getVehiculo().getConductores()) {
				for (Viaje trip: driver.getHorario()) {
					if (trip.getDia() == v.getDia()) {
						return mensaje;
					} else {
						if (conductor.getHorario() == null) {
							conductoresLibres.add(conductor);
						}
						for (Viaje viaje: conductor.getHorario()) {
							if ( Math.abs((viaje.getDia().getValue() - v.getDia().getValue())) >= 1  ) {
								conductoresLibres.add(conductor);
							}	
						}
					}
				}
					
			}
		}*/
		
		for (Conductor conductor : conductores) {
			boolean value = false;
			boolean valor = true;
			
			if (conductor.getVehiculo().getTipo() == v.getVehiculo().getTipo()) {
				
				for (Conductor driver : conductor.getVehiculo().getConductores()) {
					
					if (conductoresLibres.contains(driver)) {
						
						continue;
						
					}
					
					if (driver.getHorario()==null) {
						
						value = true;
						conductoresLibres.add(driver);
						continue;
						
					}
					
					for (Viaje viaje: driver.getHorario()) {
						
						if ((viaje.getDia().getValue() - v.getDia().getValue()) == 0) {
							
							valor = false;
							break;
							
						} else {
							valor=true;
						}
					}
					
					if (valor) {
						
						conductoresLibres.add(driver);
						
					}
				}
			}
		}
		
		for(Conductor conductor:conductoresLibres) {
			mensaje += "Nombre: " + conductor.getNombre()+ "  #ID: " + conductor.getId() + "\n";
		}
		
		return mensaje;
		
	}
	
	 /**
     * Devuelve una lista de conductores disponibles en una fecha específica y un vehiculo especifico.
     * 
     * @param fecha La fecha para verificar disponibilidad.
     * @return Una lista de conductores disponibles en la fecha proporcionada.
     */
    public ArrayList<Conductor> conductoresDisponibles(String fecha, TipoVehiculo tipo) {
        ArrayList<Conductor> conductoresDisponibles = new ArrayList<>();
        
        for (Conductor conductor : this.conductores) {
        	if (conductor.getVehiculo().getTipo() == tipo) {
	            if (conductor.disponibleFecha(fecha)) {             // Verificar si el conductor está disponible en la fecha dada
	                conductoresDisponibles.add(conductor);         // Si el conductor está disponible, agregarlo a la lista de disponibles
	            }
        	}
        }
        return conductoresDisponibles;
    }
    
    /**
     * Devuelve una lista de tipos de vehículos disponibles en la transportadora.
     * 
     * Este método recorre todos los vehículos registrados en la transportadora y verifica
     * si cada vehículo está disponible. Si un vehículo está disponible, su tipo se añade
     * a una lista de tipos de vehículos disponibles, asegurando que no se repitan tipos.
     * Finalmente, el método devuelve la lista de tipos de vehículos únicos que están disponibles.
     * 
     * @return Una lista de tipos de vehículos que están disponibles en la transportadora.
     */
	public  ArrayList<TipoVehiculo> tiposVehiculosDisponible() {
        ArrayList<TipoVehiculo> tiposDisponibles = new ArrayList<>(); // Lista para almacenar los tipos de vehículos disponibles
        for (Vehiculo vehiculo : this.getVehiculos()) {
            if (vehiculo.disponibilidad()) {
                TipoVehiculo tipo = vehiculo.getTipo();
                if (!tiposDisponibles.contains(tipo)) {
                    tiposDisponibles.add(tipo);
                }
            }
        }
        return tiposDisponibles;
    }
	
	/**
	 * Metodo para mostrar los viajes disponibles que no sean el mismo dia
	 * o 7 dias despues del viaje.
	 * @return mensaje con la lista de viajes disponibles
	 * */
	
	public String mostrarViajesDisponibles(int d, TipoVehiculo tipo) {
		
		String mensaje = "";

		ArrayList<Viaje> viajesDisponibles = new ArrayList<Viaje>();
		
		for (Viaje viaje : getViajesAsignados()) {
			if ( Math.abs((viaje.getDia().getValue() - d)) >= 1  && (viaje.getVehiculo().getTipo() == tipo)) {
				viajesDisponibles.add(viaje);
				mensaje += viaje.detallesViaje();
			}
		}
		
		return mensaje;
	}
	
	/**
	 * Metodo para encontrar un viaje mediante el id en 
	 * los viajes de la terminal.
	 * @param id del viaje buscado
	 * @return viaje que coincide con el id*/
	
	public Viaje encontrarViaje(int id) {

		for (Viaje viaje : getViajesAsignados()) {
			if (viaje.getId() == id) {
				return viaje;
			}
		}
		return null;
	}
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Establece o modifica el nombre de la transportadora.
	 * @param nombre, el nombre de la transportadora.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 *Método para obtener el nombre de la transportadora.
	 * @return el nombre de la transportadora.
	 */
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	/**
	 * Establece o modifica el dinero de la transportadora.
	 * @param dinero, el dinero de la trasnportadora.
	 */
	
	public void setDinero(double dinero) {
		
		this.dinero = dinero;
		
	}
	
	/**
	 * Método para obtener el dinero de la transportadora.
	 * @return el dinero de la transportadora.
	 */
	
	public double getDinero() {
		
		return dinero;
	}
	
	/**
	 * Establece o modifica los conductores asociados a la transportadora.
	 * @param conductores, lista de conductores de la transportadora.
	 */
	
	public void setConductores(ArrayList <Conductor> conductores) {
		
		this.conductores = conductores;
		
	}
	
	/**
	 * Método para obtener la lista de los conductores de la transportadora.
	 * @return lista de conductores asociados a la transportadora.
	 */
	
	public ArrayList <Conductor> getConductores(){
		
		return conductores;
		
	}
	
	/**
	 * Establece o modifica la lista de pasajeros asociados a la transportadora.
	 * @param pasajeros, la lista de pasajeros asociados a la transportadors.
	 */
	
	public void setPasajeros(ArrayList <Pasajero> pasajeros) {
		
		this.pasajeros = pasajeros;
		
	}
	
	/**
	 * Método para obtener la lista de pasajeros asociados a la transportadora.
	 * @return la lista de pasajeros asociados a la transportadora.
	 */
	
	public ArrayList <Pasajero> getPasajeros(){
		
		return pasajeros;
		
	}
	
	/**
	 * Establece o modifica la lista de vehículos asociados a la transportadora.
	 * @param vehiculos, la lista de vehículos asociados a la transportadora.
	 */
	
	public void setVehiculos(ArrayList <Vehiculo> vehiculos) {
		
		this.vehiculos = vehiculos;
		
	}
	
	/**
	 * Método para obtener la lista de vehículos asociados a la transportadora.
	 * @return la lista de vehículos asociados a la transportadora.
	 */
	
	public ArrayList <Vehiculo> getVehiculos(){
		
		return vehiculos;
		
	}
	/**
	 * Establece o modifica la lista de los viajes asignados a la transportadora.
	 * @param viajeAsignado, lista con los viajes asignados a la transportadora.
	 */
	
	public void setViajesAsignados(ArrayList <Viaje> viajesAsignados) {
		
		this.viajesAsignados = viajesAsignados;
		
	}
	
	/**
	 * Método para obtener la lista de los viajes asignados a la transportadora.
	 * @return lista de los viajes asignados a la transportadora.
	 */
	
	public ArrayList <Viaje> getViajesAsignados(){
		
		return viajesAsignados;
		
	}
	
	/**
	 * Método para obtener el destino asignado a la transportadora.
	 * @return el destino asignado a la transportadora.
	 */
	
	
	public Destino getDestinoAsignado() {
		
		return destinoAsignado;
		
	}
	
	/**
	 * Establece o modifica el destino asignado a la transportadora.
	 * @param destinoAsignado.
	 */
	
	public void setDestinoAsignado(Destino destinoAsignado) {
		
		this.destinoAsignado = destinoAsignado;
		
	}
	
	/**
	 * Establece o modifica la terminal asociada a la tranportadora.
	 * @param terminal, la terminal asociada a la transportadora.
	 */
	
	public void setTerminal(Terminal terminal) {
		
		this.terminal = terminal;
		
	}
	/**
	 * Método para obtener la terminal a la cuál está asociada la transportadora.
	 * @return la terminal asociada a la transportadora.
	 */
	
	public Terminal getTerminal() {
		
		return terminal;
		
	}
	
	/**
	 * Método para obtener los destinos a la cuál está asociada la transportadora.
	 * @return los destinos asociados a la transportadora.
	 */

	public ArrayList<Destino> getDestinos() {
		return destinos;
	}
	
	/**
	 * Establece o modifica los destinos asociados a la tranportadora.
	 * @param los destinos asociados a la transportadora.
	 */
	

	public void setDestinos(ArrayList<Destino> destinos) {
		this.destinos = destinos;
	}
	
	/**
	 * Establece o modifica el dueño de la transportadora.
	 * @param dueño, el dueño de la transportadora.
	 */
	
	public void setDueño(Persona dueño) {
		
		this.dueño = dueño;
		
	}
	
	/**
	 * Método para obtener el dueño de la tranportadora.
	 * @return el dueño de la transportadora.
	 */
	
	public Persona getDueño() {
		
		return dueño;
		
	}

	/**
	 * Método para obtener los conductores despedidos.
	 * @return conductores despedidos de la transportadora
	 */
	
	public ArrayList<Conductor> getconductoresRegistrados() {
		
		return conductoresRegistrados;
		
	}

	/**
	 * Establece o modifica los conductores despedidos.
	 * @param conductores despedidos de la transportadora.
	 */
	
	public void setconductoresRegistrados(ArrayList<Conductor> conductoresRegistrados) {
		
		this.conductoresRegistrados = conductoresRegistrados;
		
	}
	
	/**
	 * Devuelve la cantidad de estrellas de la transportadora
	 * @return Numero de estrellas
	 */

	public double getEstrellas() {
	
		return estrellas;
	}
	
	/**
	 * Devuelve la cantidad de viajes terminados de la transportadora
	 * @return Cantidad de viajes de la transportadora
	 */
	
	public ArrayList<Viaje> getViajesTerminados(){
		
		return viajesTerminados;
		
	}

	/**
	 * @return taller de la transportadora
 	 */
	public Taller getTaller() {
		return taller;
	}

	/**
	 * @param Asigna un taller a la transportadora
	 */
	public void setTaller(Taller taller) {
		this.taller = taller;
	}
	
	public static ArrayList<Transportadora> getTransportadoras () {
		
		return transportadoras;
	}
	
	public static void setTrasportadoras (ArrayList<Transportadora> transportadoras) {
		
		Transportadora.transportadoras = transportadoras;
	}
	
	public String getFechaPago() {
		
		return this.fechaPago;
	}
	
	public void setFechaPago(String fechaPago) {
		
		this.fechaPago = fechaPago;
	}
	
	public int getNumeroDePagosRealizados() {
		
		return numeropagosRealizados;
	}
	
	public ArrayList <Conductor> getConductoresDespedidos(){
		
		return this.conductoresDespedidos;
		
		
	}

	
	
}
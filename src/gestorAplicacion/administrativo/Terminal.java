package gestorAplicacion.administrativo;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.tiempo.Tiempo;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.usuarios.Persona;

/**
 * 	Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 *  Esta clase representa a una terminal, la cual tiene su respectivo nombre, dinero, una capacidad de vehiculos, tiene unas sedes específica,
 *  esta cuenta con una cantidad de vehiculos, las transportadoras asociadas, las reservas de los viajes, los viajes disponibles, destinos disponibles
 *  una ubicación y finalmente un administrador, esta clase nos servirá para administrar todo el sistema de la terminal.   
 */

public class Terminal implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	// Atributos
	private String nombre; // Nombre de la terminal
	private static double dinero; // Dinero de la terminal 
	private int capacidadVehiculos; // capacidad de vehículos en la terminal
	public static int cantidadSedes = 0; // cantidad de sedes de la terminal
	private int cantidadVehiculos; // cantidad de vehículos que hay en la terminal 
	private static ArrayList <Transportadora> transportadoras = new ArrayList<>(); // transportadoras asociadas a la terminal 
	private static ArrayList <Viaje> reservas = new ArrayList<>(); // reservas de viaje de la terminal
	private static ArrayList <Viaje> viajes = new ArrayList<>(); // viajes disponibles en la terminal
	private static ArrayList <Viaje> historial = new ArrayList<>(); // viajes realizados por la terminal
	private static ArrayList <Viaje> viajesEnCurso = new ArrayList<>(); // viajes en curso en la terminal
	private ArrayList <Destino> destinos = new ArrayList<>(); // destinos de la terminal 
	private ArrayList <Vehiculo> vehiculosTerminal;
	public final double comision; // comision que cobra la terminal a las transportadoras por dejarle prestar sus servicios, está será por cada viaje que realicen las transportadoras
	private Destino ubicacion; // ubicación de la terminal 
	private Persona administrador; // Administrador de la terminal
	private static ArrayList <Terminal> listaTerminales = new ArrayList <Terminal> ();
	private static ArrayList <Factura> facturas = new ArrayList<>(); // Facturas asociadas a la terminal, tener en cuenta quu solo hay una
	private static ArrayList <Pasajero> pasajeros = new ArrayList<>();
	
	public Terminal() {
		
		
		this.comision = 0;
		
	}
	
	// Constructor Clase Terminal
	public Terminal(String nombre, double dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras,
					ArrayList <Viaje> viajes, ArrayList <Viaje> viajesEnCurso, ArrayList <Destino> destinos, double comision, Destino ubicacion, Persona administrador) {
	
		this.nombre = nombre;
		this.dinero = dinero;
		this.capacidadVehiculos = capacidadVehiculos;
		this.cantidadVehiculos = cantidadVehiculos;
		Terminal.transportadoras = transportadoras;
		Terminal.viajes = viajes;
		this.reservas = new ArrayList<Viaje>();
		Terminal.historial = new ArrayList<Viaje>();
		Terminal.viajesEnCurso = viajesEnCurso;
		this.destinos = destinos;
		this.comision = comision;
		this.ubicacion = ubicacion;
		this.administrador = administrador;
		this.vehiculosTerminal = new ArrayList<> (); //Inicialización de la lista de vehiculos
		Terminal.cantidadSedes++;
	}
	
	// Constructor sobrecarga
	public Terminal(String nombre, double dinero, int capacidadVehiculos, int cantidadSedes, int cantidadVehiculos, ArrayList <Transportadora> transportadoras,
			        ArrayList <Destino> destinos, double comision, Destino ubicacion) {
		
		this.nombre = nombre;
		this.dinero = dinero;
		this.capacidadVehiculos = capacidadVehiculos;
		this.cantidadVehiculos = cantidadVehiculos;
		Terminal.transportadoras = transportadoras;
		Terminal.viajes = new ArrayList<Viaje>();
		this.reservas = new ArrayList<Viaje>();
		Terminal.historial = new ArrayList<Viaje>();
		Terminal.viajesEnCurso = new ArrayList<Viaje>();
		this.destinos = destinos;
		this.comision = comision;
		this.ubicacion = ubicacion;
		this.vehiculosTerminal = new ArrayList<> (); //Inicialización de la lista de vehiculos
		//this.administrador = administrador;
		Terminal.cantidadSedes++;
}
	/**
	 * Este método verifica la disponibilidad de cada una de 
	 * las transportadoras dentro de la terminal para realizar un viaje con un destino
	 *  selecionado, una cantidad de asienos y los viajes con los que se pueden contar
	 * @param Destino, destino deseado.
	 * @param int, asientos
	 * @param ArrayList<Viaje> lista de viajes segun tipo de pasajero
	 * @return ArrayList<Transportadora>, transporatadoras con disponibilidad.
	 */
	
	public static ArrayList<Transportadora> transportadorasViajeDisponible(Destino destinoDeseado, ArrayList<Viaje> listaViajes) {
		ArrayList <Transportadora> transportadorasConDestino = new ArrayList<>();
	for (Viaje viaje : listaViajes) {
		if (viaje != null
		
		&& viaje.getTransportadora() != null
		&& viaje.getLlegada().name().equals(destinoDeseado)) {
			
	Transportadora transportadora = viaje.getTransportadora();
			
	if (!transportadorasConDestino.contains(transportadora)) {
		transportadorasConDestino.add(transportadora);
		}
		}
		}
	
		return transportadorasConDestino;
		}
	
	public static ArrayList<Transportadora> obtenerTransportadorasUnicas(ArrayList<Viaje> viajes) {
	    // Utilizamos un Set para evitar duplicados
	    Set<Transportadora> transportadorasUnicas = new HashSet<>();
	    ArrayList<Transportadora> transportadorasSeleccionadas = new ArrayList<>();

	    // Iterar sobre cada viaje
	    for (Viaje viaje : viajes) {
	        // Obtener la transportadora del viaje
	        Transportadora transportadora = viaje.getVehiculo().getTransportadora();

	        // Verificar si la transportadora no es nula
	        if (transportadora != null) {
	            // Si la transportadora no ha sido mostrada antes
	            if (!transportadorasUnicas.contains(transportadora)) {
	                transportadorasUnicas.add(transportadora);
	                transportadorasSeleccionadas.add(transportadora); // Añadir a la lista
	            }
	        }
	    }

	    return transportadorasSeleccionadas; // Devolver la lista de transportadoras
	}
	
	public static boolean viajesDestino(Destino destino) {
		for (Viaje viaje : Terminal.getViajes()) {
			if (viaje.getLlegada()==destino) {
				return true;
				
			}
		}
		return false;
	}
	
	public static ArrayList<Transportadora> transportadorasViajeDisponible(Destino destinoSeleccionado) {
		ArrayList<Transportadora> transportadorasPorDestino = new ArrayList<>();
		for (Transportadora transportadora : Terminal.getTransportadoras()) {
            if (transportadora.getDestinos().contains(destinoSeleccionado)) {
                transportadorasPorDestino.add(transportadora);
            }
		}
		return transportadorasPorDestino;
	}
	
	public static ArrayList<Viaje> viajesDisponibles(){
		return new ArrayList<>();
	}
	
	public static Viaje masRapido(Destino destinoDeseado, ArrayList<Viaje> viajes) {
		//El tercer parametro son los viajes a los que puede acceder
		Viaje viajeMasRapido = null;
		
		for (Viaje viaje : viajes) {
	        // Verificar si el viaje tiene el destino deseado y suficientes asientos disponibles
			
	        if (viaje.getLlegada()==(destinoDeseado)
	        		&&((viaje.getVehiculo().getTipo()==TipoVehiculo.VANS)
	        		||(viaje.getVehiculo().getTipo()==TipoVehiculo.TAXI))) {
	        	
	            if (viajeMasRapido == null || viaje.getDuracion() < viajeMasRapido.getDuracion()) {
	                viajeMasRapido = viaje;
	                
	            }
	        }
	    }
		
		return viajeMasRapido;
	}
	
	
	public static Viaje obtenerViajeMasProximo(ArrayList<Viaje> listaViajes) {
	    if (listaViajes == null || listaViajes.isEmpty()) {
	        return null;
	    }

	    Viaje viajeMasProximo = listaViajes.get(0);

	    for (Viaje viaje : listaViajes) {
	        int comparacionFecha = viaje.getFecha().compareTo(viajeMasProximo.getFecha());
	        if (comparacionFecha == 0) {
	            // Si las fechas son iguales, compara la hora de salida
	            int comparacionHora = viaje.getHora().compareTo(viajeMasProximo.getHora());
	            if (comparacionHora > 0) {
	                viajeMasProximo = viaje;
	            }
	        } else if (comparacionFecha > 0) {
	            // Si la fecha del viaje actual es mayor que la fecha del viaje más próximo actual
	            viajeMasProximo = viaje;
	        }
	    }

	    return viajeMasProximo;
	}
	
	public static Viaje masEconomico(Destino destinoDeseado, ArrayList<Viaje> viajes){
		//el ultimo se refiere a los viajes a los que puede acceder desde el tipoPasajero
		
		Viaje viajeMasBarato = null;

	   	//Suponiendo que "viajes" es una arrayList con los viajes que se 
		//encuentran disponibles en la terminal y que "finalDestino" de transportadora es hacia donde se dirige

		for (Viaje viaje : viajes) {
	        // Verificar si el viaje tiene el destino deseado y suficientes asientos disponibles
			
	        if (viaje.getVehiculo().getTipo()==TipoVehiculo.BUS) {
	        	
	            if (viajeMasBarato == null || viaje.getTarifa() < viajeMasBarato.getTarifa()) {
	                viajeMasBarato = viaje;
	                
	            }
	        }
	    }

	    return viajeMasBarato;
	}
	
	/**
	 * Este método elige los viajes disponibles para regulares.
	 * @param cantidad Asientos solicitados.
	 * @param lugar donde llega
	 * @return ArrayList con los viajes que se le pueden vender.
	 */
	
	public static ArrayList<Viaje> viajesParaRegularesYDiscapacitados(int cantidad, Destino destino, TipoVehiculo vehiculo){
		
		ArrayList <Viaje> viajes = new ArrayList<>();
		
		for (Viaje viaje : Terminal.getViajes()) {

			if(viaje==null) {
				continue;
			}
			
			if (viaje.getAsientosDisponibles()>=cantidad && viaje.getLlegada()==destino
					&& viaje.getVehiculo().getTipo()==vehiculo&&!viaje.getEstado()) {
				viajes.add(viaje);
			}
		}
		
		return viajes;
		
	}
	
	//Sobrecargado
	
	public static ArrayList<Viaje> viajesParaRegularesYDiscapacitados(int cantidad, Destino destino){
		
		ArrayList <Viaje> viajes = new ArrayList<>();
		
		for (Viaje viaje : Terminal.getViajes()) {

			if(viaje==null) {
				continue;
			}
			
			if (viaje.verificarAsientos()>=cantidad && viaje.getLlegada()==destino
					&&!viaje.getEstado()) {
				viajes.add(viaje);
			}
		}
		
		return viajes;
		
	}
	
	/**
	 * Este método elige los viajes disponibles para pasajeros VIPs.
	 * @param cantidad Asientos solicitados.
	 * @param lugar donde llega
	 * @return ArrayList con los viajes que se le pueden vender.
	 */
	
	public static  ArrayList<Viaje> viajesParaVips(int cantidad, Destino destino, TipoVehiculo vehiculo){
		
		ArrayList <Viaje> viajes = new ArrayList<>(cantidad);
		
		for (Viaje viaje : Terminal.getViajes()) {

			if(viaje==null) {
				continue;
			}
			
			if (viaje.verificarAsientos()>=cantidad && viaje.getVehiculo().getTipo()==vehiculo
					&&viaje.getLlegada()==destino && vehiculo != TipoVehiculo.ESCALERA&&!viaje.getEstado()) {
				viajes.add(viaje);
			}
		}
		
		return viajes;
		
	}
	
	
	//Sobrecargado
	
	public static  ArrayList<Viaje> viajesParaVips(int cantidad, Destino destino){
		
		ArrayList <Viaje> viajes = new ArrayList<>(cantidad);
		
		for (Viaje viaje : Terminal.getViajes()) {

			if(viaje==null) {
				continue;
			}
			
			if (viaje.verificarAsientos()>=cantidad&&
				viaje.getLlegada()==destino && viaje.getVehiculo().getTipo()!=TipoVehiculo.ESCALERA&&
				!viaje.getEstado()) {
				viajes.add(viaje);
			}
		}
		
		return viajes;
		
	}
	
    public static boolean esDestinoValido(String input) {
        for (Destino destino : Destino.values()) {
            if (destino.name().equals(input)) {
                return true;
            }
        }
        return false;
    }
	
	/**
	 * Este método elige los viajes disponibles para pasajeros estudiantes.
	 * @param cantidad Asientos solicitados.
	 * @return ArrayList con los viajes que se le pueden vender.
	 */
	
	public static ArrayList<Viaje> viajesParaEstudiantes(Destino destino){
		
		ArrayList <Viaje> viajes1 = new ArrayList<>();
		
		for (Viaje viaje : Terminal.getViajes()) {
			
			if(viaje==null) {
				continue;
			}
			
			if (viaje.getAsientosDisponibles()>=1 && viaje.getVehiculo().getTipo()!=TipoVehiculo.TAXI
				&&viaje.getLlegada()==destino &&!viaje.getEstado()) {
				viajes1.add(viaje);
				
			}
			

		}
		
		return viajes1;
		
	}
	
	public static ArrayList<Viaje> viajesParaEstudiantes(Destino destino,TipoVehiculo vehiculo){
				
		ArrayList <Viaje> viajes1 = new ArrayList<>();
		
		for (Viaje viaje : Terminal.getViajes()) {
			
			if(viaje==null) {
				continue;
			}
			
			if (viaje.getAsientosDisponibles()>=1 && viaje.getVehiculo().getTipo()==vehiculo
				&&viaje.getLlegada()==destino &&!viaje.getEstado()) {
				viajes1.add(viaje);
				
			}
			

		}
		
		return viajes1;
		
	}
	
	// Programación por Vehiculo ()
	
	public static Viaje programarViaje(Destino llegada, TipoVehiculo vehiculo, String fecha, String hora, Destino salida) {
	    for (Transportadora t : Terminal.getTransportadoras()) { // Iterar sobre todas las transportadoras disponibles
	        if (t.getDestinos().contains(llegada)) {	        // Verificar si la transportadora ofrece el destino de llegada
	            for (Vehiculo v : t.getVehiculos()) {	       // Buscar un vehículo que cumpla con el tipo solicitado y que esté disponible
	                if (v.getTipo().equals(vehiculo) && v.disponibilidad()) { // Buscar un conductor disponible
	                    Conductor conductorSeleccionado = null;
	                    for (Conductor c : v.getConductores()) { // Verificar que el conductor tenga la licencia en estado correcto (puede ser ajustado según tus criterios)
	                        if (c.getEstadoLicencia()) {
	                            conductorSeleccionado = c;
	                            break;  // Selecciona el primer conductor disponible
	                        }
	                    }
	                    if (conductorSeleccionado != null) { // Verificar si se encontró un conductor disponible
	                        Viaje nuevoViaje = new Viaje(t.getTerminal(), hora, fecha, v, conductorSeleccionado, llegada, salida); // Crear y retornar el nuevo viaje
	                        return nuevoViaje;
	                    } else {  // No hay conductores disponibles para este vehículo
	                        return null;
	                    }
	                }
	            }
	            return null;
	        }
	    }
	    return null;
	}
	
	// Programacion por Conductor ()
	
	public static Viaje programarViaje(Destino llegada, Conductor conductor, TipoVehiculo tipoVehiculo, String fecha, String hora, Destino salida) {
	    for (Transportadora t : Terminal.getTransportadoras()) {
	        if (t.getDestinos().contains(llegada)) {
	            for (Vehiculo v : t.getVehiculos()) {
	                if (v.getTipo().equals(tipoVehiculo) && v.disponibilidad()) {
	                    if (t.conductoresDisponibles(fecha, tipoVehiculo).contains(conductor)) {
	                        if (conductor.getEstadoLicencia()) {
	                            Viaje nuevoViaje = new Viaje(t.getTerminal(), hora, fecha, v, conductor, llegada, salida);
	                            return nuevoViaje;
	                        } else {
	                            return null;
	                        }
	                    } else {
	                        return null;
	                    }
	                }
	            }
	            return null;
	        }
	    }
	    return null;
	}
	
	/**
	 * Cancela un viaje de forma absoluta, lo que implica eliminar el viaje de las listas de reservas y de viajes,
	 * así como reembolsar a todos los pasajeros que estaban en el viaje.
	 * 
	 * @param viaje El viaje que se desea cancelar de forma absoluta.
	 * @return Un mensaje que indica el resultado de la operación, en este caso, siempre retorna "Viaje cancelado".
	 */
	
	public static String cancelarViajeAbsoluto(Viaje viaje) {
		String cadena = "El viaje no tenia pasajeros";
		
	    ArrayList<Pasajero> pasajeros = viaje.getPasajeros();
	    
	    if (!pasajeros.isEmpty()) {
		    for (Pasajero pasajero : pasajeros) {
		        pasajero.aumentarDinero((int) viaje.getTarifa());
		    }
		    cadena = "Viaje cancelado";
	    }

	    if (Terminal.getReservas().contains(viaje)) {
	    	Terminal.getReservas().remove(viaje);
	    }
	    
	    Terminal.getViajes().remove(viaje);
	    
	    return cadena;
	}
	
	public static String cancelarViaje(Viaje viaje) {
	    ArrayList<Pasajero> pasajeros = viaje.getPasajeros();
	    boolean reubicados = false;

	    for (Viaje v : Terminal.viajes) {
	        if (!v.equals(viaje) && v.getSalida().equals(viaje.getSalida()) && v.getLlegada().equals(viaje.getLlegada()) && v.getAsientosDisponibles() >= pasajeros.size()) {
	            v.setTarifa(viaje.getTarifa());
	            v.getPasajeros().addAll(pasajeros);
	            viaje.programacionAutomatica();
	            reubicados = true;
	            break;
	        }
	    }

	    if (reubicados) {
	        return "Los pasajeros han sido reubicados en otro viaje.";
	    } else {
	        for (Pasajero p : pasajeros) {
	            p.aumentarDinero((int) viaje.getTarifa());
	        }
	        viaje.programacionAutomatica();
	        return "Los pasajeros han sido reembolsados.";
	    }
	}
	
	/**
	 * Método para saber si hay asientosDisponibles para un viaje.
	 * @return Boolean, dependiendo la condición.
	 */
	
	public Boolean consultarCapacidad(Viaje viaje) {
		int capacidadVehiculo = viaje.getVehiculo().getTipo().getCapacidad(); // Valor de la cantidad de Asientos del Vehiculo.
		int asientosOcupados = viaje.getPasajeros().size(); // Determinara la cantidad de pasajeros que tiene el viaje actualmente.
		int disponibles = capacidadVehiculo - asientosOcupados;
		Boolean capacidad;
		if (capacidadVehiculo >= asientosOcupados) {
			capacidad = true;
		}
		else {
			capacidad = false;
		}
		return capacidad;
	}
	
	
	/**
	 * Deniega una reserva de un viaje y maneja la reubicación de los pasajeros o el reembolso, según sea necesario.
	 * 
	 * @param viaje El viaje cuya reserva está siendo denegada.
	 * @return Un mensaje que indica el resultado de la operación:
	 *         - "Los pasajeros han sido reubicados en otro viaje." si se ha encontrado un viaje adecuado para reubicar a los pasajeros.
	 *         - "Los pasajeros han sido reembolsados." si no se ha encontrado un viaje adecuado y se ha procedido a reembolsar a los pasajeros.
	 */
	
	public static String denegarReserva(Viaje viaje) {
	    ArrayList<Pasajero> pasajeros = viaje.getPasajeros();
	    boolean reubicados = false;

	    // Buscar un nuevo viaje con los mismos detalles de salida y llegada
		Viaje nuevoViaje = Terminal.programarViaje(viaje.getSalida(), viaje.getVehiculo().getTipo(), viaje.getFecha(), viaje.getHora(), viaje.getLlegada());
		
	    if (nuevoViaje != null) {
	    	nuevoViaje.setTarifa(viaje.getTarifa());
	    	nuevoViaje.getPasajeros().addAll(pasajeros);
            reubicados = true;
	    }

	    if (reubicados) {
	        Terminal.getReservas().add(nuevoViaje);
	        Terminal.getReservas().remove(viaje);
	        viaje.programacionAutomatica();
	        return "Los pasajeros han sido reubicados en otro viaje.";
	    } else {
	        for (Pasajero p : pasajeros) {
	            p.aumentarDinero((int) viaje.getTarifa());
	        }
	        Terminal.getReservas().remove(viaje);
	        return "Los pasajeros han sido reembolsados no se encontro disponiblidad.";
	    }
	}
	
	public ArrayList <Factura> obtenerFinanzas(){
		
		// Implementación pendiente 
		
		return null;
		
		
	}
	
	
	public void asignarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public void designarViaje() {
		
		// Implementación pendiente
		
		
	}
	
	public static double calcularGanancias() {
		
		double ganancias = 0;
		
		for (Transportadora t: Terminal.getTransportadoras()) {
			
			if (t.verificarPagoTerminal()) {
				
				ganancias += t.RetornarValorAPagarTerminal();
				
			}
			
			
		}
		return ganancias;
		
		
		
	}
	 
	
	/**
	 * Agrega un nuevo vehiculo a la lista de la terminal
	 * @param vehiculo a agregar
	 */

	public void agregarVehiculoTerminal (Vehiculo vehiculo) {
		
		this.vehiculosTerminal.add(vehiculo);
		this.capacidadVehiculos --;
		
	}
	/**
	 * Remueve el vehiculoeleido de la lista de vehiculos de la terminal
	 * @param vehiculo a remover
	 */
	
	public void removerVehiculoTerminal (Vehiculo vehiculo) {
		
		this.vehiculosTerminal.remove(vehiculo);
	}
	
	/**
	 * Genera una lista de fechas disponibles a partir de la fecha actual.
	 * Este método calcula una serie de fechas a partir de la fecha actual guardada en {@link Tiempo#salidaFecha}.
	 * La lista contiene fechas desde el día siguiente hasta un máximo de seis días después. 
	 * La fecha actual se extrae de {@link Tiempo#salidaFecha}, que debe estar en el formato "dd/MM/yyyy".
	 * 
	 * @return Un {@link ArrayList} de {@link String} que contiene las fechas disponibles en el formato "dd/MM/yyyy".
	 *         La lista incluye la fecha actual más los siguientes seis días.
	 * 
	 * @throws NumberFormatException Si {@link Tiempo#salidaFecha} no está en el formato correcto "dd/MM/yyyy".
	 */
	
	public static ArrayList <String> fechasDisponibles () {
		ArrayList<String> fechas = new ArrayList<>();
		
    	String [] fechapartes = Tiempo.salidaFecha.split("/");
    	
    	int dia = Integer.parseInt(fechapartes[0]);
    	int mes = Integer.parseInt(fechapartes[1]);
    	int año = Integer.parseInt(fechapartes[2]);
    	
    	int i = 1;
    	
    	while (i<7) {
	    	dia += 1;
	    	if (dia > 30) {
	    		dia = 0;
	    		mes++;
	    		if (mes > 12) {
	    			mes = 0;
	    			año++;
	    		}
	    	}
	    	String fechaLlegada = dia + "/" + mes + "/" + año;
	    	fechas.add(fechaLlegada);
	    	i++;
    	} 
        return fechas;
	}
	
	/**
	 * Genera una lista de horarios disponibles para una fecha dada.
	 * 
	 * La lógica es la siguiente:
	 * 1. La fecha se descompone en día, mes y año.
	 * 2. Se determina si el día es par o impar.
	 * 3. Se determina si el mes es igual al día.
	 * 4. Se generan horarios disponibles basados en las siguientes reglas:
	 *    - Si el día es par: horarios cada hora desde las 6:00 hasta las 20:00.
	 *    - Si el día es impar: horarios cada hora, pero comienza a las 8:30.
	 *    - Si el mes es igual al día:
	 *      - Se ajusta la lista para que contenga horarios cada dos horas.
	 *      - Para días pares, los horarios son cada dos horas desde las 6:00 hasta las 20:00.
	 *      - Para días impares con mes igual al día, los horarios son cada dos horas comenzando desde las 8:30.
	 *
	 * @param fecha La fecha en formato "dd/MM/yyyy" para la cual se desea obtener los horarios disponibles.
	 * @return Una lista de cadenas que representan los horarios disponibles para la fecha dada.
	 */
	
	public static ArrayList<String> horasDisponibles(String fecha) {
	    ArrayList<String> horarios = new ArrayList<>();

	    String[] fechaPartes = fecha.split("/");

	    int dia = Integer.parseInt(fechaPartes[0]);
	    int mes = Integer.parseInt(fechaPartes[1]);
	    int año = Integer.parseInt(fechaPartes[2]);

	    boolean diaEsPar = dia % 2 == 0;
	    boolean mesIgualADia = mes == dia;

	    for (int hora = 6; hora <= 20; hora++) { // Iterar sobre las horas de 6 a 20
	        if (diaEsPar) { // Día par: horarios cada hora
	            horarios.add(String.format("%02d:00", hora));
	        } else { // Día impar: horarios cada hora, pero comienza a las 8:30
	            if (hora >= 8) {
	                horarios.add(String.format("%02d:30", hora));
	            }
	        }
	    }

	    if (mesIgualADia) {	    // Si el mes es igual al día, ajustar para que sea cada dos horas
	        ArrayList<String> horariosCadaDosHoras = new ArrayList<>();
	        for (int hora = 6; hora <= 20; hora += 2) {
	            if (diaEsPar) {
	                horariosCadaDosHoras.add(String.format("%02d:00", hora));
	            } else { 	                // Día impar con mes igual al día: cada dos horas a partir de las 8:30
	                if (hora >= 8) { 
	                    horariosCadaDosHoras.add(String.format("%02d:30", hora));
	                }
	            }
	        }
	        horarios = horariosCadaDosHoras; // Reemplazar la lista original con la lista ajustada cada dos horas
	    }

	    return horarios;
	}
	
	// METODOS GETTERS Y SETTERS
	
	/**
	 * Retorna la lista de vehiculos de la terminal
	 * @return Vehicuos de la terminal
	 */
	public ArrayList<Vehiculo> getVehiculosTerminal (){
		
		return (this.vehiculosTerminal);
	}
	
	/**
	 * Establece la lista de vehiculosTerminal
	 * @param vehiculos: Lista de vehiculos de la terminal
	 */
	
	public void setVehiculosTerminal (ArrayList<Vehiculo> vehiculos) {
		
		this.vehiculosTerminal = vehiculos;
	}
	
	
	/**
	 * Establece o modifica el nombre de la terminal.
	 * @param nombre, el nombre de la terminal.
	 */
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	/**
	 * Método para obtener el nombre de la terminal.
	 * @return nombre de la terminal.
	 */
	
	public String getNombre() {
		
		return nombre;
				
	}
	
	/**
	 * Establece o modifica el dinero de la terminal.
	 * @param dinero, el dinero de la terminal.
	 */
	
	public static void setDinero (double dinero) {
		
		Terminal.dinero = dinero;
		
	}
	
	/**
	 * Método que nos sirve para obtener el dinero de la terminal.
	 * @return el dinero de la terminal.
	 */
	
	public static double getDinero() {
		
		return dinero;
		
	}
	
	/**
	 * Establece o modifica la capacidad de veículos de la terminal.
	 * @param capacidadVehiculos, la capacidad de vehículos de la terminal.
	 */
	
	public void setCapacidadVehiculos(int capacidadVehiculos) {
		
		this.capacidadVehiculos = capacidadVehiculos;
			
	}
	
	/**
	 * Método que nos permite obtener la capacidad de vehículos en la terminal.
	 * @return la capacidad de vehiculos de la terminal.
	 */
	
	public int getCapacidadVehiculos() {
		
		return capacidadVehiculos;
		
	}
	
	/**
	 * Establece o modifica la cantidad de sedes de la terminal.
	 * @param cantidadSedes, la cantidad de sedes de la terminal.
	 */
	
	public void setCantidadSedes(int cantidadSedes) {
		
		Terminal.cantidadSedes = cantidadSedes;
		
	}
	
	/**
	 * Método que nos permite obtener la cantidad de sedes de la terminal.
	 * @return cantidad de sedes de la terminal.
	 */
	
	public int getCantidadSedes() {
		
		return Terminal.cantidadSedes;
		
	}
	
	/**
	 * Establece la cantidad de vehículos que hay en la terminal.
	 * @param cantidadVehiculos, la cantidad de vehículos en la terminal.
	 */
	
	public void setCantidadVehiculos(int cantidadVehiculos) {
		
		this.cantidadVehiculos = cantidadVehiculos;
		
	}
	
	/**
	 * Método que nos permite obtener la cantidad de vehículos que hay en la terminal.
	 * @return cantidad de vehículos que hay en la terminal.
	 */
	
	public int getCantidadVehiculos() {
		
		return cantidadVehiculos;
		
	}
	
	/**
	 * Establece o modifica las transportadoras asociadas a la terminal.
	 * @param trasnportadoras, lista de las transportadoras asociadas a la terminal.
	 */
	
	public static void setTransportadoras(ArrayList <Transportadora> transportadoras) {
		
		Terminal.transportadoras = transportadoras;
		
	}
	
	/**
	 * Método que nos permite obtener a las transportadoras asociadas a la terminal.
	 * @return lista de las transportadoras asociadas a la terminal.
	 */
	
	public static ArrayList <Transportadora> getTransportadoras(){
		
		return transportadoras;
	}
	
	/**
	 * Establece o modifica las reservas de la terminal.
	 * @param reservas, las reservas asociadas a la terminal.
	 */
	
	
	public static void setReservas(ArrayList <Viaje> reservas) {
		
		Terminal.reservas = reservas;
		
	}
	
	/**
	 * Método que nos permite obtener las reservas asociadas a la terminal.
	 * @return lista de las reservas asociadas a la terminal.
	 */
	
	public static ArrayList <Viaje> getReservas(){
		
		return Terminal.reservas;
	
	}
	
	/**
	 * Establece o modifica los viajes asocaidos a la terminal.
	 * @param viajes, lista con los viajes asociados a la terminal
	 */
	
	public static void setViajes(ArrayList <Viaje> viajes) {
		
		Terminal.viajes = viajes;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes asociados a la terminal.
	 * @retunr lista de viajes asociados a la terminal.
	 */
	
	public static ArrayList <Viaje> getViajes(){
		
		return viajes;
	
	}
	
	/**
	 * Establece o modifica los viajes en curso asocaidos a la terminal.
	 * @param viajes, lista con los viajes en curso asociados a la terminal
	 */
	
	public static void setViajesEnCurso(ArrayList <Viaje> viajesEnCurso) {
		
		Terminal.viajesEnCurso = viajesEnCurso;
		
	}
	
	/**
	 * Método para obtener la lista con los viajes en curso asociados a la terminal.
	 * @retunr lista de viajes en curso asociados a la terminal.
	 */
	
	public static ArrayList <Viaje> getViajesEnCurso(){
		
		return Terminal.viajesEnCurso;
	
	}
	
	/**
	 * Establece o modifica la lista del historial de Viajes asociado a la terminal.
	 * @param destinos, lista con los destinos asociados a la terminal.
	 */
	
public static void setHistorial(ArrayList <Viaje> historial) {
		
		Terminal.historial = viajesEnCurso;
		
	}
	
	/**
	 * Método para obtener la lista con el Historial de viajes de la terminal.
	 * @retunr lista de viajes realizados por la terminal.
	 */
	
	public static ArrayList <Viaje> getHistorial(){
		
		return Terminal.historial;
	
	}
	
	/**
	 * Establece o modifica la lista de destinos asociado a la terminal.
	 * @param destinos, lista con los destinos asociados a la terminal.
	 */
	
	public void setDestinos(ArrayList <Destino> destinos) {
		
		this.destinos = destinos;
		
	}
	
	/**
	 * Método para obtener la lista con los destinos asociados a la terminal.
	 * @return lista con los destinos asociados a la terminal
	 */
	
	public ArrayList <Destino> getDestinos(){
		
		return destinos;
	
	}
	
	/**
	 * Método para obtener la ubicación de la terminal.
	 * @return ubicación de la terminal.
	 */
	
	public Destino getUbicacion() {
		
		return ubicacion;
	}
	
	/**
	 * Establece o modifica el administrador de la terminal.
	 * @param administrador, el administrador de la terminal.
	 */
	
	 public void setAdministrador(Persona administrador) {
		this.administrador = administrador;
	}
	
	/**
	 * Método para obtener el administrador de la terminal.
	 * @return el administrador de la terminal.
	 */
	
	public Persona getAdministrador() {
		return administrador;
	}
	
	/**
	 * Método para obtener el valor de la comisión que le cobra la terminal a la transportadora.
	 * @return la comisión que le cobra la terminal a la transportadora.
	 */
	
	public double getComision() {
		
		return this.comision;
		
		
	}
	
	public static ArrayList <Factura> getFacturas(){
		
		
	return Terminal.facturas;
			
	}
	
	public static void setListaTerminales(ArrayList <Terminal> terminal) {

		Terminal.listaTerminales = terminal;

	}

	/**
	 * Método para obtener la lista de las terminales.
	 * @retunr lista de terminales creadas.
	 */

	public static ArrayList<Terminal> getListaTerminales(){

		return Terminal.listaTerminales;

	}
	
	public static ArrayList<Pasajero> getPasajerosSinViajes(){
		
		return Terminal.pasajeros;
		
	}
	
	public static void setPasajerosSinViajes(ArrayList <Pasajero> pasajero) {

		Terminal.pasajeros = pasajero;

	}
	
}
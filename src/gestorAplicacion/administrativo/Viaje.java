package gestorAplicacion.administrativo;

import gestorAplicacion.administrativo.Terminal;

import java.lang.Math;
import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.constantes.Dia;
import gestorAplicacion.tiempo.Tiempo;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Esta clase representa a un viaje que tiene atributos como id, tarifa, duración, hora, pasajeros, total viajes, vehículo, conductor, destino final, día y destino.
 * nos servirá para programar los distintos viajes que ofrecerá la transportadora.
 */
 

public class Viaje {
    private Terminal terminal; // Terminal
    private int id; // Identificador del viaje
    private double tarifa; // Tarifa del viaje
    private double duracion; // Duración del viaje en minutos
    private static int totalViajes; // Número total de viajes realizados
    private String hora; // Hora de inicio del viaje
    private String horaLlegada; // Hora de fin del viaje
    private String fecha; // Fecha del viaje
    private String fechaLlegada; // Fecha de LLegada
    private ArrayList<Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros del viaje
    private Vehiculo vehiculo; // Vehículo utilizado en el viaje
    private Conductor conductor; // Conductor del vehículo en el viaje
    private Destino llegada; // Destino final del viaje
    private Dia dia; // Día en que se realiza el viaje
    private Destino salida; // Destino actual del viaje
    private Boolean estado;
    private int asientosDisponibles;

    
    // Constructor de la clase Viaje
    public Viaje(Terminal terminal, String hora, String fecha, Vehiculo vehiculo, Conductor conductor, Destino llegada, Dia dia, Destino salida) {
        this.terminal = terminal;
    	this.id = Viaje.totalViajes;
        this.duracion = calcularDuracion(salida, llegada, vehiculo, conductor);
        this.tarifa = calcularTarifa(this.duracion, vehiculo);
        this.hora = hora;
        this.horaLlegada = calcularHoraLlegada(this.duracion, hora, fecha);
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.conductor = conductor;
        this.llegada = llegada;
        this.dia = dia;
        this.salida = salida;
        this.estado = false; // Solo se coloca en true mientras el viaje esta en curso
        asientosDisponibles = vehiculo.getTipo().getCapacidad()-pasajeros.size();
        Viaje.totalViajes++;
        terminal.getViajes().add(this);
    }
    
    
	/**
	 * Método para obtener la distancia del Viaje.
	 * @return double, la distancia entre dos lugares.
	 */
    public double calcularDistancia(Destino salida ,Destino llegada) {
    	double x1 = salida.getEjeX();
    	double x2 = llegada.getEjeX();
    	double y1 = salida.getEjeY();
    	double y2 = llegada.getEjeY();
    	
    	double distancia = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    	
    	return distancia;
    }
    
    
	/**
	 * Método para obtener la duración del Viaje.
	 * @return int, dependiendo las condiciones establecidas: Destino, Vehiculo y Experiencia del Conductor.
	 */
    public double calcularDuracion(Destino salida ,Destino llegada, Vehiculo vehiculo, Conductor conductor) {
    	double distancia = this.calcularDistancia(salida, llegada);
    	double velocidad = vehiculo.getVelocidadPromedio();
    	double tiempo =  distancia/velocidad;
    	if (conductor.getExperiencia()> 0.5) { // Verificar si la experiencia del conductor es mayor que 0.5
    		double factorReduccion = 0.9; // Reducción del 10%
            tiempo *= factorReduccion;
    	}	
    	return tiempo;
    }
    
    /**
	 * Método para calcular la hora de llegada del Viaje.
	 * @return String, dependiendo las condiciones establecidas: Destino, Vehiculo y Experiencia del Conductor.
	 */
    public String calcularHoraLlegada(double duracion, String hora, String fecha) {
    	
    	double tiempoHorasDuracion = duracion;
    	String horaSalida = hora;
    	
    	String [] partes = horaSalida.split(":");
        int horasSalida = Integer.parseInt(partes[0]);
        int minutosSalida = Integer.parseInt(partes[1]);
        
    	String [] fechapartes = fecha.split("/");
    	
    	int dia = Integer.parseInt(fechapartes[0]);
    	int mes = Integer.parseInt(fechapartes[1]);
    	int año = Integer.parseInt(fechapartes[2]);
     	
    	int horasEnterasDuracion = (int) tiempoHorasDuracion;
    	int minutosDuracion =  (int)((tiempoHorasDuracion - horasEnterasDuracion)*60);
    	
    	int totalHoras = horasSalida + horasEnterasDuracion;
    	int totalMinutos = minutosSalida + minutosDuracion;
        
     // Manejar el exceso de minutos y horas
        totalHoras += totalMinutos / 60; // Sumar las horas extra de los minutos
        totalMinutos %= 60; // Mantener solo los minutos restantes

        // Ajustar el día si supera 24 horas
        dia += totalHoras / 24;
        totalHoras %= 24;

        // Ajustar el mes si supera 30 días
        mes += dia / 30;
        dia %= 30;

        // Ajustar el año si supera 12 meses
        año += mes / 12;
        mes %= 12;
        
        // Modificar Fecha de LLegada
        String fechaLlegada = dia + "/" + mes + "/" + año;
         this.setFechaLlegada(fechaLlegada);

    	String salida = totalHoras+":"+totalMinutos;
    	
    	return salida;
    }
    
	/**
	 * Método para obtener la tarica del Viaje.
	 * @return int, dependiendo las condiciones establecidas: duración y tipo de vehiculo.
	 */
    public double calcularTarifa(double duracion, Vehiculo vehiculo) {
        int costoPorMinuto = 0;
        double total;
        // Establecer el costo por minuto según el tipo de vehículo.
        switch (vehiculo.getTipo()) {
            case TAXI:
                costoPorMinuto = 400;
                break;
            case VANS:
                costoPorMinuto = 300;
                break;
            case ESCALERA:
                costoPorMinuto = 200;
                break;
            case BUS:
                costoPorMinuto = 100;
                break;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return -1; // Valor de retorno inválido
        }
        // Calcular la tarifa total
        total = vehiculo.getTransportadora().getEstrellas()*5;  // Falta agregar el factor de la distancia // Método calcularDistancia
        total *=  (costoPorMinuto * (duracion*60));
        
        return total;
    }
    
    /**
     * Método `validacion` para verificar el estado de un viaje y actualizar las listas de viajes en la terminal.
     * 
     * Este método realiza las siguientes acciones:
     * 
     * 1. **Verificación del Viaje en la Lista de Viajes**:
     *    - Comprueba si el viaje actual (`this`) está presente en la lista de viajes de la terminal.
     * 
     * 2. **Actualización de la Lista de Viajes en Curso**:
     *    - Si el viaje está en la lista de viajes, lo añade a la lista de viajes en curso.
     * 
     * 3. **Verificación del Viaje en la Lista de Viajes en Curso**:
     *    - Verifica si el viaje especificado como parámetro (`viaje`) está en la lista de viajes en curso.
     *    - Si está en curso, elimina el viaje actual (`this`) de la lista de viajes).
     *    
          * @return Un "String" mensaje que permite validar el estado.
     */
    
    public String validacion(Viaje viaje, ArrayList<Viaje> viajesEnCurso,  ArrayList<Viaje> viajes) {
    	if (this.getTerminal().getViajes().contains(this)) {
    		this.getTerminal().getViajesEnCurso().add(this);
    		this.setEstado(true);
    		if (this.getTerminal().getViajesEnCurso().contains(viaje)) {
    			System.out.println("El viaje está en curso.");
				this.getTerminal().getViajes().remove(this);
				System.out.println("Viajes:   " + this.getTerminal().getViajes());  // Lista de Viajes Terminal
				System.out.println("En curso: " + this.getTerminal().getViajesEnCurso()); // Lista de Viajes en Curso
				System.out.println("Historial: " + this.getTerminal().getHistorial()); // Lista de Viajes en Curso
    			return "El viaje está en curso.";
            } else {
            	System.out.println("El viaje no está en curso.");
                return "Viaje perdido...";
                }
            } else {
            	System.out.println("El viaje no está en curso.");
                return "El viaje no está en curso.";
            }
    }
    
    /**
     * Método `programacionAutomatica` para manejar la continuidad automática de un viaje.
     * 
     * Este método realiza las siguientes acciones:
     * 
     * 1. **Finalización del Viaje Actual**:
     *    - Cambia el estado del viaje a `false`, indicando que el viaje ha terminado.
     * 
     * 2. **Actualización de las Listas de la Terminal**:
     *    - Verifica si el viaje actual está en la lista de viajes en curso de la terminal.
     *    - Si está, lo mueve al historial de la terminal y lo elimina de los viajes en curso.
     * 
     * 3. **Creación de un Nuevo Viaje**:
     *    - Crea una nueva instancia de `Viaje` con la misma terminal, hora, vehículo, conductor, // Tener en cuenta la modificación para cree el viaje desde la terminal del destino de llegada
     *      destino de llegada, día y salida, pero con la fecha ajustada.
     */
    
    public void programacionAutomatica() { // Metodo necesario para manejar una continuidad
    	this.setEstado(false); // Fin del Viaje el estado cambia
    	if (this.getTerminal().getViajesEnCurso().contains(this)) {
    		this.getTerminal().getHistorial().add(this);
    		this.getTerminal().getViajesEnCurso().remove(this);
    		new Viaje(this.getTerminal(), this.getHora(), this.ajusteFecha(), this.getVehiculo(), this.getConductor(), this.getLlegada(), this.getDia(), this.getSalida());
    		System.out.println("Fecha Nueva: " + this.ajusteFecha() + " Fecha Actual: " +Tiempo.salidaFecha);
    	} else {
    		System.out.println("El viaje ha sido intervenido");
    	}
    	
    }
    
    /**
     * Ajusta la fecha de llegada del objeto sumándole dos días.
     * 
     * Este método toma la fecha de llegada actual, la descompone en sus partes constituyentes
     * (día, mes, año), incrementa el día en 2 y ajusta el mes y el año en caso de que se
     * sobrepase el número de días o meses. Finalmente, construye y retorna la nueva fecha
     * en formato "día/mes/año".
     *
     * Consideraciones:
     * - Este método asume que todos los meses tienen 30 días
     * 
     * @return La nueva fecha de llegada ajustada en el formato "día/mes/año".
     */
    
    public String ajusteFecha() {
    	
    	String [] fechapartes = this.getFechaLlegada().split("/");
    	
    	int dia = Integer.parseInt(fechapartes[0]);
    	int mes = Integer.parseInt(fechapartes[1]);
    	int año = Integer.parseInt(fechapartes[2]);
    	
    	dia += 2;

    	if (dia > 30) {
    		dia = 0;
    		mes++;
    		if (mes > 12) {
    			mes = 0;
    			año++;
    		}
    		
    	}
        
        String fechaLlegada = dia + "/" + mes + "/" + año;
        
        return fechaLlegada;
        
    }
   
    public String estado() {
        // Implementación pendiente
    	
    	return null;
    }
    
    // Getters y Setters
    
    /**
     * Establece o modifica la Terminal del viaje.
     * @param id La Terminal del viaje.
     */
    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * Obtiene la Terminal del viaje.
     * @return La Terminal del viaje.
     */
    public Terminal getTerminal() {
        return terminal;
    }
    
    /**
     * Establece o modifica el identificador del viaje.
     * @param id El identificador del viaje.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del viaje.
     * @return El identificador del viaje.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece o modifica la tarifa del viaje.
     * @param tarifa La tarifa del viaje.
     */
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * Obtiene la tarifa del viaje.
     * @return La tarifa del viaje.
     */
    public double getTarifa() {
        return tarifa;
    }

    /**
     * Establece o modifica la duración del viaje.
     * @param duracion La duración del viaje en minutos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la duración del viaje.
     * @return La duración del viaje en minutos.
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * Establece o modifica el número total de viajes realizados.
     * @param totalViajes El número total de viajes realizados.
     */
    public void setTotalViajes(int totalViajes) {
        Viaje.totalViajes = totalViajes;
    }

    /**
     * Obtiene el número total de viajes realizados.
     * @return El número total de viajes realizados.
     */
    public int getTotalViajes() {
        return Viaje.totalViajes;
    }

    /**
     * Establece o modifica la hora de inicio del viaje.
     * @param hora La hora de inicio del viaje.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Obtiene la hora de inicio del viaje.
     * @return La hora de inicio del viaje.
     */
    public String getHora() {
        return hora;
    }
    
    /**
     * Establece o modifica la hora de llegada del viaje.
     * @param hora La hora de llegada del viaje.
     */
    public void setHoraLlegada(String hora) {
        this.horaLlegada = hora;
    }

    /**
     * Obtiene la hora de llegada del viaje.
     * @return La hora de llegada del viaje.
     */
    public String getHoraLlegada() {
        return horaLlegada;
    }
    
    /**
     * Establece o modifica la fecha de inicio del viaje.
     * @param fecha La fecha de inicio del viaje.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la fecha de inicio del viaje.
     * @return La fecha de inicio del viaje.
     */
    public String getFecha() {
        return fecha;
    }
    
    /**
     * Establece o modifica la fecha de Llegada del viaje.
     * @param fecha La fecha de llegada del viaje.
     */
    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    /**
     * Obtiene la fecha de llegada del viaje.
     * @return La fecha de llegada del viaje.
     */
    public String getFechaLlegada() {
        return fechaLlegada;
    }

    /**
     * Establece o modifica la lista de pasajeros del viaje.
     * @param pasajeros La lista de pasajeros del viaje.
     */
    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    /**
     * Obtiene la lista de pasajeros del viaje.
     * @return La lista de pasajeros del viaje.
     */
    public ArrayList<Pasajero> getPasajeros(){
        return pasajeros;
    }

    /**
     * Establece o modifica el vehículo utilizado en el viaje.
     * @param vehiculo El vehículo utilizado en el viaje.
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * Obtiene el vehículo utilizado en el viaje.
     * @return El vehículo utilizado en el viaje.
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Establece o modifica el conductor del vehículo en el viaje.
     * @param conductor El conductor del vehículo en el viaje.
     */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    /**
     * Obtiene el conductor del vehículo en el viaje.
     * @return El conductor del vehículo en el viaje.
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * Obtiene el destino final del viaje.
     * @return El destino final del viaje.
     */
    public Destino getLlegada() {
        return llegada;
    }

    /**
     * Obtiene el día en que se realiza el viaje.
     * @return El día en que se realiza el viaje.
     */
    public Dia getDia() {
        return dia;
    }

    /**
     * Obtiene el destino actual del viaje.
     * @return El destino actual del viaje.
     */
    public Destino getSalida() {
        return salida;
    }
    
    /**
     * Establece o modifica el estado utilizado en el viaje.
     * @param estado el estado del viaje.
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el estado  del viaje.
     * @return El estado  del viaje. (false "Estacionado" y True "Viaje en curso")
     */
    public Boolean getEstado() {
        return estado;
    }
    
    /**
     * Obtiene el numero de asientos disponibles en del viaje.
     * @return La cantida entera de asientos
     */
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}
	
    /**
     * Establece o modifica el numero de asientos disponibles en el viaje.
     * @param Numero de asientos
     */
	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
    
}
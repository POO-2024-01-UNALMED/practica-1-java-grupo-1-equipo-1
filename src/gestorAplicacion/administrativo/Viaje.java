package gestorAplicacion.administrativo;

import gestorAplicacion.administrativo.Terminal;

import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.constantes.Dia;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Esta clase representa a un viaje que tiene atributos como id, tarifa, duración, hora, pasajeros, total viajes, vehículo, conductor, destino final, día y destino.
 * nos servirá para programar los distintos viajes que ofrecerá la transportadora.
 */
 

public class Viaje {
    
    private int id; // Identificador del viaje
    private double tarifa; // Tarifa del viaje
    private double duracion; // Duración del viaje en minutos
    private static int totalViajes; // Número total de viajes realizados
    private String hora; // Hora de inicio del viaje
    private String fecha; // Fecha del viaje
    private ArrayList<Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros del viaje
    private Vehiculo vehiculo; // Vehículo utilizado en el viaje
    private Conductor conductor; // Conductor del vehículo en el viaje
    private Destino finalDestino; // Destino final del viaje
    private Dia dia; // Día en que se realiza el viaje
    private Destino destino; // Destino actual del viaje
    private Boolean estado;
    private int asientosDisponibles;
    
    // Constructor de la clase Viaje
    public Viaje(int id, String hora, String fecha, Vehiculo vehiculo, Conductor conductor, Destino finalDestino, Dia dia, Destino destino) {
        this.id = id;
        this.duracion = calcularDuracion(finalDestino, vehiculo, conductor);
        this.tarifa = calcularTarifa(this.duracion, vehiculo);
        Viaje.totalViajes++;
        this.hora = hora;
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.conductor = conductor;
        this.finalDestino = finalDestino;
        this.dia = dia;
        this.destino = destino;
        this.estado = false; // Solo se coloca en true mientras el viaje esta en curso
        asientosDisponibles = vehiculo.getTipo().getCapacidad()-pasajeros.size();
        
        // Falta Añadir viaje a la lista de la terminal.
    }
    
	/**
	 * Método para obtener la duración del Viaje.
	 * @return int, dependiendo las condiciones establecidas: Destino, Vehiculo y Experiencia del Conductor.
	 */
    public double calcularDuracion(Destino Final, Vehiculo vehiculo, Conductor conductor) {
    	double distancia = Final.getDistancia();
    	double velocidad = vehiculo.getVelocidadPromedio();
    	double tiempo =  distancia/velocidad;
    	if (conductor.getExperiencia()> 0.5) { // Verificar si la experiencia del conductor es mayor que 0.5
    		double factorReduccion = 0.9; // Reducción del 10%
            tiempo *= factorReduccion;
    	}
    	return tiempo;
    }
    
	/**
	 * Método para obtener la tarica del Viaje.
	 * @return int, dependiendo las condiciones establecidas: duración y tipo de vehiculo.
	 */
    public double calcularTarifa(double duracion, Vehiculo vehiculo) {
    	
    	int costoPorMinuto = 0;
        double total = 0;

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
            	total = vehiculo.getTransportadora().getEstrellas()*finalDestino.getDistancia()*5;
                return total;
            default:
                System.out.println("Tipo de vehículo no válido.");
                return -1; // Valor de retorno inválido
        }
        // Calcular la tarifa total
        total =  (costoPorMinuto * duracion);
        
        return total;
    }
    
    
    public String validacion(Viaje viaje, ArrayList<Viaje> viajesEnCurso,  ArrayList<Viaje> viajes) {
    	if (viajes.contains(viaje)) {
    		viajesEnCurso.add(viaje);
    		viaje.setEstado(true);
    		if (viajesEnCurso.contains(viaje)) {
    			System.out.println("El viaje está en curso.");
				viajes.remove(viaje);
				System.out.println(viajes);
				System.out.println(viajesEnCurso);
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
    
   
    public String estado() {
        // Implementación pendiente
    	
    	return null;
    }
    
    // Getters y Setters
    
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
    public Destino getFinalDestino() {
        return finalDestino;
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
    public Destino getDestino() {
        return destino;
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

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
    
    
}
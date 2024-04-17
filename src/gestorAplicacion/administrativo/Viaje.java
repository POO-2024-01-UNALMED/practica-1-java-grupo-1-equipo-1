package gestorAplicacion.administrativo;

import java.util.ArrayList;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.constantes.Dia;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Esta clase representa a un viaje que tiene atributos como id, tarifa, duración, hora, pasajeros, total viajes, vehículo, conductor, destino final, día y destino.
 * nos servirá para programar los distintos viajes que ofrecerá la transportadora.
 */
 

public class Viaje {
    
    private int id; // Identificador del viaje
    private int tarifa; // Tarifa del viaje
    private int duracion; // Duración del viaje en minutos
    private int totalViajes; // Número total de viajes realizados
    private String hora; // Hora de inicio del viaje
    private ArrayList<Pasajero> pasajeros = new ArrayList<>(); // Lista de pasajeros del viaje
    private Vehiculo vehiculo; // Vehículo utilizado en el viaje
    private Conductor conductor; // Conductor del vehículo en el viaje
    private Destino finalDestino; // Destino final del viaje
    private Dia dia; // Día en que se realiza el viaje
    private Destino destino; // Destino actual del viaje
    
    public Viaje() {
    	
    	// Implementación pendiente
        
    }
    
    
    public int calcularTarifa() {
        // Implementación pendiente
    	
    	return 0;
    }
    
    
    public String validacion() {
        // Implementación pendiente
    	
    	return null;
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
    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * Obtiene la tarifa del viaje.
     * @return La tarifa del viaje.
     */
    public int getTarifa() {
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
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece o modifica el número total de viajes realizados.
     * @param totalViajes El número total de viajes realizados.
     */
    public void setTotalViajes(int totalViajes) {
        this.totalViajes = totalViajes;
    }

    /**
     * Obtiene el número total de viajes realizados.
     * @return El número total de viajes realizados.
     */
    public int getTotalViajes() {
        return totalViajes;
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
}
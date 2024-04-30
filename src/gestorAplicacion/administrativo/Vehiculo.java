package gestorAplicacion.administrativo;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.Conductor;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Autores: Jaime Luis Osorio Gómez, Santiago Ochoa Cardona, Juan Camilo Marín Valencia, Johan Ramírez Marín, Jonathan David Osorio Restrepo.
 * Esta clase representa a un vehículo, el cual tiene atributos como integridad, placa, modelo, precio, velocidad promedio, tipo, capacidad, conductores y transportadora asociada
 * nos servirá para realizar los distintos viajes, asociar un conductor a un vehículo, reparar el vehículo y venderlo.
 */
public class Vehiculo {

    private boolean estado; //Disponibilidad para viajar del vehiculo
	private int integridad; // Nivel de integridad del vehículo
    private String placa; // Placa del vehículo
    private String modelo; // Modelo del vehículo
    private int precio; // Precio del vehículo
    private int velocidadPromedio; // Velocidad promedio del vehículo
    private TipoVehiculo tipo; // Tipo de vehículo 
    private TipoVehiculo capacidad; // Capacidad pasajeros del vehículo
    private ArrayList<Conductor> conductores; // Lista de conductores asociados al vehículo
    private Transportadora transportadora; // Transportadora asociada al vehículo

    
    /**
     * Constructor de la clase Vehículo que inicializa los atributos del vehículo.
     * @param placa, la placa del vehículo.
     * @param modelo, el modelo del vehículo.
     * @param precio, el precio del vehículo.
     * @param velocidadPromedio,la velocidad promedio del vehículo.
     * @param tipo, el tipo de vehículo.
     * @param capacidad, la capacidad de pasajeros del vehículo.
     * @param transportadora, la transportadora asociada al vehículo.
     */
    public Vehiculo(String placa, String modelo, int precio, int velocidadPromedio, TipoVehiculo tipo, TipoVehiculo capacidad, Transportadora transportadora) {
        this.integridad = 100;
        this.placa = placa;
        this.modelo = modelo;
        this.precio = precio;
        this.velocidadPromedio = velocidadPromedio;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.transportadora = transportadora;
    }
    
	public Vehiculo() {
		
		
	}
	
	/**
	 * Metodo que representa el viaje del vehiculo, en este se ejecutan otros metodos como la reduccion de la integridad del vehiculo,
	 *se calcula la posibilidad de sufrir un accidente. etc.
	 * @param kilometros; Cantidad de km del viaje
	 */
	public void viaje (int kilometros) {
		
		if (disponibilidad()) {
			
			this.reduccionIntegridad(kilometros);
			this.accidente();
				
		}
	}
	
	/**
	 * Verifica si el vehiculo esta disponible para viajar o no
	 * @return
	 */
	public boolean disponibilidad () {
		
		if (integridad > 0 && verificarConductor()) {
			this.estado = true;
			return (this.estado);
			
		}
		
		this.estado = false;
		return(this.estado);
	}
	
	/**
	 * Informa que el vehiculo fue destruido
	 * @return
	 */
	
	public String vehiculoDestruido () {
		
		return("El vehiculo ha quedado inutilizado");
		
	}
	
	/**
	 * Basandose en un random calcula si el vehiculo sufrira un accidente o no en alguna parte del viaje
	 */
	
	public void accidente () {
		
		if (new Random().nextInt(100) <= 5) {
			
			this.integridad = 0;
			this.vehiculoDestruido();
		}
 		
	}

    /**
     * Reduce la integridad del vehículo según la duración de un viaje.
     * @param duracionViaje,lLa duración del viaje en horas.
     */
    public void reduccionIntegridad(int duracionViaje) {
        this.integridad = Math.round(this.integridad - (duracionViaje * new Random().nextFloat(2) / 10));
    }

    /**
     * Asocia un conductor al vehículo.
     * @param conductor, el conductor a asociar.
     */
    public void asociarConductor(Conductor conductor) {
        this.conductores.add(conductor);
    }

    /**
     * Quita un conductor asociado al vehículo.
     * @param nombre, el nombre del conductor a quitar.
     */
    public void quitarConductor(String nombre) {
        for (Conductor i : conductores) {
            if (i.getNombre().equals(nombre)) {
                conductores.remove(i);
                System.out.println(nombre + " ha sido eliminado de los conductores asociados");
                return;
            }
        }
        System.out.println("Ningún conductor con ese nombre está asociado al vehículo");
    }

    /**
     * Repara el vehículo, restaurando su integridad al 100%.
     */
    public void reparacion() {
        this.integridad = 100;
    }

    /**
     * Verifica si el vehículo tiene conductores asociados.
     * @return True si tiene conductores asociados, False si no los tiene.
     */
    public boolean verificarConductor() {
		
		if (conductores.size() == 0) {
			
			return (false);
		}
		
		return (true);
				
	}


    // Getters y Setters
    /**
     * Establece o modifica la integridad del vehículo.
     * @param integridad La integridad del vehículo.
     */
    public void setIntegridad(int integridad) {
        this.integridad = integridad;
    }

    /**
     * Obtiene la integridad del vehículo.
     * @return La integridad del vehículo.
     */
    public int getIntegridad() {
        return integridad;
    }

    /**
     * Establece o modifica la placa del vehículo.
     * @param placa La placa del vehículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene la placa del vehículo.
     * @return La placa del vehículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece o modifica el modelo del vehículo.
     * @param modelo El modelo del vehículo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el modelo del vehículo.
     * @return El modelo del vehículo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece o modifica el precio del vehículo.
     * @param precio El precio del vehículo.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el precio del vehículo.
     * @return El precio del vehículo.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece o modifica la velocidad promedio del vehículo.
     * @param velocidadPromedio La velocidad promedio del vehículo.
     */
    public void setVelocidadPromedio(int velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    /**
     * Obtiene la velocidad promedio del vehículo.
     * @return La velocidad promedio del vehículo.
     */
    public int getVelocidadPromedio() {
        return velocidadPromedio;
    }

    /**
     * Obtiene el tipo de vehículo.
     * @return El tipo de vehículo.
     */
    public TipoVehiculo getTipo() {
        return tipo;
    }

    /**
     * Obtiene la capacidad del vehículo.
     * @return La capacidad del vehículo.
     */
    public TipoVehiculo getCapacidad() {
        return capacidad;
    }

    /**
     * Establece o modifica la lista de conductores asociados al vehículo.
     * @param conductores La lista de conductores asociados al vehículo.
     */
    public void setConductores(ArrayList<Conductor> conductores) {
        this.conductores = conductores;
    }

    /**
     * Obtiene la lista de conductores asociados al vehículo.
     * @return La lista de conductores asociados al vehículo.
     */
    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    /**
     * Establece o modifica la transportadora asociada al vehículo.
     * @param transportadora La transportadora asociada al vehículo.
     */
    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    /**
     * Obtiene la transportadora asociada al vehículo.
     * @return La transportadora asociada al vehículo.
     */
    public Transportadora getTransportadora() {
        return transportadora;
    }
    
    public boolean getEstado () {
    	return (this.estado);
    }
    
    public void setEstado (boolean estado) {
    	this.estado = estado;
    }
    
  //Testeos
  	/*public static void main (String[] Args) {
  		
  		Vehiculo v = new Vehiculo();
  		
  		v.setIntegridad(100);
  		
  		v.reduccionIntegridad(70);
  		
  		System.out.println(v.getIntegridad());
  		
  	}*/

}
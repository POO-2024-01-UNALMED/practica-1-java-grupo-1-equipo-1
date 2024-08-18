package gestorAplicacion.administrativo;
import java.io.Serializable;
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
 

public class Viaje implements Serializable {
	
	private static final long serialVersionUID = 1;
	
	// Atributos
    private Terminal terminal; // Terminal
    private int id; // Identificador del viaje
    private double tarifa; // Tarifa del viaje
    private double duracion; // Duración del viaje en minutos
    private double distancia; // Distancia del viaje en Km.
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
    private Transportadora transportadora; // transportadora asociada al viaje

    
    // Constructor de la clase Viaje
    public Viaje(Terminal terminal, String hora, String fecha, Vehiculo vehiculo, Conductor conductor, Destino llegada, Dia dia, Destino salida) {
        this.terminal = terminal;
    	this.id = Viaje.totalViajes;
        this.hora = hora;
        this.fecha = fecha;
        this.vehiculo = vehiculo;
        this.conductor = conductor;
        this.llegada = llegada;
        this.dia = dia;
        this.salida = salida;
        this.estado = false; // Solo se coloca en true mientras el viaje esta en curso
    	this.distancia = calcularDistancia(salida, llegada);  // Se deja con parametros a proposito para su implementacion en otras funcionalidades 
        this.duracion = calcularDuracion();
        this.tarifa = calcularTarifa();
        this.horaLlegada = calcularHoraLlegada();
        this.asientosDisponibles = verificarAsientos();
        Viaje.totalViajes++;
        Terminal.getViajes().add(this);
        vehiculo.getTransportadora().getViajesAsignados().add(this);
        conductor.getHorario().add(this);
    }
    
    
	/**
	 * Método para obtener la distancia del Viaje.
	 * @return double, la distancia entre dos lugares.
	 */
    public double calcularDistancia(Destino salida ,Destino llegada) {
    	// Coordenadas de la Salida (INICIO)
    	double x1 = salida.getEjeX();
    	double y1 = salida.getEjeY();
       	// Coordenadas de la llegada (FIN)
    	double y2 = llegada.getEjeY();
    	double x2 = llegada.getEjeX();
    	
    	double distancia = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    	
    	return distancia;
    }
    
    
	/**
	 * Método para obtener la duración del Viaje.
	 * @return int, dependiendo las condiciones establecidas: Destino, Vehiculo y Experiencia del Conductor.
	 */
    public double calcularDuracion() {
    	double distancia = this.distancia;
    	double velocidad = this.getVehiculo().getVelocidadPromedio();
    	double tiempo =  distancia/velocidad;
    	if (this.getConductor().getExperiencia()> 0.5) { // Verificar si la experiencia del conductor es mayor que 0.5
    		double factorReduccion = 0.9; // Reducción del 10%
            tiempo *= factorReduccion;
    	}	
    	return tiempo;
    }
    
    /**
	 * Método para calcular la hora de llegada del Viaje.
	 * @return String, dependiendo las condiciones establecidas: Destino, Vehiculo y Experiencia del Conductor.
	 */
    public String calcularHoraLlegada() {
    	
    	double tiempoHorasDuracion = this.duracion;
    	String horaSalida = this.hora;
    	
    	String [] partes = horaSalida.split(":");
        int horasSalida = Integer.parseInt(partes[0]);
        int minutosSalida = Integer.parseInt(partes[1]);
        
    	String [] fechapartes = this.fecha.split("/");
    	
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
    public double calcularTarifa() {
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
                    total = vehiculo.getTransportadora().getEstrellas()*llegada.getDistancia()*50;
                    return total;
                default:
                    System.out.println("Tipo de vehículo no válido.");
                    return -1; // Valor de retorno inválido
            }
            // Calcular la tarifa total
            total =  (costoPorMinuto * duracion);
            
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
    	} else { // Siginifica que el viaje fue cancelado antes de Salir.
			Terminal.getViajes().remove(this);
    		new Viaje(this.getTerminal(), this.getHora(), this.ajusteFecha(), this.getVehiculo(), this.getConductor(), this.getLlegada(), this.getDia(), this.getSalida());
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
   
    /**
     * Genera un informe del estado actual del viaje, con detalles que varían dependiendo de si el viaje está en curso
     * o si aún no ha salido.
     *
     * @return Una cadena de texto que describe el estado del viaje. Si el viaje está en curso, incluye detalles como
     *         la ubicación actual, pasajeros en curso. Si el viaje aún no ha salido, 
     *         muestra la información relevante comno los asientos disponibles.
     */
    public String estado() {
        boolean enCurso = this.getEstado();
        String estado;
        
        if (enCurso == true) {
        	estado = "Estado del Viaje: En curso\n" + 
        			 "Detalles del Viaje:\n" +
        			 "Salida: " + this.getSalida().name() + "\n" +
        			 "Llegada: " + this.getLlegada().name() + "\n" +
        			 "Ubicacion Actual: " + this.ubicacion() + "\n" + 
        			 "Fecha de salida: " + this.getFecha() + "\n" +
        			 "Hora de salida: " + this.getHora() + "\n" +
        			 "Vehiculo: " + this.getVehiculo().getModelo() + "\n" + 
        			 "Placa: " + this.getVehiculo().getPlaca() + "\n" +
        			 "Conductor: " + this.getConductor().getNombre() +  "\n" + 
        			 "Experiencia: " + this.getConductor().getExperiencia() + "\n" + 
        			 "Pasajeros en Curso: " + (this.getVehiculo().getCapacidad() - this.verificarAsientos()) + "\n";
        } else {
        	estado = "Estado del Viaje: Sin Salir\n" + 
	       			 "Detalles del Viaje:\n" +
	       			 "Salida: " + this.getSalida().name() + "\n" +
	       			 "Llegada: " + this.getLlegada().name() + "\n" +
	       			 "Fecha de salida: " + this.getFecha() + "\n" +
	       			 "Vehiculo: " + this.getVehiculo().getModelo() + "\n" + 
	       			 "Placa: " + this.getVehiculo().getPlaca() + "\n" +
	       			 "Conductor: " + this.getConductor().getNombre() +  "\n" + 
	       			 "Experiencia: " + this.getConductor().getExperiencia() + "\n" + 
	       			 "Asientos disponibles: " + this.getAsientosDisponibles()+ "\n";
	        }

    	return estado;
    }
    
    /**
     * Calcula y devuelve la ubicación actual del viaje en curso basado en el tiempo transcurrido desde la salida.
     * 
     * Este método determina la posición del viaje en un plano cartesiano según las coordenadas de salida y llegada,
     * el tiempo de salida, la hora actual, y la duración total del viaje. Si el viaje no está en curso, se devuelve
     * la ubicación de salida.
     * 
     * @return Una cadena de texto que representa la ubicación actual del viaje en formato "x,y". Si el viaje no ha
     *         iniciado o ya ha finalizado, se devuelve la ubicación de salida.
     * 
     */
    public String ubicacion() {
    	double duracion = this.getDuracion();
    	String horaSalida = this.getHora();
    	String horaLlegada = this.getHoraLlegada();
    	String horaActual = Tiempo.salidaHora;
    	
    	String [] partesSalida = horaSalida.split(":");
        int horasSalida = Integer.parseInt(partesSalida[0]);
        int minutosSalida = Integer.parseInt(partesSalida[1]);
        
    	String [] partesLlegada = horaLlegada.split(":");
        int horasLlegada = Integer.parseInt(partesLlegada[0]);
        int minutosLlegada = Integer.parseInt(partesLlegada[1]);
        
    	String [] partesActual = horaActual.split(":");
        int horasActual = Integer.parseInt(partesActual[0]);
        int minutosActual = Integer.parseInt(partesActual[1]);
        
        
        double enMarcha = (horasActual-horasSalida)+((double)(minutosActual-minutosSalida)/60);
        System.out.println("En marcha:  " + enMarcha + " Porcentaje " + (enMarcha/duracion));
  
    	double salidax1 = this.getSalida().getEjeX();
    	double saliday1 = this.getSalida().getEjeY();
    	
    	double llegadax2 = this.getLlegada().getEjeX();
    	double llegaday2 = this.getLlegada().getEjeY();
    	
    	String ubicacion = salidax1 + "," + saliday1; // Ubicación por defecto - - - La tendra asignada el viaje mientras no esta en movimiento
    	
		double ubiX; 
		double ubiY;
		
    	
		if (this.getEstado() == true) { // La variable estado = true implica que el viaje esta en curso por lo tanto las fechas del sistema concuerdan con las de inicio del viaje y cuando termina automaticamente pasa a ser false por lo que no existiran errores de continuidad. No hay necesidad de pensar en si la duración es mayor a 24 porque el estado determina el inicio y fin. 
	    	double porcentaje = (enMarcha/duracion);
			// Caso vectores desde el Origen	
			if (salidax1 ==0 && saliday1 == 0) {
		    	ubiX = porcentaje * llegadax2;
		    	ubiY = porcentaje * llegaday2;	
		    	ubicacion = ubiX + "," + ubiY;  			
		    } else { // Para vectores libres
	            ubiX = salidax1 + porcentaje * (llegadax2 - salidax1);  // Planteamos la solucion como una ecuación escalar parametrica donde nos basamos en los punto de salida y llegada para plantearla.
	            ubiY = saliday1 + porcentaje * (llegaday2 - saliday1);
	            ubicacion = ubiX + "," + ubiY;  
		    	}
		}
    	return ubicacion;
    }

    
    /**
     * Verifica el número de asientos disponibles en el vehículo asociado a este viaje.
     * 
     * Este método calcula la cantidad de asientos disponibles restando el número de pasajeros actuales
     * de la capacidad total del vehículo asignado a este viaje.
     * 
     * @return El número de asientos disponibles en el vehículo. Un valor positivo indica la cantidad de asientos libres,
     *         mientras que un valor de cero o negativo podría indicar que no hay asientos disponibles o que hay más pasajeros
     *         de los que puede acomodar el vehículo.
     */
    public int verificarAsientos() {
        int total;
        
        total = this.getVehiculo().getTipo().getCapacidad() - this.pasajeros.size();    // Calcula el total de asientos disponibles restando el número de pasajeros de la capacidad del vehículo.
        
        return total;
    }
    
    /**
     * Compara este objeto `Viaje` con otro objeto `Viaje` para verificar si son el mismo objeto en memoria.
     * 
     * @param f El objeto `Viaje` con el que se desea comparar este objeto `Viaje`.
     *          Puede ser nulo.
     * 
     * @return `true` si el objeto `Viaje` dado es no nulo y apunta al mismo objeto que este `Viaje`.
     *         De lo contrario, devuelve `false`.
     */
    public boolean isequals(Viaje f) {
        boolean variable = false;
        
        if (f != null) { // Verifica que el objeto pasado no sea nulo.
            if (f == this) {  // Compara las referencias de los objetos para determinar si son el mismo objeto.
                variable = true;
            }
        }
        
        return variable;
    }
    
	/**
	 * Metodo para mostrar los detalles del viaje
	 * @return string con los detalles del viaje*/
	
	public String detallesViaje() {
		String mensaje = "";
		mensaje = "Fecha del viaje: " + getFecha() + " Destino: " + getLlegada() + "Id: " + getId();
		return mensaje;
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
     * Establece o modifica la distancia del viaje.
     * @param estado el estado del viaje.
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /**
     * Obtiene la Distancia del viaje.
     * @return La distancia en Km del viaje.
     */
    public double getDistancia() {
        return distancia;
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
	
	/**
     * Establece o modifica la transportadora asociada al viaje.
     * @param transportadora nueva que se va a asociar a dicho viaje
     */
	public void setTransportadora(Transportadora transportadora) {
		
		this.transportadora = transportadora;
		
	}
	
	/**
     * Obtiene la trasnportadora asociada al viaje.
     * @return la transportadora asociada al viaje
     */
	
	public Transportadora getTransportadora() {
		
		return this.transportadora;
		
	}
	
	
    
}
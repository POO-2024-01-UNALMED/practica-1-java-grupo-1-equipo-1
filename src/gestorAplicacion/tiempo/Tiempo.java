package gestorAplicacion.tiempo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
/////// Importaciones para el tiempo ///////
import java.util.Timer;
import java.util.TimerTask;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Vehiculo;
import gestorAplicacion.usuarios.Mecanico;
import gestorAplicacion.administrativo.Taller;

//Importaciones

import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.*;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;

public class Tiempo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private transient Timer timer;  // Se coloca el modificador transient debido a que la clase Timer no es Serializable
	
	// Atributos para el Tiempo	
	public static int minutos = 0;
	public static int horas = 1;
	public static int dias = 1;
	public static int semana = 0;
	public static int meses = 1;
	public static int año = 2024;
	public static Dia diaNombre;
	public static ArrayList<Tiempo> principal = new ArrayList<>();
	
	// Atributos para formato de salida
	public static String salidaFecha = (Tiempo.dias + "/" + Tiempo.meses + "/" + Tiempo.año);
	public static String salidaHora = (Tiempo.horas + ":" + Tiempo.minutos);
	
	// Constructor
	public Tiempo() {
        timer = new Timer();
        iniciar(); // Iniciar el temporizador automáticamente al crear una instancia de SistemaDeTiempo
        Tiempo.principal.add(this);
    }
	
	// Métodos
	
    // Serialización y Deserialización Personalizada, Necesario para volver a iniciar el contador y actualizar los Atributos Staticos.
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        timer = new Timer();
        iniciar();
        Deserializador.cargarEstado(); // Restaurar estado estático
    }
   
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        Serializador.guardarEstado(); // Guardar estado estático
    }
    
	/**
	 * Imprime la fecha y hora actual en un formato completo para propósitos de prueba.
	 * 
	 * Este método muestra en la consola:
	 * - La fecha en formato "día/mes/año"
	 * - La hora en formato "hora:minutos"
	 * - El día de la semana correspondiente
	 * 
	 */
	public static String mostrarTiempo() {
	    // Construye la cadena de texto con el formato deseado
	    String tiempo = String.format(
	        "\n----------------------------------------------------------------------------------------------\n" +
	        "Fecha: %d/%d/%d     Hora: %d:%d   Hoy es: %s\n" +
	        "----------------------------------------------------------------------------------------------",
	        dias, meses, año, horas, minutos, diaNombre
	    );

	    // Retorna la cadena construida
	    return tiempo;
	}
	
	/**
	 * Calcula el día de la semana para una fecha dada.
	 * 
	 * @param fecha La fecha en formato "dd/MM/yyyy".
	 * @return El día de la semana correspondiente a la fecha.
	 */
	public static Dia calcularDia(String fecha) {
    	
    	String [] fechapartes = fecha.split("/");
    	
    	int dia = Integer.parseInt(fechapartes[0]);
    	int mes = Integer.parseInt(fechapartes[1]);
    	int año = Integer.parseInt(fechapartes[2]);
    	
        // Fecha base: 1 de enero de 2024 es un domingo
        int baseDia = 1;
        int baseMes = 1;
        int baseAño = 2024;
        Dia diaBase = Dia.LUN;

        // Calcular el número de días totales desde la fecha base
        int diasDesdeBase = 0;

        // Añadir días por años completos
        diasDesdeBase += (año - baseAño) * 360;  // Suponiendo 30 días por mes y 12 meses por año

        // Añadir días por meses completos del año actual
        diasDesdeBase += (mes - baseMes) * 30;

        // Añadir días del mes actual
        diasDesdeBase += (dia - baseDia);

        // Determinar el día de la semana
        Dia[] diasSemana = Dia.values();
        int indiceDia = (diasDesdeBase + diaBase.ordinal()) % 7;
        
        return diasSemana[indiceDia];
    }
	
	
	/**
	 * 
	 * @return fecha y hora en minutos
	 */
	public static int getFechaHora () {

		return ((525600 * año) + (43800 * meses) + (10950 * semana) + (1440 + dias) + (60 * horas));
	}
	
	public String cancel() {  // Metodo necesario para frenar el tiempo al salir del programa
		timer.cancel();
		return "Fin del Tiempo";
	}

    public void iniciar() {
        // Programar una tarea que se ejecute cada cierto intervalo de tiempo
        timer.scheduleAtFixedRate(new TareaPeriodica(), 0, 1); // Tiempo de Iteraciones 1 Segundo = 1000 Milisegundos
        
        System.out.println("Inicio del Tiempo"); // -- -- --  --  -- Activar para saber si esta coriendo el Tiempo, o si se activo otra linea (Hilo). 
    }

    private class TareaPeriodica extends TimerTask {
       
    	@Override
        public void run() {  // Métodos repetitivos
    		
    		// Métodos para calcular el Tiempo no apagar, 
    		calcularDia(); // Calcular Dia
    		calcularHora(); // Calcula la hora
            calcularSalidaHora(); // Formato de Salida Hora
            calcularSalidaFecha(); // Formato de Salida Fecha
            
    		// Formatos de Salida
            //comprobarUbicacion(); // Solo para pruebas 
    		
    		// Comprobaciones y actualizaciones
        	//comprobarViajes(Terminal.getViajes()); // Verifica el momento de salida de los viajes
        	//comprobarViajesEnCurso(Terminal.getViajesEnCurso()); // Verifica el momento de llegada de los viajes
    		//comprobarHora();
            
//            mecanicosDisponibles(); // Define que mecanicos tienen vehiculos  por reparar
//            verificarVehiculos(); // Verifica si la hora de la reparacion ya paso
//            verificarVehiculosVenta(); //Verifica si ya paso la hora de venta de los vehiculos
    		
            
        	//comprobarViajes(Terminal.viajes); // Verifica el momento de salida de los viajes
        	//comprobarViajesEnCurso(Terminal.viajesEnCurso);
         

        }
    	
    	// Métodos Repetitivos
    	
    	/**
    	 * Calcula el día de la semana basado en el número de días transcurridos desde una fecha base.
    	 * La fecha base se asume como 1 de enero de 2024, que es un lunes (LUN).
    	 * La duración del año se considera como 360 días (30 días por mes).
    	 * 
    	 * Se actualiza el atributo estático `diaNombre` con el día de la semana correspondiente.
    	 */
    	
        public static void calcularDia() {
            int baseDia = 1;   // Fecha base: 1 de enero de 2024 es un Lunes
            int baseMes = 1;
            int baseAño = 2024;
            Dia diaBase = Dia.LUN;


            int diasDesdeBase = 0;                   // Almacena el número de días totales desde la fecha base

            diasDesdeBase += (año - baseAño) * 360;  // Suponiendo 30 días por mes y 12 meses por año

            diasDesdeBase += (meses - baseMes) * 30; // Añadir días por meses completos del año actual

            diasDesdeBase += (dias - baseDia);       // Añadir días del mes actual

            Dia[] diasSemana = Dia.values();         // Determinar el día de la semana
            int indiceDia = (diasDesdeBase + diaBase.ordinal()) % 7;
            Tiempo.diaNombre = diasSemana[indiceDia];
        }
    	
    	
    	/**
    	 * Actualiza la hora, los minutos, los días, los meses y los años de acuerdo con el paso del tiempo.
    	 * Este método incrementa los minutos y realiza ajustes para las horas, días, meses y años con el paso del tiempo durante la ejecucion.
    	 * 
    	 */
        
    	public static void calcularHora() {
    	    Tiempo.minutos++;
    	    if (Tiempo.minutos > 60) {
    	        Tiempo.horas++;
    	        Tiempo.minutos = 0;
    	        if (Tiempo.horas > 23) {
    	            Tiempo.dias++;
    	            Tiempo.horas = 0;
    	            if (Tiempo.dias % 7 == 0) { // Verifica si han pasado 7 días
    	                Tiempo.semana++;
    	            }
    	            if (Tiempo.dias > 30) { // Verifica si han pasado 30 días
    	                Tiempo.meses++;
    	                Tiempo.dias = 1; // Reinicia el contador de días
    	                if (Tiempo.meses >= 12) { // Si han pasado 12 meses, suma un año y reinicia el contador de meses
    	                    Tiempo.año++;
    	                    Tiempo.meses = 1;
    	                }
    	            }
    	        }
    	    }
    	}
	
    	/**
    	 * Revisa una lista de viajes y verifica si alguno de ellos coincide con la fecha y hora actuales.
    	 * 
    	 * Este método recorre la lista de viajes proporcionada y realiza las siguientes acciones:
    	 * - Verifica si el viaje tiene la misma fecha que la fecha actual almacenada en `Tiempo.salidaFecha`.
    	 * - Si la fecha coincide, también verifica si la hora del viaje coincide con la hora actual en `Tiempo.salidaHora`.
    	 * - Si ambos coinciden, se considera que el viaje está próximo a salir,
    	 *   y se llama al método `validacion` del viaje para realizar las acciones necesarias.
    	 * 
    	 * @param viajes Lista de objetos `Viaje` que se revisarán para encontrar viajes próximos a salir.
    	 */
    	
    	public static void comprobarViajes(ArrayList<Viaje> viajes) {
            if (viajes != null) {
            	for (int i = 0; i < viajes.size(); i++) {
            		Viaje viaje = viajes.get(i);
            		// System.out.println(viaje.getHora() + viaje.getFecha());
            		if (viaje.getFecha().equals(Tiempo.salidaFecha)) {
            			//System.out.println("Viaje Próximo a Salir: " + viaje.getId());
            			if (viaje.getHora().equals(Tiempo.salidaHora)) {
            				//System.out.println("\nEl viaje " + viaje.getId()+ " Salio a la: " + Tiempo.salidaHora);
            				viaje.validacion(viaje, Terminal.getViajesEnCurso(), viajes);
            			}
            		}
            	}
            }
    	}
    	
    	/**
    	 * Revisa una lista de viajes en curso para determinar si alguno ha llegado a su destino en la fecha y hora actuales.
    	 * 
    	 * Este método recorre la lista de viajes proporcionada y realiza las siguientes acciones:
    	 * - Verifica si el viaje tiene la misma fecha que la fecha actual almacenada en `Tiempo.salidaFecha`.
    	 * - Si la fecha coincide, también verifica si la hora de llegada del viaje coincide con la hora actual en `Tiempo.salidaHora`.
    	 * - Si ambos coinciden, se considera que el viaje ha llegado a su destino, se imprime un mensaje en la consola 
    	 *   y se llama al método `programacionAutomatica` del viaje para realizar las acciones necesarias de actualización.
    	 * 
    	 * @param viajes Lista de objetos `Viaje` que se revisarán para encontrar viajes que hayan llegado a su destino.
    	 */
    	
    	public static void comprobarViajesEnCurso(ArrayList<Viaje> viajes) {
            if (viajes != null) {
            	for (int i = 0; i < viajes.size(); i++) {
            		Viaje viaje = viajes.get(i);
            		// System.out.println(viaje.getHora() + viaje.getFecha());
            		if (viaje.getFecha().equals(Tiempo.salidaFecha)) {
            			//System.out.println("Viaje Próximo a Salir: " + viaje.getId());
            			if (viaje.getHoraLlegada().equals(Tiempo.salidaHora)) {
            				//System.out.println("\nEl viaje " + viaje.getId()+ "Llego a la: " + Tiempo.salidaHora);
            				viaje.programacionAutomatica();
            			}
            		}
            	}
            }
    	}
    	
    	/** Verifica que mecanicos tienen vehiculos pendientes por arreglar
    	 * 
    	 */
    	public static void mecanicosDisponibles () {
    		// COMPROBAR SI ES NULL ANTES DE ENTRAR AL FOR
    		for (Mecanico i : Mecanico.getMecanicos()) {
    			
    			if (i.getVehiculosReparando().size() == 0 ) {
    				
    				i.setEstado(true);
    			}
    			
    			else {i.setEstado(false);}
    			
    		}
    	}
    	

    	/** Verifica si ya ocurrio la hora de salida de los vehiculos del taller
    	 * 
    	 */
        public static void verificarVehiculos () {
        	
        	for (Taller taller : Taller.getListaTalleres()) {
        		
        		for (Vehiculo vehiculo : taller.getVehiculosEnReparacion()) {
        			
        			if (vehiculo.getFechaHoraReparacion() <= Tiempo.getFechaHora()) {
        				
        				vehiculo.getMecanicoAsociado().repararVehiculo(vehiculo);
        			}
        		}
        	}
        	
        	
        }
        
        /**
         * Verificia si ya paso la hora de venta del vehiculo
         */
        
        public static void verificarVehiculosVenta () {
        	
        	for (Taller taller : Taller.getListaTalleres()) {
        		
        		for (Vehiculo vehiculo : taller.getVehiculosEnVenta()) {
        			
        			if (vehiculo.getFechaHoraReparacion() <= Tiempo.getFechaHora()) {
        				
        				vehiculo.getTransportadora().getTaller().venderVehiculo(vehiculo);
        			}
        		}
        	}
        	
        }
    	
    	/**
    	 * Calcula la hora de salida basándose en la hora y minutos actuales.
    	 * 
    	 */
    	private static void calcularSalidaHora(){
    		Tiempo.salidaHora = (Tiempo.horas + ":" + (Tiempo.minutos+1));
    	}
    	/**
    	 * Calcula la fecha de salida basándose en la hora y minutos actuales.
    	 * 
    	 */
    	private static void calcularSalidaFecha(){
    		Tiempo.salidaFecha = (Tiempo.dias + "/" + Tiempo.meses + "/" + Tiempo.año);
    	}
    }
}
package gestorAplicacion.tiempo;
import java.util.ArrayList;
import java.util.Scanner;
/////// Importaciones para el tiempo ///////
import java.util.Timer;
import java.util.TimerTask;

import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Vehiculo;

//Importaciones

import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.*;
import gestorAplicacion.usuarios.Conductor;
import gestorAplicacion.usuarios.Pasajero;

public class Tiempo {
	private Timer timer;
	
	// Atributos para el Tiempo
	public static ArrayList<Viaje> viajes; // Solo va para las pruebas
	public static ArrayList<Viaje> viajesEnCurso; // Solo va para las pruebas
	
	
	public static int minutos = 0;
	public static int horas = 1;
	public static int dias = 1;
	public static int semana = 0;
	public static int meses = 1;
	public static int año = 2024;
	public static Dia diaNombre;
	
	// Atributos para formato de salida
	public static String salidaFecha = (Tiempo.dias + "/" + Tiempo.meses + "/" + Tiempo.año);
	public static String salidaHora = (Tiempo.horas + ":" + Tiempo.minutos);
	
	// Métodos
	public Tiempo() {
        timer = new Timer();
        iniciar(); // Iniciar el temporizador automáticamente al crear una instancia de SistemaDeTiempo
    }

    public void iniciar() {
    	Tiempo.viajes = new ArrayList<Viaje>(); // Solo va para las pruebas
    	Tiempo.viajesEnCurso = new ArrayList<Viaje>();// Solo va para las pruebas
    	
    	
        // Programar una tarea que se ejecute cada cierto intervalo de tiempo
        timer.scheduleAtFixedRate(new TareaPeriodica(), 0, 10); // Tiempo de Iteraciones 1 Segundo = 1000
        
        
        /// Métodos necesarios para implementar 
        
        /**
         * comprobarViajes
         * comprobarViajesEnCurso
         * comprobarSalarios
         * comprobarReparaciones
         * 
        **/
        System.out.println("Inicio del Tiempo");
    }

    private class TareaPeriodica extends TimerTask {
       
    	@Override
        public void run() {
    		
    		// Métodos repetitivos
    		
    		// Métodos para calcular el Tiempo
    		calcularDia(); // Calcular Dia
    		calcularHora(); // Calcula la hora
    		
    		// Comprobaciones y actualizaciones
        	comprobarViajes(viajes); // Verifica el momento de salida de los viajes
    		//comprobarHora();
    		
    		// Formatos de Salida
    		//mostrarTiempo(); // Formato de salida General
            calcularSalidaHora(); // Formato de Salida Hora
            calcularSalidaFecha(); // Formato de Salida Fecha
         

        }
    	
    	/// Métodos Repetitivos
    	
    	public static void calcularDia() {
    		switch (dias % 7) {
            case 1:
            	Tiempo.diaNombre = Dia.LUN;
                break;
            case 2:
            	Tiempo.diaNombre = Dia.MAR;
                break;
            case 3:
            	Tiempo.diaNombre = Dia.MIER;
                break;
            case 4:
            	Tiempo.diaNombre = Dia.JUE;
                break;
            case 5:
            	Tiempo.diaNombre = Dia.VIE;
                break;
            case 6:
            	Tiempo.diaNombre = Dia.SAB;
                break;
            case 7:
            	Tiempo.diaNombre = Dia.DOM;
                break;
            default:
                break;
    		}
    	}
    	
    	public static void calcularHora() {
        	Tiempo.minutos++;
        	if (Tiempo.minutos == 60) {
        		Tiempo.horas++;
        		Tiempo.minutos = 0;
        		//cancel(); // Frenarlo por las pruebas
        		if (Tiempo.horas == 24) {
        			Tiempo.dias++;
        			Tiempo.horas = 0;
        			if (Tiempo.dias ==7) {
        				Tiempo.semana++;
        				Tiempo.dias = 0;
        				if (Tiempo.semana == 4) {
        					Tiempo.meses++;
        					Tiempo.semana = 0;
        					if (Tiempo.meses == 12) {
        						Tiempo.año++;
        						Tiempo.meses = 0;
        					}
        				}
        			}
        		}
        	}
    	}
    	
    	public static void mostrarTiempo() {
        	System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.print("Fecha: " + dias + "/" + meses + "/" + año + "     Hora: " + horas + ":" + minutos + "   Hoy es: " + diaNombre);
            System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.println(Tiempo.salidaHora);
    	}
    	
    	public static boolean comprobarHora() {
    		Boolean variable = false;
    		if (viajes != null) {
    			for (Viaje viaje : viajes) {
    				if (Tiempo.salidaHora.equals(viaje)) {
    					System.out.println("Salio el viaje" + Tiempo.salidaHora);
    					variable = true ;
    				}
    			}
    		}
			return variable;
    	}
    	
    	public static void comprobarViajes(ArrayList<Viaje> viajes) {
            if (viajes != null) {
            	for (int i = 0; i < viajes.size(); i++) {
            		Viaje viaje = viajes.get(i);
            		// System.out.println(viaje.getHora() + viaje.getFecha());
            		if (viaje.getFecha().equals(Tiempo.salidaFecha)) {
            			//System.out.println("Viaje Próximo a Salir: " + viaje.getId());
            			if (viaje.getHora().equals(Tiempo.salidaHora)) {
            				System.out.println("El viaje " + viaje.getId()+ " Salio a la: " + Tiempo.salidaHora);
            				viaje.validacion(viaje, viajesEnCurso, viajes);
            			}
            		}
            	}
            }
    	}
    	
    	public static void calcularSalidaHora(){
    		Tiempo.salidaHora = (Tiempo.horas + ":" + (Tiempo.minutos+1));
    	}
    	
    	public static void calcularSalidaFecha(){
    		Tiempo.salidaFecha = (Tiempo.dias + "/" + Tiempo.meses + "/" + Tiempo.año);
    	}
    	
    }
    
    //////////////////////////////////////// ESPACIO DE PRUEBAS //////////////////////////////////////////
    
    public static void main(String[] args) {
        new Tiempo(); // Crear una instancia de Tiempo automáticamente inicia el temporizador   /// IMPORTANTE
        

        ////////////////////////////////////////  OBJETOS PRUEBA ///////////////////////////////////////
        
        TipoVehiculo tipo1 = TipoVehiculo.TAXI;
        TipoVehiculo tipo2 = TipoVehiculo.VANS;

        Transportadora transportadora = new Transportadora();
        
        Vehiculo vehiculo1 = new Vehiculo("MARCOPOLO A800", "Modelo1", 100, 60, tipo1, tipo1, transportadora);
        Vehiculo vehiculo2 = new Vehiculo("FORD F600", "Modelo2", 150, 70, tipo2, tipo2, transportadora);
        

        //////////////////////////////////////// DIAS OBJETOS PRUEBA /////////////////////////////////////// 
        Dia dia1 = Dia.LUN;
        Dia dia2 = Dia.MAR;
        Dia dia3 = Dia.MIER;
        Dia dia4 = Dia.JUE;
        Dia dia5 = Dia.VIE;
        Dia dia6 = Dia.SAB;

        //////////////////////////////////////// DESTINO OBJETOS PRUEBA /////////////////////////////////////// 
        Destino destino1 = Destino.GUARNE;
        Destino destino2 = Destino.BELLO;

        //////////////////////////////////////// CONDUCTOR OBJETOS PRUEBA /////////////////////////////////////// 
        Conductor conductor = new Conductor(vehiculo1, transportadora, new ArrayList<>());
        
        //////////////////////////////////////// VIAJES OBJETOS PRUEBA ///////////////////////////////////////  
        Viaje viaje1 = new Viaje(1, "1:5", "1/1/2024" , vehiculo1, conductor, destino1, dia6, destino2);
        Viaje viaje2 = new Viaje(2, "6:35", "2/1/2024" ,vehiculo2, conductor, destino2, dia2, destino1);
        Viaje viaje3 = new Viaje(3, "7:17", "2/1/2024" ,vehiculo1, conductor, destino1, dia3, destino1);
        Viaje viaje4 = new Viaje(4, "10:12", "2/1/2024" ,vehiculo2, conductor, destino2, dia4, destino1);
        Viaje viaje5 = new Viaje(5, "19:24", "4/1/2024" ,vehiculo1, conductor, destino1, dia5, destino2);
        Viaje viaje6 = new Viaje(6, "23:57", "4/1/2024" ,vehiculo2, conductor, destino2, dia6, destino1);
        
        ////////////////////////////////////////    LISTA DE VIAJES    /////////////////////////////////////// 
        Tiempo.viajes.add(viaje1);
        Tiempo.viajes.add(viaje2);
        Tiempo.viajes.add(viaje3);
        Tiempo.viajes.add(viaje4);
        Tiempo.viajes.add(viaje5);
        Tiempo.viajes.add(viaje6);


        //////////////////////////////////////// PRUEBA DE ENTRADAS //////////////////////////////////////////  
        //System.out.println("Detalles del Viaje 1:");
        //System.out.println(viaje1.toString());
        //System.out.println("\nDetalles del Viaje 2:");
        //System.out.println(viaje2.toString());
        
        //System.out.println(Tiempo.salidaFecha + "   " + Tiempo.salidaHora);
        //System.out.println(viaje1.getFecha() + "   " + viaje1.getHora());
        
        System.out.println(viaje1.getFinalDestino().getDistancia());
        System.out.println(vehiculo1.getVelocidadPromedio());
        System.out.println(vehiculo1.getVelocidadPromedio());
        System.out.println(viaje2.getDuracion());
        System.out.println(viaje3.getDuracion());
        System.out.println(viaje4.getDuracion());


        
        if(viaje1.getHora().equals(Tiempo.salidaHora)) {
        	System.out.println("OK");
        }
        
        Scanner lectura = new Scanner (System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre = lectura.next();
        System.out.println("Ingrese su edad: ");
        int edad = lectura.nextInt();
        System.out.println("Su nombre es: " + nombre + " y su edad es: " + edad);
    	System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.print("Fecha: " + dias + "/" + meses + "/" + año + "     Hora: " + horas + ":" + minutos + "   Hoy es: " + diaNombre);
        System.out.println("\n----------------------------------------------------------------------------------------------");
        
        System.out.println(Tiempo.salidaFecha + "   " + Tiempo.salidaHora);
    }
}
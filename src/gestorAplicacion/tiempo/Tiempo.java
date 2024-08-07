package gestorAplicacion.tiempo;
import java.util.ArrayList;
import java.util.Scanner;
/////// Importaciones para el tiempo ///////
import java.util.Timer;
import java.util.TimerTask;

import gestorAplicacion.administrativo.Terminal;
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
	public static ArrayList<Viaje> historial; // Solo va para las pruebas
	
	
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
    	Tiempo.historial = new ArrayList<Viaje>();// Solo va para las pruebas
    	
    	
        // Programar una tarea que se ejecute cada cierto intervalo de tiempo
        timer.scheduleAtFixedRate(new TareaPeriodica(), 0, 1); // Tiempo de Iteraciones 1 Segundo = 1000 Milisegundos
        
        
        /// Métodos necesarios para implementar 
        
        /**
         * comprobarViajes
         * comprobarViajesEnCurso
         * comprobarSalarios
         * comprobarReparaciones
         * 
        **/
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
    		//mostrarTiempo(); // Formato de salida General para pruebas 
            //comprobarUbicacion(); // Solo para pruebas 
    		
    		// Comprobaciones y actualizaciones
        	//comprobarViajes(viajes); // Verifica el momento de salida de los viajes
        	//comprobarViajesEnCurso(viajesEnCurso); // Verifica el momento de llegada de los viajes
    		//comprobarHora();
    		
            
        	//comprobarViajes(Terminal.viajes); // Verifica el momento de salida de los viajes
        	//comprobarViajesEnCurso(Terminal.viajesEnCurso);
         

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
    	    if (Tiempo.minutos > 60) {
    	        Tiempo.horas++;
    	        Tiempo.minutos = 0;
    	        if (Tiempo.horas > 24) {
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

    	
    	
    	// Método que sirve para imprimir la hora y fecha actual con un formato completo, Util para pruebas...
    	public static void mostrarTiempo() {
        	System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.print("Fecha: " + dias + "/" + meses + "/" + año + "     Hora: " + horas + ":" + minutos + "   Hoy es: " + diaNombre);
            System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.println(salidaHora);
    	}
    	
    	public static void comprobarUbicacion() {
    		if (viajesEnCurso != null) {
    			for (Viaje viaje : viajesEnCurso) {
    				if(viaje.getSalida().getEjeX() == 0 && viaje.getSalida().getEjeY() == 0) {
    					System.out.println("Viaje "+ viaje.getId() + "  Coor: " + viaje.ubicacion() + " Hora Actual " + Tiempo.salidaHora + "   Hora de Inicio " + viaje.getHora());
    				}
    				

    			}
    		}
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
            				System.out.println("\nEl viaje " + viaje.getId()+ " Salio a la: " + Tiempo.salidaHora);
            				viaje.validacion(viaje, viajesEnCurso, viajes);
            			}
            		}
            	}
            }
    	}
    	public static void comprobarViajesEnCurso(ArrayList<Viaje> viajes) {
            if (viajes != null) {
            	for (int i = 0; i < viajes.size(); i++) {
            		Viaje viaje = viajes.get(i);
            		// System.out.println(viaje.getHora() + viaje.getFecha());
            		if (viaje.getFecha().equals(Tiempo.salidaFecha)) {
            			//System.out.println("Viaje Próximo a Salir: " + viaje.getId());
            			if (viaje.getHoraLlegada().equals(Tiempo.salidaHora)) {
            				System.out.println("\nEl viaje " + viaje.getId()+ "Llego a la: " + Tiempo.salidaHora);
            				viaje.programacionAutomatica();
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
        
        Pasajero P1 = new Pasajero(TipoPasajero.REGULAR, 33, 19, "José", 'm',new ArrayList <Viaje>(), 8, 9.9); 

        Transportadora transportadora = new Transportadora();
        
        Terminal terminal1 =  new Terminal("Terminal Principal", 1000000, 100, 1, 50, new ArrayList<Transportadora>(), viajes, Tiempo.viajesEnCurso, new ArrayList<Destino>(),8.0, Destino.MEDELLIN,P1);
        //Terminal terminal1 =  new Terminal("Terminal Principal", 1000000, 100, 1, 50, new ArrayList<Transportadora>(),new ArrayList<Destino>(), Destino.MEDELLIN);
        
        Vehiculo vehiculo1 = new Vehiculo("ABC123", "MARCOPOLO A800", 100, 50, tipo1, transportadora);
        Vehiculo vehiculo2 = new Vehiculo("ABC123", "FORD F600", 100, 60, tipo2, transportadora);
        
        

        //////////////////////////////////////// DIAS OBJETOS PRUEBA /////////////////////////////////////// 
        Dia dia1 = Dia.LUN;
        Dia dia2 = Dia.MAR;
        Dia dia3 = Dia.MIER;
        Dia dia4 = Dia.JUE;
        Dia dia5 = Dia.VIE;
        Dia dia6 = Dia.SAB;

        //////////////////////////////////////// DESTINO OBJETOS PRUEBA /////////////////////////////////////// 
        Destino destino1 = Destino.MEDELLIN;
        Destino destino2 = Destino.ANGELOPOLIS;
        Destino destino3 = Destino.LAGUAJIRA;
        Destino destino4 = Destino.BOGOTA;
        Destino destino5 = Destino.CALI;

        //////////////////////////////////////// CONDUCTOR OBJETOS PRUEBA /////////////////////////////////////// 
        Conductor conductor = new Conductor(88, 27, "Lucas", 'm', new ArrayList<Viaje>(), 1, 9000.8,true, vehiculo1, transportadora, new ArrayList<Viaje>());
        
        //////////////////////////////////////// VIAJES OBJETOS PRUEBA ///////////////////////////////////////  
        Viaje viaje1 = new Viaje(terminal1,"1:5", "1/1/2024" , vehiculo1, conductor, destino4, dia6, destino1);
        Viaje viaje2 = new Viaje(terminal1,"6:35", "2/1/2024" ,vehiculo2, conductor, destino2, dia2, destino2);
        Viaje viaje3 = new Viaje(terminal1,"7:17", "2/1/2024" ,vehiculo1, conductor, destino1, dia3, destino5);
        Viaje viaje4 = new Viaje(terminal1,"10:12", "2/1/2024" ,vehiculo2, conductor, destino2, dia4, destino1);
        Viaje viaje5 = new Viaje(terminal1,"19:24", "4/1/2024" ,vehiculo1, conductor, destino1, dia5, destino2);
        //Viaje viaje6 = new Viaje(terminal1,"23:57", "4/1/2024" ,vehiculo2, conductor, destino3, dia6, destino1);
        
        //////////////////////////////////////// PRUEBA DE SALIDAS //////////////////////////////////////////
        
        //System.out.println("Pruebas calculo de Distancia en Plano");
        
        //System.out.println("\nViaje 1:" + viaje1.getSalida().name() + " ---- " + viaje1.getLlegada().name());
        //System.out.println("Duración: " + viaje1.getDuracion() + " Horas" + "\nDistancia: " + viaje1.calcularDistancia(viaje1.getSalida(), viaje1.getLlegada()) + " Km" + "\nVelocidad: " + viaje1.getVehiculo().getVelocidadPromedio() + "Km/h" +"\nTarifa: $" + viaje1.getTarifa() + "\nHora de Salida: " + viaje1.getHora() + " Fecha de Salida: " + viaje1.getFecha() + "   \nHora de LLegada: " + viaje1.getHoraLlegada() + " Fecha de Llegada: " + viaje1.getFechaLlegada());

        //System.out.println("\nViaje 2:" + viaje2.getSalida().name() + " ---- " + viaje2.getLlegada().name()); 
        //System.out.println("Duración: " + viaje2.getDuracion() + " Horas" + "\nDistancia: " + viaje2.calcularDistancia(viaje2.getSalida(), viaje2.getLlegada()) + " Km" + "\nVelocidad: " + viaje2.getVehiculo().getVelocidadPromedio() + "Km/h" + "\nTarifa: $" + viaje2.getTarifa() + "\nHora de Salida: " + viaje2.getHora() + " Fecha de Salida: " + viaje2.getFecha() +"   \nHora de LLegada: " + viaje2.getHoraLlegada()+ " Fecha de Llegada: " + viaje2.getFechaLlegada());

        //System.out.println("\nViaje 3:" + viaje3.getSalida().name() + " ---- " + viaje3.getLlegada().name());
        //System.out.println("Duración: " + viaje3.getDuracion() + " Horas" + "\nDistancia: " + viaje3.calcularDistancia(viaje3.getSalida(), viaje3.getLlegada()) + " Km" + "\nVelocidad: " + viaje3.getVehiculo().getVelocidadPromedio() + " Km/h" + "\nTarifa: $" + viaje3.getTarifa()+ "\nHora de Salida: " + viaje3.getHora() + " Fecha de Salida: " + viaje3.getFecha() +"   \nHora de LLegada: " + viaje3.getHoraLlegada()+ " Fecha de Llegada: " + viaje3.getFechaLlegada());

        //System.out.println("\nViaje 4:" + viaje4.getSalida().name() + " ---- " + viaje4.getLlegada().name());
        //System.out.println("Duración: " + viaje4.getDuracion() + " Horas" + "\nDistancia: " + viaje4.calcularDistancia(viaje4.getSalida(), viaje4.getLlegada()) + " Km" + "\nVelocidad: " + viaje4.getVehiculo().getVelocidadPromedio() + " Km/h" + "\nTarifa: $" + viaje4.getTarifa()+ "\nHora de Salida: " + viaje4.getHora() + " Fecha de Salida: " + viaje4.getFecha() +"   \nHora de LLegada: " + viaje4.getHoraLlegada()+ " Fecha de Llegada: " + viaje4.getFechaLlegada());

        //System.out.println("\nViaje 5:" + viaje5.getSalida().name() + " ---- " + viaje5.getLlegada().name());
        //System.out.println("Duración: " + viaje5.getDuracion() + " Horas" + "\nDistancia: " + viaje5.calcularDistancia(viaje5.getSalida(), viaje5.getLlegada()) + " Km" + "\nVelocidad: " + viaje5.getVehiculo().getVelocidadPromedio() + " Km/h" + "\nTarifa: $" + viaje5.getTarifa()+ "\nHora de Salida: " + viaje5.getHora() + " Fecha de Salida: " + viaje5.getFecha() +"   \nHora de LLegada: " + viaje5.getHoraLlegada()+ " Fecha de Llegada: " + viaje5.getFechaLlegada());

        //System.out.println("\nViaje 6:" + viaje6.getSalida().name() + " ---- " + viaje6.getLlegada().name());
        //System.out.println("Duración: " + viaje6.getDuracion() + " Horas" + "\nDistancia: " + viaje6.calcularDistancia(viaje6.getSalida(), viaje6.getLlegada()) + " Km" + "\nVelocidad: " + viaje6.getVehiculo().getVelocidadPromedio() + " Km/h" + "\nTarifa: $" + viaje6.getTarifa()+ "\nHora de Salida: " + viaje6.getHora() + " Fecha de Salida: " + viaje6.getFecha() +"   \nHora de LLegada: " + viaje6.getHoraLlegada()+ " Fecha de Llegada: " + viaje6.getFechaLlegada());

        
        //System.out.println("     ");
        //System.out.println("     "); 
        //System.out.println("     ");
        
        //System.out.println(Tiempo.salidaFecha);
        //System.out.println(Tiempo.salidaHora);
       // if(viaje1.getHora().equals(Tiempo.salidaHora)) {
       //	System.out.println("OK");
       //}
        
        ////////////////////////////////////////PRUEBA DE ENTRADAS //////////////////////////////////////////  
        //Scanner lectura = new Scanner (System.in);
        //System.out.println("Ingrese su nombre: ");
        //String nombre = lectura.next();
        //System.out.println("Ingrese su edad: ");
        //int edad = lectura.nextInt();
        //System.out.println("Su nombre es: " + nombre + " y su edad es: " + edad);
    	//System.out.println("\n----------------------------------------------------------------------------------------------");
        //System.out.print("Fecha: " + dias + "/" + meses + "/" + año + "     Hora: " + horas + ":" + minutos + "   Hoy es: " + diaNombre);
        //System.out.println("\n----------------------------------------------------------------------------------------------");
        
        //System.out.println(Tiempo.salidaFecha + "   " + Tiempo.salidaHora);
    }
}
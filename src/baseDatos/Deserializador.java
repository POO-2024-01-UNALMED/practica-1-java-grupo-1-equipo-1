package baseDatos;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserializador {
	   
    private static <T> void deserializar (ArrayList <T> listas, String nombre) {
    	
    	File archivo = new File("");
    	FileInputStream fi;
    	ObjectInputStream ob;
    	
    	try {
    		File path = new File (archivo.getAbsolutePath() + "/src/baseDatos/temp/" + nombre + ".txt");
    		fi = new FileInputStream (path);
    		ob = new ObjectInputStream (fi);
    		
    		if (listas != null) {
    			listas.addAll((ArrayList<T>) ob.readObject());
    		}
    		
    		ob.close();
    		fi.close();
    		
    	} catch (FileNotFoundException e) {
    		System.out.println("Archivo no encontrado");
    	} catch (IOException e) {
    		System.out.println("Error inicialización");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}        

    }
    
    public static void deserializarListas() {
		Deserializador.deserializar(Transportadora.getTransportadoras(), "transportadora");     // OBJETOS DE TRANSPORTADORA
		Deserializador.deserializar(Terminal.getTransportadoras(), "transportadorasAsociadas");// OBJETOS DE TRANSPORTADORA ASOCIADOS A LA TERMINAL
		Deserializador.deserializar(Terminal.getListaTerminales(), "terminal");           // OBJETO TERMINAL
		Deserializador.deserializar(Terminal.getHistorial(), "historialViajes");         // VIAJES TERMINADOS - HISTORIAL
		Deserializador.deserializar(Terminal.getViajesEnCurso(), "viajesEnCurso");      // VIAJES SIN TERMINAR - EN CURSO
		Deserializador.deserializar(Terminal.getViajes(), "viajesDisponibles");        // VIAJES SIN SALIR - DISPONIBLES
		Deserializador.deserializar(Terminal.getReservas(), "reservas");              // VIAJES EN RESERVA
		Deserializador.deserializar(Terminal.getFacturas(), "facturas");              // FACTURAS ASOCIADAS
		Deserializador.deserializar(Tiempo.principal, "tiempoObjetos");              // OBJETOS TIEMPO - PERMITE GUARDAR EL PROGRESO DEL TIEMPO
		Deserializador.deserializar(Persona.getSerializarPersonas(), "personas");   // OBJETOS TIPO PERSONA
		Deserializador.deserializar(Factura.getFacturasCreadas(), "facturas");     // OBJETOS TIPO FACTURA
		Deserializador.deserializar(Taller.getListaTalleres(), "talleres");       // OBJETOS TIPO TALLER
		Deserializador.deserializar(Vehiculo.getListaVehiculos(), "vehiculos");  // OBJETOS TIPO VEHICULO
    }
    
    public static void cargarEstado() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\baseDatos\\temp\\estado_tiempo.txt"))) {
            Tiempo.minutos = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.horas = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.dias = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.semana = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.meses = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.año = Integer.parseInt(reader.readLine().split("=")[1]);
            Tiempo.salidaFecha = reader.readLine().split("=")[1];
            Tiempo.salidaHora = reader.readLine().split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package baseDatos;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.Dia;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializador {
    
	private static void serializar(ArrayList< ?extends Object > listas, String nombre) {
		
		File archivo = new File("");
		
		try {
			File path = new File(archivo.getAbsolutePath()+"/src/baseDatos/temp/" + nombre + ".txt");
			
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(listas);
			
			o.close();
			f.close();
		}
		catch(FileNotFoundException e){
			System.out.println("No se encuentra el archivo");
		}
		catch(IOException e){
			System.out.println("Error inicialización");
		}
	}
	
	public static void serializarListas(){
		Serializador.serializar(Transportadora.getTransportadoras(), "transportadora");      // OBJETOS DE TRANSPORTADORA
		Serializador.serializar(Terminal.getTransportadoras(), "transportadorasAsociadas"); // OBJETOS TRANSPORTAORA ASOCIADOS A LA TERMINAL
		Serializador.serializar(Terminal.getListaTerminales(), "terminal");                // OBJETO TERMINAL
		Serializador.serializar(Terminal.getHistorial(), "historialViajes");              // VIAJES TERMINADOS - HISTORIAL
	    Serializador.serializar(Terminal.getViajesEnCurso(), "viajesEnCurso");           // VIAJES SIN TERMINAR - EN CURSO
	    Serializador.serializar(Terminal.getViajes(), "viajesDisponibles");             // VIAJES SIN SALIR - DISPONIBLES
	    Serializador.serializar(Terminal.getReservas(), "reservas");                   // VIAJES EN RESERVA
	    Serializador.serializar(Terminal.getFacturas(), "facturas");               // FACTURAS ASOCIADAS
	    Serializador.serializar(Terminal.getPasajerosSinViajes(), "pasajeros");    // PASAJEROS
	    Serializador.serializar(Tiempo.principal, "tiempoObjetos");               // OBJETOS TIEMPO - PERMITE GUARDAR EL PROGRESO DEL TIEMPO
	    Serializador.serializar(Persona.getSerializarPersonas(), "personas");    // OBJETOS TIPO PERSONA
	    Serializador.serializar(Factura.getFacturasCreadas(), "facturas");      // OBJETOS TIPO FACTURA
	    Serializador.serializar(Taller.getListaTalleres(), "talleres");        // OBJETOS TIPO TALLER
	    Serializador.serializar(Vehiculo.getListaVehiculos(), "vehiculos");   // OBJETOS TIPO VEHICULO
	}
		
	
    // Métodos de Guardar y Cargar Estado Estático - - - - Utlizado para actualizar los atributos de clase de Tiempo, debido a que implementa la Clase Timer que no es Serializable. 
    public static void guardarEstado() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\baseDatos\\temp\\estado_tiempo.txt"))) {
            writer.write("minutos=" + Tiempo.minutos);
            writer.newLine();
            writer.write("horas=" + Tiempo.horas);
            writer.newLine();
            writer.write("dias=" + Tiempo.dias);
            writer.newLine();
            writer.write("semana=" + Tiempo.semana);
            writer.newLine();
            writer.write("meses=" + Tiempo.meses);
            writer.newLine();
            writer.write("año=" + Tiempo.año);
            writer.newLine();
            writer.write("salidaFecha=" + Tiempo.salidaFecha);
            writer.newLine();
            writer.write("salidaHora=" + Tiempo.salidaHora);
        } catch (IOException e) {
        	System.out.println("Error inicialización");
        }
    }	
	
}

        
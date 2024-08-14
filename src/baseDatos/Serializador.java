package baseDatos;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializador {
    public static void main(String[] args) {
    	
    }
	public static void serializar() throws IOException {
        // VIAJES TERMINADOS - HISTORIAL
        try (FileOutputStream fileOutputStream1 = new FileOutputStream("src\\baseDatos\\temp\\historialViajes.txt");
             ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1)) {
            
            objectOutputStream1.writeObject(Terminal.getHistorial());
            System.out.println("Viajes terminados serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // VIAJES SIN TERMINAR - EN CURSO
        try (FileOutputStream fileOutputStream2 = new FileOutputStream("src\\baseDatos\\temp\\viajesEnCurso.txt");
             ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(fileOutputStream2)) {
            
            objectOutputStream2.writeObject(Terminal.getViajesEnCurso());
            System.out.println("Viajes en curso serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // VIAJES SIN SALIR - DISPONIBLES
        try (FileOutputStream fileOutputStream3 = new FileOutputStream("src\\baseDatos\\temp\\viajesDisponibles.txt");
             ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(fileOutputStream3)) {
            
            objectOutputStream3.writeObject(Terminal.getViajes());
            System.out.println("Viajes disponibles serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // OBJETOS TIEMPO - PERMITE GUARDAR EL PROGRESO DEL TIEMPO
        try (FileOutputStream fileOutputStream4 = new FileOutputStream("src\\baseDatos\\temp\\tiempoObjetos.txt");
             ObjectOutputStream objectOutputStream4 = new ObjectOutputStream(fileOutputStream4)) {
            
            objectOutputStream4.writeObject(Tiempo.principal);
            System.out.println("Objeto Tiempo serializado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    // OBJETOS TERMINAL
    
    // OBJETOS VEHICULO
    
    // OBJETOS ...

	}
	
    // Métodos de Guardar y Cargar Estado Estático
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
            e.printStackTrace();
        }
    }

	public static void objetosBase() {
		new Tiempo();
		
	}
}

        
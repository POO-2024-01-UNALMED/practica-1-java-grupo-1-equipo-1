package baseDatos;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserializador {
	
    public static void main(String[] args) {

    }
    public static void deserializar() throws IOException, ClassNotFoundException {     // Método para deserializar todos los objetos
        // VIAJES TERMINADOS - HISTORIAL
        try (FileInputStream fileInputStream1 = new FileInputStream("src\\baseDatos\\temp\\historialViajes.txt");
             ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1)) {
            
            ArrayList<Viaje> historialViajes = (ArrayList<Viaje>) objectInputStream1.readObject();
            Terminal.setHistorial(historialViajes);
            System.out.println("Viajes terminados deserializados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar viajes terminados: " + e.getMessage());
        }

        // VIAJES SIN TERMINAR - EN CURSO
        try (FileInputStream fileInputStream2 = new FileInputStream("src\\baseDatos\\temp\\viajesEnCurso.txt");
             ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2)) {
            
            ArrayList<Viaje> viajesEnCurso = (ArrayList<Viaje>) objectInputStream2.readObject();
            Terminal.setViajesEnCurso(viajesEnCurso);
            System.out.println("Viajes en curso deserializados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar viajes en curso: " + e.getMessage());
        }

        // VIAJES SIN SALIR - DISPONIBLES
        try (FileInputStream fileInputStream3 = new FileInputStream("src\\baseDatos\\temp\\viajesDisponibles.txt");
             ObjectInputStream objectInputStream3 = new ObjectInputStream(fileInputStream3)) {
            
            ArrayList<Viaje> viajesDisponibles = (ArrayList<Viaje>) objectInputStream3.readObject();
            Terminal.setViajes(viajesDisponibles);
            System.out.println("Viajes disponibles deserializados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar viajes disponibles: " + e.getMessage());
        }

        // OBJETO TIEMPO - PERMITE GUARDAR EL PROGRESO DEL TIEMPO
        try (FileInputStream fileInputStream4 = new FileInputStream("src\\baseDatos\\temp\\tiempoObjetos.txt");
             ObjectInputStream objectInputStream4 = new ObjectInputStream(fileInputStream4)) {
            
            Tiempo.principal = (Tiempo) objectInputStream4.readObject();
            System.out.println("Objeto Tiempo deserializado correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar objeto Tiempo: " + e.getMessage());
        }

        // ESPACIO PARA AGREGAR LAS OTRAS DESERIALIZACIONES 
        

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

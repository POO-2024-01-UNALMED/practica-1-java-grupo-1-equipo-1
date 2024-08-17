package uiMain;

import java.util.ArrayList;

import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.Pasajero;
import java.util.Formatter;

public interface Tablas {
	
    /**
     * con este metodo se muestran las transportadoras sin sus respectivas estrellas
     * para para que los estudiantes elijan
     * @param destino
     * @param viajes
     */
	
	public static void transportadorasSinEstrellas(Destino destino, TipoVehiculo vehiculo,ArrayList<Viaje> viajes) {

        int anchoNombre = 25;

        System.out.println("\n" + "_".repeat(anchoNombre));
        System.out.println(String.format("%-" + anchoNombre + "s", "Transportadora"));
        System.out.println("-".repeat(anchoNombre));

        for (Transportadora transportadora : Terminal.transportadorasViajeDisponible(destino, 1, viajes)) {
        	
            boolean transportadoraMostrada = false;
            
            for (Viaje viaje : transportadora.getViajesAsignados()) {
                    if (!transportadoraMostrada) {
                        // Imprimir el nombre de la transportadora en una fila formateada
                        System.out.println(String.format("%-" + anchoNombre + "s", transportadora.getNombre()));
                        transportadoraMostrada = true;
                    }
                    
                    break;
                
            }
            
        }

        System.out.println("_".repeat(anchoNombre));
        
    }
	
    /**
     * con este metodo se muestran las transportadoras con sus respectivas estrellas
     * para posteriormente elegirlas
     * @param destino
     * @param viajes
     */
    
    public static void transportadorasConEstrellas(Destino destino, ArrayList<Viaje> viajes, int cantidad) {
        
        ArrayList<Transportadora> transportadoras = Terminal.transportadorasViajeDisponible(destino, cantidad, viajes);
        
        int anchoNombre = 30; // Ajusta el ancho según sea necesario
        int anchoEstrellas = 10; // Ajusta el ancho según sea necesario
        
        System.out.println("\n" + "-".repeat(anchoNombre + anchoEstrellas + 7));
        System.out.printf("| %-"+anchoNombre+"s | %-"+anchoEstrellas+"s |\n", "Nombre", "Estrellas");
        System.out.println("-".repeat(anchoNombre + anchoEstrellas + 7));
        
        int i = 1;
        
        for (Transportadora transportadora : transportadoras) {
        	
        	System.out.printf("| %-4d | %-"+anchoNombre+"s | %-"+anchoEstrellas+"d |\n", i, transportadora.getNombre(), transportadora.getEstrellas());
            i++;  
        }
        
        System.out.println("-".repeat(anchoNombre + anchoEstrellas + 7));
        
    }
    
    public static void tablaViajesTransportadora(Transportadora transportadora, ArrayList<Viaje> viajes) {
        // Definimos los anchos de las columnas
        int anchoId = 10;
        int anchoFecha = 12;
        int anchoHora = 8;
        int anchoTarifa = 10;
        int anchoPlaca = 15;

        // Nombre de la transportadora
        String nombreTransportadora = transportadora.getNombre(); // Ajusta el método si es necesario

        // Encabezado de la tabla con columnas separadas por |
        String encabezado = String.format("| %-10s | %-12s | %-8s | %-10s | %-15s |", 
            "ID viaje", "Fecha", "Hora", "Tarifa", "Placa Vehículo");
        
        // Línea divisoria hecha solo de guiones
        int anchoLineaDivisoria = anchoId + anchoFecha + anchoHora + anchoTarifa + anchoPlaca + 19;
        String lineaDivisoria = "-".repeat(anchoLineaDivisoria);
        
        // Imprimir el nombre de la transportadora
        System.out.println("Transportadora: " + nombreTransportadora);
        System.out.println(); // Línea en blanco para separar el nombre de la tabla
        
        // Imprimir encabezado de la tabla
        System.out.println(encabezado);

        // Imprimir línea divisoria
        System.out.println(lineaDivisoria);

        // Formateo de cada fila incluyendo el ID del viaje
        for (Viaje viaje : viajes) {
        	
            if (!viaje.getEstado() && viaje.verificarAsientos() >= 1) {
                String fila = String.format("| %-10s | %-12s | %-8s | %-10.2f | %-15s |", 
                    viaje.getId(), 
                    viaje.getFecha(),
                    viaje.getHora(),
                    viaje.getTarifa(),
                    viaje.getVehiculo().getPlaca());
                System.out.println(fila);
            }
        }
    }
    
    public static void tablaInformacionViaje(Viaje viaje) {
    	
        System.out.println("Detalles del Viaje:");
        System.out.println("-------------------");
        
        System.out.println("ID del Viaje:         " + viaje.getId());
        System.out.println("-------------------");
        
        System.out.println("Llegada:             " + viaje.getLlegada());
        System.out.println("-------------------");
        
        System.out.println("Hora:                " + viaje.getHora());
        System.out.println("-------------------");
        
        System.out.println("Tarifa:              $" + String.format("%.2f", viaje.getTarifa()));
        System.out.println("-------------------");
        
        System.out.println("Placa del Vehículo:  " + viaje.getVehiculo().getPlaca());
        System.out.println("-------------------");
        
        System.out.println("Fecha:               " + viaje.getFecha());
        System.out.println("-------------------");
        
        System.out.println("Transportadora:      " + viaje.getTransportadora());
        System.out.println("-------------------");
    }
    	
    
}

package uiMain;

import java.util.ArrayList;

import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.*;
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
    
    public static void tablaDestinos(ArrayList<Destino> destinos) {
        // Determinar el máximo ancho necesario para los nombres de los destinos
        int maxNombre = 0;
        for (Destino destino : destinos) {
            int nombre = destino.name().length();
            maxNombre = Math.max(maxNombre, nombre);
        }
        
        // Determinar el máximo ancho necesario para las distancias
        int maxDistancia = 0;
        for (Destino destino : destinos) {
            int distancia = (int) Viaje.calcularDistancia(Destino.MEDELLIN, destino);
            maxDistancia = Math.max(maxDistancia, String.valueOf(distancia).length());
        }

        // Ajustar el ancho de la tabla con base en el nombre más largo y la distancia más larga
        int tablaAncho = maxNombre + maxDistancia + 10 + 13; // 10 para el espacio de la opción, 13 para bordes y separadores

        // Imprimir el título centrado
        String tituloCentrado = String.format("%" + ((tablaAncho - 18) / 2) + "s%s%" + ((tablaAncho - 18) / 2) + "s",  "", "INFORMACIÓN", "");
        
        System.out.println("-".repeat(tablaAncho));
        System.out.println(tituloCentrado);
        System.out.println("-".repeat(tablaAncho));
        
        // Imprimir la cabecera de la tabla
        System.out.printf("| %-" + 10 + "s | %-" + maxNombre + "s | %-" + maxDistancia + "s |\n", "OPCION", "DESTINO", "#Km");
        System.out.println("-".repeat(tablaAncho));
        
        // Imprimir los destinos con su distancia
        int i = 1;
        for (Destino destino : destinos) {
            int distancia = (int) Viaje.calcularDistancia(Destino.MEDELLIN, destino);
            System.out.printf("| %-" + 10 + "d | %-" + maxNombre + "s | %" + maxDistancia + "d |\n", i, destino.name(), distancia);
            i++;
        }
        
        System.out.println("-".repeat(tablaAncho));
    }

    public static void tablaTransportadorasporDestino(ArrayList<Transportadora> transportadorasPorDestino) {
    	
        // Verifica si la lista es null o está vacía
        if (transportadorasPorDestino == null || transportadorasPorDestino.isEmpty()) {
            System.out.println("No hay transportadoras disponibles.");
            return;
        }

        // Determina el ancho máximo para la columna de nombres de transportadora
        int maxNombreTransportadora = 0;
        for (Transportadora transportadora : transportadorasPorDestino) {
            int nombre = transportadora.getNombre().length();
            maxNombreTransportadora = Math.max(maxNombreTransportadora, nombre);
        }

        // Asegúrate de que el ancho mínimo sea 10 para la columna "OPCION"
        int anchoOpcion = 10;
        // Define el ancho total de la tabla: espacio para bordes y separadores
        int tablaAncho1 = Math.max(anchoOpcion + maxNombreTransportadora + 7, 30); // Define un ancho mínimo para evitar valores muy pequeños

        // Imprime la cabecera de la tabla
        System.out.println("-".repeat(tablaAncho1));
        System.out.printf("| %-"+anchoOpcion+"s | %-" + maxNombreTransportadora + "s |\n", "OPCION", "TRANSPORTADORA");
        System.out.println("-".repeat(tablaAncho1));

        // Imprime las transportadoras con numeración
        int j = 1;
        for (Transportadora transportadora : transportadorasPorDestino) {
            System.out.printf("| %-" + anchoOpcion + "d | %-" + maxNombreTransportadora + "s |\n", j, transportadora.getNombre());
            j++;
        }

        // Imprime el borde inferior de la tabla
        System.out.println("-".repeat(tablaAncho1));
    }


    
    public static void tablaFechasDisponibles(ArrayList <String> fechas) {    
        int anchoOpcion = 10; // Ancho reservado para la columna de opciones
        int anchoFecha = 15;  // Ancho reservado para la columna de fechas
        int anchoTotal = anchoOpcion + anchoFecha + 7; // Ancho total de la tabla 

        // Imprimir cabecera de la tabla
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("| %-"+anchoOpcion+"s | %-"+anchoFecha+"s |\n", "OPCION", "FECHA");
        System.out.println("-".repeat(anchoTotal));
        
        // Imprimir fechas con numeración
        int i = 1;
        for (String fecha : fechas) {
            System.out.printf("| %-"+anchoOpcion+"d | %-"+anchoFecha+"s |\n", i, fecha);
            i++;
        }

        // Imprimir línea final de la tabla
        System.out.println("-".repeat(anchoTotal));
    }
    
    public static void tablaConductoresDisponibles(ArrayList<Conductor> conductores) {
        // Encuentra el ancho máximo para el nombre y la experiencia
        int maxNombre = 0;
        int maxExperiencia = 0;
        
        for (Conductor conductor : conductores) {
            int nombreLength = conductor.getNombre().length();
            int experienciaLength = String.valueOf(conductor.getExperiencia()).length();
            maxNombre = Math.max(maxNombre, nombreLength);
            maxExperiencia = Math.max(maxExperiencia, experienciaLength);
        }
        
        int tablaAncho = 10 + maxNombre + maxExperiencia + 7; // Ancho total de la tabla
        String tituloCentrado = String.format("%" + ((tablaAncho - 18) / 2) + "s%s%" + ((tablaAncho - 18) / 2) + "s", "", "  CONDUCTORES DISPONIBLES", "");

        // Imprimir encabezado de la tabla
        System.out.println("-".repeat(tablaAncho));
        System.out.println(tituloCentrado);
        System.out.println("-".repeat(tablaAncho));
        System.out.printf("| %-" + 10 + "s | %-" + maxNombre + "s | %-" + maxExperiencia + "s |\n", "OPCION", "NOMBRE", "EXPERIENCIA");
        System.out.println("-".repeat(tablaAncho));

        // Imprimir los datos de los conductores
        int i = 1;
        for (Conductor conductor : conductores) {
            System.out.printf("| %-" + 10 + "d | %-" + maxNombre + "s | %-" + maxExperiencia + "d |\n", i, conductor.getNombre(), conductor.getExperiencia());
            i++;
        }

        // Imprimir el borde inferior de la tabla
        System.out.println("-".repeat(tablaAncho));
    }
    	
    
}

package uiMain;

import java.util.ArrayList;

import gestorAplicacion.administrativo.Factura;
import gestorAplicacion.administrativo.Terminal;
import gestorAplicacion.administrativo.Transportadora;
import gestorAplicacion.administrativo.Viaje;
import gestorAplicacion.constantes.Destino;
import gestorAplicacion.constantes.TipoPasajero;
import gestorAplicacion.constantes.TipoVehiculo;
import gestorAplicacion.usuarios.*;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Set;

public interface Tablas {
	
    /**
     * Con este método se muestran las transportadoras sin sus respectivas estrellas
     * para que los estudiantes elijan.
     * @param destino
     * @param viajes
     */
	public static void transportadorasSinEstrellas(ArrayList<Viaje> viajes) {
	    int anchoNombre = 25;

	    // Utilizamos un Set para evitar duplicados
	    Set<Transportadora> transportadorasMostradas = new HashSet<>();

	    // Alternativa para String.repeat si estás usando una versión anterior de Java
	    String separador = new String(new char[anchoNombre + 5]).replace("\0", "_");

	    System.out.println("\n" + separador);
	    System.out.println(String.format("%-5s%-" + anchoNombre + "s", "Índice", "Transportadora"));
	    System.out.println(new String(new char[5]).replace("\0", "-") + new String(new char[anchoNombre]).replace("\0", "-"));

	    int index = 0; // Índice para las transportadoras

	    // Iterar sobre cada viaje
	    for (Viaje viaje : viajes) {
	        // Obtener la transportadora del viaje
	        Transportadora transportadora = viaje.getVehiculo().getTransportadora();

	        // Verificar si la transportadora no es nula
	        if (transportadora != null) {
	            // Si la transportadora no ha sido mostrada antes
	            if (!transportadorasMostradas.contains(transportadora)) {
	                System.out.printf("%-5d%-" + anchoNombre + "s\n", index, transportadora.getNombre());
	                transportadorasMostradas.add(transportadora);
	                index++; // Incrementar el índice
	            }
	        }
	    }

	    System.out.println(separador);
	}
	
    /**
     * con este metodo se muestran las transportadoras con sus respectivas estrellas
     * para posteriormente elegirlas
     * @param destino
     * @param viajes
     */
    
	public static void transportadorasConEstrellas(ArrayList<Viaje> viajesDisponibles) {
	    int anchoNombre = 30; // Ajusta el ancho según sea necesario
	    int anchoEstrellas = 10; // Ajusta el ancho según sea necesario

	    // Utilizamos un Set para evitar duplicados
	    Set<Transportadora> transportadorasMostradas = new HashSet<>();
	    
	    // Alternativa para String.repeat si estás usando una versión anterior de Java
	    String separador = new String(new char[anchoNombre + anchoEstrellas + 7]).replace("\0", "-");

	    System.out.println("\n" + separador);
	    System.out.printf("| %-5s | %-"+anchoNombre+"s | %-"+anchoEstrellas+"s |\n", "Índice", "Nombre", "Estrellas");
	    System.out.println(separador);

	    int i = 0; // Índice para las transportadoras

	    // Iterar sobre cada viaje
	    for (Viaje viaje : viajesDisponibles) {
	        // Obtener la transportadora del viaje
	        Transportadora transportadora = viaje.getVehiculo().getTransportadora();
	        
	        // Verificar si la transportadora no es nula
	        if (transportadora != null) {
	            // Si la transportadora no ha sido mostrada antes
	            if (!transportadorasMostradas.contains(transportadora)) {
	                System.out.printf("| %-4d | %-"+anchoNombre+"s | %-"+anchoEstrellas+".2f |\n", i, transportadora.getNombre(), transportadora.getEstrellas());
	                transportadorasMostradas.add(transportadora);
	                i++; // Incrementar el índice
	            }
	        }
	    }

	    System.out.println(separador);
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
    
    public static void tablaFactura(Viaje viaje, Pasajero pasajero, int cantidad) {
        // Calcular el total
        double total = pasajero.getDinero();
        
        // Definir el ancho de las columnas
        final int ANCHO = 50;

        // Imprimir encabezado
        System.out.println("+" + "-".repeat(ANCHO + 2) + "+");
        System.out.println("|" + " ".repeat(ANCHO + 2) + "|");
        System.out.printf("| %-"+ ANCHO +"s |\n", "FACTURA");
        System.out.println("|" + " ".repeat(ANCHO + 2) + "|");
        System.out.println("+" + "-".repeat(ANCHO + 2) + "+");

        // Imprimir detalles del viaje
        System.out.printf("| %-"+ ANCHO +"s |\n", "ID Viaje: " + viaje.getId());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Destino: " + viaje.getLlegada());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Asientos " + cantidad);
        System.out.printf("| %-"+ ANCHO +"s |\n", "Tipo vehiculo " + viaje.getVehiculo().getTipo().name());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Hora: " + viaje.getHora());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Tarifa Total: $" + String.format("%.2f", total));
        System.out.printf("| %-"+ ANCHO +"s |\n", "Fecha: " + viaje.getFecha());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Transportadora: " + viaje.getVehiculo().getTransportadora().getNombre());

        // Separador entre viaje y pasajero
        System.out.println("+" + "-".repeat(ANCHO + 2) + "+");

        // Imprimir detalles del pasajero
        System.out.printf("| %-"+ ANCHO +"s |\n", "Nombre: " + pasajero.getNombre());
        System.out.printf("| %-"+ ANCHO +"s |\n", "ID Pasajero: " + pasajero.getId());
        System.out.printf("| %-"+ ANCHO +"s |\n", "Tipo: " + pasajero.getTipo());

        // Imprimir pie de factura
        System.out.println("+" + "-".repeat(ANCHO + 2) + "+");
    }
    
    public static void tablaInformacionViaje(Viaje viaje,TipoPasajero tipo) {
    	
        System.out.println("Detalles del Viaje:");
        System.out.println("-------------------");
        
        System.out.println("ID del Viaje:         " + viaje.getId());
        System.out.println("-------------------");
        
        System.out.println("Llegada:             " + viaje.getLlegada());
        System.out.println("-------------------");
        
        System.out.println("Hora:                " + viaje.getHora());
        System.out.println("-------------------");
        
        System.out.println("Tarifa:              $" + String.format("%.2f", viaje.getTarifa()-viaje.getTarifa()*tipo.getDescuento()));
        System.out.println("-------------------");
        
        System.out.println("Placa del Vehículo:  " + viaje.getVehiculo().getPlaca());
        System.out.println("-------------------");
        
        System.out.println("Fecha:               " + viaje.getFecha());
        System.out.println("-------------------");
        
        System.out.println("Transportadora:      " + viaje.getVehiculo().getTransportadora().getNombre());
        System.out.println("-------------------");
    }

	public static void imprimirDetallesFactura(Factura factura) {
		
		System.out.println("---------------------------------------------------");
        System.out.println("                  DETALLES DE LA FACTURA           ");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-30s %s%n", "Número de Factura:", factura.getNumeroFactura());
        System.out.printf("%-30s $%.2f%n", "Total:", factura.getTotal());
        System.out.println("---------------------------------------------------");
        System.out.printf("%-30s %s%n", "Pasajero:", factura.getPasajero().getNombre());
        System.out.printf("%-30s %s%n", "Terminal:", factura.getTerminal().getNombre());
        System.out.printf("%-30s %s%n", "Conductor:", factura.getConductor().getNombre());
        System.out.printf("%-30s %s%n", "Viaje:", factura.getViaje().getLlegada());
        System.out.printf("%-30s %s%n", "Vehículo:", factura.getVehiculo().getModelo());
        System.out.printf("%-30s %s%n", "Transportadora:", factura.getTransportadora().getNombre());
        System.out.println("---------------------------------------------------");
	
		
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
        for (int i = 1; i < destinos.size(); i++) {
            Destino destino = destinos.get(i);
            int distancia = (int) Viaje.calcularDistancia(Destino.MEDELLIN, destino);
            System.out.printf("| %-" + 10 + "d | %-" + maxNombre + "s | %" + maxDistancia + "d |\n", i, destino.name(), distancia);
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
            int experienciaLength = String.valueOf(conductor.getExperiencia()).length() + 4; // +4 para " años"
            maxNombre = Math.max(maxNombre, nombreLength);
            maxExperiencia = Math.max(maxExperiencia, experienciaLength);
        }
        
        int tablaAncho =  maxNombre + maxExperiencia + 25; // Ancho total de la tabla
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
            String experienciaStr = conductor.getExperiencia() + "     Años";
            System.out.printf("| %-" + 10 + "d | %-" + maxNombre + "s | %-" + maxExperiencia + "s |\n", i, conductor.getNombre(), experienciaStr);
            i++;
        }

        // Imprimir el borde inferior de la tabla
        System.out.println("-".repeat(tablaAncho));
    }
    
    public static void tablaHorasDisponibles(ArrayList <String> fechas) {    
        int anchoOpcion = 10; // Ancho reservado para la columna de opciones
        int anchoFecha = 15;  // Ancho reservado para la columna de fechas
        int anchoTotal = anchoOpcion + anchoFecha + 7; // Ancho total de la tabla 

        // Imprimir cabecera de la tabla
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("| %-"+anchoOpcion+"s | %-"+anchoFecha+"s |\n", "OPCION", "HORA");
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
    
    public static void tablaViajesDisponiblesId(ArrayList<Viaje> viajes) {
        int anchoId = 5;
        int anchoLlegada = 15;
        int anchoFecha = 15;
        int anchoHora = 10;
        int anchoDia = 10;
        int anchoTransportadora = 25;
        int anchoAsientos = 10;
        int anchoTotal = anchoId + anchoLlegada + anchoFecha + anchoHora + anchoDia + anchoTransportadora + anchoAsientos + 22; 

        System.out.println("-".repeat(anchoTotal));
        System.out.printf("| %-"+anchoId+"s | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"s |\n",
                "N°", "LLEGADA", "FECHA", "HORA", "DIA", "TRANSPORTADORA", "ASIENTOS");
        System.out.println("-".repeat(anchoTotal));
        
        int i = 0;
        for (Viaje viaje : viajes) {
            System.out.printf("| %-"+anchoId+"d | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"d |\n",
                    i, viaje.getLlegada(), viaje.getFecha(), viaje.getHora(), viaje.getDia(), viaje.getVehiculo().getTransportadora().getNombre(), viaje.getAsientosDisponibles());
            i++;
        }

        System.out.println("-".repeat(anchoTotal));
    }
    
    public static void viajeIndividual(Viaje viaje) {
        int anchoId = 5;
        int anchoLlegada = 15;
        int anchoFecha = 15;
        int anchoHora = 10;
        int anchoDia = 10;
        int anchoTransportadora = 20;
        int anchoAsientos = 10;
        int anchoTotal = anchoId + anchoLlegada + anchoFecha + anchoHora + anchoDia + anchoTransportadora + anchoAsientos + 22; // Total width of the table

        // Print table header
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("| %-"+anchoId+"s | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"s |\n",
                "ID", "LLEGADA", "FECHA", "HORA", "DIA", "TRANSPORTADORA", "ASIENTOS");
        System.out.println("-".repeat(anchoTotal));

        //String transportadoraNombre = viaje.getVehiculo().getTransportadora().getNombre() != null ? viaje.getVehiculo().getTransportadora().getNombre(): "N/A";
        System.out.printf("| %-"+anchoId+"d | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"d |\n",
        viaje.getId(), viaje.getLlegada(), viaje.getFecha(), viaje.getHora(), viaje.getDia(), viaje.getVehiculo().getTransportadora().getNombre(), viaje.getAsientosDisponibles());


        // Print table footer
        System.out.println("-".repeat(anchoTotal));
    }
    
    // Tabla Viajes disponibles
    
    public static void tablaViajesDisponibles(ArrayList<Viaje> viajes) {
        int anchoId = 5;
        int anchoLlegada = 15;
        int anchoFecha = 15;
        int anchoHora = 10;
        int anchoDia = 10;
        int anchoTransportadora = 25;
        int anchoAsientos = 10;
        int anchoTotal = anchoId + anchoLlegada + anchoFecha + anchoHora + anchoDia + anchoTransportadora + anchoAsientos + 22; // Total width of the table

        // Print table header
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("| %-"+anchoId+"s | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"s |\n",
                "ID", "LLEGADA", "FECHA", "HORA", "DIA", "TRANSPORTADORA", "ASIENTOS");
        System.out.println("-".repeat(anchoTotal));
        
        // Print each viaje
        for (Viaje viaje : viajes) {
            System.out.printf("| %-"+anchoId+"d | %-"+anchoLlegada+"s | %-"+anchoFecha+"s | %-"+anchoHora+"s | %-"+anchoDia+"s | %-"+anchoTransportadora+"s | %-"+anchoAsientos+"d |\n",
                    viaje.getId(), viaje.getLlegada(), viaje.getFecha(), viaje.getHora(), viaje.getDia(), viaje.getVehiculo().getTransportadora().getNombre(), viaje.getAsientosDisponibles());
        }

        // Print table footer
        System.out.println("-".repeat(anchoTotal));
    }
    
}

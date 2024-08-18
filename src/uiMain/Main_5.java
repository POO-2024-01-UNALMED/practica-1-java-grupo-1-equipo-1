package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.administrativo.*;
import gestorAplicacion.constantes.*;
import gestorAplicacion.tiempo.Tiempo;
import gestorAplicacion.usuarios.Conductor;

public class Main_5 {
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean regresar = false;

        while (!regresar) {
            System.out.println("Ejecutando Funcionalidad 5");
            // Lógica específica para Funcionalidad 5

            System.out.println("Seleccione una acción:\n");
            System.out.println("1. Progamación de Viajes");
            System.out.println("2. Regresar al menú Principal");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	ejemplo();
                	programacionViajes();
                    break;
                case 2:
                    regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    // Desarrollar los metodos a partir de aqui
    
    public static void ejemplo() {
    	System.out.println(Tiempo.salidaFecha + "  " + Tiempo.salidaHora); 
    }
    
    private static void programacionViajes() {
        Scanner scanner = new Scanner(System.in); // Se quita cuando sea un solo Main
        
        boolean programar = true;
        
        System.out.println("Bienvenido al Sistema de programación de Viajes.");
        
        while (programar) {
            // SubMenu
            System.out.println("Tipo de programación:\n");
            System.out.println("1. Programación de Viajes");
            System.out.println("2. Administración de Reservas");
            System.out.println("3. Regresar al Menú Principal");
            
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    programarViaje(scanner);
                    break;
                case 2:
                    administrarReservas(scanner);
                    break;
                case 3:
                    System.out.println("Regresando al menú principal");
                    programar = false; // Termina el bucle para regresar al menú principal
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, marque números enteros del 1 al 3.");
            }
        }
        
        scanner.close();
    }
    
    private static void programarViaje(Scanner scanner) {
        boolean viaje = true;
        
        while (viaje) {
            System.out.println("Has decidido programar un nuevo viaje.");
            System.out.println("Elige un destino:\n");

            // Selecionar Destinos
        	ArrayList<Destino> destinos = new ArrayList<>();
            for (Destino destino : Destino.values()) {
                destinos.add(destino);
            }
            
            Tablas.tablaDestinos(destinos); // Llamado al formato de las tablas
            
            System.out.println("Elige el destino por su número:");
            int destinoSeleccionado = scanner.nextInt();
            
            if (destinoSeleccionado < 1 || destinoSeleccionado > destinos.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + destinos.size());
                continue;
            }
            
            Destino destinoElegido = destinos.get(destinoSeleccionado - 1);  // Destino como parametro para el constructor ma adelante
            System.out.println("Has elegido el destino: " + destinoElegido.name());
            System.out.println(Terminal.getTransportadoras());
            
            
            // Seleccionar Transportadora
            ArrayList<Transportadora> transportadorasPorDestino = Terminal.transportadorasViajeDisponible(destinoElegido);

            System.out.println("Estas son las transportadoras asociadas al destino, " + destinoElegido.name() + ":");
            Tablas.tablaTransportadorasporDestino(transportadorasPorDestino);
            System.out.println("Elige una transportadora por número:");

            int transportadoraSeleccionada = scanner.nextInt();

            if (transportadoraSeleccionada < 1 || transportadoraSeleccionada > transportadorasPorDestino.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + transportadorasPorDestino.size());
            }

            Transportadora transportadoraElegida = transportadorasPorDestino.get(transportadoraSeleccionada - 1);
            System.out.println("Has elegido la transportadora: " + transportadoraElegida.getNombre());
            
            // Detalles del viaje
            
            // FECHA 
            
            System.out.println("HORA ACTUAL");
            System.out.println(Tiempo.mostrarTiempo());
            System.out.println("Fechas Disponibles");
            
            ArrayList<String> fechasDisponibles = Terminal.fechasDisponibles();
            
            Tablas.tablaFechasDisponibles(fechasDisponibles);
            
            System.out.println("Elige una fecha por número:");
            int fechaSeleccionada = scanner.nextInt();
            
            if (fechaSeleccionada < 1 || fechaSeleccionada > fechasDisponibles.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + fechasDisponibles.size());
            }
            
            // Seleción del conductor
            
            String fecha = fechasDisponibles.get(fechaSeleccionada - 1); // -1 porque las listas son basadas en cero
            System.out.println("Has seleccionado la fecha: " + fecha);
            
            
            // HORA
            System.out.println("HORA ACTUAL");
            System.out.println(Tiempo.mostrarTiempo());
            System.out.println("Horas Disponibles");
            
            ArrayList<String> horasDisponibles = Terminal.fechasDisponibles();
            
            Tablas.tablaFechasDisponibles(horasDisponibles);
            
            System.out.println("Elige una fecha por número:");
            int horaSeleccionada = scanner.nextInt();
            
            if (horaSeleccionada < 1 || horaSeleccionada > horasDisponibles.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + horasDisponibles.size());
            } else {
                String hora = horasDisponibles.get(horaSeleccionada - 1); // -1 porque las listas son basadas en cero
                System.out.println("Has seleccionado la fecha: " + fecha);
            }
            
         // Selección del tipo de Vehiculo
            System.out.println("Selecciona el tipo de Vehiculo:\n");
            int indice = 1;
            ArrayList<TipoVehiculo> tiposDisponibles = transportadoraElegida.tiposVehiculosDisponible(); // Obtén la lista de tipos disponibles

            // Mostrar las opciones de tipos de vehículos disponibles
            for (TipoVehiculo tipo : tiposDisponibles) {
                System.out.println(indice + " " + tipo.name());
                indice++;
            }

            // Leer la selección del usuario
            int tipoVehiculoSeleccionado = scanner.nextInt();

            // Validar la opción seleccionada
            if (tipoVehiculoSeleccionado < 1 || tipoVehiculoSeleccionado > tiposDisponibles.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + tiposDisponibles.size());
            }
            
            // Obtener el tipo de vehículo seleccionado
            TipoVehiculo tipoSeleccionado = tiposDisponibles.get(tipoVehiculoSeleccionado - 1);
            System.out.println("Has seleccionado el tipo de vehículo: " + tipoSeleccionado.name());
            
            // Seleccion del condutor
            
            System.out.println("Selecciona el conductor");
            
            ArrayList<Conductor> conductoresDisponibles = transportadoraElegida.conductoresDisponibles(fecha, tipoSeleccionado);
            
            Tablas.tablaConductoresDisponibles(conductoresDisponibles);
            
            int conductorSeleccionado = scanner.nextInt();

            if (conductorSeleccionado < 1 || conductorSeleccionado > conductoresDisponibles.size()) {
                System.out.println("Opción no válida. Por favor, elige un número del 1 al " + conductoresDisponibles.size());
            }
            
            Conductor conductorElegido = conductoresDisponibles.get(conductorSeleccionado - 1);
            System.out.println("Has seleccionado el conductor: " + conductorElegido.getNombre() + " con " + conductorElegido.getExperiencia() + " años de experiencia.");
            
            System.out.println("Programación terminada. (Proceso de validacion)");
            
            //Terminal.programarViaje(destinoElegido, conductorElegido, tipoSeleccionado, fecha, fecha, destinoElegido).detallesViaje();
            
            System.out.println("¿Deseas programar otro viaje?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int respuesta = scanner.nextInt();
            
            if (respuesta == 2) {
                viaje = false; // Termina el bucle de programación de viajes
            }
        }
    }
    
    private static void administrarReservas(Scanner scanner) {
        System.out.println("Has decidido administrar las reservas.");
        // Aquí puedes agregar la lógica para administrar reservas
        
        // Ejemplo de lógica básica
        System.out.println("1. Ver reservas actuales");
        System.out.println("2. Ver reservas en curso");
        System.out.println("3. Regresar al menú de programación de viajes");
        
        int opcionReserva = scanner.nextInt();
        
        switch (opcionReserva) {
            case 1:
                System.out.println("Mostrando reservas actuales...");
                // Lógica para mostrar reservas actuales
                break;
            case 2:
                System.out.println("Mostrando reservas en curso...");
                // Lógica para mostrar reservas en curso
                break;
            case 3:
                System.out.println("Regresando al menú de programación de viajes");
                break;
            default:
                System.out.println("Opción no válida. Por favor, marque números enteros del 1 al 3.");
        }
    }
}

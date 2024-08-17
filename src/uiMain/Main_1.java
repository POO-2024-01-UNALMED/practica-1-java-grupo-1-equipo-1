package uiMain;

import java.util.ArrayList;
import java.util.Scanner;


import gestorAplicacion.constantes.*;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.Pasajero;
import gestorAplicacion.usuarios.Persona;

public class Main_1 {
    
    public static void main() {
    	
        String nombre = "";
        TipoPasajero tipo = null;
        String destino;
        Destino destinoDeseado = null;
        Scanner scanner = new Scanner(System.in);
        int cantidad = 0;
        TipoVehiculo vehiculo = null;
        int modalidad = -1;
        boolean regresar = false;
        boolean regresar2 = false;
        int contador = 0;
        Transportadora transportadoraSeleccionada = null;
        Viaje viajeSeleccionado = null;
        int tipoPasajero = 0;
        int indiceV = 0;
        int indiceT = 0;
        double id;
        
        ArrayList<Viaje> viajesDisponibles = Pasajero.viajesDisponibles(); 

        while (!regresar) {
        	
            System.out.println("\nEjecutando Funcionalidad 1...\n");

            if (contador == 0) {
                System.out.println("1. Vender viaje");
            } else {
                System.out.println("1. Vender otro viaje");
            }
            
            contador++;

            System.out.println("2. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            regresar2 = false;
            while (!regresar2) {
            	
                switch (opcion) {
                
                    case 1:
                    	
                        System.out.println("\nIngrese el nombre del destino\n");
                        destino = scanner.nextLine().toUpperCase();
                        
                        if (Pasajero.esDestinoValido(destino)) {
                            destinoDeseado = Destino.valueOf(destino);
                            System.out.println("Destino seleccionado: " + destinoDeseado + "\n");
                        } else {
                            System.out.println("Destino no válido. Por favor, ingresa uno de los valores permitidos.");
                            break;
                        }
                        
                        System.out.println("\nElija el tipo de pasajero\n" +
                                "1. Estudiante\n" +
                                "2. Discapacitado\n" +
                                "3. Regular\n" +
                                "4. VIP\n");
                           
                        tipoPasajero = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (tipoPasajero != 1) {
                        	
                            System.out.println("\nCuantos asientos desea vender");
                            
                            if (tipoPasajero == 2) {
                                cantidad = scanner.nextInt();
                                scanner.nextLine();
                                tipo = TipoPasajero.DISCAPACITADO;
                            } else {
                                tipo = TipoPasajero.REGULAR;
                                cantidad = scanner.nextInt();
                                scanner.nextLine();
                            }
                            
                            System.out.println("\nElegir viaje por...\n" +
                                    "1. mayor velocidad\n" +
                                    "2. salida con mayor antelación\n" +
                                    "3. mas economico\n" +
                                    "4. transportadora\n");

                            if (scanner.hasNextInt()) {
                                modalidad = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("Modalidad no válida. Debe ingresar un número.");
                                scanner.next(); //Limpiar la entrada no válida
                                break;
                            }
                            
                        } else {
                            cantidad = 1;
                        }

                        switch (tipoPasajero) {
                        
                            case 1:
                            	
                                tipo = TipoPasajero.ESTUDIANTE;
                                System.out.println("\nElija el tipo de vehiculo en el que quieve viajar\n" +
                                        "1. Bus\n" +
                                        "2. Escalera\n" +
                                        "3. Vans\n");
                                
                                indiceV = scanner.nextInt();
                                scanner.nextLine();
                                
                                if (indiceV == 1) {
                                    vehiculo = TipoVehiculo.BUS;
                                } else if (indiceV == 2) {
                                    vehiculo = TipoVehiculo.ESCALERA;
                                } else {
                                    vehiculo = TipoVehiculo.VANS;
                                }
                                
                                viajesDisponibles = Pasajero.viajesParaEstudiantes(destinoDeseado, vehiculo);

                                if (viajesDisponibles.isEmpty()) {
                                    System.out.println("No hay viajes disponibles");
                                } else {
                                    Tablas.transportadorasSinEstrellas(destinoDeseado, vehiculo, viajesDisponibles);
                                    System.out.println("\nElija una transportadora por su indice\n");
                                    indiceT = scanner.nextInt();
                                    scanner.nextLine();
                                    transportadoraSeleccionada = Terminal.transportadorasViajeDisponible(destinoDeseado, 1, viajesDisponibles).get(indiceT);
                                    for (Viaje viaje : viajesDisponibles) {
                                        if (viaje.getLlegada() == destinoDeseado && viaje.getTransportadora() == transportadoraSeleccionada) {
                                            viajeSeleccionado = viaje;
                                            break;
                                        }
                                    }
                                }
                                break;
                            
                            case 2,3:
                                viajesDisponibles = Pasajero.viajesParaRegularesYDiscapacitados(cantidad, destinoDeseado, vehiculo);
                                
                                switch (modalidad) {
                                
                                    case 1:
                                    	
                                        if (viajesDisponibles.isEmpty()) {
                                            System.out.println("No hay viajes disponibles");
                                        } else {
                                            viajeSeleccionado = Pasajero.masRapido(destinoDeseado, cantidad, viajesDisponibles);
                                        }
                                        break;
                                        
                                    case 2:
                                        // Implementar lógica para seleccionar viaje con salida más pronta
                                        break;
                                        
                                    case 3:
                                    	
                                        if (viajesDisponibles.isEmpty()) {
                                            System.out.println("No hay viajes disponibles");
                                        } else {
                                            viajeSeleccionado = Pasajero.masEconomico(destinoDeseado, cantidad, viajesDisponibles);
                                        }
                                        break;
                                        
                                    case 4:
                                    	
                                        System.out.println("\nElija el tipo de vehiculo en el que quieve viajar\n" +
                                                "1. Bus\n" +
                                                "2. Escalera\n" +
                                                "3. Vans\n" +
                                                "4. Taxi");
                                        
                                        indiceV = scanner.nextInt();
                                        scanner.nextLine();
                                        
                                        if (indiceV == 1) {
                                            vehiculo = TipoVehiculo.BUS;
                                            
                                        } else if (indiceV == 2) {                                        	
                                            vehiculo = TipoVehiculo.ESCALERA;
                                            
                                        } else if (indiceV == 3) {
                                            vehiculo = TipoVehiculo.VANS;
                                            
                                        } else {
                                            vehiculo = TipoVehiculo.TAXI;
                                        }
                                        
                                        viajesDisponibles = Pasajero.viajesParaRegularesYDiscapacitados(cantidad, destinoDeseado, vehiculo);
                                        Tablas.transportadorasConEstrellas(destinoDeseado, viajesDisponibles, cantidad);
                                        
                                        System.out.println("\nElija una transportadora por su indice\n");
                                        indiceT = scanner.nextInt();
                                        scanner.nextLine();
                                        
                                        transportadoraSeleccionada = Terminal.transportadorasViajeDisponible(destinoDeseado, cantidad, viajesDisponibles).get(indiceT);
                                        
                                        for (Viaje viaje : viajesDisponibles) {
                                            if (viaje.getLlegada() == destinoDeseado && viaje.getTransportadora() == transportadoraSeleccionada) {
                                                viajeSeleccionado = viaje;
                                                break;
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Opción no válida");
                                }
                                break;
                            
                            case 4:
                                viajesDisponibles = Pasajero.viajesParaVips(cantidad, destinoDeseado, vehiculo);
                                
                                switch (modalidad) {
                                    case 1:
                                    	
                                        if (viajesDisponibles.isEmpty()) {
                                            System.out.println("No hay viajes disponibles");
                                        } 
                                        else {
                                            viajeSeleccionado = Pasajero.masRapido(destinoDeseado, cantidad, viajesDisponibles);
                                        }
                                        break;
                                        
                                    case 2:
                                    	
                                        // Implementar lógica para seleccionar viaje con salida más pronta
                                        break;
                                        
                                    case 3:
                                    	//Seleccionar por ma economico
                                        if (viajesDisponibles.isEmpty()) {
                                            System.out.println("No hay viajes disponibles");
                                            
                                        } else {
                                            viajeSeleccionado = Pasajero.masEconomico(destinoDeseado, cantidad, viajesDisponibles);
                                        }
                                        break;
                                        
                                    case 4:
                                    	
                                        System.out.println("\nElija el tipo de vehiculo en el que quieve viajar\n" +
                                                "1. Bus\n" +
                                                "2. Vans\n" +
                                                "3. Taxi");
                                        
                                        indiceV = scanner.nextInt();
                                        
                                        if (indiceV == 1) {
                                            vehiculo = TipoVehiculo.BUS;
                                            
                                        } else if (indiceV == 2) {
                                            vehiculo = TipoVehiculo.VANS;
                                            
                                        } else {
                                            vehiculo = TipoVehiculo.TAXI;
                                        }
                                        
                                        Tablas.transportadorasConEstrellas(destinoDeseado, viajesDisponibles, cantidad);
                                        
                                        System.out.println("\nElija una transportadora por su indice\n");
                                        indiceT = scanner.nextInt();
                                        scanner.nextLine();
                                        
                                        transportadoraSeleccionada = Terminal.transportadorasViajeDisponible(destinoDeseado, cantidad, viajesDisponibles).get(indiceT);
                                        
                                        for (Viaje viaje : viajesDisponibles) {
                                        	
                                            if (viaje.getLlegada() == destinoDeseado && viaje.getTransportadora() == transportadoraSeleccionada) {
                                                viajeSeleccionado = viaje;
                                                break;
                                            }
                                            
                                        }
                                        
                                        break;
                                    default:
                                        System.out.println("Opción no válida");
                                }
                                
                                Tablas.tablaInformacionViaje(viajeSeleccionado);
                                break;
                                
                            default:
                                System.out.println("Opción no válida");
                        }
                        
                        break;

                    case 2:
                    	
                        regresar = true;
                        regresar2 = true;
                        System.out.println("Regresando al Menú Principal");
                        break;

                    default:
                    	
                        System.out.println("Opción no válida");
                        
                }
                
                if (!regresar2) {
                	
                    Tablas.tablaInformacionViaje(viajeSeleccionado);
                    
                    System.out.println("¿Se realizará la venta?\n" +
                            "1. SI\n" +
                            "2. NO");
                    
                    int replantear = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (replantear == 2) {
                    	
                        regresar2 = true;
                        System.out.println("Volviendo a vender");
                        
                    } else {
                    	
                    	System.out.println("Ingrese su nombre");
                        nombre = scanner.nextLine();
                        System.out.println("Ingrese su número de identificación");
                        id = scanner.nextDouble();
                        System.out.println("Venta realizada");
                        regresar2 = true;
                        
                    }
                }
             }
        }
        
        scanner.close();
    }
}
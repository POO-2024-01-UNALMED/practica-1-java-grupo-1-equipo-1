package uiMain;
import java.util.Scanner;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;
import gestorAplicacion.tiempo.*;
import gestorAplicacion.constantes.*;

public class Main_3 {
	
	public static Scanner sc = new Scanner(System.in);
	
    public static int readInt() {
    	
    	return sc.nextInt();
    }
    
    public static String readString() {
    	
    	return sc.nextLine();
    
    }
    
    public static void ejecutar() {
    	
        Scanner scanner = new Scanner(System.in);
        boolean regresar = false;

        do {
        	
            System.out.println("Ejecutando Funcionalidad 3");
            // Lógica específica para Funcionalidad 3

            System.out.println("Seleccione una acción:");
            System.out.println("1. Tarifas Viajes");
            System.out.println("2. Ver transportadoras que han cancelado monto");
            System.out.println("3. Ver estadisticas generales");
            System.out.println("4. Conductores liquidados");
            System.out.println("5. Verificar compra tiquetes");
            System.out.println("6. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
            
                case 1:
                    
                	Main_3.verTarifas();
                    break;
                    
                case 2:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                    
                case 3:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                case 4:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                    
                case 5:
                    // Poner los metodos a ejecutar para cada accion.
                    break;
                    
                case 6:
                	
                    regresar = true;
                    System.out.println("Regresando al Menú Principal");
                    break;
                    
                default:
                    System.out.println("Opción no válida");
            }
            
        }while (!regresar);
    }
    
    
    public static void verTarifas() {
    	
    	System.out.print("Bienvenido señor administrador, a continuación se le mostrarán las transportadoras disponibles de su terminal ");
    	System.out.println("Para que pueda consultar las respectivas tarifas de la transportadora que desee");
    	int i = 1;
    	
    	if (!(Terminal.getTransportadoras().isEmpty())) {
    		
    		for (Transportadora t: Terminal.getTransportadoras()) {
        		
        		System.out.println(i +". " + t.getNombre() );
        		i++;
        		
        	}
    		
    		System.out.println("Seleccione la transportadora a la cual quiere ver dicha tarifa de viajes");
        	int scanner = Main_3.readInt();
        	
        	while (scanner >=0 && scanner >= Terminal.getTransportadoras().size()) {
        		
        		System.out.println("Ingrese nuevamente su número, ingresó un numero incorrecto ");
            	scanner = Main_3.readInt();
     	
        	}
        		
        		Transportadora TransportadoraSeleccionada = Terminal.getTransportadoras().get(scanner);
        		
        		int j = 1;
            	
            	if (!(TransportadoraSeleccionada.getViajesAsignados().isEmpty())) {
            		
            		for (Viaje v: TransportadoraSeleccionada.getViajesAsignados()) {
                		
                		String formato = "Viaje " + j + v.getSalida() + " - " + v.getLlegada() + " - Tarifa: $" + String.format("%.2f", v.getTarifa());
                		System.out.println(formato);
                		j++;
                	
                	}
            		
            	}else { System.out.println("Algo está fallando");}
            			
    	}else { System.out.println("Algo está fallando");}
    	
    }
    
}

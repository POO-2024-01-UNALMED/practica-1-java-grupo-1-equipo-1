package uiMain;
import java.io.IOException;
import java.util.Scanner;
import baseDatos.*;
import gestorAplicacion.constantes.*;
import gestorAplicacion.tiempo.Tiempo;
import gestorAplicacion.administrativo.*;
import gestorAplicacion.usuarios.*;

public class Main_Principal {
	
	public static Scanner sc = new Scanner(System.in);
	
	 public static int readInt() {
	    	
	    	return sc.nextInt();
	    }
	    
	    public static String readString() {
	    	
	    	return sc.nextLine();
	    
	    }
	
    static {
    	Serializador.objetosBase();
    	//Deserializador.deserializarListas();
    }
    
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("Seleccione una acción:");
            System.out.println("1. Funcionalidad 1 - Venta de Viajes");
            System.out.println("2. Funcionalidad 2 - Gestion Conductores");
            System.out.println("3. Funcionalidad 3 - Facturacion y Finanzas");
            System.out.println("4. Funcionalidad 4 - Administrar Vehiculos");
            System.out.println("5. Funcionalidad 5 - Programación de Viajes");
            System.out.println("6. Salir\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    //Main_1.ejecutar();
                    break;
                case 2:
                	Main_2.ejecutar();
                    break;
                case 3:
                	
                	Main_3.facturacionYFinanzas();
                    break;
                case 4:
                	Main_4.ejecutar4();
                    break;
                case 5:
                	Main_5.ejecutar();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    //System.out.println(Tiempo.principal.); // Fin del Tiempo
                    //Serializador.serializarListas();;
                    break;
                default:
                    System.out.println("Opción no válida, Marque de 1,6");
            }
        }
        scanner.close();
        
        
    }
}

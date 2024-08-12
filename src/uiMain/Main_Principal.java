package uiMain;

import java.util.Scanner;
import gestorAplicacion.tiempo.Tiempo;

public class Main_Principal {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        Tiempo a = new Tiempo();  // Inicio del Tiempo 
        
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
                    Main_1.ejecutar();
                    break;
                case 2:
                	Main_2.ejecutar();
                    break;
                case 3:
                	Main_3.ejecutar();
                    break;
                case 4:
                	Main_4.ejecutar();
                    break;
                case 5:
                	Main_5.ejecutar();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    System.out.println(a.cancel()); // Fin del Tiempo 
                    break;
                default:
                    System.out.println("Opción no válida, Marque de 1,6");
            }
        }

        scanner.close();
    }
}
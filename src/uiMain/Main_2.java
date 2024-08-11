package uiMain;

import java.util.Scanner;

public class Main_2 {
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean regresar = false;

        while (!regresar) {
            System.out.println("Ejecutando Funcionalidad 2");
            // Lógica específica para Funcionalidad 2
            
            System.out.println("Seleccione una acción:");
            System.out.println("1. Accion 1");
            System.out.println("2. Accion 2");
            System.out.println("3. Accion 3");
            System.out.println("4. Accion 4");
            System.out.println("5. Accion 5");
            System.out.println("6. Regresar al menú Principal\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Poner los metodos a ejecutar para cada accion.
                	ejemplo();
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
        }
    }
    
    // Desarrollar los metodos a partir de aqui
    public static void ejemplo() {
    	System.out.println("Ejemplo Funcionalidad 2"); 
    }
}

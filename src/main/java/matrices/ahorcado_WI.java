package matrices;

import java.util.Scanner;
import java.util.Random;

public class ahorcado_WI {

    static Scanner sc = new Scanner(System.in);
    static Random rd = new Random();

    public static void main(String[] args) {
        String[][] palabra = new String[3][2];
        palabra[0][0] = "perro";
        palabra[0][1] = "animal";
        palabra[1][0] = "teclado";
        palabra[1][1] = "periferico de ordenador";
        palabra[2][0] = "negro";
        palabra[2][1] = "color";        
        jugadores(palabra);
    }

    public static void jugadores(String[][] palabra) {
        String[][] jugadores = new String[10][2];
        String[][] records = new String[10][3];
        for (int i = 0; i < 10; i++) {
            records[i][0] = Integer.toString((i+1)) + ". ";
        }
        int jugador = 0, intentos, opcion;
        boolean salir = false;
        while (!salir) {
            System.out.println("..::Ahorcado::.."
                    + "\n1.Nueva partida"
                    + "\n2.Historial de records"
                    + "\n3.Salir");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    sc.nextLine();
                    jugadores[jugador][0] = pideNombre();
                    intentos = juego(palabra);
                    jugadores[jugador][1] = Integer.toString(intentos);
                    records = introduceRecord(records, jugadores);
                    if(jugador==0){
                        System.out.println("¡Nuevo Record! Nº de intentos: " + intentos);
                    } else if(intentos<10){
                        if(tieneRecord(records, intentos, jugador)){
                            System.out.println("¡Nuevo Record! Nº de intentos: " + intentos);
                        }
                    }
                    jugador++;
                    break;
                case 2:
                    System.out.println("Historial de records: \n");
                    System.out.println(mostrarRecords(records));
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no valida");
            }
            
        }

    }
    
    public static String[][] introduceRecord(String[][] records, String[][] jugadores){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                records[i][j+1] = jugadores[i][j];
            }
        }
        return records;
    }
    
    public static boolean tieneRecord(String [][] records, int intentos, int jugador){
        boolean record = false;
        int jugadas;
        for (int i = 0; i < jugador; i++) {
            jugadas = Integer.parseInt(records[i][2]);
            if(intentos<jugadas){
                record = true;
            }
        }
        return record;
    }
    
    public static String mostrarRecords(String[][] records){
        String historialRecords = "";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                historialRecords += (records[i][j] + " ");
            }
            historialRecords += "\n";
        }
        return historialRecords;
    }
    
    public static String pideNombre(){
        System.out.println("Introduzca su nombre: ");
        String nombre = sc.nextLine();
        return nombre;
    }

    public static int juego(String[][] palabra) {
        String letra;
        char letr;
        int contIntentos = 0, fila;
        fila = rd.nextInt(3);
        char[] numLetras = new char[palabra[fila][0].length()];
        boolean correcto = false;
        for (int i = 0; i < palabra[fila][0].length(); i++) {
            numLetras[i] = '_';
        }
        while (!correcto && contIntentos <= 10) {
            for (int i = 0; i < numLetras.length; i++) {
                System.out.print(numLetras[i] + " ");
            }
            System.out.println("\nAdivinar un " + palabra[fila][1] + ". Introducir letra: ");
            letra = sc.nextLine();
            letra = letra.toLowerCase();
            if (letra.length() == 1) {
                letr = letra.charAt(0);
                boolean contiene = false;
                for (int i = 0; i < palabra[fila][0].length(); i++) {
                    if (letr == (palabra[fila][0].charAt(i))) {
                        contiene = true;
                        numLetras[i] = palabra[fila][0].charAt(i);
                    }
                }
                if (!contiene) {
                    System.out.println("La letra " + letr + " no está en la palabra");
                }
                String adivinar = "";
                for (int i = 0; i < numLetras.length; i++) {
                    adivinar += numLetras[i];
                }
                if (adivinar.equals(palabra[fila][0])) {
                    correcto = true;
                }

            } else {
                System.out.println("Introduzca solo una letra porfavor.");
            }
            contIntentos++;
        }
        if (correcto) {
            System.out.println(palabra[fila][0]);
            System.out.println("¡Correcto!, ¡has acertado!. Nº de intentos: " + contIntentos);
        } else {
            System.out.println("¡Lo siento!, te has quedado sin intentos. La palabra era: " + palabra[fila][0]);
        }
        return contIntentos;
    }
}

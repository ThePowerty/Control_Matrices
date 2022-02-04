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
        juego(palabra);
    }

    public static void juego(String[][] palabra) {
        String letra, adivinar = "";
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
                System.out.print(numLetras[i]+" ");
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
                if(!contiene){
                    System.out.println("La letra " + letr + " no está en la palabra");
                }
                adivinar = "";
                for (int i = 0; i < numLetras.length; i++) {
                    adivinar += numLetras[i];
                }
                if(adivinar.equals(palabra[fila][0])){
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
    }
}

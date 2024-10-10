package puzzleGame;

import javax.swing.*;
import java.util.Scanner;

public class MyApp {
    public static void main(String[] args){

         try{
             ejecutaJuego();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Se ha cerrado el juego");
         }
    }
    static void ejecutaJuego() throws Exception {



        Puzzle8 game = new Puzzle8(modelo());

        do{
            String cadena = stringTablero(game.tablero);

            String[] opciones = game.lugaresDisponibles();

            int n = JOptionPane.showOptionDialog(null, cadena, "Mueve una ficha", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, "");

            try{
                game.movimientoDeFicha(Integer.parseInt(opciones[n]));
            }catch(NumberFormatException e){
                if(opciones[n].equals("undo")){
                    game.undo();

                }
                else if(opciones[n].equals("redo")){
                    game.redo();
                }

            }

        }while(!game.verificarEstado());

        String cadena = stringTablero(game.tablero);


        JOptionPane.showMessageDialog(null, cadena);
        JOptionPane.showMessageDialog(null, "GANASTE!\nGracias por jugar");
    }
    static int[] modelo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escoja su tablero");

        int[] opcion1 = new int[]{1,2,3,
                                  4,5,6,
                                  7,8,0};

        int[] opcion2 = new int[]{1,2,3,
                                  8,0,4,
                                  7,6,5};

        int[] opcion3 = new int[]{0,8,7,
                                  6,5,4,
                                  3,2,1};

        System.out.println("\nopcion 1");
        System.out.println(stringTablero(opcion1));
        System.out.println("\nopcion 2");
        System.out.println(stringTablero(opcion2));
        System.out.println("\nopcion 3");
        System.out.println(stringTablero(opcion3));

        System.out.print("Opcion: ");

        switch (sc.nextInt()){
            case 1:
                return opcion1;
            case 2:
                return opcion2;
            case 3:
                return opcion3;
            default:
                return opcion2;
        }
    }

    static String stringTablero(int[] tabla){
        String cadena = "";
        for(int i = 0; i < tabla.length; i++){

            if(tabla[i] != 0) cadena += "|" + tabla[i] + "| ";
            else cadena += "|  | ";
            if(i == 2 || i == 5) cadena+="\n";
        }
        cadena+="\n";
        return cadena;
    }



}

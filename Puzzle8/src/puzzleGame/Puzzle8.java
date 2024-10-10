package puzzleGame;
import java.math.*;
import java.util.Arrays;
import ed.ito.*;

import javax.swing.*;

public class Puzzle8 {

    public int[] tablero;
    public int[] resultadoEsperado;

    public PilaDinamica<int[]> undoPila; //pila para obtener el estado anterior
    public PilaDinamica<int[]> redoPila; // pila para regresar al estado actual

    public Puzzle8(int[] modelo){
        tablero = new int[9];
        generarNumeros();

        resultadoEsperado = modelo;
        undoPila = new PilaDinamica<>();
        redoPila = new PilaDinamica<>();
        try{
            guardarEstado();
        }catch(Exception e){
            System.out.println("error en la operacion");
        }

    }

    //metodo para rellenar arreglo aleatorio
    public int[] generarNumeros(){
        int[] array = tablero;
        for(int i = 0; i < array.length; i++){
            array[i] = (int) (Math.random()*9);
            for(int j = 0; j < i; j++){
                if(array[i] == array[j]){
                    array[i] = (int) (Math.random()*9);
                    j = -1;
                }
            }
        }
        return array;
    }

    //iniciar los movimientos
    public void movimientoDeFicha(int ficha){
        int posicion0 = -1;
        int posicionFicha = -1;

        for(int i = 0; i < tablero.length; i++){
            if(tablero[i] == 0) posicion0 = i;
            if (tablero[i] == ficha) posicionFicha = i;
        }
        tablero[posicionFicha] = 0;
        tablero[posicion0] = ficha;

        try{
            guardarEstado();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    public String[] lugaresDisponibles(){
        int lugar = 0;
        for(int i = 0; i < tablero.length; i++){
            if(tablero[i] == 0){
                lugar = i;
            }
        }

        switch (lugar){
            case 0:
                return new String[]{tablero[1]+"", tablero[3]+"", "undo", "redo"};
            case 1:
                return new String[]{tablero[0]+"", tablero[2]+"", tablero[4]+"" , "undo", "redo"};
            case 2:
                return new String[]{tablero[1]+"", tablero[5]+"", "undo", "redo"};
            case 3:
                return new String[]{tablero[0]+"", tablero[4]+"", tablero[6]+"", "undo", "redo"};
            case 4:
                return new String[]{tablero[1]+"", tablero[3]+"", tablero[5]+"", tablero[7]+"", "undo", "redo"};
            case 5:
                return new String[]{tablero[2]+"", tablero[4]+"", tablero[8]+"", "undo", "redo"};
            case 6:
                return new String[]{tablero[3]+"", tablero[7]+"", "undo", "redo"};
            case 7:
                return new String[]{tablero[4]+"", tablero[6]+"", tablero[8]+"", "undo", "redo"};
            case 8:
                return new String[]{tablero[5]+"", tablero[7]+"", "undo", "redo"};

            default:
                return new String[]{};
        }

    }



    //metodo para recuperar estado actual
    public int[] tableroActual(){
        try{
            return undoPila.top();
        }catch(Exception e){
            System.out.println("Error de operacion");
            return new int[]{};
        }
    }

    //verifica si el estado final es el esperado
    public boolean verificarEstado(){ // modificar cuando se implemente la pila
        if(Arrays.equals(tablero, resultadoEsperado)) return true;
            return false;
    }

    public void guardarEstado() throws Exception{
        undoPila.push(tablero.clone());

    }


    //metodo para cambiar el estado del tablero
    public void undo() throws ExcepcionDePilaVacia, ExcepcionDePilaLlena{
        if(!undoPila.isEmpty()){
            redoPila.push(tablero.clone());
            tablero = undoPila.pop();
        }
        else JOptionPane.showMessageDialog(null,"No hay mas movimientos");
    }

    //metodo para recuperar el estado
    public void redo() throws ExcepcionDePilaVacia, ExcepcionDePilaLlena{
        if(!redoPila.isEmpty()) {
            undoPila.push(tablero.clone());
            tablero = redoPila.pop();
        }
        else JOptionPane.showMessageDialog(null,"No hay mas movimientos");
    }


}

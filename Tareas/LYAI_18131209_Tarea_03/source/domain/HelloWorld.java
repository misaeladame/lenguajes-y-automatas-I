/*
Tarea 03

Alumno: José Misael Adame Sandoval    18131209

Lenguajes y Autómatas I

Descripción: Programa en Java que guarda mi primer nombre (José) en un arreglo y lo muestra
*/

package domain;

public class HelloWorld {

    static String[] nombre = new String[4];

    public static void main(String[] args) {
        agregar();
    }
    
    public static void agregar() {
        nombre[0] = "J";
        nombre[1] = "o";
        nombre[2] = "s";
        nombre[3] = "é";
        
        for (int i = 0; i < 4; i++)
            System.out.print(nombre[i]);
    }
}
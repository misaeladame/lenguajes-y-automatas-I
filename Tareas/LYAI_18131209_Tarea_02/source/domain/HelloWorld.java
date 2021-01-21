/*
Tarea 02

Alumno: José Misael Adame Sandoval    18131209

Lenguajes y Autómatas I

Descripción: Programa en Java que guarda el año 2020 en un arreglo y lo muestra
*/

package domain;

public class HelloWorld {

    static int[] anio = new int[4];

    public static void main(String[] args) {
        agregar();
    }
    
    public static void agregar() {
        anio[0] = 2;
        anio[1] = 0;
        anio[2] = 2;
        anio[3] = 0;
        
        for (int i = 0; i < 4; i++)
            System.out.print(anio[i]);
    }
}
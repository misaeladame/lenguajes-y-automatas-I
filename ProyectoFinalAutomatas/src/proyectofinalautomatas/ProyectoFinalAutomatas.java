/*
Proyecto Final - Para subir calificacion

Alumno: José Misael Adame Sandoval    18131209

Lenguajes y Autómatas I

14 de enero del 2021

Descripción: Programa en java, donde solicita al usuario una estructura de selección 
             simple (if, else, switch) o repetitiva (for, while, do while), indica si 
             esta correcta o incorrecta la sintaxis.
 */
package proyectofinalautomatas;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProyectoFinalAutomatas {

    public static void main(String[] args) {
        ejecutar();
    }

    public static void ejecutar() {

        System.out.println("Alumno: 18131209 - Jose Misael Adame Sandoval");
        
        System.out.println("\nEsribe una estructura de selección simple o "
                + "repetitiva, incluyendo su sintaxis como se indica en los ejemplos (también "
                + "puedes hacerlo de manera de spaguetti)\n");

        Ejemplo();

        Scanner consola = new Scanner(System.in);
        String sentencia = consola.nextLine();

        switch (sentencia.charAt(0)) {

            case 'i':
                estructuraIf(sentencia);
                break;

            case 'd':
                estructuraDoWhile(sentencia);
                break;

            case 'f':
                estructuraFor(sentencia);
                break;

            case 's':
                estructuraSwitch(sentencia);
                break;

            case 'w':
                estructuraWhile(sentencia);
                break;

            default:
                System.out.println("No se uso una estructura válida");
        }
    }

    private static void estructuraIf(String sentencia) {
        // Se usan las expresiones regulares para realizar la validación
        Pattern espaguetti = Pattern.compile("if[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s]*"
                + "(((&&)|(\\|\\|))[\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s])*[\\s]*[)][\\s]*[{].*[}][\\s]*"
                + "[\\s]*(else([\\s]*+if[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s]*"
                + "(((&&)|(\\|\\|))[\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s])*[\\s]*"
                + "[)])?[\\s]*[{].*[}][\\s]*)*");
        Matcher mat = espaguetti.matcher(sentencia);

        Pattern sintaxis = Pattern.compile("if[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s]*"
                + "(((&&)|(\\|\\|))[\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)*[\\s]*)*[\\s]*[)][\\s]*");
        Matcher mat2 = sintaxis.matcher(sentencia);

        if (mat.matches()) {
            System.out.println("\nEstas usando if");
            System.out.println("La sintaxis es correcta");
        } else if (mat2.matches()) {

            Scanner consola = new Scanner(System.in);
            sentencia = consola.nextLine();

            Pattern opcion = Pattern.compile("[\\s]*[{][\\s]*");
            mat = opcion.matcher(sentencia);

            if (mat.matches()) {
                System.out.println("//Estructura");

                sentencia = consola.nextLine();

                opcion = Pattern.compile("[\\s]*[}][\\s]*");
                mat = opcion.matcher(sentencia);

                if (mat.matches()) {
                    System.out.println("\t\tLa sintaxis es correcta, si desea seguir escribe else, pero si "
                            + "quiere terminar escribe 's'");

                    sentencia = consola.nextLine();

                    opcion = Pattern.compile("s");
                    mat = opcion.matcher(sentencia);

                    opcion = Pattern.compile("[\\s]*else[\\s]*");
                    mat2 = opcion.matcher(sentencia);

                    if (mat.matches()) {
                        System.out.println("Programa terminado");
                    } else if (mat2.matches()) {
                        sentencia = consola.nextLine();

                        opcion = Pattern.compile("[\\s]*[{][\\s]*");
                        mat = opcion.matcher(sentencia);

                        if (mat.matches()) {
                            System.out.println("//Estructura");

                            sentencia = consola.nextLine();

                            opcion = Pattern.compile("[\\s]*[}][\\s]*");
                            mat = opcion.matcher(sentencia);

                            if (mat.matches()) {
                                System.out.println("\nEstas usando else");
                                System.out.println("La sintaxis es correcta");
                            } else {
                                System.out.println("\nEstas usando else");
                                System.out.println("La sintaxis es incorrecta");
                            }
                        } else {
                            System.out.println("\nEstas usando else");
                            System.out.println("La sintaxis es incorrecta");
                        }
                    } else {
                        System.out.println("\nEstas usando else");
                        System.out.println("La sintaxis es incorrecta");
                    }
                } else {
                    System.out.println("\nEstas usando if");
                    System.out.println("La sintaxis es incorrecta");
                }
            } else {
                System.out.println("\nEstas usando if");
                System.out.println("La sintaxis es incorrecta");
            }
        } else {
            System.out.println("\nEstas usando if");
            System.out.println("La sintaxis es incorrecta");
        }
    }

    private static void estructuraSwitch(String sentencia) {

        Pattern spaguetti = Pattern.compile("switch[\\s]*[(][\\s]*[a-zA-Z_]+[\\s]*[)][\\s]*[{].*[}][\\s]*");
        Matcher mat = spaguetti.matcher(sentencia);

        Pattern sintaxis = Pattern.compile("switch[\\s]*[(][\\s]*[a-zA-Z_]+[\\s]*[)][\\s]*");
        Matcher mat2 = sintaxis.matcher(sentencia);

        if (mat.matches()) {
            System.out.println("\nEstas usando switch");
            System.out.println("La sintaxis es correcta");
        } else if (mat2.matches()) {
            Scanner consola = new Scanner(System.in);
            sentencia = consola.nextLine();

            Pattern opcion = Pattern.compile("[\\s]*[{][\\s]*");
            mat = opcion.matcher(sentencia);

            if (mat.matches()) {
                System.out.println("//Estructura");

                sentencia = consola.nextLine();

                opcion = Pattern.compile("[\\s]*[}][\\s]*");
                mat = opcion.matcher(sentencia);

                if (mat.matches()) {
                    System.out.println("\nEstas usando switch");
                    System.out.println("La sintaxis es correcta");
                } else {
                    System.out.println("\nEstas usando switch");
                    System.out.println("La sintaxis es incorrecta");
                }
            } else {
                System.out.println("\nEstas usando switch");
                System.out.println("La sintaxis es incorrecta");
            }
        } else {
            System.out.println("\nEstas usando switch");
            System.out.println("La sintaxis es incorrecta");
        }
    }

    private static void estructuraFor(String sentencia) {
        Pattern spaguetti = Pattern.compile("for[\\s]*[(][\\s]*(int)?[\\s]*[\\w]+[\\s]*[=][\\s]*[\\w][;][\\s]*[\\w]+[\\s]*([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+[;][\\s]*[\\w]+[\\s]*((--)|(\\+\\+)|(((\\+=)|(-=)|(\\*=)|(/=))[\\s]*[\\w]+))[\\s]*[)][\\s]*[{].*[}][\\s]*");
        Matcher mat = spaguetti.matcher(sentencia);

        Pattern sintaxis = Pattern.compile("for[\\s]*[(][\\s]*(int)?[\\s]*[\\w]+[\\s]*[=][\\s]*[\\w][;][\\s]*[\\w]+[\\s]*([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+[;][\\s]*[\\w]+[\\s]*((--)|(\\+\\+)|(((\\+=)|(-=)|(\\*=)|(/=))[\\s]*[\\w]+))[\\s]*[)][\\s]*");
        Matcher mat2 = sintaxis.matcher(sentencia);

        if (mat.matches()) {
            System.out.println("\nEstas usando for");
            System.out.println("La sintaxis es correcta");
        } else if (mat2.matches()) {
            Scanner consola = new Scanner(System.in);
            sentencia = consola.nextLine();

            Pattern opcion = Pattern.compile("[\\s]*[{][\\s]*");
            mat = opcion.matcher(sentencia);

            if (mat.matches()) {
                System.out.println("//Estructura");

                sentencia = consola.nextLine();

                opcion = Pattern.compile("[\\s]*[}][\\s]*");
                mat = opcion.matcher(sentencia);

                if (mat.matches()) {
                    System.out.println("\nEstas usando for");
                    System.out.println("La sintaxis es correcta");
                } else {
                    System.out.println("\nEstas usando for");
                    System.out.println("La sintaxis es incorrecta");
                }
            } else {
                System.out.println("\nEstas usando for");
                System.out.println("La sintaxis es incorrecta");
            }
        } else {
            System.out.println("\nEstas usando for");
            System.out.println("La sintaxis es incorrecta");
        }
    }

    private static void estructuraDoWhile(String sentencia) {
        Pattern spaguetti = Pattern.compile("do[\\s]*[{][\\s]*[}][\\s]*while[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)+[\\s]*[)][\\s]*[;][\\s]*");
        Matcher mat = spaguetti.matcher(sentencia);

        Pattern sintaxis = Pattern.compile("do[\\s]*");
        Matcher mat2 = sintaxis.matcher(sentencia);

        if (mat.matches()) {
            System.out.println("\nEstas usando do while");
            System.out.println("La sintaxis es correcta");
        } else if (mat2.matches()) {
            Scanner consola = new Scanner(System.in);
            sentencia = consola.nextLine();

            Pattern opcion = Pattern.compile("[\\s]*[{][\\s]*");
            mat = opcion.matcher(sentencia);

            if (mat.matches()) {
                System.out.println("//Estructura");

                sentencia = consola.nextLine();

                opcion = Pattern.compile("[\\s]*[}][\\s]*");
                mat = opcion.matcher(sentencia);

                if (mat.matches()) {
                    sentencia = consola.nextLine();

                    opcion = Pattern.compile("[\\s]*while[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)+[\\s]*[)][\\s]*[;][\\s]*");
                    mat = opcion.matcher(sentencia);

                    if (mat.matches()) {
                        System.out.println("\nEstas usando do while");
                        System.out.println("La sintaxis es correcta");
                    } else {
                        System.out.println("\nEstas usando do while");
                        System.out.println("La sintaxis es incorrecta");
                    }

                } else {
                    System.out.println("\nEstas usando do while");
                    System.out.println("La sintaxis es incorrecta");
                }
            } else {
                System.out.println("\nEstas usando do while");
                System.out.println("La sintaxis es incorrecta");
            }
        } else {
            System.out.println("\nEstas usando do while");
            System.out.println("La sintaxis es incorrecta");
        }
    }

    private static void estructuraWhile(String sentencia) {

        Pattern spaguetti = Pattern.compile("while[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)+[\\s]*[)][\\s]*[{][\\s]*[}][\\s]*");
        Matcher mat = spaguetti.matcher(sentencia);

        Pattern sintaxis = Pattern.compile("while[\\s]*[(][\\s]*[\\w]+[\\s]*(([>|<|%]|([>|<|!|=][=]))[\\s]*[\\w]+)+[\\s]*[)][\\s]*");
        Matcher mat2 = sintaxis.matcher(sentencia);

        if (mat.matches()) {
            System.out.println("\nEstas usando While");
            System.out.println("La sintaxis es correcta");
        } else if (mat2.matches()) {
            Scanner consola = new Scanner(System.in);
            sentencia = consola.nextLine();

            Pattern opcion = Pattern.compile("[\\s]*[{][\\s]*");
            mat = opcion.matcher(sentencia);

            if (mat.matches()) {
                System.out.println("//Estructura");

                sentencia = consola.nextLine();

                opcion = Pattern.compile("[\\s]*[}][\\s]*");
                mat = opcion.matcher(sentencia);

                if (mat.matches()) {
                    System.out.println("\nEstas usando while");
                    System.out.println("La sintaxis es correcta");
                } else {
                    System.out.println("\nEstas usando while");
                    System.out.println("La sintaxis es incorrecta");
                }
            } else {
                System.out.println("\nEstas usando while");
                System.out.println("La sintaxis es incorrecta");
            }
        } else {
            System.out.println("\nEstas usando while");
            System.out.println("La sintaxis es incorrecta");
        }
    }

    private static void Ejemplo() {
        System.out.println("EJEMPLOS DE SINTAXIS");
        System.out.println("-------------------------------------------------------");
        System.out.println("while(edad<=18)   if(edad<=18)   for(int i=0;i<18;i++)"
                + "\n{                 {              {"
                + "\n//Estructura      //Estructura   //Estructura"
                + "\n}                 }              }                        ");
        System.out.println("-------------------------------------------------------");
    }
}

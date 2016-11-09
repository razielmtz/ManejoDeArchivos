/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejodearchivos;
import java.util.*;
import java.io.*;
/**
 *
 * @author Raziel 2
 */
public class ManejoDeArchivos {

    /**
     * @param args the command line arguments
     */      
    public static void main(String[] args) {
 
        int opcion;
        String[][] datos;
        String nombre;
        opcion = menu();
        switch (opcion){
            case 1:
                datos = registrarAlumno();
                nombre = pedirNombreDelArchivo();
                crearArchivo(datos, nombre);
                break;
            case 2:
                mostrarRegistro(datos);
                break;
            default:
                mensajeSalida();
            
        }
    }
    public static int menu(){
        Scanner entrada= new Scanner (System.in);
        int opcion;
        do{
        System.out.println("Bienvenido al registro de alumnos.\nPor favor introduzca una opción del menú: \n");
        System.out.println("\t1. Registrar Alumnos.\n\t2. Mostrar Registro.\n\t3. Salir.\n");
        opcion = entrada.nextInt();}
        while (opcion<0 || opcion>3);
        return opcion;
    }
    public static String[][] registrarAlumno(){
        int a;
        String archivo;
        a = pedirNumAlumnos();
        String[][] arr = new String[a][2];
        arr = llenarDatos(arr);
        return arr;
    }
    public static int pedirNumAlumnos(){
        int num;
        Scanner entrada=new Scanner (System.in);
        System.out.print("Por favor, introduzca la cantidad de alumnos que hay: ");
        num = entrada.nextInt();
        return num;
    }
    public static String pedirNombreDelArchivo(){
        String nombre;
        Scanner entrada= new Scanner (System.in);
        System.out.print("Introduzca el nombre para el archivo a guardar:  ");
        nombre = entrada.nextLine();
        return nombre;
    }
    public static String[][] llenarDatos(String[][] datos){
        int orden=1;
        String nombre, matricula;
        Scanner entrada = new Scanner(System.in);
        for(int i=0;i<datos.length;i++){
            for(int j=0;j<datos[0].length;j++){
                if(j==0){
                System.out.print("Por favor, ingresar el nombre del alumno número       [ "+(orden)+" ] de lista: ");
                nombre = entrada.nextLine();
                for(int k=0;k<i;k++){
                    while(datos[k][0].contains(nombre)){
                        System.out.print("Usted ya ingresó este nombre, intente de nuevo:\n Ingrese uno diferente: ");
                        nombre = entrada.nextLine();
                    }
                }
                    orden ++;
                    datos[i][j]=nombre;
             }
                    if(j==1){
                    System.out.print("Porfavor, ingresar la matricula del alumno número     [ "+(orden-1)+" ] de lista:");
                    matricula = entrada.nextLine();
                    for(int k=0;k<i;k++){
                        while(datos[k][1].contains(matricula)){
                        System.out.print("Usted ya ingreso esta matrícula, ingrese una diferente: ");
                        matricula = entrada.nextLine();
                    }
                    }
                    datos[i][j]=matricula;
                    }
                }
            }
            return datos;
        }
    public static void mostrarRegistro(String[][] datos){
        
        
    }
    public static void mensajeSalida(){
        System.out.println("Gracias por usar este programa.");
        System.exit(0);
    }
    public static void crearArchivo(String [][]datos, String nombre){
        //Paso 1 Instanciamos un objeto de la clase File 
        //al instanciar escribimos como parámetro 
        //el nombre del archivo para manipularlo
        File archivo = new File(nombre);
        //Paso 2. Si no existe el archivo
        if (!archivo.exists())
        {   try {  //try nos sirve para manejar excepciones. En caso de que algo
            //pueda salga mal.
            //Creamos un archivo nuevo. 
            archivo.createNewFile();
            } catch (IOException ex) {
               ex.printStackTrace();
            } 
        }  else {
            System.err.println("Ya existe un archivo con ese nombre.");
            System.out.println("Por favor introduce otro nombre.");
            nombre = pedirNombreDelArchivo();
            crearArchivo(datos,nombre);
            }
        //Paso 3. Escritura en el archivo
        try {
            //Instanciamos un objeto de la clase PrintWriter
            //como parámetros enviamos el la instancia de File y el formato de
            //archivo de texto
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
            //Escribimos el contenido del archivo.
            escribir.println(datos);
            //Cerramos el archivo.
            escribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println("El archivo se creó con éxito.");
} 
}


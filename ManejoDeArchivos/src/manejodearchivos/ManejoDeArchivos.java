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
public class ManejoDeArchivos { //Ultimo corregido

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       menu();
    }
    public static void menu(){
        int opcion; //Esta variable sirve para escoger que categoria usar del menu.
        boolean reg=true; //Sirve para volver al menú.
        String[][] datos; //Es el arreglo donde están las matrículas y nombres.
        String nombre; //Es la variable del nombre del archivo.
        Scanner entrada= new Scanner (System.in); //Variable del scanner
        while (reg==true){
            System.out.println("\n\nBienvenido al registro de alumnos.\nPor favor introduzca una opción del menú: \n");
            System.out.println("\t1. Registrar Alumnos.\n\t2. Mostrar Registro.\n\t3. Salir.\n"); //menú del sistema.
            opcion = entrada.nextInt();   //el usuario da un valor a la variable opcion para continuar con el menú.    
            switch (opcion){ //Tomamos el valor de la opcion dado anteriormente y depende de ese resultado el ciclo acatará una orden del menú.
            case 1: //Si el valor es uno proseguirá a registrar los nombres y matrículas de un grupo de alumnos.
                datos = registrarAlumno(); //llamamos al método de registrarAlumno().
                nombre = pedirNombreDelArchivo(); //llamamos el método de pedirNombreDelArchivo().
                crearArchivo(datos, nombre); //Aquí creamos un arhcivo con el nombre y matrículas asignadas.
                regresar();
                break;
         
            case 2:
                nombre = pedirNombreDelArchivo(); //Llamamos el metodo de pedirNombreDelArchivo
                leerArchivo(nombre); //Se recupera la informaciónd de los datos para mostrarlos.
                break;
            case 3:
                mensajeSalida(); //Se cierra el programa
            default:
                System.out.print("Gracias por usar el programa.");
                System.exit(0);//Se despide al usuario cerrando la aplicación.
        } 
        
    }
    }
    public static String[][] registrarAlumno(){
        int a;
        String archivo;
        a = pedirNumAlumnos();
        String[][] arr = new String[a][2];
        arr = llenarDatos(arr);
        return arr;
    }
    public static int pedirNumAlumnos(){ //En éste método se pide la cantidad de Alumnos a registrarse
        int num; //Variable de cantidad de Alumnos
        Scanner entrada=new Scanner (System.in); 
        System.out.print("Por favor, introduzca la cantidad de alumnos que hay: "); // Se muestra mensaje
        num = entrada.nextInt(); //Se ingresa por teclado la variable
        return num; //Se regresa la variable de cantidad de Alumnos
    }
    public static String pedirNombreDelArchivo(){ //En éste método se pide el nombre del Archivo
        String nombre; //Variable del nombre del Archivo de tipo String
        Scanner entrada= new Scanner (System.in);
        System.out.print("Introduzca el nombre del archivo:  "); //Se muestra mensaje 
        nombre = entrada.nextLine(); //Se pide la variable por teclado
        return nombre; //Se regresa la variable que contiene el nombre del Archivo
    }
    public static String[][] llenarDatos(String[][] datos){
        int orden=1; //variable de entrada entero con valor de uno.
        String nombre, matricula; //Se declaran dos variables para el nombre y matrícula del alumno
        Scanner entrada = new Scanner(System.in); //Se declara entrada como nuevo scanner.
        for(int i=0;i<datos.length;i++){ //Se declara una variable i en un ciclo for, esté servirá para asignar una valor a las matrículas.
                for(int j=0;j<datos[0].length;j++){ //Se decalara una variable j en un ciclo for, esté servirá para asignar un valor al valor de nombre.
                if(j==0){ 
                System.out.print("Por favor, ingresar el nombre del alumno número       [ "+(orden)+" ] de lista: ");
                nombre = entrada.nextLine(); //Recibimos un valor del scanner y se lo asignamos al número.
                for(int k=0;k<i;k++){
                    while(datos[k][0].contains(nombre)){ //Si el nombre ya se ingreso te pedirá que repitas el proceso.
                        System.out.print("Usted ya ingresó este nombre, intente de nuevo:\n Ingrese uno diferente: ");
                        nombre = entrada.nextLine(); //Se declara un nuevo nombre.
                    }
                }
                    orden ++; //Esta variable solo agrega un cierto nivel de orden para que el usuario pueda saber en que posición está en su lista.
                    datos[i][j]=nombre; //Por el momento se guardan las variables nombre.
             }
                    if(j==1){ //El ciclo se repetirá y ahora asignará los valores j pero ahora vale 1, por eso no se repité el proceso pasado debido a que necesita valer 0 para que se vuelva a ocurrir esa etapa.
                    System.out.print("Porfavor, ingresar la matricula del alumno número     [ "+(orden-1)+" ] de lista: ");
                    matricula = entrada.nextLine(); //Se crea una nueva matricula.
                    for(int k=0;k<i;k++){ //Se crea un ciclo para volver a asignar una nueva matrícula en caso que se haya repetido.
                        while(datos[k][1].contains(matricula)){
                        System.out.print("Usted ya ingreso esta matrícula, ingrese una diferente: ");
                        matricula = entrada.nextLine(); //Se crea una nueva matrícula.
                    }
                    }
                    datos[i][j]=matricula; //Ahora se guardan las variables matricula
                    }
                }
            }
            return datos;
        }
    public static void mensajeSalida(){
        System.out.println("Gracias por usar este programa.");
        System.exit(0);
    }
    public static void crearArchivo(String [][]datos, String nombre){
        //Paso 1 Instanciamos un objeto de la clase File 
        //al instanciar escribimos como parámetro 
        //el nombre del archivo para manipularlo
        File archivo = new File(nombre + ".txt");
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
    public static void leerArchivo(String nombre){
        // Paso 1. Instanciamos un objeto de la clase File y una variable cadena
        File archivo = new File (nombre + ".txt");
        String cadena="";
        if (!archivo.exists()){
            System.err.print("El archivo " + nombre  +" no existe.");  
        }
        try {//Par el manejo de excepciones
            //Instanciamos un objeto de la clase FileReader y otro de la clase
            //BufferedReader, los cuales nos serviran para dar lectura al archivo
            //deben estar dentro de try.
           FileReader lectura = new FileReader(archivo);
           BufferedReader bufferL = new BufferedReader(lectura);
           //Paso 2. Recorremos el archivo.
           while (cadena!=null){ //Mientras la cadena no sea nula
               cadena = bufferL.readLine(); //Leemos líena por línea el archivo.
               if(cadena!=null) { //Si no encontramos null dentro del archivo
                   System.out.println(cadena); //Lo muestra en pantalla.
               }
           }
           //Paso 3. Cerramos las instancias de BufferedReader y FileReader.
           bufferL.close();
           lectura.close();
        } catch (Exception e) {
            e.printStackTrace();
        }  
        regresar();
        
    }
    public static boolean regresar(){ //Este método sirve para hacer que cuando termine una acción del menú regrese a la interfaz del programa.
        boolean regresar=false; //Se declara una variable "x" falsa.
        Scanner entrada= new Scanner (System.in); //Declaramos entrada como scanner.
        int op; //Declaramos una variable de tipo entero.
        System.out.println("¿Desea regresar al menú?\n1.\tSI\n2.\tNO (Cerrar Programa)"); //Si el scanner lee 1 regresa al menú, si lee otro número se despide agradeciendo al usuario.
        op=entrada.nextInt();
        while (regresar==false){
        if(op==1){ 
                    regresar=true; //La condición true hace que regrese a la interfaz.
                    }else {
                        System.out.println("Gracias por usar el programa.");
                        System.exit(0); 
                    }
        }return regresar; //regresa el valor de "x" para decidir si continúa el programa o no.
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Angel FV
 * * A continuación crearás un programa que permita
 * leer los datos del fichero creado.
 * Puedes verificar que puedes leer un registro 
 * cualquierea, por eso es de acceso aleatorio,
 * desplazándote con el seek al registro 
 * mediante la fórmula 
 * (numeroderegistroabuscar -1)*tamañoderegistro.
 * 
 * 
 *  * los datos del fichero son este formato:
 * 
 *      numeroempleado entero + 
 *      nombre(10 caracteres)+
 *      edad entero + 
 *      salario double
 * 
 * teniendo en cuenta que los enteros son 4 bytes, 
 * los caracteres el doble de bytes de lo que ocupan
 * y los doubles 8 bytes. Tenemos que el registro 
 * ocupa 4+20+4+8=36 bytes
 * 
 */
public class Ejercicio17_LeerRAF {
    public static void main(String[] args) {
               
        /*
            Java proporciona una clase RandomAccessFile 
            para este tipo de entrada/salida, la cual permite
            leer y escribir sobre el fichero, no es necesario 
            dos clases diferentes.
        
            Posee métodos específicos de desplazamiento 
            como seek(long posicion) o skipBytes(int desplazamiento) 
            para poder movernos de un registro a otro del fichero, 
            o posicionarnos directamente en una posición concreta 
            del fichero.
        
            Por esas características que presenta la clase, 
            un archivo de acceso directo tiene sus registros 
            de un tamaño fijo o predeterminado de antemano.
        
            La clase posee dos constructores:

                RandomAccessFile(File file, String mode).

                RandomAccessFile(String name, String mode)
        
                En el primer caso se pasa un objeto File 
                como primer parámetro, mientras que en el 
                segundo caso es un String. 
                El modo es: "r" si se abre en modo lectura 
                o "rw" si se abre en modo lectura y escritura.
        */
        String rutaFOrigen = "src/main/resources/ficheroRAF.dat";  
        File fichOrigen = new File(rutaFOrigen);
           
        if (!fichOrigen.exists()){
            System.out.println("no existe el fichero origen");
        }else{
            //instancio al fichero random en modo solo lectura
            try{     
                leerSecuencial1(fichOrigen);  
                
                System.out.println("---------------------------------------");
                
                leerSecuencial2(fichOrigen);  
                
                System.out.println("---leo el tercer registro del fichero--");
                
                leerunregistroRaf(3);
                
            }catch(IOException ioe){
                    ioe.printStackTrace();
            }
        }
    }
    
    public static void leerSecuencial1(File nombre) throws IOException{
        
        try(RandomAccessFile raf = new RandomAccessFile(nombre, "r")){
            
                //variable para almacenar el apellido
                StringBuffer buffer = null; 

                // Mueve el puntero al inicio del fichero
                raf.seek(0); 

                // Leer registros en secuencia
                while (true) {
                    try {
                        // Leer el número de empleado
                        int numeroEmpleado = raf.readInt();

                        // Leer el apellido (10 caracteres)
                        StringBuilder apellido = new StringBuilder();
                        for (int j = 0; j < 10; j++) {
                            apellido.append(raf.readChar()); // Leer 2 bytes por carácter
                        }
                        // Leer el número de departamento
                        int departamento = raf.readInt();

                        // Leer el salario
                        double salario = raf.readDouble();

                        // Imprimir el registro
                        System.out.printf("Empleado %d: Apellido: %s, Departamento: %d, Salario: %.2f%n",
                                numeroEmpleado, apellido.toString().trim(), departamento, salario);
                    } catch (IOException e) {
                        break; // Salir del bucle si se alcanza el final del archivo
                               //al llegar al final produce una excepcion de tipo IOException
                    }
                }
        }       
    }
 
    public static void leerSecuencial2(File nombre) throws IOException{
        
        try(RandomAccessFile raf = new RandomAccessFile(nombre, "r")){

                // Mueve el puntero al inicio del fichero
                raf.seek(0); 
                
                StringBuilder apellido;
                
                // Leer registros en secuencia
                // hasta llegar al final del fichero
                while (raf.getFilePointer()!=raf.length()) {
                        // Leer el número de empleado
                        int numeroEmpleado = raf.readInt();

                        // Leer el apellido (10 caracteres)
                        apellido = new StringBuilder();
                        for (int j = 0; j < 10; j++) {
                            apellido.append(raf.readChar()); // Leer 2 bytes por carácter
                        }
                        // Leer el número de departamento
                        int departamento = raf.readInt();

                        // Leer el salario
                        double salario = raf.readDouble();

                        // Imprimir el registro
                        System.out.println("NumEmp:"+numeroEmpleado+", Apellido:"+apellido+", "
                        + "departamento:"+departamento+", salario:"+salario);
                }
        } 
    } 
    
    public static void leerunregistroRaf(int numeroregistro) throws IOException{
      
      int numemp,dep;
      Double salario;
      StringBuilder apellido;
      
      try (RandomAccessFile raf = new RandomAccessFile(new File("./src/main/resources/FicheroRAF.dat"), "r")) {
            
            raf.seek((numeroregistro-1)*36); // (Numeroderegitro-1)*tamañoregistro
                                             //Mediante este método nos posicionamos en el registro  a leer
                                             //En el ejemplo leemos a Lopez, podría cambiar
            numemp=raf.readInt(); //leer numemp
            
            // Leer el apellido (10 caracteres)
            apellido = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                 apellido.append(raf.readChar()); // Leer 2 bytes por carácter
            }
       
            String apel=new String (apellido);
            
            dep=raf.readInt();
            salario=raf.readDouble();
            
            System.out.println("NumEmp:"+numemp+", Apellido:"+apellido+", "
                    + "departamento:"+dep+", salario:"+salario);
                       
           }
    }
}

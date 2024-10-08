/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import modelo.Persona;

/**
 *
 * @author Angel FV
 * escribir un programa que permitar escribir un
 * fichero de acceso aleatorio, un random access file, 
 * denominado ficheroRAF.dat
 * 
 * los datos que va a guardar tendrá este formato:
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
 * Para evitar la petición por teclado, los 
 * datos los inicializarás en la aplicación
 *
 * Posee métodos específicos de desplazamiento 
            como seek(long posicion) o skipBytes(int desplazamiento) 
            para poder movernos de un registro a otro del fichero, 
            o posicionarnos directamente en una posición concreta 
            del fichero.
 */
public class Ejercicio16_CrearRAF {
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

        /*no debo crear los objetos de fileoutputstream y dataoutpstream
        sin antes estar seguros de que el fichero existe.
        */
        RandomAccessFile raf = null;
           
        if (!fichOrigen.exists()){
            System.out.println("no existe el fichero origen");
            System.out.println("lo creamos");
 
            try{
                fichOrigen.getParentFile().mkdirs();
                fichOrigen.createNewFile();
                
                System.out.println("ya existe");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        
        try{
                int [] dep = {10, 20, 10, 10, 30, 30,  
                                20};
                
                String[] apellido = {"Fernandez", "Gil", 
                                   "Lopez", "Ramos", 
                                   "Sevilla", "Casilla", "Rey"};

                double [] salario = {1100.11, 1100.12, 
                    1100.13, 1100.14,1100.15, 
                    1200.11, 1200.0};
                
                //variable para almacenar el apellido
                StringBuffer buffer = null; 
                
                //lo abrimos para lectura y escritura
                raf = new RandomAccessFile(fichOrigen, "rw");
                
                //no situamos al final del fichero
                raf.seek(raf.length());
                
                for (int i=0; i < dep.length;i++){
                    raf.writeInt(i + 1); //numero de empleado
                    
                    buffer = new StringBuffer(apellido[i]);
                    buffer.setLength(10); //para que mida exactamente 10.
                    
                    raf.writeChars(buffer.toString());
                    raf.writeInt(dep[i]);
                    raf.writeDouble(salario[i]);
                }
    
        }catch(IOException ioe){
                    ioe.printStackTrace();
        }finally{
                try{
                    raf.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
        }
    }  
}

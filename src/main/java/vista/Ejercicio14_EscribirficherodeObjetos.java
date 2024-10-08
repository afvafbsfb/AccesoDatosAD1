/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import modelo.Persona;

/**
 *
 * @author Angel FV
 * programa que permita crear un fichero de objetos de la clase
 * persona, guardaremos nombre, edad y salario denominado 
 * fichbinarioorigen.dat. para evitar la petición de datos por 
 * teclado, los datos los inicializarás en la aplicación
 * 
 * el proceso de lectura/escritura I/O de los ficheros
 * lo haremos usando las clases 
 * FileInputStream, FileOutputStream,
 * DataInputStream, DataOutputStream
 */
public class Ejercicio14_EscribirficherodeObjetos {
    public static void main(String[] args) {
               
        String rutaFOrigen = "src/main/resources/fichbinarioorigen.dat";  
        File fichOrigen = new File(rutaFOrigen);

        /*no debo crear los objetos de fileoutputstream y dataoutpstream
        sin antes estar seguros de que el fichero existe.
        */
        FileOutputStream fos = null;
        ObjectOutputStream dos = null;
           
        if (!fichOrigen.exists()){
            System.out.println("no existe el fichero origen");
            System.out.println("lo creamos");
 
            try{
                fichOrigen.getParentFile().mkdirs();
                fichOrigen.createNewFile();
                
                System.out.println("ya existe");
                
                fos = new FileOutputStream(fichOrigen);
                dos = new ObjectOutputStream(fos);
                
                Persona persona;
                
                String [] nombre ={"angel", "pepito", 
                    "Alicia", "Pedro", 
                    "Manuel", "Andres",
                    "Julio", "Antonio", "Maria Jesus"};
                int [] edad = {18, 24, 33, 16, 21, 17, 
                                22, 78, 54};
                double [] salario = {1100.11, 1100.12, 
                    1100.13, 1100.14,1100.15, 
                    1200.11, 1200.0, 1200.21, 1200.33};
                
                for (int i=0; i < edad.length;i++){
                    persona = new Persona(nombre[i], edad[i], salario[i]);
                    dos.writeObject(persona);
                }
    
            }catch(FileNotFoundException fnfe){
                    fnfe.printStackTrace();   
            }catch(IOException ioe){
                    ioe.printStackTrace();
            }finally{
                try{
                    dos.close();
                    fos.close(); //optional. con cerrar el DataOutputStreem 
                    //sería suficiente porque cierra el otro fichero pero 
                    //tambien se puede indicar
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        
    }    
}

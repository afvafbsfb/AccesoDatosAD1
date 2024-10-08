/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Angel FV
 * por datos basicos se entiende datos primitivos java: int double, String...
 * Escribir un programa que permita crear un fichero 
 * de datos(nombre, edad y salario de personas)
 * denominado fichorigen.dat
 * los datos se inicializaran por la aplicación
 * A continuación crearás otro programa que permita leer el
 * fichorigen.dat y mostrará los datos por pantalla
 * El proceso de escritura y lectura I/O los harás 
 * utilizando las clases FileInputStream, FileOutputStream,
 * DataOutputStream y DataInputStream
 */
public class Ejercicio12_Escribirdatosbasicos {
    public static void main(String[] args) {
               
        String rutaFOrigen = "src/main/resources/fichorigen.dat";  
        File fichOrigen = new File(rutaFOrigen);

        /*no debo crear los objetos de fileoutputstream y dataoutpstream
        sin antes estar seguros de que el fichero existe.
        */
        FileOutputStream fos = null;
        DataOutputStream dos = null;
           
        if (!fichOrigen.exists()){
            System.out.println("no existe el fichero origen");
            System.out.println("lo creamos");
 
            try{
                fichOrigen.getParentFile().mkdirs();
                fichOrigen.createNewFile();
                
                System.out.println("ya existe");
                fos = new FileOutputStream(fichOrigen);
                dos = new DataOutputStream(fos);
                
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
                    dos.writeUTF(nombre[i]);
                    dos.writeInt(edad[i]);
                    dos.writeDouble(salario[i]);
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

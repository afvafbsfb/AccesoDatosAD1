/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Angel FV
 * escribir un programa que permita crear un fichero binario
 * a partir de un fichero binario origen.
 * Para hacer la prueba elige un fichero binario(ejem: una imagen)
 * El proceso de lectura/escritura (I/O) de los ficheros lo harás utilizando
 * ls clases FileInputStream y FileOutputStream
 * 
 */
public class Ejercicio11_LeerFicherobinario {
    public static void main(String[] args) {
        
        String rutaFOrigen = "src/main/resources/Fachada.JPG";
        String rutaFDestino = "src/main/resources/FachadaDestino.JPG";
        
        File fichOrigen = new File(rutaFOrigen);
        File fichDestino = new File(rutaFDestino);
        
        if (!fichOrigen.exists()){
            System.out.println("no existe el fichero origen");
        }else{
            
            try(FileInputStream fi = new FileInputStream(fichOrigen);
                FileOutputStream fo = new FileOutputStream(fichDestino);    ){

                int car;
                while((car = fi.read()) != -1){
                    fo.write(car);
                }                 

            }catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();   
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
           
            
            /*optimizado, leer bloques de 1024 bytes */
            /*  este codigo comentado no funciona al abrir el ficheor de salida
                con lo que no usarlo.
            try(FileInputStream fi = new FileInputStream(fichOrigen);
                FileOutputStream fo = new FileOutputStream(fichDestino);    ){

                byte[] buffer = new byte[1024];
                int byteLeidos;
                while((byteLeidos = fi.read()) != -1){
                    fo.write(buffer,0,byteLeidos);
                }                 
                
                // Asegurarse de que el flujo de salida se cierra 
                //y los datos se escriben correctamente
                fo.flush();
                
            }catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();   
            }catch(IOException ioe){
                ioe.printStackTrace();
            }           
             */
            
        }
       
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import modelo.Persona;

/**
 *
 * @author Angel FV
 */
public class Ejercicio15_LeerFicherodeObjetos {
    public static void main(String[] args) {
               
        String rutaFOrigen = "src/main/resources/fichbinarioorigen.dat";  
        File fichOrigen = new File(rutaFOrigen);
   
        Persona persona;
        if (!fichOrigen.exists()){
            System.out.println("error el fichero no existe");
        }else{
 
            try(FileInputStream fis = new FileInputStream(fichOrigen);
                ObjectInputStream ois = new ObjectInputStream(fis);){        
                
                //la otra forma de leer ficheros binarios. en este caso cuando
                //se llega al final del fichero salta una excepcion EOFException
                //y hay que tratarla detras del filenotfoundexception 
                //seguir el orden de tratamiento de las excepciones.
                while (true){
                    persona =  (Persona) ois.readObject();
                    System.out.println(persona.toString());
                }
    
            }catch(FileNotFoundException fnfe){
                    fnfe.printStackTrace();   
            }catch(EOFException fnfe){
                    System.out.println("final del fichero.");  
            }catch(IOException ioe){
                    ioe.printStackTrace();
            }catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
            }
        } 
    }    
}

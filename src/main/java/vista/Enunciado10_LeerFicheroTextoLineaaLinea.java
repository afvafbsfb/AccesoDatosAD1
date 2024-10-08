/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Angel FV
 * que permita crear un fichero de texto destino.txt
 * a partir de un fichero de texto origen.txt
 * Si el fichero de origen no existe, no lo creamos e 
 * indicamos que no existe. si el fichero origen no 
 * existe no realizará el proceso.
 * 
 * El proceso de lectura/escritura de los ficheros de texto
 * se hará linea a linea utilizando las clases FileReader y FileWriter,
 * así como BufferedReader y BufferedWriter.
 */
public class Enunciado10_LeerFicheroTextoLineaaLinea {
    public static void main(String[] args) {
    
        String rutaFOrigen = "src/main/resources/origen.txt";
        String rutaFDestino = "src/main/resources/destino.txt";
        
        File fichOrigen = new File(rutaFOrigen);
        File fichDestino = new File(rutaFDestino);
        
        if (!fichOrigen.exists()){
            System.out.println("el fichero origen no existe");
            System.out.println("lo creamos");
            try(FileWriter fw = new FileWriter(fichOrigen)){
                fichOrigen.getParentFile().mkdirs();
                fichOrigen.createNewFile();
                fw.write("linea1 fichero origen afv \n");
                fw.write("linea2 fichero origen afv \n");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }else{
            System.out.println("el fich origen ya existe");
        }
        
        try(FileReader fr = new FileReader(fichOrigen);
            FileWriter fw = new FileWriter(fichDestino, true);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter wr = new BufferedWriter(fw);){    
            
            String linea;
            linea = br.readLine();
            while (linea != null){
                wr.write(linea + "\n");
                linea = br.readLine();
            }
            
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
 
    }
}

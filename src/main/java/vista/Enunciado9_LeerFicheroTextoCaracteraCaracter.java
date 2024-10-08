/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Angel FV
 * 
 * leer y escribir ficheros de texto caracter a caracter. 
 * el fichero destino se creara a partir del fichero 
 * origen y escribiendo caracter a caracter
 * utilizando las clases FileReader y FileWriter
 * 
 */
public class Enunciado9_LeerFicheroTextoCaracteraCaracter {
    
    public static void main(String[] args) {
        
        String rutaFichOrigen = "src/main/resources/origen.txt";
        String rutaFichDestino = "src/main/resources/destino.txt";
        File ficheroOrigen = new File(rutaFichOrigen);
        File ficheroDestino = new File(rutaFichDestino);
        
        /* Sino existe el fichero, lo crea*/
        if (!ficheroOrigen.exists()){
            try{ 
                 System.out.println("el fichero origen " + ficheroOrigen.getPath() + " no existe. lo creamos");
                 ficheroOrigen.getParentFile().mkdirs();
                 ficheroOrigen.createNewFile();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        
        /*si declaro los FileReader y FileWriter en el Try, 
          no hace falta borrar los ficheros en la clausula 
          finally.
        */
        
        
        /* Escribo 2 lineas de texto en el fichero*/
        /* si declaro el objeto del FileWriter en el try() 
        /*       no necesito cerrar el objeto en la clausula finally
        */
        try(FileWriter fwOrigen = new FileWriter(ficheroOrigen);){
             
             fwOrigen.write("linea 1 para el fichero origen \n");
             fwOrigen.write("linea 2 para el fichero origen \n");
             
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        
        /*el texto del fichero de entrada, lo escribo en el 
          fichero de salida, caracter a caracter.
        */
        
        try(
            FileReader frOrigen = new FileReader(ficheroOrigen);
            FileWriter fwDestino = new FileWriter(ficheroDestino);){
            
            int caracter = frOrigen.read();
            while(caracter != -1){
                fwDestino.write(caracter);
                caracter = frOrigen.read();
            }
            
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    
        /*****************************************
         * leo los contenidos de los ficheros con la 
         * clase FileReader (lecturas en bloques de 1024 bytes)
         * lo que leo lo transformo a string <-- muy importante
         */
        
        try(
            FileReader frOrigen = new FileReader(ficheroOrigen);
            FileReader frDestino = new FileReader(ficheroDestino);){
            
            System.out.println("muestro el contenido del fichero origen, leo bloques 1024 bytes:");
            System.out.println("----------------------------------------------------------------");
            char[]buffer = new char[1024];
            
            String textoLeido;
            int bytesRead = frOrigen.read(buffer);
            int contador = 0;
            while(bytesRead != -1){
                textoLeido = new String(buffer,0,bytesRead);
                contador +=1;
                System.out.println("El Numero de read realizados es : " + contador + ". Leo en bloques de 1024 bytes. texto leido es : \n" + textoLeido);
                bytesRead = frOrigen.read(buffer);
            }
            
            
            System.out.println("muestro el contenido del fichero destino, leo bloques 1024 bytes:");
            System.out.println("-----------------------------------------------------------------");
            
            
            bytesRead = frDestino.read(buffer);
            contador = 0;
            while(bytesRead != -1){
                textoLeido = new String(buffer,0,bytesRead);
                contador +=1;
                System.out.println("El Numero de read realizados es : " + contador + ". Leo en bloques de 1024 bytes. texto leido es : \n" + textoLeido);
                bytesRead = frDestino.read(buffer);
            }
            
            System.out.println("muestro el contenido del fichero destino, leo bloques 1024 bytes:");
            System.out.println("-----------------------------------------------------------------");
            
            bytesRead = frDestino.read(buffer);
            contador = 0;
            while(bytesRead != -1){
                textoLeido = new String(buffer,0,bytesRead);
                contador +=1;
                System.out.println("El Numero de read realizados es : " + contador + ". Leo en bloques de 1024 bytes. texto leido es : \n" + textoLeido);
                bytesRead = frDestino.read(buffer);
            }            
            
            
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        
         /*****************************************
         * leo los contenidos de los ficheros con la 
         * clase FileReader (lecturas en bloques de 1 bytes)
         * lo que leo lo transformo a string
         * 
         * uso finally para cerrar los ficheros y su instanciación en el try,
         * recordar que se inicializan a null antes del try.
         */
         
        FileReader frOrigen=null;
        FileReader frDestino=null;
        try{           
            frOrigen = new FileReader(ficheroOrigen);
            frDestino = new FileReader(ficheroDestino);
            
            System.out.println("muestro el contenido del fichero origen, leo caracter a caracter:");
            System.out.println("-----------------------------------------------------------------");
            char[]buffer = new char[1];
            
            String textoLeido;
            int bytesRead = frOrigen.read(buffer);
            int contador = 0;
            while(bytesRead != -1){
                textoLeido = new String(buffer,0,bytesRead);
                contador +=1;
                System.out.print(textoLeido);
                bytesRead = frOrigen.read(buffer);
            }
            
            System.out.println("El Numero de read realizados es : " + contador );
            
            System.out.println("muestro el contenido del fichero destino, leo caracter a caracter:");
            System.out.println("-----------------------------------------------------------------");
            
   
            bytesRead = frDestino.read(buffer);
            contador = 0;
            while(bytesRead != -1){
                textoLeido = new String(buffer,0,bytesRead);
                contador +=1;
                System.out.print(textoLeido);
                bytesRead = frDestino.read(buffer);
            }
            
            System.out.println("El Numero de read realizados es : " + contador );
  
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            try{
                frOrigen.close();
                frDestino.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }      
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Angel FV
 */
public class AD1EjercComplem9 {

    public static void main(String[] args) {
        String rutaRelativaOrigen = "src/main/resources/origen.txt";
        File objetoFileOrigen = new File(rutaRelativaOrigen);
        
        String rutaRelativaDestino = "src/main/resources/destino.txt";
        File objetoFileDestino = new File(rutaRelativaDestino);
        
        if(objetoFileOrigen.exists()){
            System.out.println("ya existe el fichero src/main/resources/origen.txt");
        }else{  
            FileWriter fwOrigen=null;
            try{
                    // Crear el archivo y sus directorios padres si es necesario
                    objetoFileOrigen.getParentFile().mkdirs();
                    objetoFileOrigen.createNewFile();
                    
                    System.out.println("Se ha creado el fichero : " + objetoFileOrigen.getPath());
                    
                    fwOrigen = new FileWriter(rutaRelativaOrigen, true);
                    //escribo texto
                    fwOrigen.write("esta es la primera linea del fich origen \n");
                    fwOrigen.flush();
                    fwOrigen.write("esta es la segunda linea del fich origen \n");
                    fwOrigen.flush();
                    
            }catch(IOException ioe){
                ioe.printStackTrace();
            }finally{
                try {
                    fwOrigen.close();
                 } catch (IOException ex) {
                     ex.printStackTrace();
                 }
            }
        }
              
        try{
            if(!objetoFileDestino.exists()){
                        // Crear el archivo y sus directorios padres si es necesario
                        objetoFileDestino.getParentFile().mkdirs();
                        objetoFileDestino.createNewFile();

                        System.out.println("Se ha creado el fichero : " + objetoFileDestino.getPath());
            }
            FileReader frOrigen = new FileReader(rutaRelativaOrigen);
            
            /*en el fichero destino siempre machaco el contenido, no le pongo el parametro append a true*/
            //FileWriter fwDestino = new FileWriter(rutaRelativaDestino, true);
            FileWriter fwDestino = new FileWriter(rutaRelativaDestino);

            /*
            int car;
            fwDestino.write("FICHERO COPIADO\n");
            
            
            while ((car = frOrigen.read()) != -1) {
              fwDestino.write(car);            
           }
            */

            int car;
            fwDestino.write("FICHERO COPIADO\n");
            car = frOrigen.read();
            
            while (car != -1) {
              fwDestino.write(car);   
              car = frOrigen.read();
           }
           
            
            frOrigen.close();
            fwDestino.close();
             
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        System.out.println(" ");
        System.out.println("mostrar por pantalla el contenido del primer fichero");
        System.out.println("====================================================");
        
        try{
            FileReader frOrigen = new FileReader(rutaRelativaOrigen);

            
            char[] buffer = new char[1024];
            String textoLeido;
            int bytesRead = frOrigen.read(buffer);
            int numeroDeLecturas = 0;
            
            while(bytesRead != -1){
                    numeroDeLecturas += 1;
                    
                    //leo texto
                    textoLeido = new String(buffer,0,bytesRead);
                    System.out.println("textoLeido del fichero origen : " + textoLeido + "\n");
                    
                    bytesRead = frOrigen.read(buffer);
            } 
            
            System.out.println("numero de lecturas realizadas, caracter a caracter, en el fichero origen es : " + numeroDeLecturas + "\n");
 
            frOrigen.close();
            
            FileReader frDestino = new FileReader(rutaRelativaDestino);

            
            buffer = new char[1024];
           
            bytesRead = frDestino.read(buffer);
            numeroDeLecturas = 0;
            
            while(bytesRead != -1){
                    numeroDeLecturas += 1;
                    
                    //leo texto
                    textoLeido = new String(buffer,0,bytesRead);
                    System.out.println("textoLeido del fichero destino : " + textoLeido + "\n");
                    
                    bytesRead = frDestino.read(buffer);
            } 
            
            System.out.println("numero de lecturas realizadas, caracter a caracter, en el fichero destino es : " + numeroDeLecturas + "\n");
 
            frDestino.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

            
            
            
           

        
    }
}

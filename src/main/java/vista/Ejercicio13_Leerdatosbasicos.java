/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Angel FV
 * 
 * FileInputStream fis = new FileInputStream(fichOrigen);: 
 * Este objeto abre el archivo binario desde el que vas a leer los datos. 
 * FileInputStream se utiliza para leer archivos en formato binario.
 * DataInputStream dis = new DataInputStream(fis);: DataInputStream se utiliza 
 * para leer datos primitivos de Java (como int, double, UTF strings, etc.) 
 * que fueron previamente escritos en formato binario.
 * 
 * 
 * dis.available() != 0: Este método devuelve la cantidad de bytes que quedan 
 * disponibles para ser leídos sin bloquear. 
 * Si el resultado es diferente de 0, significa que todavía hay datos 
 * en el archivo, por lo que el bucle seguirá ejecutándose. 
 * En este caso, el ciclo sigue leyendo hasta que no queden más bytes por leer.

* Es importante notar que available() no siempre es la mejor opción para 
* determinar el final de un archivo binario, 
* pero puede funcionar en este caso si estás 
* seguro de la estructura de los datos.
 * 
 * Este proceso asegura que los datos binarios se lean y muestren correctamente, 
 * siempre y cuando el archivo fue escrito con la misma estructura 
 * (es decir, con writeUTF, writeInt y writeDouble en ese orden).
 */
public class Ejercicio13_Leerdatosbasicos {
    public static void main(String[] args) {
               
        String rutaFOrigen = "src/main/resources/fichorigen.dat";  
        File fichOrigen = new File(rutaFOrigen);
   
        if (!fichOrigen.exists()){
            System.out.println("error el fichero no existe");
        }else{
 
            try(FileInputStream fis = new FileInputStream(fichOrigen);
                DataInputStream dis = new DataInputStream(fis);){        
                
                //"\t"  se utiliza para meter un espacio en blanco o tabulacion
                while (dis.available()!=0){
                    System.out.println("Nombre: "+dis.readUTF()+ 
                            "\tEdad:"+dis.readInt() + "\tSalario: "+dis.readDouble());
                }
    
            }catch(FileNotFoundException fnfe){
                    fnfe.printStackTrace();   
            }catch(IOException ioe){
                    ioe.printStackTrace();
            }
        } 
    }    
}

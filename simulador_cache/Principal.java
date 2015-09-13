/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 *
 * @author Matheus
 */
public class Principal {
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("Bienvenidos al Simulador de Cache");
        String archivo="";
        String tipo="";
        String entradas="";
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            // if(args!=null){
                if(args.length!=3){
                    System.exit(0);
                }
                archivo=args[0];
                tipo=args[1];
                entradas=args[2];
                System.out.println(archivo+" "+tipo+" "+entradas);
                try{
                    int cantidad = Integer.parseInt(entradas);
                    if(tipo.contains("LRU")){
                        file = new File(archivo);
                        if(file.exists()){
                          fr = new FileReader(file);
                          br = new BufferedReader (fr);
                         try{
                            LruCacheNormal lru = new LruCacheNormal(cantidad);
                            String linea;
                            int aum=0;
                            while((linea=br.readLine())!=null){
                               lru.put(aum, linea);
                               aum++;
                            }

                            System.out.println((lru.getMiss()/lru.getTotalproccess())*100);
                         }catch(Exception e){
                            System.out.println(e);
                         }finally{
                            // En el finally cerramos el fichero, para asegurarnos
                            // que se cierra tanto si todo va bien como si salta 
                            // una excepcion.
                            try{                    
                               if( null != fr ){   
                                  fr.close();     
                               }                  
                            }catch (Exception e2){}
                         }
  			                }else{
                  				System.out.println("else");				
                  			}
                    }
                }catch(Exception io){
                
                }
            
            
        }catch(Exception io){
        }
    }
    
}

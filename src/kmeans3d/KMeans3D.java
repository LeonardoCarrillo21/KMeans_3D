/*
    LEONARDO JAVIER CARRILLO MARTINEZ
    ARTURO TREJO JUAREZ

*/
package kmeans3d;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KMeans3D {

    //datos de la ventana
    public int k,clases,items,planoX,planoY,planoZ,cuentaPuntos;
    public String opcion;  //opcion de calcular distancia   
    
    //puntos  control
    public double x,y,z; //coordenadas del nuevo punto (se actualiza cada rato)
    public ArrayList<Item> itemsArray;
    public ArrayList<Item> nuevosPuntos;
    public ArrayList<Integer> indicesRepetidos;
    public ArrayList<ArrayList<Item>> puntosDivididos = new ArrayList<ArrayList<Item>>();
    public double miExponente;
    //clusters y distancias a los puntos
    public double [][] distancias; // [cluster][distancia]
    public double [][] coordenadasDeCentroides;
    public double [][] viejasCoordenadas;
    //objetos  utiles
    public Random r;
    
    public KMeans3D(int k, int items,double x,double y, double z,String opcion){
    
        this.k = k;
        this.x=x;
        this.y =y;
        this.z=z;
        this.items = items;
        this.planoX=1000;
        this.planoY=1000;
        this.planoZ=1000;
        this.cuentaPuntos=0;
        
        
        //usadas en calculaNuevosCentroides
        this.itemsArray = new ArrayList();
        this.nuevosPuntos = new ArrayList();
        
        
        this.coordenadasDeCentroides = new double[k][3];
        this.viejasCoordenadas = new double[k][3];
        this.indicesRepetidos = new ArrayList<Integer>();
        
        //usada en distancias
        this.distancias = new double[k][items];
        
        this.r = new Random();
    }//Constructor
    
    public void seleccionDistancia(String opc){
    
        switch(opc){
            case "euclideana" -> {
                calculaDistancia1();
            }
            case "manhatan" -> {
                calculaDistancia2();
            }
            case "fraccionaria" -> {
                calculaDistancia3();
            }
            case "cuadratica" -> {
                calculaDistancia4();
            }
            case "inventada"->{
                calculaDistancia5(this.miExponente);
            }
            default -> {
            }
                
        }//switch
    
    }//seleccion
    
    public void generaPuntos(){
    
        for (int i = 0; i < this.items; i++) {
            
            //coordenadas de cada nuevo punto aleatorio
            double a = r.nextDouble(this.planoX);
            double b = r.nextDouble(this.planoY);
            double c = r.nextDouble(this.planoZ);
            
            Item nuevo = new Item("",a,b,c,Color.BLACK);
            itemsArray.add(nuevo);
        }//for 
    
    }//generaPuntos
    
    public double[] getVector(ArrayList<Item> a,int indice){
        
        //System.out.println("en getVector");
        
        double[] auxiliar = new double[a.size()];
        //System.err.println("tam: "+a.size());
            
        for (int i = 0; i < a.size(); i++) {
            switch (indice) {
                case 0:
                    auxiliar[i] = a.get(i).x;
                    break;
                case 1:
                    auxiliar[i] = a.get(i).y;
                    break;
                case 2:
                    auxiliar[i] = a.get(i).z;
                    break;
                default:
                    break;
            }
        }

        return auxiliar;
    }
    
    public void divideItems(){
    
        /*
            evalua por cada cluster si cada punto le pertenece , lo agrega al unColor
            y por cada cluster se guarda un nuevo arreglo en el puntosDivididos
        
        */
        for (int i = 0; i < cuentaPuntos; i++) {

            ArrayList<Item> unColor = new ArrayList<Item>();
            for (int j = 0; j < items; j++) {                
                if(itemsArray.get(j).centroideMasCercano == i)
                    unColor.add(itemsArray.get(j));

            }
            puntosDivididos.add(unColor);
            
        }
    
    }
    
    public void recalculaPuntos(){
        
        /*
            para calcular los centroides necesitamos:
                1) calcular la distancia entre todos los puntos hacia los centroides
                2) calcular el centroide mas cercano
                3) calcular la coordenada cuadrada promedio de las clases mas cercanas a un centroide
                4) guardar las nuevas coordenadas del centroide 
         */
        System.out.println("\t recalculando");
        
        seleccionDistancia(this.opcion);
        
        calculaNuevoCentroides();
        //impresionDeControl();
    }
    
    public void actualizaCoordenadasDeCentroides(){
    
        if(indicesRepetidos.isEmpty()){
        } else {
            for (int i = 0; i < k-1; i++) {
                
                if(indicesRepetidos.get(0)==i){
                
                    for (int j = 0; j < ((k-1)-i); j++) {
                        
                        coordenadasDeCentroides[i+j][0] = coordenadasDeCentroides[i+j+1][0];
                        coordenadasDeCentroides[i+j][1] = coordenadasDeCentroides[i+j+1][1];
                        coordenadasDeCentroides[i+j][2] = coordenadasDeCentroides[i+j+1][2];
                    }
                    
                }
                
            }
            indicesRepetidos.remove(0);
        }
        
    
    }
    
    public void calculaDistancia1(){
        
        //distancia euclidiana 
        //System.err.println("Distancia euclidiana");
        
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                distancias[i][j] = Math.sqrt(
                        Math.pow(coordenadasDeCentroides[i][0] - itemsArray.get(j).x, 2)
                        + Math.pow(coordenadasDeCentroides[i][1] - itemsArray.get(j).y, 2)
                        + Math.pow(coordenadasDeCentroides[i][2] - itemsArray.get(j).z, 2)
                );
                //System.out.println("calculado: "+distancias[i][j]);
            }
            //System.out.println("Distancia del primer punto: " + distancias[i][0]);
        }
        
        
    }
    public void calculaDistancia2(){
        
        //System.err.println("Distancia manhatan");
        // distancia manhatan 
        
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                distancias[i][j] = 
                        Math.abs(coordenadasDeCentroides[i][0] - itemsArray.get(j).x)+
                        Math.abs(coordenadasDeCentroides[i][1] - itemsArray.get(j).y)+
                        Math.abs(coordenadasDeCentroides[i][2] - itemsArray.get(j).z);
                
                //System.out.println("calculado: "+distancias[i][j]);
            }
            //System.out.println("Distancia del primer punto: " + distancias[i][0]);
        }
        
    }
    public void calculaDistancia3(){
        
        //distancia fraccionaria 
        //System.err.println("Distancia fraccionaria .75");
        
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                distancias[i][j] =Math.pow( 
                        Math.pow(Math.abs(coordenadasDeCentroides[i][0] - itemsArray.get(j).x),.75)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][1] - itemsArray.get(j).y),.75)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][2] - itemsArray.get(j).z),.75),1/.75);
                
                //System.out.println("calculado: "+distancias[i][j]);
            }
            //System.out.println("Distancia del primer punto: " + distancias[i][0]);
        }
        
        
        
    }
    public void calculaDistancia4(){
        
        System.err.println("Distancia Cuadratica");
        //cubica
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                distancias[i][j] =Math.pow( 
                        Math.pow(Math.abs(coordenadasDeCentroides[i][0] - itemsArray.get(j).x),4.0)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][1] - itemsArray.get(j).y),4.0)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][2] - itemsArray.get(j).z),4.0),1.0/4.0);
                
                //System.out.println("calculado: "+distancias[i][j]);
            }
            //System.out.println("Distancia del primer punto: " + distancias[i][0]);
        }
        
    }
    public void calculaDistancia5(double e){
        
        //System.err.println("Inventada");
        //inventada 1
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                distancias[i][j] =Math.pow( 
                        Math.pow(Math.abs(coordenadasDeCentroides[i][0] - itemsArray.get(j).x),(double)e)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][1] - itemsArray.get(j).y),(double)e)+
                        Math.pow(Math.abs(coordenadasDeCentroides[i][2] - itemsArray.get(j).z),(double)e),1.0/(double)e);
                
                //System.out.println("calcule: " + distancias[i][j]);
                //System.out.println("calculado: "+distancias[i][j]);
            }
            //System.out.println("Distancia del primer punto: " + distancias[i][0]);
        }
        
    }

    
    public void calculaNuevoCentroides(){
        //guardamos para la comparacion y detener el algoritmo
        
        
        for (int i = 0; i < cuentaPuntos; i++) {
            
            viejasCoordenadas[i][0] = coordenadasDeCentroides[i][0];
            viejasCoordenadas[i][1] = coordenadasDeCentroides[i][1];
            viejasCoordenadas[i][2] = coordenadasDeCentroides[i][2];
        }
        
        
//        for (int i = 0; i < cuentaPuntos; i++) {
//            
//            System.out.println( (i+1) + " viejas: " + viejasCoordenadas[i][0] + "," + viejasCoordenadas[i][1] + "," + viejasCoordenadas[i][2]);
//            System.out.println( (i+1) + " nuevas: " + coordenadasDeCentroides[i][0] + "," + coordenadasDeCentroides[i][1] + "," + coordenadasDeCentroides[i][2]);
//        }
        
        //imprimeDistancias();
        
        for (int i = 0; i < items; i++) {

            int indiceDelMasCercano = 0;
            for (int j = 0; j < cuentaPuntos; j++) {

                if (distancias[indiceDelMasCercano][i] > distancias[j][i]) {

                    indiceDelMasCercano = j;

                }

            }

            itemsArray.get(i).centroideMasCercano = indiceDelMasCercano;
            itemsArray.get(i).color = nuevosPuntos.get(indiceDelMasCercano).color;
        }

        
        //actualiza la nuevas coordenadas del centroide
        //3) calcular la coordenada cuadrada promedio de las clases mas cercanas a un centroide
        int sumaX = 0;
        int sumaY = 0;
        int sumaZ = 0;
        int coincidentes = 0;
        
        //recorremos todos los items K veces
        for (int i = 0; i < cuentaPuntos; i++) {

            for (int j = 0; j < items; j++) {

                if (itemsArray.get(j).centroideMasCercano == i) {
                    sumaX += itemsArray.get(j).x;
                    sumaY += itemsArray.get(j).y;
                    sumaZ += itemsArray.get(j).z;
                    coincidentes++;
                }
                
            }
            if(coincidentes<1){
                    
                cuentaPuntos--;
                nuevosPuntos.remove(i);
                indicesRepetidos.add(i);
                actualizaCoordenadasDeCentroides();
                JOptionPane.showMessageDialog(new JFrame(),"Se encontro un punto \n muy cercano a otro\n Por lo que se elimino");
                
            }else{
            coordenadasDeCentroides[i][0] = sumaX / coincidentes;
            coordenadasDeCentroides[i][1] = sumaY / coincidentes;
            coordenadasDeCentroides[i][2] = sumaZ / coincidentes;
            }
            sumaX = 0;
            sumaY = 0;
            sumaZ = 0;
            coincidentes = 0;
        }
    
        for (int i = 0; i < cuentaPuntos; i++) {
            
            nuevosPuntos.get(i).x = coordenadasDeCentroides[i][0];
            nuevosPuntos.get(i).y = coordenadasDeCentroides[i][1];
            nuevosPuntos.get(i).z = coordenadasDeCentroides[i][2];
            
        }
//        for (int i = 0; i < cuentaPuntos; i++) {
//            
//            System.out.println( (i+1) + " viejas: " + viejasCoordenadas[i][0] + "," + viejasCoordenadas[i][1] + "," + viejasCoordenadas[i][2]);
//            System.out.println( (i+1) + " nuevas: " + coordenadasDeCentroides[i][0] + "," + coordenadasDeCentroides[i][1] + "," + coordenadasDeCentroides[i][2]);
//        }
    }
    
    public int hayCentrados(){
        
        int centrados=0;
        
        for (int i = 0; i < cuentaPuntos; i++) {
            
            if(
                    Math.abs( viejasCoordenadas[i][0] - coordenadasDeCentroides[i][0] )<1 &&
                    Math.abs( viejasCoordenadas[i][1] - coordenadasDeCentroides[i][1] )<1 &&
                    Math.abs( viejasCoordenadas[i][2] - coordenadasDeCentroides[i][2] )<1                     
            )
            centrados++;
            
        }
        
        System.err.println("Centrados: " + centrados);
        return centrados;
    }
    
    public void imprimeDistancias(){
    
        for (int i = 0; i < cuentaPuntos; i++) {
            
            for (int j = 0; j < items; j++) {
                    
                System.out.println( "distancia " + i +"->" + j + ":" + distancias[i][j]);
                
            }
            
        }
        
    }
    
    public void impresionDeControl(){
    
        System.out.println("Centroides ");
        for (int i = 0; i < cuentaPuntos; i++) {

            System.out.println("cluster "+ i + ":" + coordenadasDeCentroides[i][0] + "," + coordenadasDeCentroides[i][1] + "," + coordenadasDeCentroides[i][2]);

        }
    
    }
    
}//clase

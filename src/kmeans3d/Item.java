/*
    LEONARDO JAVIER CARRILLO MARTINEZ
    ARTURO TREJ0 JUAREZ
*/
package kmeans3d;

import java.awt.Color;


public class Item {
    
    public String tipo = "";
    public Color color;
    public int centroideMasCercano; // 0 1 2 3 
    public double x,y,z;
    public double distancia; //distancia al centroide mas cercano

    public Item(String tipo, double x, double y, double z,Color color ){
        
        this.x = x;
        this.y = y;
        this.z=z;
        this.tipo = tipo;
        this.color = color;
        this.centroideMasCercano=0;
    
    }
    
}

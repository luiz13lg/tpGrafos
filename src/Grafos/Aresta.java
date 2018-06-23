/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

/**
 *
 * @author Giulia
 */
public class Aresta implements Comparable<Aresta>{
    private int peso;
    private Vertice vertice1;
    private Vertice vertice2;

    public Aresta(){
        
    }
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public void setVertice1(Vertice vertice1) {
        this.vertice1 = vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public void setVertice2(Vertice vertice2) {
        this.vertice2 = vertice2;
    }
    
    @Override
    public int compareTo(Aresta o) {
        if(this.peso == o.peso){
            return 0;
        }
        else if(this.peso < o.peso){
            return -1;
        }
        else return 1;
        
        
    }
    
    
    
    
}

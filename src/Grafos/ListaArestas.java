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
public class ListaArestas {
    private int peso;
    private int flag = 0;
    private int origem;
    private int destino;
    
    
    
    public ListaArestas(int origem, int destino, int peso){
        this.destino = destino;
        this.origem = origem;
        this.peso = peso;        
    }
    
    public int compareTo(ListaArestas o){
        if(this.peso > o.getPeso())
            return 1;
        else if(this.peso < o.getPeso())
            return -1;
         
         return 0;      
    }
    
    public int getPeso(){
        return peso;
    }
    
    public int getOrigem(){
        return origem;
    }
    
    public String toString(){
        return "Origem:" + origem + " --> Destino:" + destino + " --> Peso: "+peso;
    }

    public int getDestino(){
        return destino;
    }

    public int getFlag(){
        return flag;
    }

    public void setFlag(int flag){
        this.flag = flag;
    }
}

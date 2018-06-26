/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.classe;

import Grafos.Vertice;
import java.util.ArrayList;

/**
 *
 * @author AlphaLegends
 */
public class CaminhoMinimoDijkstra {
    int[] distancia = null;
    int[] pai = null;

    public CaminhoMinimoDijkstra() {
    }

    public int[] getDistancia() {
        return distancia;
    }

    public int[] getPai() {
        return pai;
    }
    
    
    
    public void relaxa(int u,int v,int peso,int[] d,int[] pai){
        if(d[v]>(d[u]+(peso))){
            d[v] = d[u] + peso;
            pai[v] = u;
        }
    }
    
    public boolean vazio(int[] Q){
        for(int i=0;i<Q.length;i++){
            if(Q[i] == 0)return false;
        }
        return true;
    }
    
    public int extrairMinimo(int[] Q,int[] chave){
        int menor = Integer.MAX_VALUE;
        int vertice = 0;
        for(int i=0;i<Q.length;i++){
            if(Q[i]==0 && chave[i]<menor){
                menor = chave[i];
                vertice = i;
            }
        }
        Q[vertice] = 1;
        return vertice;
    }
    
    public void caminhoMinimo(Vertice[] vertices,int raiz){
        int[] pai = new int [vertices.length];
        int[] d = new int[vertices.length];
        int[] visitados = new int[vertices.length];
        int u;
        

        for(int i=0;i<vertices.length;i++){//inicializacao
            d[i]=Integer.MAX_VALUE;
            pai[i]=-1;
            visitados[i]=0;
        }
        d[raiz]=0; 
        
        
        while(!vazio(visitados)){
            u = extrairMinimo(visitados,d);//obtencao da menor aresta entre os adjacentes
            
            ArrayList<String> lista = vertices[u].getAdjacencia();
            for(int i=0;i<lista.size();i++){
                String[] msg = lista.get(i).split(" ");
 
                int v = Integer.parseInt(msg[0]);
                    relaxa(u,v,Integer.parseInt(msg[1]),d,pai);
            }
        }
        this.distancia = d;
        this.pai = pai;
    }
}

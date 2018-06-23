/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.classe;

import Grafos.Aresta;
import Grafos.Vertice;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Giulia
 */
public class AGMKruskal {
    private ArrayList<ArrayList<Vertice>> agm;
    private ArrayList<Aresta> conjuntoArestas;
    
    
    public AGMKruskal(Vertice [] vertices){
        int nVertice;
        conjuntoArestas = new ArrayList<Aresta>();
        iniLista(vertices);
        
        nVertice = vertices.length;
        
        for(int i = 0; i < nVertice; i++){
            for(String adj : vertices[i].getAdjacencia()){
                Aresta a = new Aresta();
                String vertAdj[] = adj.split(" ");
                int vertice1 = Integer.parseInt(vertAdj[0]);
                int peso = Integer.parseInt(vertAdj[1]);
                
                Vertice aux = buscaAdj(vertices, vertice1);
                
                a.setPeso(peso); //adicionei o peso
                a.setVertice1(vertices[i]); //adicionei um dos vertices
                a.setVertice2(aux); //adicionei o outro
                //Não faz diferença cada vertice estar na setVertice1 ou setVertice2
                //o vertice b chega em c e o vertice c em b
                //mas a aresta é so uma
                conjuntoArestas.add(a);
            }
        }
        Collections.sort(conjuntoArestas);
    }
    
    public Vertice buscaAdj(Vertice[] vertices, int vert1){
        for(int i = 0; i < vertices.length; i++){
            if(vertices[i].getNumero() == vert1){
                return vertices[i];
            }
        }
        return null;
    }
    
    public void iniLista(Vertice[] vertices){
        Vertice aux = new Vertice();
        agm = new ArrayList<ArrayList<Vertice>>();
        for(int i = 0; i < vertices.length; i++){
            ArrayList<Vertice> vertice = new ArrayList<Vertice>();
            aux = vertices[i];
            vertice.add(aux);
            agm.add(vertice);
        }
        
    }
    
    public ArrayList<Vertice> busca(Vertice a){
        for(int i = 0; i < agm.size(); i++){
            if(agm.get(i).contains(a)){
                return agm.get(i);
            }
        }
        return null;
    }
    
    public void Results(){
        int pesoFinal = 0;
        Vertice u, v;
        
        for(int i = 0; i < conjuntoArestas.size(); i++){
            ArrayList<Vertice> a = busca(conjuntoArestas.get(i).getVertice1());
            ArrayList<Vertice> b = busca(conjuntoArestas.get(i).getVertice2());
            if(a != b){
                a.addAll(b);
                pesoFinal += conjuntoArestas.get(i).getPeso();
                agm.remove(b);
            }
            else{
                conjuntoArestas.remove(i);
            }
        }
        
        
        System.out.println("Imprimindo o resultado");
        for(int i = 0; i < agm.size(); i++){
            ArrayList<Vertice> aux = agm.get(i);
            for(int j = 0; j < aux.size(); j++){
                System.out.println("Vertice" + aux.get(j).getNumero());
            }
        }
    }
    
    
    
}

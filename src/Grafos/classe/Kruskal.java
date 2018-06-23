/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.classe;

import Grafos.Vertice;
import static java.lang.Integer.MAX_VALUE;
import java.util.ArrayList;

/**
 *
 * @author Giulia
 */
public class Kruskal{

    private Vertice[] arvore;
    private Vertice[] conjuntoArestas;
    private String sol;
    
    public Kruskal(){
        
    }
    
    public Kruskal(Vertice[] vertices){
        int n, tamanho;
        n = vertices.length;
        tamanho = (n*(n-1))/2;
        conjuntoArestas = new Vertice[2*tamanho];
        inicializaVetor(conjuntoArestas);
        iniKruskal(vertices);
    }
    
    public void inicializaVetor(Vertice [] vetor){
        for(int i = 0; i < vetor.length; i++){
            vetor[i] = new Vertice();
            vetor[i].setPeso(MAX_VALUE);
        }
    }
    
    private void iniKruskal(Vertice[] vertices){
        Vertice aux;
        int tamanho, nVertice, aux1;
        
        aux1 = 0;
        nVertice = vertices.length;
        tamanho = (nVertice*(nVertice - 1))/2; //numero maximo de arestas
        
//        Vertice[] aLinha = new Vertice[2*tamanho];
//        inicializaVetor(aLinha);
        
        aux = new Vertice();
        for(int i = 0; i < nVertice; i++){
            for(String adj : vertices[i].getAdjacencia()){
                String vertAdj[] = adj.split(" ");
                int pos = Integer.parseInt(vertAdj[0]);
                int peso = Integer.parseInt(vertAdj[1]);
                aux.setNumero(pos);
                conjuntoArestas[aux1].setNumero(vertices[i].getNumero()); //armazeno o numero
                conjuntoArestas[aux1].setPeso(peso); //o peso da aresta
                conjuntoArestas[aux1].setAdj(aux); //e o vertice que a aresta chega
                
                System.out.println(conjuntoArestas[aux1].getAdj().getNumero());
                aux1++;
            }
        }
        System.out.println("AQUI: "+conjuntoArestas[0].getAdj().getNumero() + "Peso" + conjuntoArestas[0].getPeso());
        bubbleSortClassico(conjuntoArestas);
       for(int i = 0; i < conjuntoArestas.length; i++){
            System.out.println("Vertice: "+ conjuntoArestas[i].getNumero()+" Adj: "+conjuntoArestas[i].getAdj().getNumero());
       }
      System.out.println("fim");

    }
    
    private void bubbleSortClassico(Vertice vetor[]) { //bubble sort sem melhorias
        int n = vetor.length;
        Vertice aux;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < n - j - 1; i++) {
                if (vetor[i].getPeso() > vetor[i + 1].getPeso()) { //compara todas as posições do vetor, se sucessor menor, troca de posição
                    aux = vetor[i];
                    //vetor[i] = vetor[i + 1];
                    //vetor[i + 1] = aux;
                    
                    vetor[i].setNumero(vetor[i+1].getNumero());
                    vetor[i].setPeso(vetor[i+1].getPeso());
                    vetor[i].setAdj(vetor[i+1].getAdj());
                    
                    vetor[i+1].setNumero(aux.getNumero());
                    vetor[i+1].setPeso(aux.getPeso());
                    vetor[i+1].setAdj(aux.getAdj());
                }
            }
        }
        System.out.println("Terminou de ordenar");
    }
    
    private int pertenceArvore(Vertice arvore, int vertice){
        for(int i = 0; i < arvore.getArvoreKrus().size(); i++){
            if(arvore.getArvoreKrus().get(i) == null) return 0;
            if(arvore.getArvoreKrus().get(i) == vertice)
                return 1; // ja pertence a arvore
        }
        return 0; //não pertence a arvore
    }
    
    public void AGM_Kruskal(){
        Vertice u, v;
        //u = arvore[0];
        int pesoFinal = 0;
        
        for(int i = 0; i < conjuntoArestas.length; i++){
            u = conjuntoArestas[i];
            v = conjuntoArestas[i].getAdj();
            conjuntoArestas[i].iniArvore();
            
                        
            if(pertenceArvore(u, v.getNumero()) == 0){
                v.addArvore(u.getNumero());
                u.addArvore(v.getNumero());
                pesoFinal = conjuntoArestas[i].getPeso();
                sol += Integer.toString(u.getNumero()) + "-"+ Integer.toString(v.getNumero())+";";
            }
        }
        System.out.println(sol);
    }
    
    public String getSol(){
        return sol;
    }
    
}


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
 * @author Luiz
 */
public class BFS {
    private ArrayList <Vertice> fila = new ArrayList <Vertice>();
    String resultado = "";
    
    public void addFila(Vertice vert){
        fila.add(vert);
    }
    
    public void BFS(Vertice grafo[], Vertice raiz){
        resultado = "";
        
        for(Vertice vert : grafo){      //iniciando todos os vertices
            vert.setCor(0);             //
            vert.setDistancia(-1);      //
            vert.setPredecessor(null);  //
        }
        raiz.setCor(1);                 //iniciando o vertice raiz
        raiz.setDistancia(0);           //
        raiz.setPredecessor(null);      //

        addFila(raiz);
        resultado += "Vertice[" + raiz.getNumero() + "] " + "> " + raiz.getDistancia()+"\n\n";
        while(!fila.isEmpty()){
            Vertice u = Desenfileira();
            
            for(String v : u.getAdjacencia()){              //array com vertices e seus pesos. Ex.: [vert peso; 1 3; 3 4]
                String vertice[] = v.split(" ");            //separando o numero do vertice do seu peso. [1;3]        
                int posicao = Integer.parseInt(vertice[0]); //transformando a posicao em int [1];
                if(grafo[posicao].getCor() == 0){
                    grafo[posicao].setCor(1);
                    grafo[posicao].setDistancia(
                        u.getDistancia()+1);
                    grafo[posicao].setPredecessor(u);
                    
                    resultado += "Vertice[" + grafo[posicao].getNumero() + "] > Distância: " + grafo[posicao].getDistancia() + "\n";
                    
                    fila.add(grafo[posicao]);
                }
            } u.setCor(2);
        }
    }
    
    private Vertice Desenfileira(){
        Vertice v = fila.get(0);
        fila.remove(0);
        return v;
    }
    
    public void verificaCaminho (Vertice grafo[], Vertice u, Vertice v){
        resultado += "Vertice[" + u.getNumero() +"] >>> " + "Vertice["+ v.getNumero() +"]\n\n";;
        for(Vertice todos : grafo){
            todos.setCor(0);
            todos.setDistancia(-1);
            todos.setPredecessor(null);
        }
        u.setCor(1);
        u.setDistancia(0);
        
        addFila(u);   
        while(!fila.isEmpty()){
            Vertice x = Desenfileira();
            for(String adj : x.getAdjacencia()){                //array com vertices e seus pesos. Ex.: [vert peso; 1 3; 3 4]
                String vertAdj[] = adj.split(" ");              //separando o numero do vertice do seu peso. [1;3]
                int posicao = Integer.parseInt(vertAdj[0]);     //transformando a posicao em int [1];
                if(grafo[posicao].getCor() == 0){
                    grafo[posicao].setCor(1);
                    grafo[posicao].setDistancia(x.getDistancia()+1);
                    grafo[posicao].setPredecessor(x);
                    
                    addFila(grafo[posicao]);
                }
            }
            if(x == v){
                impressaoAdj(x);
                return;
            }
        }
        resultado += "O caminho não é possível! :(";
    }
    
    private void impressaoAdj (Vertice v){
        ArrayList <String> result = new ArrayList <String>();
        Vertice x = v;
        Vertice impressao = v.getPredecessor();
        while (impressao != null){
            result.add("Vertice[" + impressao.getNumero() +"] > " + "Vertice["+ x.getNumero() +"]\n");
            x = x.getPredecessor();
            impressao = x.getPredecessor();            
        }
        for(int aux = result.size()-1 ; aux >= 0 ; aux--)
            resultado += result.get(aux);
    }
        
    public void BFSMatriz(int raiz, int matriz[][], int ordemMatriz){
        int corVertice[] = new int [ordemMatriz];
        int distanciaVertice[] = new int [ordemMatriz];
        ArrayList <Integer> listaProximo = new ArrayList <Integer>();
        
        for(int percorre = 0 ; percorre < corVertice.length ; percorre++){
            corVertice[percorre] = 0;
            distanciaVertice[percorre] = -1;
        }
        
        listaProximo.add(raiz);
        corVertice[raiz] = 1;
        distanciaVertice[raiz] = 0;

        while(!listaProximo.isEmpty()){
            int x = listaProximo.remove(0);
            
            for(int j = 0; j < ordemMatriz ; j++){
                if(matriz[x][j] != Integer.MAX_VALUE){
                    if(corVertice[j] == 0){
                        listaProximo.add(j);
                        corVertice[j] = 1;
                        distanciaVertice[j] = distanciaVertice[x]+1;
                    }
                }
            }  
        }
        impressaoBFSMatriz(distanciaVertice, raiz);
    }
    
    private void impressaoBFSMatriz(int vetor[], int raiz){
        
        resultado += "Vertice[" + raiz + "] > " + vetor[raiz] + "\n\n";
        
        for(int aux = 1; aux < vetor.length; aux++){
            if(aux != raiz)
                resultado += "Vertice[" + aux + "] > Distância: " + vetor[aux]+"\n";
        }
    }
    
    
    
    public void verificaCaminhoMatriz (int vert1, int vert2, int grafo[][], int ordemMatriz){
        resultado = "Vertice[" + vert1 +"] >>> " + "Vertice[" + vert2 + "]\n\n";
        int corVertice[] = new int[ordemMatriz];
        int distanciaVertice[] = new int[ordemMatriz];
        int predecessor[] = new int[ordemMatriz];
        
        ArrayList <Integer> listaProximo = new ArrayList <Integer>();
        
        for(int percorre = 0 ; percorre < corVertice.length ; percorre++){
            corVertice[percorre] = 0;
            distanciaVertice[percorre] = -1;
        }
        
        listaProximo.add(vert1);
        distanciaVertice[vert1] = 0;
        corVertice[vert1] = 1;
        predecessor[vert1] = -1;
        
        while(!listaProximo.isEmpty()){
            int x = listaProximo.remove(0);
            
            
            for(int j = 0; j < ordemMatriz ; j++){  
                if(grafo[x][j] != Integer.MAX_VALUE){
                    if(corVertice[j] == 0){
                        listaProximo.add(j);
                        predecessor[j] = x;
                        corVertice[j] = 1;
                        distanciaVertice[j] = distanciaVertice[x]+1;
                    }
                }
            }
            if(x == vert2){
                impressaoAdjMatriz(vert2, predecessor); return;}
        }
        resultado += "O caminho não é possível! :(";
    }
    
    private void impressaoAdjMatriz(int vert2,int predecessor[]){
        ArrayList <String> result = new ArrayList <String>();
        for(int aux = vert2; aux != -1; aux = predecessor[aux])
            result.add("Vertice["+predecessor[aux]+"] >" + "Vertice[" +aux + "]\n");
        for(int aux = result.size()-2 ; aux >= 0 ; aux--)
            resultado += result.get(aux);
    }
    
    public String getResultado(){
        return resultado;
    }
    
}
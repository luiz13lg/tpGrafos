/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import java.util.ArrayList;

public class Vertice {
    private int numero;
    private int componente = 0;
    private int cor;                    // atingibilidade do vertice 1- Branco 2- Cinza 3-Preto
    private Vertice predecessor; //"pai" do vertice          //para BFS
    private int distancia;              //distancia ate a raiz      //para BFS
    private int tempoInicial;                   //tempo de descoberta       //para DFS
    private int tempoFinal;                     //tempo de descoberta       //para DFS
    private ArrayList <String> adjacencia = new ArrayList <String>();    //vertices adjacentes e seus pesos (para lista de adj) //coloracao
    private int peso; //kruskal
    private Vertice adj; //kruskal
    private ArrayList<Integer> arvoreKrus = new ArrayList <Integer>(); //para kruskal
    
    public Vertice(int valor) {
        this.numero = valor;
    }
    
    public Vertice(){
        
    }

    public ArrayList<Integer> getArvoreKrus() {
        return arvoreKrus;
    }

    public void setArvoreKrus(ArrayList<Integer> arvoreKrus) {
        this.arvoreKrus = arvoreKrus;
    }

    public Vertice getAdj() {
        return adj;
    }

    public void setAdj(Vertice adj) {
        this.adj = adj;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public ArrayList<String> getAdjacencia() {
        return adjacencia;
    }

    public void setAdjacencia(ArrayList<String> adjacencia) {
        this.adjacencia = adjacencia;
    }

    public int getCor() {
        return cor;
    }
    
    public int getComponente(){
        return componente;
    }
    
    public void setComponente(int componente){
        this.componente = componente;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
    
    public Integer busca(Integer numero){
        for(int i = 0; i < adjacencia.size(); i++){
            if(adjacencia.get(i).equals(numero))
                return 1;
        }
        return 0;
    }
    
    public void addAdjacencia(String adj){
        this.adjacencia.add(adj);
    }
    
    public void setPredecessor(Vertice v){
        this.predecessor = v;
    }
    
    public Vertice getPredecessor(){
        return this.predecessor;
    }
    
    public void setDistancia (int d){
        this.distancia = d;
    }
    
    public int getDistancia (){
        return this.distancia;
    }
    
    public void setTempoInicial(int tempo){
        tempoInicial = tempo;
    }
    
    public int getTempoInicial(){
        return tempoInicial;
    }
    
    public void setTempoFinal (int tempo){
        tempoFinal = tempo;
    }
    
    public int getTempoFinal (){
        return tempoFinal;
    }
    
    public void addArvore(int v){
        arvoreKrus.add(v);
    }
    
    public void iniArvore(){
        arvoreKrus = new ArrayList<Integer>(adjacencia.size());
    }
    
}

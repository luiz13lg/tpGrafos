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
public class DFS {
    
    int tempo;  //tempo de descoberta
    
    //variaveis para matriz
    private int cor [];
    private int tempoIni [];
    private int tempoFin [];
    private int predecessor[];
    
    private int componente []; //para detectar componentes distintos no grafo
    private int componenteAtual; //para atualizar o valor do componente
        
    private String impressao = "";   //variavel para exibir resultados    
    
    private void inicializa (Vertice grafo[], Vertice raiz){
        for(Vertice vert : grafo){
            vert.setCor(0);
            vert.setTempoInicial(-1);
            vert.setTempoFinal(-1);
        }
        componenteAtual = 1;
        tempo = 0;
        
        visita(raiz, grafo);
        
        for(Vertice vert : grafo){
            if (vert.getCor() == 0){
                componenteAtual++;
                visita(vert, grafo);
            }
        }
    }
    
    private void visita (Vertice vert, Vertice grafo[]){
        vert.setCor(1);                 //cinzou
        vert.setComponente(componenteAtual);
        tempo++;                        //aumentando tempo de descoberta
        vert.setTempoInicial(tempo);    //setando tempo de descoberta ao vertice
    
        for(String adj : vert.getAdjacencia()){             //array com vertices e seus pesos. Ex.: [vert peso; 1 3; 3 4]
            String vertsAdj[] = adj.split(" ");             //separando o numero do vertice do seu peso. [1;3]
            int posicao = Integer.parseInt(vertsAdj[0]);    //transformando a posicao em int [1];
            if(grafo[posicao].getCor() == 0){
                vert.setPredecessor(vert);
                visita(grafo[posicao], grafo);
            }
        }
        vert.setCor(2); //preteou
        tempo++;
        vert.setTempoFinal(tempo);
    }
    
    public void conexo(Vertice grafo[]){
        impressao = "";
        inicializa(grafo, grafo[0]);

        if(componenteAtual == 1)
            impressao = "O grafo é conexo!";
        else {
            impressao += "O grafo não é conexo! \n_____________________\n\n";
            componente = new int[grafo.length];
            for(Vertice vert : grafo){
                impressao += "Vertice[" + vert.getNumero() + "]: Componente: " + vert.getComponente() + "\n";
                componente[vert.getNumero()] = vert.getComponente();
            }
        }
    }
    
    public void conexoMatriz(int grafo[][], int ordem){
        impressao = "";
        inicializaMatriz(0, grafo, ordem);
        
        if(componenteAtual == 1)
            impressao = "O grafo é conexo!";
        else {
            impressao += "O grafo não é conexo! \n_____________________\n\n";
            for(int aux = 0 ; aux < componente.length ; aux++){
                impressao += "Vertice[" + aux + "]: Componente: " + componente[aux] + "\n";
            }
        }
    }
    
    public void executa(Vertice raiz, Vertice grafo[]){
        inicializa(grafo, raiz);
        impressao(grafo);
    }
    
    private void impressao(Vertice grafo[]){
        impressao = "";
        for(Vertice vert : grafo){
            impressao += ("Vertice["+vert.getNumero()+"]:\n - TempoIni: "+ vert.getTempoInicial() 
                    +"  \n - TempoFin: "+ vert.getTempoFinal()+" \n - Componente: "+vert.getComponente()+"\n\n");
            //System.out.println("Vertice["+vert.getNumero()+"]: TempoIni: "+ vert.getTempoInicial() 
             //       +"  | TempoFin: "+ vert.getTempoFinal()+" | Componente: "+vert.getComponente());
        }
    }
    
    private void inicializaMatriz(int vertice, int grafo[][], int ordemMatriz){      
        cor = new int [ordemMatriz];
        tempoIni = new int [ordemMatriz];
        tempoFin = new int [ordemMatriz];
        
        componente = new int [ordemMatriz]; //tamanho maximo de componentes e´ o numero maximo de verts do grafo
        componenteAtual = 1;
        
        for(int comecandoOsParanaue = 0; comecandoOsParanaue < ordemMatriz; comecandoOsParanaue++){
            cor[comecandoOsParanaue] = 0;
            componente[comecandoOsParanaue] = 0;
            tempoIni[comecandoOsParanaue] = 0;
            tempoFin[comecandoOsParanaue] = 0;
        }
        
        tempo = 0;
        visitaMatriz(vertice, grafo, ordemMatriz);
        
        for(int aux = 0; aux < ordemMatriz; aux++){
            if(cor[aux] == 0){
                componenteAtual = componenteAtual+1;  //o algotimo so entra aqui caso acabem as adjs do vert e ainda existam verts brancos
                visitaMatriz(aux, grafo, ordemMatriz);
            }
        }
    }
    
    private void visitaMatriz(int vertice, int grafo[][], int ordemMatriz){
        cor[vertice] = 1;   //cinzou
        tempo++;
        tempoIni[vertice] = tempo;
        componente[vertice] = componenteAtual;

        for(int j = 0; j < ordemMatriz; j++)            //j sao as colunas da matriz, representa um vertice
            if(grafo[vertice][j] != Integer.MAX_VALUE)  //verificando se ha´ adjacencia entre os vertices
                if(cor[j] == 0)                         //se a cor do vertice com adj e´ branco
                    visitaMatriz(j, grafo, ordemMatriz);
        
        cor[vertice] = 2;   //preteou
        tempo++;
        tempoFin[vertice] = tempo;
    }
    
    public void executaMatriz(int vertice, int grafo[][], int ordemMatriz){
        inicializaMatriz(vertice, grafo, ordemMatriz);
        impressaoMatriz();
    }
    
    private void impressaoMatriz(){
        impressao = "";
        int tamanho = cor.length;
        for(int aux = 0; aux < tamanho; aux++){
            impressao += ("Vertice ["+aux+"]"+": \n - TempoIni: "+tempoIni[aux]+"\n - TempoFin:"+tempoFin[aux]+ 
                    "\n - Componente: "+componente[aux]) + "\n\n";
//            System.out.println("Vertice ["+aux+"]"+": TempoIni: "+tempoIni[aux]+"| TempoFin:"+tempoFin[aux]+ 
//                    "| Componente: "+componente[aux]);
        }
    }
    
    public void caminho(Vertice vert1, Vertice vert2, Vertice grafo[]){
        impressao = "";
        ArrayList <String> result = new ArrayList <String>();
        for(Vertice vert : grafo){
            vert.setCor(0);
            vert.setPredecessor(null);
        }
        
        visitaCaminho(vert1, vert2, grafo);
        
        impressao += "Vertice[" + vert1.getNumero() + "] >>> " + "Vertice[" + vert2.getNumero() + "]\n\n";
        for( ; vert2.getPredecessor() != null; vert2 = vert2.getPredecessor())
            result.add("Vertice["+ vert2.getPredecessor().getNumero() +"] > "+ "Vertice[" + vert2.getNumero() + "]\n");
        
        if(result.size() > 0)
            for(int aux = result.size()-1 ; aux >= 0 ; aux--)
                impressao += result.get(aux);
        else impressao += "O caminho não é possível! :(";
    }
    
    private void visitaCaminho(Vertice vert1, Vertice vert2, Vertice grafo[]){
        vert1.setCor(1);
        // na busca em prof o algoritmo nao e´ relancado, pois se precisar ser, e´ pq o vertice nao e´ atingivel
        for(String adj : vert1.getAdjacencia()){
            String vertAdj[] = adj.split(" ");
            int posicao = Integer.parseInt(vertAdj[0]);
            if(grafo[posicao].getCor() == 0){
                grafo[posicao].setPredecessor(vert1);
                if(grafo[posicao] == vert2) return;
                visitaCaminho(grafo[posicao], vert2, grafo);
            }
        }
    }

    public void caminhoMatriz(int vert1, int vert2, int grafo[][], int ordemMatriz){
        impressao = "";
        cor = new int [ordemMatriz];
        tempoIni = new int [ordemMatriz];
        tempoFin = new int [ordemMatriz];
        predecessor = new int [ordemMatriz];
        
        for(int aux = 0 ; aux < ordemMatriz ; aux++){
            cor[aux] = 0;
            tempoIni[aux] = 0;
            tempoFin[aux] = 0;
            predecessor [aux] = -1;
        }
        
        visitaCaminhoMatriz(vert1, vert2, grafo, ordemMatriz);
        
        impressao += "Vertice[" + vert1 + "] >>> " + "Vertice[" + vert2 + "]\n\n";
        
        ArrayList <String> resultados = new ArrayList <String>();
        
        for(int aux = vert2; predecessor[aux] != -1; aux = predecessor[aux]){
            resultados.add("Vertice[" + predecessor[aux] + "] > " + "Vertice[" + aux + "] \n");
        }
        if(resultados.size() > 0)
            for(int aux = resultados.size()-1 ; aux >= 0; aux--)
                impressao += resultados.get(aux);
        else impressao += "O Caminho não é possível! :(";
    }
    
    private void visitaCaminhoMatriz(int vert1, int vert2, int grafo[][], int ordemMatriz){
        cor[vert1] = 1;
        for(int j = 0; j < ordemMatriz; j++)
            if(grafo[vert1][j] != Integer.MAX_VALUE)
                if(cor[j] == 0){
                    predecessor[j] = vert1;
                    if(j == vert2) break;
                    visitaCaminhoMatriz(j, vert2, grafo, ordemMatriz);
                }
        tempoFin[vert1] = tempo++;
    }
    
    public DFS(){
        
    }
    
    public String getResultado (){
        return impressao;
    }
    
    public int getQuantComponentes(){
        return componenteAtual;
    }
    
    public int[] getComponentes(){
        return componente;
    }
}

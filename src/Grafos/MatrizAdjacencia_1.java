package Grafos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatrizAdjacencia_1 {
    private int matrizAdjacencia[][];
    private int ordem;
    private int grafoDigrafo;
    private String resultado;
    
    private static Integer infinito = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int grafoDigrafo, nVertices;

        BufferedReader br = new BufferedReader(new FileReader   //carrega o txt contendo os grafos
            ("grafinhos.txt"));       //local e nome do txt

        String linha = br.readLine() + " " + br.readLine();     //leitura grafo|digrafo & leitura qtd grafos
        String valores[] = linha.split(" ");                    //separando os valores lidos
        grafoDigrafo = Integer.parseInt(valores[0]);
        nVertices = Integer.parseInt(valores[1]);

        int [][] matrizAdjacencia = new int [nVertices][nVertices];     //iniciando matriz p/ o grafo
        iniciaMatriz(matrizAdjacencia, nVertices);                      //inicializando matriz

        if(grafoDigrafo == 0)               //leitura de grafo ou digrafo
            leGrafoMatriz(matrizAdjacencia, br);
        else leDigrafoMatriz(matrizAdjacencia, br);

        //impressao(matrizAdjacencia, nVertices);   //imprimindo matriz
    }

    public void inicia(BufferedReader br){
        
        try { 
            int nVertices;
            String linha = br.readLine() + " " + br.readLine();     //leitura grafo|digrafo & leitura qtd grafos
            String valores[] = linha.split(" ");                    //separando os valores lidos
            grafoDigrafo = Integer.parseInt(valores[0]);
            nVertices = Integer.parseInt(valores[1]);
            matrizAdjacencia = new int [nVertices][nVertices];      //iniciando matriz p/ o grafo
            iniciaMatriz(matrizAdjacencia, nVertices);              //inicializando matriz
            if(grafoDigrafo == 0)               //leitura de grafo ou digrafo
                leGrafoMatriz(matrizAdjacencia, br);
            else leDigrafoMatriz(matrizAdjacencia, br);
            
            //impressao(matrizAdjacencia, nVertices);   //imprimindo matriz
            ordem = nVertices;
        } catch (IOException ex) {
            Logger.getLogger(MatrizAdjacencia_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void impressao(int matriz[][], int nVertices){
        int aux1, aux2;
        for(aux1 = 0; aux1 < nVertices; aux1++){        //imprimindo matriz
            for(aux2 = 0; aux2 < nVertices; aux2++){
                if(matriz[aux1][aux2] == infinito)      //verifica se o valor da matriz é vazio ou nao
                    System.out.print(" ∞ ");
                else System.out.print(" " + matriz[aux1][aux2]+" ");
            } System.out.print("\n");
        }
    }

    private static void iniciaMatriz(int matriz[][], int nVertices){
        int aux1, aux2;
        for(aux1 = 0 ; aux1 < nVertices ; aux1++ ){     //setando todas as posições com valor infinito
            for(aux2 = 0; aux2 < nVertices; aux2++)
                matriz[aux1][aux2] = infinito;
       }
    }

    private static void leGrafoMatriz(int matrizAdj[][],BufferedReader br) throws IOException{
        String linha;
        int vert1, vert2, peso;
        String valores[];

        while(br.ready()){
            linha = br.readLine();  //leitura linha a linha da fonte

            valores = linha.split(" ");

            vert1 = Integer.parseInt(valores[0]);   //obtendo vertice 1 em int
            vert2 = Integer.parseInt(valores[1]);   //obtendo vertice 2 em int
            peso = Integer.parseInt(valores[2]);    //obtendo peso da aresta
      
            matrizAdj[vert2][vert1] = matrizAdj [vert1][vert2] = peso;  //atribuindo adjacencia entre os vertices selecionados
        } br.close();         //fechando arquivo
    }

    private static void leDigrafoMatriz(int matrizAdj[][], BufferedReader br) throws IOException{
        String linha;
        int vert1, vert2, peso;
        String valores[];

        while(br.ready()){
            linha = br.readLine();

            valores = linha.split(" ");
            vert1 = Integer.parseInt(valores[0]);
            vert2 = Integer.parseInt(valores[1]);
            peso = Integer.parseInt(valores[2]);
            
            matrizAdj[vert1][vert2] = peso;
        } br.close();
    }
    
    public int[][] getMatriz(){
        return matrizAdjacencia;
    }
    
    public int getOrdem(){
        return ordem;
    }
    
    public int grafoDigrafo(){
        return grafoDigrafo;
    }
    
    public String getResultado(){
        return resultado;
    }
    
    public void BellmanFord(int raiz){
        resultado = "";
        int antecessor[] = new int [ordem];
        int distancia[] = new int[ordem];
        
        for(int aux = 0; aux < ordem; aux++){             // inicializacao
            distancia[aux] = (Integer.MAX_VALUE);     //
            antecessor[aux] = -1;                     //
        }
        
        distancia[raiz] = 0;
        
        for(int l = 1; l < ordem -1 ; l++)
            for(int i = 0; i < ordem; i++)
                for(int j = 0; j < ordem; j++)
                    if(matrizAdjacencia[i][j] != Integer.MAX_VALUE)
                        relaxa(i, j, matrizAdjacencia[i][j], distancia, antecessor); //relaxamento
        
        resultado += "RAIZ >>> Vertice[" + raiz + "]\n"; 
        
        for(int aux = 0; aux < ordem; aux++){
            if(aux == raiz) aux++;
            resultado += "\nVertice: [" + aux +"]:\n";
            for(int aux2 = aux; antecessor[aux2] != -1; aux2 = antecessor[aux2]){
                resultado += "- Vertice: [" + aux2 +"] > Antecessor: [" + antecessor[aux2] + "] \n";
            }
        }
    }

    public void Dijkstra(int raiz){
        resultado = "";
        int antecessor[] = new int[ordem];
        int distancia [] = new int[ordem];
        int listaControle [] = new int[ordem];
        int i;
        
        for(int aux = 0; aux < ordem; aux++){
            antecessor[aux] = -1;
            distancia [aux] = Integer.MAX_VALUE;
            listaControle [aux] = 1;
        }

        distancia [raiz] = 0;

        while(visitouTodos(listaControle)){
            i = min(listaControle, distancia);
            
            for(int aux = 0; aux < ordem; aux++)
                if(matrizAdjacencia[i][aux]!= Integer.MAX_VALUE)
                    relaxa(i, aux, matrizAdjacencia[i][aux], distancia, antecessor);
        }
        
        resultado += "RAIZ >>> Vertice[" + raiz + "]\n"; 
        
        for(int aux = 0; aux < ordem; aux++){
            if(aux == raiz) aux++;
            resultado += "\nVertice: [" + aux +"]:\n";
            for(int aux2 = aux; antecessor[aux2] != -1; aux2 = antecessor[aux2]){
                resultado += "- Vertice: [" + aux2 +"] > Antecessor: [" + antecessor[aux2] + "] \n";
            }
        } 
    }

    private boolean visitouTodos(int lista[]){
        for(int aux = 0; aux < ordem; aux++){
            if(lista[aux]  == 1) return true;
        } return false;
    }
    
    private int min(int lista[], int dist[]){
        int menor = Integer.MAX_VALUE;
        int vert = 0;

        for(int aux = 0; aux < lista.length; aux++)
            if(lista[aux] == 1 && dist[aux] < menor){
                menor = dist[aux];
                vert = aux;
            }

        lista[vert] = 0;    //setando visita
        return vert;
    }
    
    private void relaxa(int i, int j, int pesoAresta, int[] dist, int[] ant){
        if(dist[j] > (dist[i] + pesoAresta)){
            dist[j] = dist[i] + pesoAresta;
            ant[j] = i;
        }
    }
}
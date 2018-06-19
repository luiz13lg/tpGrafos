
package Grafos;

import Grafos.classe.BFS;
import Grafos.classe.Kruskal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaAdjacencia {
    private Vertice listaAdj[];
    private String resultado;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int grafoDigrafo;
        int nVertices;
        String valores[];
        BFS busca = new BFS();
        BufferedReader br = new BufferedReader(new FileReader       //lendo txt contendo os grafos
            ("grafinhos2.txt"));       //local e nome do txt

        String linha = br.readLine() + " " + br.readLine();   //leitura digrafo|grafo
        valores = linha.split(" ");
        grafoDigrafo = Integer.parseInt(valores[0]);

        nVertices = Integer.parseInt(valores[1]);
        Vertice listaAdjacencia[] = new Vertice [nVertices]; //conjunto de vertices | lista de adjacencia
        
        for(int aux = 0; aux < nVertices; aux++){
            listaAdjacencia[aux] = new Vertice();
        }

        if(grafoDigrafo == 0)       //leitura de grafo ou digrafo
            leGrafo(br, listaAdjacencia);
        else leDigrafo(listaAdjacencia, br);

        impressao(listaAdjacencia);
        
        Kruskal ks = new Kruskal(listaAdjacencia);
        ks.AGM_Kruskal();
    }
    
    public ListaAdjacencia(){
    
    }
    
    public void iniciaListaAdjacencia(BufferedReader br){
        try {
            int grafoDigrafo;
            int nVertices;
            String valores[];
            
            String linha = br.readLine() + " " + br.readLine();   //leitura digrafo|grafo
            valores = linha.split(" ");
            grafoDigrafo = Integer.parseInt(valores[0]);
            
            nVertices = Integer.parseInt(valores[1]);
            listaAdj = new Vertice [nVertices]; //conjunto de vertices | lista de adjacencia
            
            for(int aux = 0; aux < nVertices; aux++){
                listaAdj[aux] = new Vertice();
            }
            
            if(grafoDigrafo == 0)       //leitura de grafo ou digrafo
                leGrafo(br, listaAdj);
            else leDigrafo(listaAdj, br);
            
            //impressao(listaAdjacencia);
        } catch (IOException ex) {
            Logger.getLogger(ListaAdjacencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void impressao(Vertice[] listaAdjacencia){
        int aux = 0;
        for (Vertice vertice : listaAdjacencia) {
            System.out.print(aux + " -> "); aux++;
            for (String adjacencia : vertice.getAdjacencia()) {
                String impressao[] = adjacencia.split(" ");
                System.out.print(impressao[0]+", "+impressao[1]+" - ");
            } System.out.print("//\n");
        }System.out.println("");
    }

    private static void leDigrafo(Vertice[] listaAdjacencia, BufferedReader br) throws IOException{
        String linha;
        String valores[];
        int vert1, vert2;

        while(br.ready()){                  
            linha = br.readLine();  //leitura linha a linha da fonte
            valores = linha.split(" ");

            vert1 = Integer.parseInt(valores[0]); //obtendo vertice 1 em int
            vert2 = Integer.parseInt(valores[1]);
            
            listaAdjacencia[vert1].setNumero(vert1);
            listaAdjacencia[vert2].setNumero(vert2);
            listaAdjacencia[vert1].addAdjacencia(valores[1]+" "+valores[2]);

        }br.close();         //fechando arquivo  
    }

    private static void leGrafo(BufferedReader br, Vertice[] listaAdjacencia) throws IOException{
        String linha;
        String valores[];
        int vert1, vert2;
        String peso;

        while(br.ready()){                  
            linha = br.readLine();  //leitura linha a linha da fonte
            valores = linha.split(" ");

            vert1 = Integer.parseInt(valores[0]); //obtendo vertice 1 em int
            vert2 = Integer.parseInt(valores[1]); //obtendo vertice 2 em int

            listaAdjacencia[vert1].setNumero(vert1);
            listaAdjacencia[vert2].setNumero(vert2);

            listaAdjacencia[vert1].addAdjacencia(Integer.toString(vert2)+" "+valores[2]);
            listaAdjacencia[vert2].addAdjacencia(Integer.toString(vert1)+" "+valores[2]);

        }br.close();         //fechando arquivo  
    }
    
    public Vertice [] getListaAdj(){
        return listaAdj;
    }
    
    public String getResultado(){
        return resultado;
    }
    
    public void BellmanFord(Vertice raiz){
        resultado = "";
        
        for(Vertice vert : listaAdj){               // inicializacao
            vert.setDistancia(Integer.MAX_VALUE);   //
            vert.setPredecessor(null);              //
        }
        
        raiz.setDistancia(0);
        
        for(Vertice vert : listaAdj){
            for(String adj : vert.getAdjacencia()){             //array com vertices e seus pesos. Ex.: [vert peso; 1 3; 3 4]
                String pesos[] = adj.split(" ");
                int pos = Integer.parseInt(pesos[0]); int peso = Integer.parseInt(pesos[1]);
                relaxa(vert, listaAdj[pos], peso);
            }
        }
        
        resultado += "RAIZ >>> Vertice[" + raiz.getNumero() + "]\n";
        for(int aux = 0; aux < listaAdj.length; aux++){
            if(listaAdj[aux] == raiz) aux++;
            resultado += "\nVertice: [" + aux +"]:\n";
            for(int aux2 = aux; listaAdj[aux2].getPredecessor() != null; aux2 = listaAdj[aux2].getPredecessor().getNumero())
                resultado += "- Vertice: [" + aux2 + "] > " + "Antecessor: [" + listaAdj[aux2].getPredecessor().getNumero() + "]\n";
        }
    }

    private void relaxa(Vertice vert1, Vertice vert2, int pesoAresta){
        if(vert2.getDistancia() > (vert1.getDistancia() + pesoAresta)){
            vert2.setDistancia(vert1.getDistancia() + pesoAresta);
            vert2.setPredecessor(vert1);
        }
    }
    
    public void Dijkstra(Vertice raiz){
        resultado = "";
        int listaControle [] = new int[listaAdj.length];
        int i;
        
        for(int aux = 0; aux < listaAdj.length; aux++){
            listaAdj[aux].setPredecessor(null);
            listaAdj[aux].setDistancia(Integer.MAX_VALUE);
            listaControle [aux] = 1;
        }

        raiz.setDistancia(0);

        while(visitouTodos(listaControle)){
            i = min(listaControle, listaAdj);
            
            for(Vertice vert : listaAdj){
                for(String adj : vert.getAdjacencia()){
                    String pesos[] = adj.split(" ");
                    int pos = Integer.parseInt(pesos[0]); int peso = Integer.parseInt(pesos[1]);
                    relaxa(vert, listaAdj[pos], peso);
                }
            }
        }
        
        resultado += "RAIZ >>> Vertice[" + raiz.getNumero() + "]\n";
        for(int aux = 0; aux < listaAdj.length; aux++){
            if(listaAdj[aux] == raiz) aux++;
            resultado += "\nVertice: [" + aux +"]:\n";
            for(int aux2 = aux; listaAdj[aux2].getPredecessor() != null; aux2 = listaAdj[aux2].getPredecessor().getNumero())
                resultado += "- Vertice: [" + aux2 + "] > " + "Antecessor: [" + listaAdj[aux2].getPredecessor().getNumero() + "]\n";
        }
    }
    
    private boolean visitouTodos(int lista[]){
        for(int aux = 0; aux < lista.length; aux++){
            if(lista[aux]  == 1) return true;
        } return false;
    }
    
    private int min(int lista[], Vertice listaAdj[]){
        int menor = Integer.MAX_VALUE;
        int vert = 0;

        for(int aux = 0; aux < lista.length; aux++)
            if(lista[aux] == 1 && listaAdj[aux].getDistancia() < menor){
                menor = listaAdj[aux].getDistancia();
                vert = aux;
            }

        lista[vert] = 0;    //setando visita
        return vert;
    }
    
}
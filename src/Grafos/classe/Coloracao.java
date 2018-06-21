/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
*/

package Grafos.classe;
import Grafos.*;


/**
 *
 * @author Danilo Medeiros Eler
*/
public class Coloracao {
    private int cores[] = null;
    
    public void execute(Vertice[] lista) {
        cores = new int[lista.length];
        for (int i=0; i<cores.length; i++){
            cores[i] = -1;
        }
        int vMaiorGrau = verticeMaiorGrau(lista);
        System.out.println("Vertice de Maior Grau: " + vMaiorGrau);
        coloreVertice(lista, vMaiorGrau);
    }

    private void coloreVertice(Vertice[] lista, int vert) {
        cores[vert] = corApropriada(lista, vert);
        for(int i=0;i<lista[vert].getAdjacencia().size();i++){
            if(cores[ Integer.parseInt(String.valueOf(lista[vert].getAdjacencia().get(i).charAt(0)))] == -1){
                coloreVertice(lista,Integer.parseInt(String.valueOf(lista[vert].getAdjacencia().get(i).charAt(0))));
            }
        }
    }
    
    private int corApropriada(Vertice[] lista, int vert){
        int cor = -1;    
        boolean flag = false;
        int i=0;
        while(!flag){
            cor++;
            i=0;
            while(i <lista[vert].getAdjacencia().size() && cores[Integer.parseInt(String.valueOf(lista[vert].getAdjacencia().get(i).charAt(0)))] != cor)
                i++;
            if(i == lista[vert].getAdjacencia().size())flag = true;
            
            
        }
        return cor;
    }

    private int verticeMaiorGrau(Vertice[] lista){
        int vert = 0;
        int maior = Integer.MIN_VALUE;
        for (int i=0; i<lista.length; i++){
            if(lista[i].getAdjacencia().size()>maior){
                maior = lista[i].getAdjacencia().size();
                vert = i;
            }
        }
        return vert;
    }

    public int getNumCores(){
        int numComp = 0;
        int count[] = new int[cores.length];
        for (int i=0; i< count.length; i++){
            count[i] = 0;
        }

        for (int i=0; i<cores.length; i++){
            if (count[cores[i]] == 0){
                count[cores[i]]++;
            }
        }

        for (int i=0; i<count.length; i++){
            numComp = numComp + count[i];
        }

        return numComp;
    }

    public int[] getCores() {
        return cores;
    }    
}

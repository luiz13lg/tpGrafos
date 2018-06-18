/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetografos;

import Grafos.Grafo;
import Grafos.ListaAdjacencia;
import Grafos.MatrizAdjacencia;

/**
 *
 * @author Danilo
 */
public class TestesGrafo {
    public static void main(String args[]){
        //Grafo g = new Grafo(5, new MatrizAdjacencia());
        Grafo g = new Grafo(5, new ListaAdjacencia());
        g.addAresta(0, 1);
        g.addAresta(0, 2);
        g.addAresta(2, 1);
        g.addAresta(3, 1);
        g.addAresta(4, 2);
        g.addAresta(2, 3);
        g.imprimeRepresentacao("Grafo");
    }
}

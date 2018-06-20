/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos.aplicacao;

import Grafos.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Guilherme
 */
public class Composicao {
    private ArrayList<Campeao> campeoes = new ArrayList();
    private Campeao[] teamA = new Campeao[5];
    private Campeao[] teamB = new Campeao[5];
    ListaAdjacencia listaAdjacencia;
    Vertice[] lista;

    public Composicao() throws FileNotFoundException, IOException {
    //carregar campeoes
        BufferedReader br = new BufferedReader(new FileReader("basecampeoes.txt"));  
        while(br.ready()){
            String linha = br.readLine();  //leitura linha a linha da fonte
            String[] valores = linha.split(",");
            addCampeao(valores[0],valores[1]);
        }
        br.close();
    }

    
    
    public ArrayList<Campeao> getCampeoes() {
        return campeoes;
    }

    public Campeao[] getTeamA() {
        return teamA;
    }

    public Campeao[] getTeamB() {
        return teamB;
    }

    public void setCampeoes(ArrayList<Campeao> campeoes) {
        this.campeoes = campeoes;
    }

    public void setTeamA(Campeao[] teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(Campeao[] teamB) {
        this.teamB = teamB;
    }
    
    public void addCampeao(String nome,String funcao){
        Campeao campeao = new Campeao(nome.toLowerCase(),funcao.toLowerCase());
        campeoes.add(campeao);
    }
    
    public Campeao buscarCampeao(String nome){
        for(int i=0;i<campeoes.size();i++){
            if(campeoes.get(i).getNome().equals(nome.toLowerCase())){
                return campeoes.get(i);
            }
        }
        return null;
    }
    
    public boolean removerCampeao(String nome){
        Campeao campeao = buscarCampeao(nome);
        if(campeao != null){
            campeoes.remove(campeao);
            return true;
        }
        return false;
    }
    
    
    
    
}

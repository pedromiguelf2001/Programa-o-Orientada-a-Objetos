package Projeto_POO.src;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Equipa implements Serializable {
    //private static final long serialVersionUID = -5108118841006066145;
    private String nome;
    private List<Jogador> jogadores;
    private List<Jogador> banco;

    public Equipa(String nomeE) {
        nome=nomeE;
        jogadores = new ArrayList<>();
        banco = new ArrayList<>();
    }

    public Equipa() {
        nome = "";
        jogadores = new ArrayList<>();
        banco = new ArrayList<>();
    }
    public Equipa(Equipa e){
        this.nome = e.getNome();
        this.jogadores = e.getJog();
        this.banco = e.getBanco();
    }
    public Equipa clone(){
        return new Equipa(this);
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    public void insereJogador(Jogador j) {
        jogadores.add(j.clone());
    }
    public void insereBanco(Jogador j) {
        banco.add(j.clone());
    }


    public String getNome(){
        return nome;
    }
    public ArrayList<Integer> getJogadores(){
        ArrayList<Integer> jog = new ArrayList<>();
        for(Jogador j : jogadores){
            jog.add(j.getNumero());
        }
        return jog;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Jogador> getJog(){
        ArrayList<Jogador> jog = new ArrayList<>();
        for(Jogador j: jogadores){
            jog.add(j.clone());
        }
        return jog;
    }
    public ArrayList<Jogador> getBanco(){
        ArrayList<Jogador> jog = new ArrayList<>();
        for(Jogador j: banco){
            jog.add(j.clone());
        }
        return jog;
    }
    public String toString(){
        String r =  "Equipa:" + nome + "\n";
        for (Jogador j : jogadores){
            r += j.toString();
        }
        return r;
    }


    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void setBanco(List<Jogador> banco) {
        this.banco = banco;
    }

    public Equipa addJog(Jogador j){
        if(jogadores.size()<11){
            this.jogadores.add(j.clone());
        }
        else{
            this.banco.add(j.clone());
        }
        return this;
    }
    public void atualizaHist(){
        for (Jogador j : jogadores){
            if(!j.getHistorial().contains(getNome())){
                j.getHistorial().add(getNome());
            }
        }
    }

    public Map<Integer,Integer> geraSubs(){
        Map<Integer,Integer> resultado = new HashMap<>();
        List<String> gasto = new ArrayList<>();
        int limite = 3;
        for(Jogador jog : this.getJog()){
            for(Jogador ban : this.getBanco()){
                if(jog.getClass().equals(ban.getClass())&& !gasto.contains(jog.getPos())&&limite>0){
                    gasto.add(jog.getPos());
                    resultado.put(jog.getNumeroJogador(),ban.getNumeroJogador());
                    limite--;
                }
            }
        }
        return resultado;
    }


}


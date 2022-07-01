package Projeto_POO.src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Jogador implements Serializable {

    private String nomeJogador;
    private int numeroJogador;
    private int velocidade, resistencia, destreza, impulsao, cabeca, remate, passe;
    private ArrayList<String> historial;
    public Jogador(){
        nomeJogador     = "";
        numeroJogador   = ThreadLocalRandom.current().nextInt(1,100);
        velocidade      = ThreadLocalRandom.current().nextInt(50,101);
        resistencia     = ThreadLocalRandom.current().nextInt(50,101);
        destreza        = ThreadLocalRandom.current().nextInt(50,101);
        impulsao        = ThreadLocalRandom.current().nextInt(50,101);
        cabeca          = ThreadLocalRandom.current().nextInt(50,101);
        remate          = ThreadLocalRandom.current().nextInt(50,101);
        passe           = ThreadLocalRandom.current().nextInt(50,101);
        historial = new ArrayList<>();

    }

    public Jogador(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p){
        nomeJogador = nomeJ;
        numeroJogador = numeroJ;
        velocidade = vel;
        resistencia = res;
        destreza = des;
        impulsao = imp;
        cabeca = cab;
        remate = rem;
        passe = p;
        historial= new ArrayList<>();
    }
    public Jogador(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, List<String> hist){
        nomeJogador = nomeJ;
        numeroJogador = numeroJ;
        velocidade = vel;
        resistencia = res;
        destreza = des;
        impulsao = imp;
        cabeca = cab;
        remate = rem;
        passe = p;
        historial = new ArrayList<>(hist);
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }

    public Jogador(Jogador j) {
        nomeJogador = j.getNomeJogador();
        numeroJogador = j.getNumeroJogador();
        velocidade = j.getVelocidade();
        resistencia = j.getResistencia();
        destreza = j.getDestreza();
        impulsao = j.getImpulsao();
        cabeca = j.getCabeca();
        remate = j.getRemate();
        passe = j.getPasse();
        historial = j.getHistorial();

    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getNumeroJogador() {
        return numeroJogador;
    }

    public void setNumeroJogador(int numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getCabeca() {
        return cabeca;
    }

    public void setCabeca(int cabeca) {
        this.cabeca = cabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getPasse() {
        return passe;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    @Override
    public abstract Jogador clone();

    /*public static Jogador parse(String input){
        String[] campos = input.split(",");
        return new Jogador(campos[0], Integer.parseInt(campos[1]), campos[2],
                                        Integer.parseInt(campos[3]),
                                        Integer.parseInt(campos[4]),
                                        Integer.parseInt(campos[5]),
                                        Integer.parseInt(campos[6]),
                                        Integer.parseInt(campos[7]),
                                        Integer.parseInt(campos[8]));
    }*/


    public String toString(){
        StringBuilder sb= new StringBuilder();
        return sb   .append("Nome do Jogador: ").append(this.getNomeJogador())
                    .append("\nNumero do Jogador: ").append(this.getNumeroJogador())
                    .append("\nHistorial: ").append(this.getHistorial().toString())
                    .append("\nVelocidade: ").append(this.getVelocidade())
                    .append("\nResistencia: ").append(this.getResistencia())
                    .append("\nDestreza: ").append(this.getDestreza())
                    .append("\nImpulsao: ").append(this.getImpulsao())
                    .append("\nJogo de Cabeca: ").append(this.getCabeca())
                    .append("\nRemate: ").append(this.getRemate())
                    .append("\nPasse: ").append(this.getPasse())
                    .toString();
    }
    public boolean equals(Object o){
        if(o == this) return true;
        if((o==null )||!o.getClass().equals(this.getClass())) return false;
        Jogador j = (Jogador) o;
        return  this.nomeJogador.equals(j.getNomeJogador()) &&
                this.numeroJogador == j.getNumeroJogador()  &&
                this.velocidade == j.getVelocidade()        &&
                this.resistencia == j.getResistencia()      &&
                this.destreza == j.getDestreza()            &&
                this.impulsao == j.getImpulsao()            &&
                this.cabeca == j.getCabeca()                &&
                this.remate == j.getRemate()                &&
                this.passe == j.getPasse()                  &&
                this.historial.equals(j.getHistorial());
    }
    public Jogador getJogador(String jogEscolhido)  {
        for(Jogador j : View.getJogadoresEmMemoria()){
            if(j.getNomeJogador().equals(jogEscolhido)){
                return j.clone();
            }
        }
        return null;

    }

    public Integer getNumero() {
        return this.numeroJogador;
    }
    public void setNumero(Integer num){
        this.numeroJogador = num;
    }
    public abstract int eval();

    public abstract String getPos();
}
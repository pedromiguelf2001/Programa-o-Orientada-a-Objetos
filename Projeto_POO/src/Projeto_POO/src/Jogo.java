package Projeto_POO.src;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
/*
* Definimos um jogo com tendo diferentes "stages", para termos uma maior aproximacao da realidade.
* Assim,por exemplo:
*   -Se os medios conseguirem passarem os medios adversarios -> A proxima jogada seria avancados contra defesas
* */

public class Jogo implements Serializable {
    private String equipaCasa;
    private String equipaFora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private List<Integer> jogadoresCasa;
    private List<Integer> jogadoresFora;
    private Map<Integer, Integer> substituicoesCasa;
    private Map<Integer, Integer> substitucoesFora;
    private int duracao;



    public Jogo(Jogo jogo) {
        this.equipaCasa = jogo.getEquipaCasa();
        this.equipaFora = jogo.getEquipaFora();
        this.golosCasa = jogo.getGolosCasa();
        this.golosFora = jogo.getGolosFora();
        this.date = jogo.getDate();
        this.jogadoresCasa = jogo.getJogadoresCasa();
        this.jogadoresFora = jogo.getJogadoresFora();
        this.substituicoesCasa = jogo.getSubstituicoesCasa();
        this.substitucoesFora = jogo.getSubstitucoesFora();
        this.duracao = jogo.getDuracao();
    }

    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }
    public Jogo(){
        equipaCasa = "";
        equipaFora = "";
        golosCasa = 0;
        golosFora = 0;
        date = LocalDate.now();
        jogadoresCasa = new ArrayList<>();
        jogadoresFora = new ArrayList<>();
        substituicoesCasa = new HashMap<>();
        substitucoesFora = new HashMap<>();

    }

    public Jogo (String ec, String ef, int gc, int gf, LocalDate d, ArrayList<Integer> jc, Map<Integer, Integer> sc, ArrayList<Integer> jf, Map<Integer, Integer> sf){
        equipaCasa = ec;
        equipaFora = ef;
        golosCasa = gc;
        golosFora = gf;
        date = d;
        jogadoresCasa = new ArrayList<>(jc);
        jogadoresFora = new ArrayList<>(jf);
        substituicoesCasa = new HashMap<>(sc);
        substitucoesFora = new HashMap<>(sf);
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getJogadoresCasa() {
        return jogadoresCasa;
    }

    public void setJogadoresCasa(List<Integer> jogadoresCasa) {
        this.jogadoresCasa = jogadoresCasa;
    }

    public List<Integer> getJogadoresFora() {
        return jogadoresFora;
    }

    public void setJogadoresFora(List<Integer> jogadoresFora) {
        this.jogadoresFora = jogadoresFora;
    }

    public Map<Integer, Integer> getSubstituicoesCasa() {
        return substituicoesCasa;
    }

    public void setSubstituicoesCasa(Map<Integer, Integer> substituicoesCasa) {
        this.substituicoesCasa = substituicoesCasa;
    }

    public Map<Integer, Integer> getSubstitucoesFora() {
        return substitucoesFora;
    }

    public void setSubstitucoesFora(Map<Integer, Integer> substitucoesFora) {
        this.substitucoesFora = substitucoesFora;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public void addGolo(Equipa e){
        if(e.getNome().equals(equipaCasa)){
            golosCasa++;
        }
        else if (e.getNome().equals(equipaFora)){
            golosFora++;
        }
    }

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        return new Jogo(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]),
                LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])),
                (ArrayList<Integer>) jc, subsC, (ArrayList<Integer>) jf, subsF);
    }
    //Formatacao de um jogo para o converter num log
    public String toString(){
        /*
        Jogo:
        Sporting Club Schubert,
        Wagner Athletic,
        3,4,
        2021-04-09,
        36,23,1,48,16,50,15,11,17,22,39,
        50->46,22->43,39->40,
        44,29,12,28,34,7,38,42,33,4,2,
        33->45,34->47,42->14
        */
        StringBuilder sb = new StringBuilder();
        sb.append("Jogo:").append(getEquipaCasa()).append(",")
                .append(getEquipaFora()).append(",")
                .append(getGolosCasa()).append(",").append(getGolosFora()).append(",")
                .append(getDate()).append(",")
                .append(formataArray(getJogadoresCasa()))
                .append(formataMap(getSubstituicoesCasa()))
                .append(formataArray(getJogadoresFora()))
                .append(formataMap(getSubstitucoesFora()));
        //Eliminar a virgula no final
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();

    }
    public String formataArray(List<Integer> arrayList){
        StringBuilder sb = new StringBuilder();
        for(Integer x : arrayList){
            sb.append(x).append(",");
        }
        return sb.toString();
    }
    public  String formataMap(Map<Integer,Integer> map){
        StringBuilder sb = new StringBuilder();
        for(Integer x : map.keySet()){
            sb.append(x).append("->").append(map.get(x)).append(",");
        }
        return sb.toString();
    }
    //Formatacao de um jogo para o converter num log




    public Jogo clone(){
        return new Jogo(this);
    }




    //Daqui em diante encontra se o metodo de determinacao do resultado
    public void iniciarJogo(Equipa casa, Equipa fora, Jogo jogo,int duracao){
        medsVSmeds(casa,fora,duracao,jogo);
    }
    public void avanVSdef(Equipa atq,Equipa adv, int duracao,Jogo jogo){


        if(duracao<90) {
            int nAvanc = 0;
            int averageAvanc = 0;
            //OVERALL DOS AVANCADOS
            for (Jogador j : atq.getJog()) {
                if (j instanceof Avancado || j instanceof Lateral) {
                    nAvanc++;
                    averageAvanc += j.eval();
                }

            }
            averageAvanc = averageAvanc / nAvanc;

            //OVERALL DOS DEFESAS
            int nDefs = 0;
            int averageDefs = 0;
            for (Jogador j : adv.getJog()) {
                if (j instanceof Defesa) {
                    nDefs++;
                    averageDefs += j.eval();
                }

            }
            averageDefs = averageDefs / nDefs;
            //CALCULO DO RESUTADO
            int probAvan = ThreadLocalRandom.current().nextInt(averageAvanc/2, averageAvanc + 1);
            int probDefs = ThreadLocalRandom.current().nextInt(averageDefs/2, averageDefs + 1);
            duracao++;
            //CHAMAR OUTROS METODOS
            if (probAvan > probDefs) {
                avanVSgR(atq,adv, duracao, jogo);
            } else defVSmeds(adv,atq,duracao, jogo);

        }
    }

    public void avanVSgR(Equipa atq,Equipa adv,int duracao,Jogo jogo){



        if(duracao<90) {
            int nAvanc = 0;
            int averageAvanc = 0;
            //OVERALL DOS AVANCADOS
            for (Jogador j : atq.getJog()) {
                if (j instanceof Avancado) {
                    nAvanc++;
                    averageAvanc += j.eval();
                }

            }
            averageAvanc = averageAvanc / nAvanc;

            //OVERALL DO GUARDA RDES

            int averageGR = 0;
            for (Jogador j : adv.getJog()) {
                if (j instanceof GuardaRedes) {

                    averageGR += j.eval();
                }

            }

            //CALCULO DO RESUTADO
            int probAvan = ThreadLocalRandom.current().nextInt(averageAvanc/2, averageAvanc + 1);
            int probGR = ThreadLocalRandom.current().nextInt(averageGR-10, averageGR + 1);
            duracao++;
            //CHAMAR OUTROS METODOS
            if (probAvan > probGR) {
                String nome = "";

                int getAvan = ThreadLocalRandom.current().nextInt(0,nAvanc);
                for(Jogador j: atq.getJog()){
                    if(j instanceof Avancado){

                        if(getAvan == 0){
                            nome = j.getNomeJogador();
                        }
                        getAvan--;
                    }
                }
                System.out.println(duracao+"' Golo Equipa- "+atq.getNome() + " - Marca: " + nome);
                jogo.addGolo(atq);
                iniciarJogo(adv,atq,jogo,duracao);

            } else defVSmeds(adv,atq,duracao, jogo);
        }


    }
    public void defVSmeds(Equipa atq,Equipa adv,int duracao,Jogo jogo){




        if(duracao<90) {
            int nDefs = 0;
            int averageDefs = 0;
            //OVERALL DOS DEFESAS
            for (Jogador j : atq.getJog()) {
                if (j instanceof Defesa || j instanceof Lateral) {
                    nDefs++;
                    averageDefs += j.eval();
                }

            }
            averageDefs = averageDefs / nDefs;

            //OVERALL DOS MEDIOS ADVERSARIOS
            int nMeds = 0;
            int averageMeds = 0;
            for (Jogador j : adv.getJog()) {
                if (j instanceof Medio) {
                    nMeds++;
                    averageMeds += j.eval();
                }

            }
            averageMeds = averageMeds / nMeds;
            //CALCULO DO RESUTADO
            int probDefs = ThreadLocalRandom.current().nextInt(averageDefs/2, averageDefs + 1);
            int probMeds = ThreadLocalRandom.current().nextInt(averageMeds/2, averageMeds + 1);
            duracao++;
            //CHAMAR OUTROS METODOS
            if (probDefs > probMeds) {
                medsVSmeds(atq,adv, duracao , jogo);
            } else avanVSdef(adv,atq,duracao, jogo);

        }


    }
    public void medsVSmeds(Equipa atq,Equipa adv,int duracao,Jogo jogo) {


        if (duracao < 90) {
            int nMeds = 0;
            int averageMeds = 0;
            //OVERALL DOS MEDIOS/LATERAIS
            for (Jogador j : atq.getJog()) {
                if (j instanceof Medio || j instanceof Lateral) {
                    nMeds++;
                    averageMeds += j.eval();
                }


            }
            averageMeds = averageMeds / nMeds;

            //OVERALL DOS MEDIOS ADVERSARIOS
            int nMedsAdv = 0;
            int averageMedsAdv = 0;
            for (Jogador j : adv.getJog()) {
                if (j instanceof Defesa) {
                    nMedsAdv++;
                    averageMedsAdv += j.eval();
                }

            }
            averageMedsAdv = averageMedsAdv / nMedsAdv;
            //CALCULO DO RESUTADO
            int probMeds = ThreadLocalRandom.current().nextInt(averageMeds/2, averageMeds + 1);
            int probMedsAdv = ThreadLocalRandom.current().nextInt(averageMedsAdv/2, averageMedsAdv + 1);
            duracao++;
            //CHAMAR OUTROS METODOS
            if (probMeds > probMedsAdv) {
                avanVSdef(atq,adv, duracao , jogo);
            } else medsVSmeds(adv,atq,duracao, jogo);
        }
    }




}

package Projeto_POO.src;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Jogador> jmemoria;
    private ArrayList<Equipa> ememoria;


    public Controller(ArrayList<Jogador>j,ArrayList<Equipa> nmemoria) {
        this.jmemoria = j;
        this.ememoria = nmemoria;
    }
    /*
    public void guardaEquipaCasa(String filename) throws IOException{
        j.guardaEquipa(filename);
    }
    */

    public void guardaEquipa(String filename,Equipa e) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(e);
        oos.flush();
        oos.close();
    }
    public Equipa carregaEquipa(String filename) throws ClassNotFoundException, IOException{


        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Equipa e = (Equipa) ois.readObject();
        ois.close();
        return e;

    }


    public Jogador getJogador(String jogEscolhido)  {
        for (Jogador j : View.getJogadoresEmMemoria()) {
            if (j.getNomeJogador().equals(jogEscolhido)) {
                return j.clone();
            }
        }
        return null;
    }
    public Equipa getEquipa(String nome) {
        for(Equipa e : View.getEquipasEmMemoria()){
            if(e.getNome().equals(nome)){
                return e.clone();
            }
        }
        return null;
    }


    public Jogador carregaJogador(String filename)  throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Jogador j = (Jogador) ois.readObject();
        ois.close();
        return j;

    }

    public void guardarJogador(String filename, Jogador guardar) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(guardar);
        oos.flush();
        oos.close();
    }
}

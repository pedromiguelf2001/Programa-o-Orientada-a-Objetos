package Projeto_POO.src;

public class Main {
    public static void main(String[] args) {
        Controller cont = new Controller(View.getJogadoresEmMemoria(),View.getEquipasEmMemoria());
        View v = new View(cont);
        v.run();
    }

}

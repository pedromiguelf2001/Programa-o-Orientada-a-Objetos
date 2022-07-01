package Projeto_POO.src;



import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class View {


    private Controller c;
    private static ArrayList<Jogador> jogadoresEmMemoria = new ArrayList<>();
    private static ArrayList<Equipa> equipasEmMemoria = new ArrayList<>();
    private static ArrayList<Jogo> jogosemMemoria = new ArrayList<>();



    public View(Controller c){
        this.c = c;
    }

    public Controller getC() {
        return c;
    }

    public void setC(Controller c) {
        this.c = c;
    }

    public static ArrayList<Jogador> getJogadoresEmMemoria() {
        return jogadoresEmMemoria;
    }

    public static void setJogadoresEmMemoria(ArrayList<Jogador> jogadoresEmMemoria) {
        View.jogadoresEmMemoria = jogadoresEmMemoria;
    }

    public static ArrayList<Equipa> getEquipasEmMemoria() {
        return equipasEmMemoria;
    }

    public static void setEquipasEmMemoria(ArrayList<Equipa> equipasEmMemoria) {
        View.equipasEmMemoria = equipasEmMemoria;
    }

    public static ArrayList<Jogo> getJogosemMemoria() {
        return jogosemMemoria;
    }

    public static void setJogosemMemoria(ArrayList<Jogo> jogosemMemoria) {
        View.jogosemMemoria = jogosemMemoria;
    }

    public void run()  {

        Menu m = new Menu(new String []{
                "Jogar",
                "Opcoes Equipa",
                "Opcoes Jogador",
                "Opcoes Log"






        });

        int op;
        do{
            m.executa("Menu");
            op=m.getOpcao();
            switch(op){
                case 1:
                    menuJogar();

                    break;
                case 2:
                    menuEquipa();

                    break;
                case 3:
                    menuJogador();
                    break;
                case 4:
                    menuLog();

                    break;


                default:
                    System.out.println("----------------------------------------------");
                    break;
            }
        }while (op!=0);
    }

    private void menuLog() {
        Menu m = new Menu(new String []{
                "Guardar Log",
                "Carregar Log"


        });
        int op;
        do{
            m.executa("Opcoes Log");
            op=m.getOpcao();
            switch (op){
                case 1:
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Insira o nome do ficheiro que pretende guardar:");
                    String nome = sc.next();
                    try{
                        toCSV(nome);
                    }
                    catch (IOException e){
                        System.out.println("Nao foi possivel guardar o Log");
                    }
                    break;

                case 2:
                    Scanner load = new Scanner(System.in);
                    System.out.println("Insira o nome do Log a carregar");
                    String carregar = load.next();
                    loadLog(carregar);
                    constroiEquipas();

            }

        }while(op!=0);
    }

    private void constroiEquipas() {
        for (Equipa e : equipasEmMemoria){
           int titulares=11;
           ArrayList<Jogador> titular = new ArrayList<Jogador>();
           ArrayList<Jogador> banco = new ArrayList<>();
                for(Jogador j:e.getJog()){
                    if(titulares > 0){
                        titular.add(j.clone());
                        titulares--;
                    }
                    else{
                        banco.add(j.clone());
                    }

                }
                e.setJogadores(titular);
                e.setBanco(banco);
        }

    }

    private void loadLog(String nome)  {
        try{
            Parser.parse(nome);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Nao foi possivel carregar o log");
        }

    }

    public void carregarEquipa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome do ficheiro");
        String filename = sc.next();
        try{
            equipasEmMemoria.add(c.carregaEquipa(filename));
            for(Jogador j : c.carregaEquipa(filename).getJog()){
                jogadoresEmMemoria.add(j.clone());
            }
            for(Jogador j : c.carregaEquipa(filename).getBanco()){
                jogadoresEmMemoria.add(j.clone());
            }
            System.out.println("Equipa carregada com sucesso");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nao foi possivel carregar esta equipa");
        }
    }

    public void guardaEquipa() {
        Equipa guardar = new Equipa(listarEquipas());
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome do ficheiro");
        String filename = sc.next();
        try{
            c.guardaEquipa(filename,guardar);
            System.out.println("Equipa gravada com sucesso");


        } catch (IOException  e) {
            System.out.println("Nao foi possivel guardar a equipa");
        }
    }
    public Equipa listarEquipas()  {
        List<String> equipas = new ArrayList<>();
        for(Equipa e : View.equipasEmMemoria){
            equipas.add(e.clone().getNome());
        }
        if(equipas.isEmpty()){
            System.out.println("Nao existem equipas carregadas!");
        }
        else{
            Menu m = new Menu(equipas);
            m.executa("Equipas");
            int op = m.getOpcao();
            if(op!=0){
                String equipaEscolhida = equipas.get(op-1);
                Equipa escolhida = c.getEquipa(equipaEscolhida);
                System.out.println(escolhida.getNome()+":\n");
                System.out.println("11 TITULAR:\n");
                int soma  = 0;
                int njogador = 0;

                for(Jogador j : escolhida.getJog()){
                    soma += j.eval();
                    njogador++;
                    System.out.println(j.getPos()+" - " +j.getNumero()+ " - "+j.getNomeJogador()+ " - Overall: "+j.eval()+"\n");
                }
                System.out.println("BANCO:\n");
                for(Jogador j : escolhida.getBanco()){
                    System.out.println(j.getPos()+" - " +j.getNumero()+ " - "+j.getNomeJogador()+ " - Overall: "+j.eval()+"\n");
                }
                System.out.println("Overall da equipa: "+soma/njogador);
                return escolhida;
            }
        }
        return null;
    }




    public void listarPosicoes(){

        Menu p = new Menu(new String[]{
                "Guarda Redes",
                "Defesa",
                "Lateral",
                "Medio",
                "Avancado"});
        int op;
        //do{
        Scanner sc = new Scanner(System.in);
        p.executa("Criar Jogador");
        op = p.getOpcao();


            switch(op){
                case 1:
                    System.out.println("Insira o nome do Jogador:");
                    String nome = sc.nextLine();
                    System.out.println("\nInsira o numero do Jogador:");
                    int num = sc.nextInt();
                    System.out.println("\nInsira a velocidade do Jogador:");
                    int vel = sc.nextInt();
                    System.out.println("\nInsira a resitencia do Jogador:");
                    int res = sc.nextInt();
                    System.out.println("\nInsira a destreza do Jogador:");
                    int des = sc.nextInt();
                    System.out.println("\nInsira a impulsao do Jogador:");
                    int imp = sc.nextInt();
                    System.out.println("\nInsira a habilidade do cabeceamento do Jogador:");
                    int cab = sc.nextInt();
                    System.out.println("\nInsira o remate do Jogador:");
                    int rem = sc.nextInt();
                    System.out.println("\nInsira o passe do Jogador:");
                    int passe = sc.nextInt();
                    System.out.println("\nInsira a elasticidade do Jogador:");
                    int elas = sc.nextInt();
                    GuardaRedes gr = new GuardaRedes(nome,num,vel,res,des,imp,cab,rem,passe,elas);
                    jogadoresEmMemoria.add(gr.clone());



                    break;
                case 2:
                    System.out.println("Insira o nome do Jogador:");
                    String nomeD = sc.nextLine();
                    System.out.println("\nInsira o numero do Jogador:");
                    int numD = sc.nextInt();
                    System.out.println("\nInsira a velocidade do Jogador:");
                    int velD = sc.nextInt();
                    System.out.println("\nInsira a resitencia do Jogador:");
                    int resD = sc.nextInt();
                    System.out.println("\nInsira a destreza do Jogador:");
                    int desD = sc.nextInt();
                    System.out.println("\nInsira a impulsao do Jogador:");
                    int impD = sc.nextInt();
                    System.out.println("\nInsira a habilidade do cabeceamento do Jogador:");
                    int cabD = sc.nextInt();
                    System.out.println("\nInsira o remate do Jogador:");
                    int remD = sc.nextInt();
                    System.out.println("\nInsira o passe do Jogador:");
                    int passeD = sc.nextInt();
                    System.out.println("\nInsira a desarme do Jogador:");
                    int desarme = sc.nextInt();
                    Defesa def = new Defesa(nomeD,numD,velD,resD,desD,impD,cabD,remD,passeD,desarme);
                    jogadoresEmMemoria.add(def.clone());
                    break;
                case 3:
                    System.out.println("Insira o nome do Jogador:");
                    String nomeL = sc.nextLine();
                    System.out.println("\nInsira o numero do Jogador:");
                    int numL = sc.nextInt();
                    System.out.println("\nInsira a velocidade do Jogador:");
                    int velL = sc.nextInt();
                    System.out.println("\nInsira a resitencia do Jogador:");
                    int resL = sc.nextInt();
                    System.out.println("\nInsira a destreza do Jogador:");
                    int desL = sc.nextInt();
                    System.out.println("\nInsira a impulsao do Jogador:");
                    int impL = sc.nextInt();
                    System.out.println("\nInsira a habilidade do cabeceamento do Jogador:");
                    int cabL = sc.nextInt();
                    System.out.println("\nInsira o remate do Jogador:");
                    int remL = sc.nextInt();
                    System.out.println("\nInsira o passe do Jogador:");
                    int passeL = sc.nextInt();
                    System.out.println("\nInsira o cruzamento do Jogador:");
                    int cruzamentoL = sc.nextInt();
                    Lateral lat = new Lateral(nomeL,numL,velL,resL,desL,impL,cabL,remL,passeL,cruzamentoL);
                    jogadoresEmMemoria.add(lat);
                    break;
                case 4:
                    System.out.println("Insira o nome do Jogador:");
                    String nomeM = sc.nextLine();
                    System.out.println("\nInsira o numero do Jogador:");
                    int numM = sc.nextInt();
                    System.out.println("\nInsira a velocidade do Jogador:");
                    int velM = sc.nextInt();
                    System.out.println("\nInsira a resitencia do Jogador:");
                    int resM = sc.nextInt();
                    System.out.println("\nInsira a destreza do Jogador:");
                    int desM = sc.nextInt();
                    System.out.println("\nInsira a impulsao do Jogador:");
                    int impM = sc.nextInt();
                    System.out.println("\nInsira a habilidade do cabeceamento do Jogador:");
                    int cabM = sc.nextInt();
                    System.out.println("\nInsira o remate do Jogador:");
                    int remM = sc.nextInt();
                    System.out.println("\nInsira o passe do Jogador:");
                    int passeM = sc.nextInt();
                    System.out.println("\nInsira a recuperacao de bola do Jogador:");
                    int rec = sc.nextInt();
                    Medio med = new Medio(nomeM,numM,velM,resM,desM,impM,cabM,remM,passeM,rec);
                    jogadoresEmMemoria.add(med);
                    break;
                case 5:
                    System.out.println("Insira o nome do Jogador:");
                    String nomeA = sc.nextLine();
                    System.out.println("\nInsira o numero do Jogador:");
                    int numA = sc.nextInt();
                    System.out.println("\nInsira a velocidade do Jogador:");
                    int velA = sc.nextInt();
                    System.out.println("\nInsira a resitencia do Jogador:");
                    int resA = sc.nextInt();
                    System.out.println("\nInsira a destreza do Jogador:");
                    int desA = sc.nextInt();
                    System.out.println("\nInsira a impulsao do Jogador:");
                    int impA = sc.nextInt();
                    System.out.println("\nInsira a habilidade do cabeceamento do Jogador:");
                    int cabA = sc.nextInt();
                    System.out.println("\nInsira o remate do Jogador:");
                    int remA = sc.nextInt();
                    System.out.println("\nInsira o passe do Jogador:");
                    int passeA = sc.nextInt();
                    System.out.println("\nInsira a aceleracao do Jogador:");
                    int aceleracaoA = sc.nextInt();
                    Avancado avan = new Avancado(nomeA,numA,velA,resA,desA,impA,cabA,remA,passeA,aceleracaoA);
                    jogadoresEmMemoria.add(avan);
                    break;
                default:
                    System.out.println("----------------------------------------------");
                    break;
            }
        //}while (op!=0);



    }
    public void menuJogar(){
        Menu p = new Menu(new String[]{
                "Selecionar a equipa de casa",
                "Selecionar a equipa visitante",
                "Comecar o jogo",
                "Jogos no Log"
        });
        Equipa casa = new Equipa();
        Equipa fora = new Equipa();
        int op;
        do {
            System.out.println("Equipa selecionada - Casa: " + casa.getNome());
            System.out.println("Equipa selecionada - Fora: " + fora.getNome());
            p.executa("Jogar");
            op = p.getOpcao();
            switch (op) {
                //PODEMOS POR A MESMA EQUIPA A JOGAR CONTRA SI PROPRIA
                case 1:
                    //Selecionar equipa de casa
                    if(!equipasEmMemoria.isEmpty()) casa = new Equipa(listarEquipas());
                    break;


                case 2:
                    //Selecionar equipa de fora
                    if(!equipasEmMemoria.isEmpty()) fora = new Equipa(listarEquipas());
                    break;
                case 3:
                    //Verificar se selcionaram as duas equipas
                    if(!casa.getNome().equals("") & !fora.getNome().equals("")){
                        Jogo jogo = new Jogo(casa.getNome(), fora.getNome(),0,0, LocalDate.now(),
                                 casa.getJogadores(),casa.geraSubs(), fora.getJogadores(),fora.geraSubs());

                        int coin = ThreadLocalRandom.current().nextInt(0,2);
                        if(coin==0){
                            System.out.println("\nA bola começa do lado do: "+casa.getNome());
                            jogo.iniciarJogo(casa,fora,jogo,0);
                        }
                        else {
                            System.out.println("\n\nA bola começa do lado do: "+fora.getNome());
                            jogo.iniciarJogo(fora, casa, jogo, 0);
                        }
                        System.out.println("----------------------------------------------");
                        System.out.println("Golos Casa: "+jogo.getGolosCasa());
                        System.out.println("Golos Fora: "+jogo.getGolosFora());
                        getJogosemMemoria().add(jogo.clone());
                        if(jogo.getGolosFora()>jogo.getGolosCasa()){
                            System.out.println("\nGanha o "+jogo.getEquipaFora()+"!\n");
                        }
                        else if (jogo.getGolosFora()<jogo.getGolosCasa()){
                            System.out.println("\nGanha o "+jogo.getEquipaCasa()+"!\n");
                        }
                        else{System.out.println("Empate!\n");}
                    }
                    break;
                case 4:
                    if(View.jogosemMemoria.isEmpty()){
                        System.out.println("Nenhum Log foi carregado!");
                    }
                    else{
                        for(Jogo j :getJogosemMemoria()){
                            System.out.println(j.toString());
                        }
                    }
                default:
                    System.out.println("----------------------------------------------");
                    break;

            }
        }while(op!=0);

    }
    public void menuEquipa(){
        Menu m = new Menu(new String[]{
                "Criar Equipa",
                "Carregar Equipa",
                "Guardar Equipa",
                "Lista de Equipas",
                "Adicionar Jogador a uma Equipa",
                "Remover Jogador a uma Equipa"
        });
        int op;
        do{
            m.executa("Menu");
            op=m.getOpcao();
            switch(op){
                case 1:
                    criarEquipa();
                    break;
                case 2:
                    carregarEquipa();
                    break;
                case 3:
                    guardaEquipa();
                    break;
                case 4:
                    listarEquipas();
                    break;
                case 5:
                    addJogEquipa();
                    break;
                case 6:
                    removeJogEquipa();
                    break;
                default:
                    System.out.println("----------------------------------------------");
                    break;




            }
        }while(op!=0);
    }

    private void removeJogEquipa() {
        List<String> equipas = new ArrayList<>();
        List<String> jog = new ArrayList<>();
        for (Equipa e : View.equipasEmMemoria) {
            equipas.add(e.getNome());
        }
        if (equipas.isEmpty()) {
            System.out.println("Nao ha equipas carregadas");
        }
        else {
            Menu m = new Menu(equipas);
            m.executa("Equipas");
            int op = m.getOpcao();
            if(op!=0){
                String eqSelc = equipas.get(op-1);
                Equipa escolhida = null;
                for(Equipa e : equipasEmMemoria){
                    if(e.getNome().equals(eqSelc)){
                        escolhida = e;
                    }
                }
                try {
                    assert escolhida != null;
                    for(Jogador j : escolhida.getJog()){
                        jog.add(j.getNomeJogador());
                    }
                    if(jog.isEmpty()){
                        System.out.println("Nao existem jogadores na equipa");
                    }
                    else{
                        Menu j = new Menu(jog);
                        j.executa("Jogadores");
                        int opcao = j.getOpcao();

                        if(opcao!=0) {
                            String jogSelec = jog.get(op - 1);
                            Jogador escolhido = null;
                            for (Jogador jogador : escolhida.getJog()) {
                                if (jogador.getNomeJogador().equals(jogSelec)) {
                                    escolhido = jogador;
                                }
                            }
                            if(escolhido != null){
                                Equipa nova = new Equipa(escolhida.getNome());
                                for(Jogador elim : escolhida.getJog()) {
                                    if (!elim.equals(escolhido)) {
                                        nova.addJog(elim);
                                    }
                                }
                                for(Jogador ban : escolhida.getBanco()){
                                    nova.addJog(ban);
                                }
                                View.equipasEmMemoria.add(nova);
                                View.equipasEmMemoria.remove(escolhida);


                                System.out.println("Jogador removido com sucesso");
                            }
                            else{
                                System.out.println("Esse jogador nao existe!");
                            }
                        }
                    }
                }
                catch(NullPointerException e){
                    System.out.println("Nenhum Jogador selecionado!");
                }


            }

        }
    }

    public void menuJogador(){
        Menu m = new Menu(new String[]{
                "Criar Jogador",
                "Carregar Jogador",
                "Guardar Jogador",
                "Lista de Jogadores"
        });
        int op;
        do{
            m.executa("Menu Jogador");
            op=m.getOpcao();
            switch(op){
                case 1:
                    listarPosicoes();
                    break;
                case 2:
                    carregarJogador();
                    break;
                case 3:
                    guardarJogador();
                    break;
                case 4:
                    listarJogadores();
                    break;
                default:
                    System.out.println("----------------------------------------------");
                    break;


            }
        }while (op!=0);
    }

    private void guardarJogador() {
        Jogador guardar = listarJogadores().clone();
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome do ficheiro");
        String filename = sc.next();
        try{
            c.guardarJogador(filename,guardar);
            System.out.println("Jogador gravado com sucesso");
        }
        catch(IOException e){
            System.out.println("Nao foi possivel gravar o jogador");
        }

    }

    private void carregarJogador() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome do ficheiro");
        String filename = sc.next();
        try{
            jogadoresEmMemoria.add(c.carregaJogador(filename));
            System.out.println("Jogador carregado com sucesso");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Nao foi possivel carregar este jogador");
        }
    }

    public Jogador listarJogadores()  {
        List<String> nomes = new ArrayList<>();
        for (Jogador j : View.jogadoresEmMemoria) {
            nomes.add(j.clone().getNomeJogador());
        }
        if (nomes.isEmpty()) {
            System.out.println("Nao ha jogadores carregados");
        } else {
            Menu m = new Menu(nomes);
            m.executa("Jogadores");
            int op = m.getOpcao();
            if(op!=0){
                String jogEscolhido = nomes.get(op - 1);
                Jogador escolhido = c.getJogador(jogEscolhido);
                System.out.println("Detalhes do jogador: ");
                System.out.println(escolhido.toString());
                System.out.println("Overall: "+escolhido.eval());
                return escolhido;
            }
        }

        return null;
    }
    public void criarEquipa(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o nome da equipa: ");
        String nome = sc.nextLine();
        Equipa e = new Equipa(nome);
        equipasEmMemoria.add(e.clone());
    }
    public void addJogEquipa(){

            List<String> equipas = new ArrayList<>();
            for (Equipa e : View.equipasEmMemoria) {
                equipas.add(e.getNome());
            }
            if (equipas.isEmpty()) {
                System.out.println("Nao ha equipas carregadas");
            }
            else {
                Menu m = new Menu(equipas);
                m.executa("Equipas");
                int op = m.getOpcao();
                if(op!=0){
                    String eqSelc = equipas.get(op-1);
                    Equipa escolhida = null;
                    for(Equipa e : equipasEmMemoria){
                        if(e.getNome().equals(eqSelc)){
                            escolhida = e;
                        }
                    }
                    try {
                        Jogador jog = listarJogadores();
                        assert escolhida != null;
                        escolhida.addJog(jog);
                        jog.getHistorial().add(escolhida.getNome());
                    }
                    catch(NullPointerException e){
                        System.out.println("Nenhum Jogador selecionado!");
                    }


                }

            }

    }
    public void toCSV(String filename) throws IOException {
        PrintWriter file = new PrintWriter(filename);
        for(Jogo j: View.getJogosemMemoria()){
            file.println(j.toString());
        }
        file.flush();
        file.close();
    }



}



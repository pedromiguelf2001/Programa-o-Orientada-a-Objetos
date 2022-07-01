package Projeto_POO.src;


import java.io.Serializable;

public class Medio extends Jogador implements Serializable {
    private int recuperacao;

    //CONSTRUTOR PARAMETERIZADO
    public Medio(String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int rec) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        recuperacao = rec;

    }
    //CONSTRUTOR DE COPIA
    public Medio(Medio medio){
        super(medio);
        this.recuperacao = medio.getRecuperacao();
    }
    //GETTER E SETTER PARA RECUPERACAO DE BOLAS
    public int getRecuperacao(){
        return recuperacao;
    }
    public void setRecuperacao(int rB){
        this.recuperacao = rB;
    }

    //EQUALS
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Medio jog = (Medio) object;
        return  this.getNomeJogador().equals(jog.getNomeJogador()) &&
                this.getNumeroJogador() == jog.getNumeroJogador()  &&
                this.getVelocidade() == jog.getVelocidade()        &&
                this.getResistencia() == jog.getResistencia()      &&
                this.getDestreza() == jog.getDestreza()            &&
                this.getImpulsao() == jog.getImpulsao()            &&
                this.getCabeca() == jog.getCabeca()                &&
                this.getRemate() == jog.getRemate()                &&
                this.getPasse() == jog.getPasse()                  &&
                this.getHistorial().equals(jog.getHistorial())     &&
                jog.getRecuperacao() == this.recuperacao;
    }
    //CLONE


    public Medio clone(){
        return  new Medio(this);
    }

    //TOSTRING
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\nRecuperacao de Bolas: ").append(this.getRecuperacao());
        return sb.toString();
    }

    public int eval(){
        int overall = 100;
        overall = (int) (overall    -(((100-this.getRecuperacao())      *0.95)
                                    + ((100-this.getVelocidade())       *0.7)
                                    + ((100-this.getResistencia())      *0.7)
                                    + ((100-this.getDestreza())         *0.6)
                                    + ((100-this.getImpulsao())         *0.4)
                                    + ((100-this.getCabeca())           *0.3)
                                    + ((100-this.getRemate())           *0.5)
                                    + ((100-this.getPasse())            *0.9))/8);


        return overall;
    }
    public String getPos(){
        return "Medio";
    }


    public static Medio parse(String input){
        String[] campos = input.split(",");
        return new Medio(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9]));
    }


}

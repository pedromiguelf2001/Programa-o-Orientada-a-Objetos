package Projeto_POO.src;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Avancado extends Jogador implements Serializable {
    //private static final long serialVersionUID = -5108118841006066145;
    private int aceleracao;


    public Avancado(){
        super();
        aceleracao = ThreadLocalRandom.current().nextInt(50,101);
    }

    public Avancado(String nome,int num,int vel , int res , int des , int imp , int jC, int rem , int cP, int acel){
        super(nome,num,vel,res,des,imp,jC,rem,cP);
        this.aceleracao = acel;
    }
    public Avancado (Avancado avancado){
        super(avancado);
        this.aceleracao = avancado.getAceleracao();
    }

    public int getAceleracao() {
        return aceleracao;
    }

    public void setAceleracao(int acel) {
        this.aceleracao = acel;
    }

    //EQUALS
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Avancado jog = (Avancado) object;
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
                jog.getAceleracao() == this.aceleracao;
    }
    //CLONE
    public Avancado clone(){
        return new Avancado(this);
    }

    //TOSTRING
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\nAceleracao: ").append(this.getAceleracao());
        return sb.toString();
    }
    public int eval(){
        int overall = 100;
        overall = (int) (overall    -(((100-this.getAceleracao())       *0.95)
                                    + ((100-this.getVelocidade())       *0.9)
                                    + ((100-this.getResistencia())      *0.7)
                                    + ((100-this.getDestreza())         *0.9)
                                    + ((100-this.getImpulsao())         *0.7)
                                    + ((100-this.getCabeca())           *0.9)
                                    + ((100-this.getRemate())           *0.95)
                                    + ((100-this.getPasse())            *0.5))/8);


        return overall;
    }
    public String getPos(){
        return "Avancado";
    }

    public static Avancado parse(String input){
        String[] campos = input.split(",");
        return new Avancado(campos[0], Integer.parseInt(campos[1]),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                ThreadLocalRandom.current().nextInt(50, 100 + 1));

    }

}

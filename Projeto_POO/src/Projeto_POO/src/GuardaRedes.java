package Projeto_POO.src;


import java.io.Serializable;

public class GuardaRedes extends Jogador implements Serializable {
    //Classe Guarda Redes criada a partir de uma super classe Jogador

    private int elasticidade;


    //GETTERS E SETTERS
    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }
    //FIM GETTERS E SETTERS


    //CONSTRUTORES


    public GuardaRedes (String nomeJ, int numeroJ, int vel, int res, int des, int imp, int cab, int rem, int p, int elast) {
        super(nomeJ, numeroJ, vel, res, des, imp, cab, rem, p);
        elasticidade = elast;
    }

    public GuardaRedes(GuardaRedes g) {
        super(g);
        this.elasticidade = g.getElasticidade();
    }


    //TOSTRING , CLONE E EQUALS
    public String toString() {
        return (super.toString() + "\nElasticidade: " + this.elasticidade);
    }

    public GuardaRedes clone() {
        return new GuardaRedes(this);
    }


    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GuardaRedes jog = (GuardaRedes) object;
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
                jog.getElasticidade() == this.elasticidade;

    }
    public int eval(){
        int overall = 100;
        overall = (int) (overall    -(((100-this.getElasticidade())       *0.95)
                                    //Nao precisa de velocidade
                                    //Nao precisa de resitencia
                                    + ((100-this.getDestreza())         *0.6)
                                    + ((100-this.getImpulsao())         *0.9)
                                    + ((100-this.getCabeca())       *0.1)
                                    + ((100-this.getRemate())           *0.6)
                                    + ((100-this.getPasse())  *0.9))/6);


        return overall;
    }
    public String getPos(){
        return "Guarda-Redes";
    }

    public static GuardaRedes parse(String input){
        String[] campos = input.split(",");
        return new GuardaRedes(campos[0], Integer.parseInt(campos[1]),
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

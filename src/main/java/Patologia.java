public class Patologia {

    private String nome;
    private double riscoComplicador;

    public Patologia(String nome, double riscoComplicador) {
        setNome(nome);
        setRiscoComplicador(riscoComplicador);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRiscoComplicador(double riscoComplicador) {
        this.riscoComplicador = riscoComplicador;
    }

    public String getNome() {
        return nome;
    }

    public double getRiscoComplicador() {
        return riscoComplicador;
    }

}

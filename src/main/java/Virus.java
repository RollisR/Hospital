public class Virus {

    private String nome;
    private double probInfeccao;

    public Virus (String nome, double probInfeccao) {
        setNome(nome);
        setProbInfeccao(probInfeccao);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProbInfeccao(double probInfeccao) {
        this.probInfeccao = probInfeccao;
    }

    public String getNome() {
        return nome;
    }

    public double getProbInfeccao() {
        return probInfeccao;
    }
}

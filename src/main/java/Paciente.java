import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private String nome;
    private int idade;
    private List<Patologia> listaPatologias;

    public Paciente(String nome, int idade) {
        setNome(nome);
        setIdade(idade);
        listaPatologias = new ArrayList<Patologia>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void addPatologia(Patologia patologia) {
        listaPatologias.add(patologia);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public List<Patologia> getListaPatologias() {
        return listaPatologias;
    }
}

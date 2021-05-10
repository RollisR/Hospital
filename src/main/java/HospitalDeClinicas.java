import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;

public class HospitalDeClinicas implements AmbienteHospitalar {

    private static Virus virus;
    private List<Paciente> listaDePacientes;
    private List<Paciente> pacientesInfectados;

    public HospitalDeClinicas() {
        listaDePacientes = new ArrayList<>();
        pacientesInfectados = new ArrayList<>();
    }

    public void addPaciente(Paciente paciente){
        listaDePacientes.add(paciente);
    }

    public void setVirus(Virus virus) {
        this.virus = virus;
    }

    //Gera as infecçoẽs em todos os pacientes do hospital - Itens 6 e 7
    public void gerarInfeccoes() {
        //Inicia o gerador de dados aleatórios
        MockNeat mock = MockNeat.threadLocal();


        for (Paciente paciente: listaDePacientes) {
            //A chance básica de Infecção é dada pelo Vírus 7.a
            double chanceDeContaminacao = virus.getProbInfeccao();

            //Adiciona o fator complicador 7.b
            if (paciente.getIdade() > 70) {
                chanceDeContaminacao += 0.229;
            }

            //Pega a lista de patologias do paciente, se ele tiver alguma patologia, adiciona o risco complicador à prob
            // de infecção 7.c
            if (paciente.getListaPatologias().size() > 0) {
                List<Patologia> listaPatologias = paciente.getListaPatologias();
                for (Patologia patologia: listaPatologias) {
                    chanceDeContaminacao += patologia.getRiscoComplicador();
                }
            }

            //Gera um número aleatório, se esse número for menor do que a chance de contaminação o peciente será infectado
            if (mock.floats().get() <= chanceDeContaminacao) {
                pacientesInfectados.add(paciente);
            }
        }

    }

    public String getRelatorioContaminacao() {
        String relatorio = "Relatório de Contaminação no Hospital\n";
        relatorio = relatorio + "Pacientes infectados pelo Vírus: " + virus.getNome() + "\n";
        relatorio += "=======================================================================\n";
        for (Paciente paciente:pacientesInfectados) {
            relatorio = relatorio + "Nome: " + paciente.getNome() + "\n";
            relatorio = relatorio + "Idade: " + paciente.getIdade() + " anos\n";
            relatorio += "*************************************************************************\n";
            relatorio += "Patologias do Paciente\n";
            List<Patologia> patologiasDoPaciente = paciente.getListaPatologias();
            if (patologiasDoPaciente.size() == 0) {
                relatorio += "- Nada Consta\n";
            }
            for (Patologia patologia : patologiasDoPaciente) {
                relatorio = relatorio + "- " + patologia.getNome() + "\n";
            }

            relatorio += "=======================================================================\n";
        }

        relatorio += "Total de Pacientes internados: " + listaDePacientes.size() + "\n";
        relatorio += "Total de Pacientes infectados: " + pacientesInfectados.size() + "\n";
        double taxaInfectados = ((double) pacientesInfectados.size() / listaDePacientes.size()) * 100;
        String s = String.format("Percentual de pacientes infectados: %.2f %%", taxaInfectados);
        relatorio += s;

        return relatorio;
    }
}

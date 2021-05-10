import net.andreinc.mockneat.MockNeat;

import java.util.ArrayList;
import java.util.List;

import static net.andreinc.mockneat.MockNeat.threadLocal;
import static net.andreinc.mockneat.unit.types.Ints.ints;

public class Driver {

    public static void main(String[] args) {
        //Inicia o MockNeat, objeto que cria dados aleatórios
        MockNeat mock;
        mock = threadLocal();

        //Cria o ambiente hospitalar
        AmbienteHospitalar hospital = new HospitalDeClinicas();
        //Cria o vírus -- Requisito 3
        Virus virus = new Virus("Covid", 0.02);
        hospital.setVirus(virus);

        //Cria as patologias -- Requisito 2 --
        List<Patologia> listaPatologias = new ArrayList<>();
        listaPatologias.add(new Patologia("Diabetes", 0.26));
        listaPatologias.add(new Patologia("Anemia", 0.15));
        listaPatologias.add(new Patologia("Osteoporose", 0.12));
        listaPatologias.add(new Patologia("Reumatismo", 0.3));
        listaPatologias.add(new Patologia("Artrose", 0.51));
        listaPatologias.add(new Patologia("Artrite reumatóide", 0.17));
        listaPatologias.add(new Patologia("Otite", 0.243));
        listaPatologias.add(new Patologia("Hipertensão", 0.105));
        listaPatologias.add(new Patologia("Depressão", 0.155));
        listaPatologias.add(new Patologia("Ansiedade", 0.0992));

        //Monta o conjunto de 2000 pacientes -- Requisito 1
        for(int i = 0; i < 2000; i++) {

            //cria um novo paciente com nome e idades aleatórios
            String nome = mock.names().get();
            int idade = ints().range(1, 92).get();
            Paciente paciente = new Paciente(nome, idade);

            //Define se o paciente vai possuir alguma patologia
            boolean hasPatologia = mock.bools().get();

            while (hasPatologia) {
                //Pega uma patologia da lista geral
                Patologia novaPatologia = listaPatologias.get(ints().range(0,9).get());
                //Pega a lista de Patologias que o paciente já tem
                List<Patologia> patologiasPaciente = paciente.getListaPatologias();
                //Sai do laço quando o paciente já tem todas as patologias listadas
                if (patologiasPaciente.size() == 10)
                    break;

                //verifica se a patologia nova já foi adicionada antes
                if (!patologiasPaciente.contains(novaPatologia)){
                    paciente.addPatologia(novaPatologia);
                }

                //atualiza o controle para adicionar ou não mais uma patologia
                hasPatologia = mock.bools().get();
            }

            hospital.addPaciente(paciente);
        }

        hospital.gerarInfeccoes();
        //Gera o relatório -- Requisito 4
        System.out.println(hospital.getRelatorioContaminacao());

    }
}

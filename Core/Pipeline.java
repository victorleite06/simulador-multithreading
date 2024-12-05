package Core;
import java.util.ArrayList;
import java.util.List;

public class Pipeline {
    private List<Instrucao> estagios; // Estágios do pipeline
    private List<Instrucao> instrucoesFinalizadas; // Instruções finalizadas (para estatísticas)
    private int ciclos; // Contador de ciclos
    private int bolhas; // Contador de bolhas

    public Pipeline(int numeroEstagios) { 
        estagios = new ArrayList<>(); // Inicializa lista de estágios
        instrucoesFinalizadas = new ArrayList<>(); // Inicializa lista de instruções finalizadas
        ciclos = 0;
        bolhas = 0;

        // Inicializa estágios vazios
        for (int i = 0; i < numeroEstagios; i++) { 
            estagios.add(null); 
        }
    }

    public void avancarCiclo(Instrucao novaInstrucao) {
        if (detecConflito(novaInstrucao)) { // Verifica se há conflito de dados antes de avançar o ciclo
            estagios.add(0, new Instrucao(InstrucaoTipoEnum.NOP, null, null, null)); // Adiciona uma bolha (NOP) no pipeline
            bolhas++;
        } else {
            Instrucao finalizada = estagios.remove(estagios.size() - 1); // Remove a última instrução do pipeline
            if (finalizada != null) { // Armazena a instrução finalizada na lista de instruções finalizadas
                instrucoesFinalizadas.add(finalizada); 
            }        

            estagios.add(0, novaInstrucao); // Adiciona uma nova instrução no início do pipeline
        }

        ciclos++; // Incrementa o contador de ciclos
    }

    private boolean detecConflito(Instrucao novaInstrucao) { // Verifica se há conflito em dependências de dados
        if (novaInstrucao == null){
            return false;
        }else{
            for (Instrucao instrucao : estagios) { 
                // Verifica se a instrução está usando o mesmo registrador que a nova instrução (dependência de dados)
                if (instrucao != null && instrucao.getRegistradorIndex() != null) {
                    // RAW - leitura antes da escrita
                    if (novaInstrucao.getOp1() != null && novaInstrucao.getOp1().equals(instrucao.getRegistradorIndex())) {
                        return true;
                    }

                    // WAW - duas instruções escrevendo no mesmo registrador
                    if (novaInstrucao.getRegistradorIndex() != null && novaInstrucao.getRegistradorIndex().equals(instrucao.getRegistradorIndex())) {
                        return true;
                    }

                    // WAR - escrita depois de leitura
                    if (novaInstrucao.getOp2() != null && novaInstrucao.getOp2().equals(instrucao.getRegistradorIndex())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int getCiclos() {
        return ciclos;
    }

    public int getBolhas() {
        return bolhas;
    }
}

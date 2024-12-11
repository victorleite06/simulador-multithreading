/*
 * Arquitetura BMT (Burst Mode Threading):
    - A arquitetura **BMT** é projetada para executar múltiplas instruções simultaneamente em "blocos" (ou ciclos de execução em paralelo), aumentando a eficiência da execução.
    - Em vez de processar uma instrução por ciclo, vários blocos de instruções são executados ao mesmo tempo, o que otimiza a utilização do hardware e reduz o número de ciclos necessários para concluir um conjunto de instruções.
    - Ideal para sistemas que buscam alta paralelização e execução em massa de operações, típicas em tarefas como processamento de dados ou gráficos.
 */

package Arquiteturas;

import Core.Instrucao;

public class BMT extends ArquiteturaBase {

    private int pipelineIF = -1; // Instruction Fetch
    private int pipelineID = -1; // Instruction Decode
    private int pipelineEX = -1; // Execute
    private int pipelineWB = -1; // Write Back

    // Construtores
    public BMT(int numeroRegistradores) {
        super(numeroRegistradores, 1024); // Inicializa com memória de tamanho 1024
    }

    // Sobrescreve o método de execução do ciclo (Lógica de execução do BMT)
    @Override
    protected void executarCiclo() {
        if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
            pararExecucao();
            return;
        }

        // Em um ciclo, até 4 instruções podem ser processadas
        int maxInstrucoesPorCiclo = 4;

        // Processa até o limite de instruções por ciclo
        for (int i = 0; i < maxInstrucoesPorCiclo && !this.instrucoes.isEmpty(); i++) {
            Instrucao instrucao = this.instrucoes.remove(0);
            System.out.println("Executando instrução " + instrucao.getTipo() + " no BMT");

            // Simula o pipeline
            fetch(instrucao);
            decode();
            execute();
            writeBack();
        }

        // Incrementa o ciclo de execução
        incrementarCiclo();
    }

    // Simula a fase de busca da instrução (Instruction Fetch)
    private void fetch(Instrucao instrucao) {
        pipelineIF = instrucao.getOp1();
    }

    // Simula a fase de decodificação da instrução (Instruction Decode)
    private void decode() {
        pipelineID = pipelineIF;
    }

    // Simula a fase de execução (Execute)
    private void execute() {
        pipelineEX = pipelineID;
    }

    // Simula a fase de escrita de volta (Write Back)
    private void writeBack() {
        pipelineWB = pipelineEX;
    }
}

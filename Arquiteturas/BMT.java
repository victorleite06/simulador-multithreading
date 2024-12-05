/*
 * Arquitetura BMT (Burst Mode Threading):
    - A arquitetura **BMT** é projetada para executar múltiplas instruções simultaneamente em "blocos" (ou ciclos de execução em paralelo), aumentando a eficiência da execução.
    - Em vez de processar uma instrução por ciclo, vários blocos de instruções são executados ao mesmo tempo, o que otimiza a utilização do hardware e reduz o número de ciclos necessários para concluir um conjunto de instruções.
    - Ideal para sistemas que buscam alta paralelização e execução em massa de operações, típicas em tarefas como processamento de dados ou gráficos.
 */

package Arquiteturas;

import Core.Instrucao;

public class BMT extends ArquiteturaBase {
    // Construtores
    public BMT(int numeroRegistradores) {
        super(numeroRegistradores);
    }

    // Sobrescreve o método de execução do ciclo (Lógica de execução do BMT)
    @Override
    protected void executarCiclo() {
        if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
            pararExecucao();
            return;
        }

        int maxInstrucoesPorCiclo = 4;  // Número máximo de instruções a serem processadas por ciclo

        // Processa as instruções em blocos
        for (int i = 0; i < maxInstrucoesPorCiclo && !this.instrucoes.isEmpty(); i++) { // Processa até o limite de instruções por ciclo
            Instrucao instrucao = this.instrucoes.remove(0);
            System.out.println("Executando instrução " + instrucao.getTipo() + " no BMT");

            // Adicionar a lógica específica para manipular os registradores, realizando as operações necessárias com base no tipo de instrução
            executarInstrucoes(instrucao);
        }

        this.cicloExecucao++; // Incrementa o contador de ciclos
    }

    // Método para executar uma instrução individualmente
    private void executarInstrucoes(Instrucao instrucao) {
        // Implementar o restante das lógica de instruçoês, manipulando os registradores
        switch (instrucao.getTipo()) { // Verifica o tipo de instrução
            case ADD: // Adição
                int op1 = lerRegistrador(instrucao.getOp1()); // Lê o valor do registrador 
                int op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                int resultado = op1 + op2; // Realiza a operação
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado); // Escreve o resultado no registrador
                break; // Sai do switch

            case SUB: // Subtração
                op1 = lerRegistrador(instrucao.getOp1()); // Lê o valor do registrador
                op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                resultado = op1 - op2; // Realiza a operação
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado); // Escreve o resultado no registrador
                break; // Sai do switch

            case DIV:
                op1 = lerRegistrador(instrucao.getOp1()); // Lê o valor do registrador
                op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                resultado = op1 / op2; // Realiza a operação
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado); // Escreve o resultado no registrador
                break; // Sai do switch

            case MUL:
                op1 = lerRegistrador(instrucao.getOp1()); // Lê o valor do registrador
                op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                resultado = op1 * op2; // Realiza a operação
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado); // Escreve o resultado no registrador
                break; // Sai do switch

            case LW:
                op1 = instrucao.getOp1(); // Pega valor constante
                op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                escreverRegistrador(instrucao.getRegistradorIndex(), lerRegistrador(op1 + op2)); // Lê e escreve no registrador
                break; // Sai do switch

            case SW:
                op1 = instrucao.getOp1(); // Pega valor constante
                op2 = lerRegistrador(instrucao.getOp2()); // Lê o valor do registrador
                escreverRegistrador((op1 + op2), lerRegistrador(instrucao.getRegistradorIndex())); // Lê e escreve no registrador
                break; // Sai do switch

            case BEQ:

                break; // Sai do switch

            case BNE:

                break; // Sai do switch

            case JMP:

                break; // Sai do switch

            case HALT:
                pararExecucao(); // Para a execução da simulação
                break; // Sai do switch

            default: // Caso a instrução não seja suportada
                System.out.println("Instrução não suportada no BMT");
                break;
        }
    }
}

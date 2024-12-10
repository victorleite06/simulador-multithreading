package Arquiteturas;

import java.util.List;

import Core.Instrucao;
import Core.Pipeline;

import java.util.ArrayList;

public abstract class ArquiteturaBase {
    // Armazenamento dos registradores e instruções
    protected List<Integer> registradores;  // Lista de registradores
    protected List<Instrucao> instrucoes;  // Fila de instruções a serem executadas
    
    // Estado da execução
    protected boolean emExecucao; // Indica se a execução está em andamento
    protected int cicloExecucao; // Contador de ciclos de execução

    // Construtores
    public ArquiteturaBase(int numeroRegistradores) {
        this.registradores = new ArrayList<>(numeroRegistradores);
        this.instrucoes = new ArrayList<>();
        this.emExecucao = false;
        this.cicloExecucao = 0;

        // Inicializa os registradores (assumindo que são inteiros de 32 bits)
        for (int i = 0; i < numeroRegistradores; i++) {
            //this.registradores.add(0);  // Inicializando registradores com 0
            int random = (int) Math.ceil(Math.random() * 101);
            this.registradores.add(random > 0 ? random : (random * -1));  // Inicializando registradores com valores aleatórios
        }
    }

    // Métodos abstratos
    // Executar um ciclo de execução (Esse Método abstrato deve ser implementado nas subclasses)
    protected abstract void executarCiclo();

    // Adiciona uma instrução à fila de execução
    public void adicionarInstrucao(Instrucao instrucao) {
        this.instrucoes.add(instrucao);
    }

    // Executar as instruções (ciclo de execução)
    public void iniciarExecucao() {
        if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
            return;
        }
        this.emExecucao = true;
        this.cicloExecucao = 0;

        // Executa enquanto houver instruções na fila e a execução não foi interrompida
        while (this.emExecucao && !this.instrucoes.isEmpty()) {
            executarCiclo();
        }
    }

    // Método para parar a execução
    public void pararExecucao() {
        this.emExecucao = false;
    }

    // Métodos de acesso aos registradores
    public int lerRegistrador(int index) { // Retorna o valor do registrador
        if (index < 0 || index >= this.registradores.size()) {
            throw new IndexOutOfBoundsException("Índice de registrador inválido.");
        }
        return this.registradores.get(index);
    }

    // Escrever um valor no registrador manualmente
    public void escreverRegistrador(int index, int valor) {
        if (index < 0 || index >= this.registradores.size()) {
            throw new IndexOutOfBoundsException("Índice de registrador inválido.");
        }
        this.registradores.set(index, valor);
    }

    // Getters
    public List<Instrucao> getInstrucoes() {
        return instrucoes;
    }

    public boolean isEmExecucao() {
        return emExecucao;
    }

    public int getCicloExecucao() {
        return cicloExecucao;
    }

    public List<Integer> getRegistradores() {
        return registradores;
    }

    // Exemplo de método de resetar a arquitetura (caso seja necessário reiniciar o estado)
    public void resetarArquitetura() {
        this.registradores.clear();
        this.instrucoes.clear();
        this.emExecucao = false;
        this.cicloExecucao = 0;
        for (int i = 0; i < registradores.size(); i++) {
            this.registradores.add(0);  // Resetando os registradores
        }
    }

    // Método para executar uma instrução individualmente
    public void executarInstrucoes(Instrucao instrucao) {
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
                System.out.println("Instrução não suportada");
                break;
        }
    }
}

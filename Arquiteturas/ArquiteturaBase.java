package Arquiteturas;

import java.util.List;

import Core.Instrucao;
import Core.Pipeline;

import java.util.ArrayList;

public abstract class ArquiteturaBase {
    // Armazenamento dos registradores e instruções
    protected List<Integer> registradores;  // Lista de registradores
    protected List<Instrucao> instrucoes;  // Fila de instruções a serem executadas
    protected List<Integer> memoria; // Memória de dados

    // Estado da execução
    protected boolean emExecucao; // Indica se a execução está em andamento
    protected int cicloExecucao; // Contador de ciclos de execução
    protected int pc; // Program Counter (contador de programa)
    protected int instrucoesExecutadas = 0; // Declara e inicializa o contador

    // Construtores
    public ArquiteturaBase(int numeroRegistradores, int tamanhoMemoria) {
        this.registradores = new ArrayList<>(numeroRegistradores);
        this.instrucoes = new ArrayList<>();
        this.memoria = new ArrayList<>(tamanhoMemoria);
        this.emExecucao = false;
        this.cicloExecucao = 0;
        this.pc = 0; // Inicializa o contador de programa

        // Inicializa os registradores (assumindo que são inteiros de 32 bits)
        for (int i = 0; i < numeroRegistradores; i++) {
            int random = (int) Math.ceil(Math.random() * 101);
            this.registradores.add(random > 0 ? random : (random * -1));  // Inicializando registradores com valores aleatórios
        }

        // Inicializa a memória com 0s
        for (int i = 0; i < tamanhoMemoria; i++) {
            this.memoria.add(0);
        }
    }

    // Métodos abstratos
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
            incrementarCiclo();
        }
    }

    // Método para parar a execução
    public void pararExecucao() {
        this.emExecucao = false;
    }

    // Métodos de acesso aos registradores
    public int lerRegistrador(int index) {
        if (index < 0 || index >= this.registradores.size()) {
            throw new IndexOutOfBoundsException("Índice de registrador inválido.");
        }
        return this.registradores.get(index);
    }

    public void escreverRegistrador(int index, int valor) {
        if (index < 0 || index >= this.registradores.size()) {
            throw new IndexOutOfBoundsException("Índice de registrador inválido.");
        }
        this.registradores.set(index, valor);
    }

    // Métodos de acesso à memória
    public int lerMemoria(int endereco) {
        if (endereco < 0 || endereco >= this.memoria.size()) {
            throw new IndexOutOfBoundsException("Endereço de memória inválido.");
        }
        return this.memoria.get(endereco);
    }

    public void escreverMemoria(int endereco, int valor) {
        if (endereco < 0 || endereco >= this.memoria.size()) {
            throw new IndexOutOfBoundsException("Endereço de memória inválido.");
        }
        this.memoria.set(endereco, valor);
    }

    // Incrementa o contador de ciclos de execução
    public void incrementarCiclo() {
        this.cicloExecucao++;
    }

    // Reseta a arquitetura
    public void resetarArquitetura() {
        this.registradores.clear();
        this.instrucoes.clear();
        this.memoria.clear();
        this.emExecucao = false;
        this.cicloExecucao = 0;
        // Re-inicializa os registradores
        for (int i = 0; i < registradores.size(); i++) {
            this.registradores.add(0);  // Resetando os registradores
        }
    }

    // Método para executar uma instrução individualmente
    public void executarInstrucoes(Instrucao instrucao) {
        switch (instrucao.getTipo()) {
            case ADD: 
                int op1 = lerRegistrador(instrucao.getOp1());
                int op2 = lerRegistrador(instrucao.getOp2());
                int resultado = op1 + op2;
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado);
                break;

            case SUB: 
                op1 = lerRegistrador(instrucao.getOp1());
                op2 = lerRegistrador(instrucao.getOp2());
                resultado = op1 - op2;
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado);
                break;

            case DIV:
                op1 = lerRegistrador(instrucao.getOp1());
                op2 = lerRegistrador(instrucao.getOp2());
                if (op2 == 0) {
                    System.out.println("Erro: Divisão por zero.");
                    break;
                }
                resultado = op1 / op2;
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado);
                break;

            case MUL: 
                op1 = lerRegistrador(instrucao.getOp1());
                op2 = lerRegistrador(instrucao.getOp2());
                resultado = op1 * op2;
                escreverRegistrador(instrucao.getRegistradorIndex(), resultado);
                break;

            case LW:
                op1 = instrucao.getOp1(); 
                op2 = lerRegistrador(instrucao.getOp2()); 
                escreverRegistrador(instrucao.getRegistradorIndex(), lerMemoria(op1 + op2)); 
                break;

            case SW:
                op1 = instrucao.getOp1(); 
                op2 = lerRegistrador(instrucao.getOp2()); 
                escreverMemoria(op1 + op2, lerRegistrador(instrucao.getRegistradorIndex())); 
                break;

                case BEQ:
                op1 = lerRegistrador(instrucao.getOp1());
                op2 = lerRegistrador(instrucao.getOp2());
                // Desvia se os registradores forem iguais
                if (op1 == op2) {
                    this.pc = instrucao.getOp1(); // Salta para o endereço armazenado em op1**
                }
                break;

            case BNE:
                op1 = lerRegistrador(instrucao.getOp1());
                op2 = lerRegistrador(instrucao.getOp2());
                // Desvia se os registradores forem diferentes
                if (op1 != op2) {
                    this.pc = instrucao.getOp1(); // Salta para o endereço armazenado em op1**
                }
                break;

            case JMP:
                // Desvio incondicional
                this.pc = instrucao.getOp1(); // Atualiza o PC para o endereço de salto
                break;

            case HALT:
                // Interrompe a execução
                pararExecucao(); 
                break;

            default:
                System.out.println("Instrução não suportada");
                break;
        }
    }

    // Getters e Setters
    public List<Instrucao> getInstrucoes() {
        return new ArrayList<>(this.instrucoes);
    }

    public int getInstrucoesExecutadas() { // Getter para acessar o contador
        return instrucoesExecutadas;
    }

    public void incrementarInstrucoesExecutadas() {
        this.instrucoesExecutadas++;
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

    public List<Integer> getMemoria() {
        return memoria;
    }

    public int getPc() {
        return pc;
    }
}

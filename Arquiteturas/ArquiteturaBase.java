package Arquiteturas;

import java.util.List;

import Core.Instrucao;

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
            this.registradores.add(0);  // Inicializando registradores com 0
        }
    }

    // Métodos abstratos
    // Executar um ciclo de execução (Esse Método abstrato deve ser implementado nas subclasses)
    protected abstract void executarCiclo();

    // Adiciona uma instrução à fila de execução
    public void adicionarInstrução(Instrucao instrucao) {
        this.instrucoes.add(instrucao);
    }

    // Executar as instruções (ciclo de execução)
    public void iniciarExecucao() {
        if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
            throw new IllegalStateException("Nenhuma instrução para executar.");
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
}

/*
 * Arquitetura IMT (In-Order Multi-Threading):
    - A arquitetura **IMT** permite a execução de múltiplas threads em uma mesma unidade de execução, mas mantém a ordem das instruções.
    - As instruções são executadas na sequência em que são recebidas, mas diferentes threads podem ser processadas simultaneamente, aumentando a eficiência e a utilização do processador.
    - Essa abordagem é útil em cenários onde a ordem de execução é importante, mas ainda se busca algum nível de paralelismo.
 */

package Arquiteturas;
import Core.Instrucao;
import java.util.List;
import java.util.ArrayList;

public class IMT extends ArquiteturaBase {
    private static final int NUM_THREADS = 4; // Número de threads

    public IMT(int numeroRegistradores, int tamanhoMemoria) {
        super(numeroRegistradores, tamanhoMemoria); // Passando os parâmetros para a superclasse
    }

    @Override
    protected void executarCiclo() {
        if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
            pararExecucao();
            return;
        }

        // Cria e inicia múltiplas threads para processar as instruções
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS && !this.instrucoes.isEmpty(); i++) {
            final Instrucao instrucao = this.instrucoes.remove(0); // Remove a instrução da lista

            Thread thread = new Thread(() -> {
                System.out.println("Executando instrução " + instrucao.getTipo() + " em thread IMT");
                // Executa a instrução diretamente
                executarInstrucoes(instrucao);
            });

            threads.add(thread);
            thread.start();
        }

        // Espera todas as threads terminarem
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.cicloExecucao++; // Incrementa o contador de ciclos
    }
}


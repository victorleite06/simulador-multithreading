/*
 * Arquitetura SMT (Simultaneous Multithreading):
    - A arquitetura **SMT** permite que múltiplos threads sejam executados simultaneamente no mesmo núcleo.
    - Em vez de agrupar várias instruções em blocos, cada thread pode ter seu próprio conjunto de instruções e os threads podem ser processados em paralelo.
    - Ideal para sistemas que exigem alta utilização do processador, especialmente em tarefas que podem ser executadas em paralelo, como processamento de gráficos ou cálculos pesados.
 */
package Arquiteturas;

import Core.Instrucao;
 
import java.util.List;
import java.util.ArrayList;
 
 public class SMT extends ArquiteturaBase {
     private static final int NUM_THREADS = 4; // Número de threads simultâneas
     
     public SMT(int numeroRegistradores) {
         super(numeroRegistradores);
     }
 
     @Override
     protected void executarCiclo() {
         if (this.instrucoes.isEmpty()) { // Lista de instruções vazia
             pararExecucao();
             return;
         }
 
         // Cria e inicia múltiplas threads para processar as instruções simultaneamente
         List<Thread> threads = new ArrayList<>();
         for (int i = 0; i < NUM_THREADS && !this.instrucoes.isEmpty(); i++) {
             final Instrucao instrucao = this.instrucoes.remove(0); // Remove a instrução da lista
 
             Thread thread = new Thread(() -> {
                 System.out.println("Executando instrução " + instrucao.getTipo() + " em thread SMT");
                 iniciarExecucao();
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

/*
 * Arquitetura SMT (Simultaneous Multithreading):
    - A arquitetura **SMT** permite que múltiplos threads sejam executados simultaneamente no mesmo núcleo.
    - Em vez de agrupar várias instruções em blocos, cada thread pode ter seu próprio conjunto de instruções e os threads podem ser processados em paralelo.
    - Ideal para sistemas que exigem alta utilização do processador, especialmente em tarefas que podem ser executadas em paralelo, como processamento de gráficos ou cálculos pesados.
 */
package Arquiteturas;

import Core.Instrucao;
import Core.InstrucaoTipoEnum;

import java.util.List;
import java.util.ArrayList;
 
 public class SMT extends ArquiteturaBase {
     private static final int NUM_THREADS = 4;// Número de threads simultâneas
     private int ciclos; // Contador de ciclos
     private int bolhas; // Contador de bolhas
     
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
                 avancarCiclo(instrucao);
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

     public void avancarCiclo(Instrucao novaInstrucao) {
         if(!instrucoes.isEmpty()) {
             if (detecConflito(novaInstrucao)) { // Verifica se há conflito de dados antes de avançar o ciclo
                 instrucoes.add(0, new Instrucao(InstrucaoTipoEnum.NOP, null, null, null)); // Adiciona uma bolha (NOP) no pipeline
                 bolhas++;
             } else {
                 System.out.println("Executando instrução " + novaInstrucao.getTipo() + " em thread SMT");
                 Instrucao finalizada = instrucoes.remove(instrucoes.size() - 1); // Remove a última instrução do pipeline
                 if (finalizada != null) { // Armazena a instrução finalizada na lista de instruções finalizadas
                     executarInstrucoes(finalizada);
                 }

                 instrucoes.add(0, novaInstrucao); // Adiciona uma nova instrução no início do pipeline
             }

             ciclos++; // Incrementa o contador de ciclos
         }
     }

     private boolean detecConflito(Instrucao novaInstrucao) { // Verifica se há conflito em dependências de dados
         if (novaInstrucao == null){
             return false;
         }else{
             for (Instrucao instrucao : instrucoes) {
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

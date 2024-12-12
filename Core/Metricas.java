package Core;

import java.util.List;

import Arquiteturas.ArquiteturaBase;

public class Metricas {
    public static double calcularIPC(int instrucoesExecutadas, int ciclos) { // Cálculo do IPC (Instruções por ciclo)
        return (double) instrucoesExecutadas / ciclos;
    }

    public static double calcularCustoPorInstrucao(int instrucoesExecutadas, int ciclos) { // Cálculo do custo por instrução
        if (instrucoesExecutadas == 0){
            return 0.0;
        }
        return (double) ciclos / instrucoesExecutadas;
    }

    public static double calcularCustoBolhas(int bolhas, int ciclos) { // Cálculo do custo de bolhas
        if (ciclos == 0) {
            return 0.0;
        }
        return (double) bolhas / ciclos * 100; // Percentual de bolhas
    }

}

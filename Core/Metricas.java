package Core;

import java.util.List;

public class Metricas {
    public static double calcularIPC(int instrucoesExecutadas, int ciclos) { // Cálculo do IPC (Instruções por ciclo)
        return (double) instrucoesExecutadas / ciclos;
    }

    public static double calcularCustoPorInstrucao(List<Instrucao> instrucoes, int ciclos) { // Cálculo do custo por instrução
        if (instrucoes.isEmpty()){
            return 0.0;
        }
        return (double) ciclos / instrucoes.size();
    }

    public static double calcularCustoBolhas(int bolhas, int ciclos) { // Cálculo do custo de bolhas
        if (ciclos == 0) {
            return 0.0;
        }
        return (double) bolhas / ciclos * 100; // Percentual de bolhas
    }
}
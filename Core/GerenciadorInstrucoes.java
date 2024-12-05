package Core;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorInstrucoes { // Gerenciador de instrucoes para o processador
    private List<Instrucao> instrucoes; // Lista de instrucoes a serem executadas
    private int limite = 100; // Limite de instrucoes a serem executadas

    public GerenciadorInstrucoes() { // Construtor padrão
        instrucoes = new ArrayList<>();
    }

    public void adicionarInstrucao(Instrucao instrucao) { // Adiciona uma instrucao a lista de instrucoes
        if (instrucoes.size() >= limite) {
            throw new IllegalStateException("Buffer de instruções cheio.");
        }
        instrucoes.add(instrucao);
    }

    public Instrucao proximaInstrucao() { // Retorna a proxima instrucao a ser executada
        if (instrucoes.isEmpty()) return null;
        return instrucoes.remove(0);
    }

    public boolean temInstrucoes() { // Verifica se ainda existem instrucoes a serem executadas
        return !instrucoes.isEmpty();
    }

    public void reiniciar() { // Limpa a lista de instrucoes (Controle de Estado)
        instrucoes.clear();
    }
    
    public String listarInstrucoes() { // Lista as instrucoes pendentes (Debug)
        StringBuilder sb = new StringBuilder();
        for (Instrucao instrucao : instrucoes) {
            sb.append(instrucao).append("\n");
        }
        return sb.toString();
    }
    
}

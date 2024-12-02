import java.util.List;
import java.util.ArrayList;

public class ArquiteturaBase {
    private List<Integer> registradores;

    private List<Instrucao> instrucoes;

    public ArquiteturaBase() {
        this.registradores = new ArrayList<Integer>();
    }

    public void setRegistradores(List<Integer> registradores) { this.registradores = registradores; }
    public List<Integer> getRegistradores() { return this.registradores; } 

    public void setInstrucoes(List<Instrucao> instrucoes) { this.instrucoes = instrucoes; }
    public List<Instrucao> getInstrucoes() { return this.instrucoes; }
}
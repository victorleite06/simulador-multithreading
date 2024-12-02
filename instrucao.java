public class Instrucao {
    private InstrucaoTipo tipo; // tipo de instrucaos
    
    private Integer op1; // index do registrador ou constante
    private Integer op2; // index do registrador ou constante

    private Integer registradorIndex; // index do registrador de salvar ou leitura

    public Instrucao() {}

    public void setTipo(InstrucaoTipo tipo) { this.tipo = tipo; }
    public InstrucaoTipo getTipo() { return this.tipo; }

    public void setOp1(Integer op1) { this.op1 = op1; }
    public Integer getOp1() { return this.op1; }

    public void setOp2(Integer op2) { this.op2 = op2; }
    public Integer getOp2() { return this.op2; }

    public void setRegistradorIndex(Integer registradorIndex) { this.registradorIndex = registradorIndex; }
    public Integer getRegistradorIndex() { return this.registradorIndex; }
}
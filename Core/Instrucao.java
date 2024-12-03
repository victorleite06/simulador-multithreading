package Core;
public class Instrucao {
    private InstrucaoTipoEnum tipo; // tipo de instruções
    private Integer op1; // index do registrador ou constante
    private Integer op2; // index do registrador ou constante
    private Integer registradorIndex; // index do registrador de salvar ou leitura

    // Construtores
    public Instrucao() {
        this.tipo = null; 
        this.op1  = null;  
        this.op2  = null;
        this.registradorIndex = null;
    }

    public Instrucao(InstrucaoTipoEnum tipo, Integer op1, Integer op2, Integer registradorIndex) {
        this.tipo = tipo; 
        this.setOp1(op1);  // Validações no setter
        this.setOp2(op1);  // Validações no setter
        this.setRegistradorIndex(registradorIndex);  // Validações no setter
    }

    // Setters
    /*public void setTipo(InstrucaoTipoEnum tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de instrução não pode ser nulo.");
        }
        this.tipo = tipo;
    }*/

    public void setOp1(Integer op1) { 
        if (op1 == null || op1 < 0) {
            throw new IllegalArgumentException("Operando 1 não pode ser nulo ou negativo");
        }
        this.op1 = op1;
    }

    public void setOp2(Integer op2) { 
        if (op2 == null || op2 < 0) {
            throw new IllegalArgumentException("Operando 2 não pode ser nulo ou negativo");
        }
        this.op2 = op2;
    }

    public void setRegistradorIndex(Integer registradorIndex) { 
        if (registradorIndex == null || registradorIndex < 0 || registradorIndex >= 32) {  
            throw new IllegalArgumentException("Índice de registrador inválido. Esperado entre 0 e 31."); // Considerando (RISC-V com 32 registradores)
        }
        this.registradorIndex = registradorIndex;
    }

    // Getters
    public InstrucaoTipoEnum getTipo() {
        return this.tipo; 
    }

    public Integer getOp1() { 
        return this.op1; 
    }

    public Integer getOp2() { 
        return this.op2; 
    }

    public Integer getRegistradorIndex() { 
        return this.registradorIndex; 
    }
}
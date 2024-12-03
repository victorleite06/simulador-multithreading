package Core;
public enum InstrucaoTipoEnum { // Conjunto de instrucoes suportadas
    ADD, // Soma
    SUB, // Subtracao
    DIV, // Divisao
    MUL, // Multiplicacao
    LW,  // Load Word (Carrega um valor da memoria para um registrador)
    SW,  // Store Word (Salva um valor de um registrador na memoria)
    BEQ, // Branch if Equal (Desvia para um endereco se dois registradores forem iguais)
    BNE, // Branch if Not Equal (Desvia para um endereco se dois registradores forem diferentes)
    NOP,  // No Operation (Bolha - não faz nada)
    JMP,  // Jump (Desvio incondicional)
    HALT  // Halt (Fim da execução)
}
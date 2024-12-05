import Arquiteturas.ArquiteturaBase;
import Arquiteturas.BMT;
import Arquiteturas.IMT;
import Arquiteturas.SMT;
import Core.GerenciadorInstrucoes;
import Core.Instrucao;
import Core.InstrucaoTipoEnum;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Exibe as opções para o usuário escolher a arquitetura
        System.out.println("Escolha a arquitetura:");
        System.out.println("1 - BMT (Base Multithreading)");
        System.out.println("2 - SMT (Simultaneous Multithreading)");
        System.out.println("3 - IMT (Instruction Multiple Threading)");
        int escolha = scanner.nextInt();

        ArquiteturaBase arquitetura = null;

        // Base Multithreading (BMT), Simultaneous Multithreading (SMT) ou Instruction Multiple Threading (IMT)
        switch (escolha) {
            case 1:
                arquitetura = new BMT(32);  // 32 registradores para BMT
                break;
            case 2:
                arquitetura = new SMT(32);  // 32 registradores para SMT
                break;
            case 3:
                arquitetura = new IMT(32);  // 32 registradores para IMT
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        System.out.println("\nQuantas instruções serão criadas: (1 - 100)");
        int quantidadeInstrucoes = scanner.nextInt();

        GerenciadorInstrucoes gerenciadorInstrucoes = new GerenciadorInstrucoes();

        for(int i = 0; i < quantidadeInstrucoes; i++) {
            System.out.println("\nQual a instrução de número " + (i+1) + ": ");
            System.out.println("1 - ADD\t\t2 - SUB");
            System.out.println("3 - DIV\t\t4 - MUL");
            System.out.println("5 - LW\t\t6 - SW");
            System.out.println("7 - BEQ\t\t8 - BNE");
            System.out.println("9 - NOP\t\t10 - JMP");
            System.out.println("\t11 - HALT");
            int instrucaoIndex = scanner.nextInt();

            InstrucaoTipoEnum instrucao;
            int op1 = -1, op2 = -1, registradorIndex = -1;
            switch (instrucaoIndex) {
                case 1:
                    instrucao = InstrucaoTipoEnum.ADD;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " op1, op2, registrador");
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    registradorIndex = scanner.nextInt();
                    break;
                case 2:
                    instrucao = InstrucaoTipoEnum.SUB;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " op1, op2, registrador");
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    registradorIndex = scanner.nextInt();
                    break;
                case 3:
                    instrucao = InstrucaoTipoEnum.DIV;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " op1, op2, registrador");
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    registradorIndex = scanner.nextInt();
                    break;
                case 4:
                    instrucao = InstrucaoTipoEnum.MUL;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " op1, op2, registrador");
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    registradorIndex = scanner.nextInt();
                    break;
                case 5:
                    instrucao = InstrucaoTipoEnum.LW;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " registrador, op1, ( op2 )");
                    registradorIndex = scanner.nextInt();
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    break;
                case 6:
                    instrucao = InstrucaoTipoEnum.SW;
                    System.out.println("\nAgora preencha a instrução: ");
                    System.out.println(instrucao + " registrador, op1, ( op2 )");
                    registradorIndex = scanner.nextInt();
                    op1 = scanner.nextInt();
                    op2 = scanner.nextInt();
                    break;
                case 7:
                    instrucao = InstrucaoTipoEnum.BEQ;
                    break;
                case 8:
                    instrucao = InstrucaoTipoEnum.BNE;
                    break;
                case 9:
                    instrucao = InstrucaoTipoEnum.NOP;
                    break;
                case 10:
                    instrucao = InstrucaoTipoEnum.JMP;
                    break;
                case 11:
                    instrucao = InstrucaoTipoEnum.HALT;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(instrucao, op1, op2, registradorIndex));
        }
        
        // Adicionando algumas instruções para testes
        /*gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.ADD, 0, 1, 2));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.SUB, 1, 2, 3));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.MUL, 2, 3, 4));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.DIV, 3, 4, 5));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.LW, 4, 5, 6));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.SW, 5, 6, 7));*/
        
        // Adiciona as instruções à arquitetura selecionada
        while (gerenciadorInstrucoes.temInstrucoes()) {
            arquitetura.adicionarInstrucao(gerenciadorInstrucoes.proximaInstrucao());
        }
        
        // Inicia a execução da arquitetura selecionada
        arquitetura.iniciarExecucao();
        
        // Exibe o estado final dos registradores
        System.out.println("\nEstado final dos registradores:");
        for (int i = 0; i < arquitetura.getRegistradores().size(); i++) {
            System.out.println("R" + i + ": " + arquitetura.lerRegistrador(i));
        }
    }
}

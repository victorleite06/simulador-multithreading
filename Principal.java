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

        GerenciadorInstrucoes gerenciadorInstrucoes = new GerenciadorInstrucoes();
        
        // Adicionando algumas instruções para testes
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.ADD, 0, 1, 2));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.SUB, 1, 2, 3));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.MUL, 2, 3, 4));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.DIV, 3, 4, 5));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.LW, 4, 5, 6));
        gerenciadorInstrucoes.adicionarInstrucao(new Instrucao(InstrucaoTipoEnum.SW, 5, 6, 7));
        
        // Adiciona as instruções à arquitetura selecionada
        while (gerenciadorInstrucoes.temInstrucoes()) {
            arquitetura.adicionarInstrucao(gerenciadorInstrucoes.proximaInstrucao());
        }
        
        // Inicia a execução da arquitetura selecionada
        arquitetura.iniciarExecucao();
        
        // Exibe o estado final dos registradores
        System.out.println("Estado final dos registradores:");
        for (int i = 0; i < arquitetura.getRegistradores().size(); i++) {
            System.out.println("R" + i + ": " + arquitetura.lerRegistrador(i));
        }
    }
}

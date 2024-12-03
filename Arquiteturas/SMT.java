/*
 * Arquitetura SMT (Simultaneous Multi-Threading):
    - A arquitetura **SMT** permite que múltiplas threads sejam executadas simultaneamente no mesmo núcleo de processador, compartilhando os recursos de execução (como unidades de ALU, unidades de carga/armazenamento, etc.).
    - Ao contrário da IMT, onde a ordem das instruções é mantida, a SMT permite que as threads sejam intercaladas de forma mais eficiente, aproveitando melhor o ciclo de execução de cada unidade de execução.
    - Com a SMT, um único núcleo pode executar múltiplas threads ao mesmo tempo, o que melhora a utilização do processador e aumenta o desempenho geral, principalmente em tarefas paralelizadas.
 */

package Arquiteturas;

import Core.Instrucao;

public class SMT extends ArquiteturaBase {

	public SMT() {
		//super();
	}

	@Override
	public void executarCiclo() {
		
	}

}
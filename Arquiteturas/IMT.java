/*
 * Arquitetura IMT (In-Order Multi-Threading):
    - A arquitetura **IMT** permite a execução de múltiplas threads em uma mesma unidade de execução, mas mantém a ordem das instruções.
    - As instruções são executadas na sequência em que são recebidas, mas diferentes threads podem ser processadas simultaneamente, aumentando a eficiência e a utilização do processador.
    - Essa abordagem é útil em cenários onde a ordem de execução é importante, mas ainda se busca algum nível de paralelismo.
 */

package Arquiteturas;

import Core.Instrucao;

public class IMT extends ArquiteturaBase {

	public IMT() {
		//super();
	}

	@Override
	public void executarCiclo() {
		
	}

}
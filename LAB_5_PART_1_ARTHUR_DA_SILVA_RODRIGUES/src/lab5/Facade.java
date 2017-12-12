package lab5;

public class Facade {

	private Sistema sistema;

	public void inicializa(int caixa, double taxa) {
		this.sistema = new Sistema(caixa, taxa);
	}

	public int getCaixa() {
		return this.sistema.getCaixa();
	}

	public int cadastrarCenario(String descricao) {
		return this.sistema.cadastrarCenario(descricao);
	}
	
	public String exibirCenario(int cenario) {
		return this.sistema.exibeCenario(cenario);
	}
	
	public String exibirCenarios() {
		return this.sistema.exibirCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.sistema.cadastrarAposta(cenario, apostador, valor, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.sistema.valorTotalDeApostas(cenario);
	}
	
	public int totalDeApostas(int cenario) {
		return this.sistema.totalDeApostas(cenario);
	}
	
	public void fechaAposta(int cenario, boolean ocorreu) {
		this.sistema.fecharAposta(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario){
		return this.sistema.getCaixaCenario(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return this.sistema.getTotalRateioCenario(cenario);
	}
	
}
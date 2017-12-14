package lab5;

import java.util.ArrayList;

public class Sistema {

	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	private int cenarioAtual;
	
	public Sistema(int caixa, double taxa) {
		
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new ArrayList<Cenario>();
		this.cenarioAtual = 1;
	}

	public int getCaixa() {
		return caixa;
	}

	public double getTaxa() {
		return taxa;
	}

	public ArrayList<Cenario> getCenarios() {
		return cenarios;
	}

	public int getCenarioAtual() {
		return cenarioAtual;
	}
	
	public int cadastrarCenario(String descricao) {
		Cenario cenario = new Cenario(this.cenarioAtual,descricao);
		this.cenarios.add(cenario);
		this.cenarioAtual += 1;
		return this.cenarioAtual - 1;
 
	}
	
	public String exibirCenario(int cenario) {
		return this.cenarios.get(cenario - 1).toString();
	}

	public String exibirCenarios(){
		
		String retorno = "";
		for (Cenario cenarioCadastrado : this.cenarios) {
			
			retorno += (cenarioCadastrado.toString() + "\n");
		}
		
		return retorno.trim(); 
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		Aposta aposta = new Aposta(apostador, valor, previsao);
		this.cenarios.get(cenario - 1).getApostas().add(aposta);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.cenarios.get(cenario - 1).somaApostas();
	}
	
	public int totalDeApostas(int cenario) {
		return this.cenarios.get(cenario - 1).getApostas().size();
	}
	
	public String exibirApostas(int cenario) {
		String retorno = "";
		Cenario cnario = this.cenarios.get(cenario - 1);

		for (Aposta aposta : cnario.getApostas()) {
			retorno += ("\n" + aposta.toString());
		}
		return retorno;
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		if (ocorreu) {
			this.cenarios.get(cenario - 1).setEstadoCenario(EstadoDoCenario.FINALIZADO_OCORREU);
		} else {
			this.cenarios.get(cenario - 1).setEstadoCenario(EstadoDoCenario.FINALIZADO_NAO_OCORREU);
		}
		
		this.caixa += (this.getCaixaCenario(cenario)/100);
	}
	
	public int getCaixaCenario(int cenario) {
		if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_OCORREU)) {
			return (int)this.cenarios.get(cenario - 1).getCaixa("NAO VAI ACONTECER");
			
		} else if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_NAO_OCORREU)) {
			return (int)this.cenarios.get(cenario - 1).getCaixa("VAI ACONTECER");
		}
		return 0;
	}
		
	public int getTotalRateioCenario(int cenario) {
		if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_OCORREU)) {
			int total = (this.cenarios.get(cenario - 1).getCaixa("N VAI ACONTECER") / 100);
			int rateio = (int) (this.getCaixaCenario(cenario) * this.taxa * 100) - total;
			this.cenarios.get(cenario - 1).setTotalRateado(rateio);
			
			return rateio;

		} else if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_NAO_OCORREU)) {
			int total = (this.cenarios.get(cenario - 1).getCaixa("VAI ACONTECER") / 100);
			int rateio = (int) (this.getCaixaCenario(cenario) * this.taxa * 100) - total;
			this.cenarios.get(cenario - 1).setTotalRateado(rateio);
			
			return rateio;
			
		}
		return 0;
	}
	
		
	
}

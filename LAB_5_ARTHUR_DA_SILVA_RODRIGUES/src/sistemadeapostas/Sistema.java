package sistemadeapostas;

import java.util.ArrayList;

public class Sistema {

	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	private int cenarioAtual;
	
	public Sistema(int caixa, double taxa) {
		
		if (caixa < 0) {
			NullPointerException invalidCash = new NullPointerException(
					"Erro na inicializacao: Caixa nao pode ser inferior a 0");
			throw invalidCash;
		}
		if (taxa < 0) {
			NullPointerException invalidRate = new NullPointerException(
					"Erro na inicializacao: Taxa nao pode ser inferior a 0");
			throw invalidRate;
		}
		
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
		
		if (descricao.trim().equals("")) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		
		Cenario cenario = new Cenario(this.cenarioAtual, descricao);

		for (Cenario cenarioCadastrado : this.cenarios) {
			if (cenarioCadastrado.equals(cenario)) {
				return 0;
			}
		}
		
		this.cenarios.add(cenario);
		this.cenarioAtual += 1;
		return this.cenarioAtual - 1;
 
	}
	
	public String exibeCenario(int cenario) {
		
		if (cenario <= 0) {
			throw new NullPointerException("Erro na consulta de cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NullPointerException("Erro na consulta de cenario: Cenario nao cadastrado");
		}
		
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
		
		if (cenario <= 0) {
			throw new NullPointerException("Erro no cadastro de aposta: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NullPointerException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		if (valor <= 0) {
			throw new NullPointerException("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}

		
		Aposta aposta = new Aposta(apostador, valor, previsao);
		this.cenarios.get(cenario - 1).getApostas().add(aposta);
	}
	
	public int valorTotalDeApostas(int cenario) {
		
		if (cenario <= 0) {
			throw new NullPointerException("Erro na consulta do valor total de apostas: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NullPointerException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
		
		return this.cenarios.get(cenario - 1).somaApostas();
	}
	
	public int totalDeApostas(int cenario) {
		return this.cenarios.get(cenario - 1).getApostas().size();
	}
	
	public String exibirApostas(int cenario) {
		
		if (cenario <= 0) {
			throw new NullPointerException("Erro na consulta do total de apostas: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NullPointerException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
		
		String retorno = "";
		Cenario cnario = this.cenarios.get(cenario - 1);

		for (Aposta aposta : cnario.getApostas()) {
			retorno += ("\n" + aposta.toString());
		}
		return retorno;
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		
		if (cenario <= 0) {
			
			throw new NullPointerException("Erro ao fechar aposta: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			
			throw new NullPointerException("Erro ao fechar aposta: Cenario nao cadastrado");
		}		
		if (!(this.cenarios.get(cenario-1).getEstadoCenario().equals(EstadoDoCenario.NAO_FINALIZADO))) {
			
			throw new NullPointerException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		
		if (ocorreu) {
			this.cenarios.get(cenario - 1).setEstadoCenario(EstadoDoCenario.FINALIZADO_OCORREU);
		} else {
			this.cenarios.get(cenario - 1).setEstadoCenario(EstadoDoCenario.FINALIZADO_NAO_OCORREU);
		}
		
		this.caixa += (this.getCaixaCenario(cenario)/100);

	}

	public int getCaixaCenario(int cenario) {
		
		if (cenario <= 0) {
			
			throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			
			throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario-1).getEstadoCenario().equals(EstadoDoCenario.NAO_FINALIZADO)) {
			
			throw new NullPointerException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		
		
		if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_OCORREU)) {
			return (int) this.cenarios.get(cenario - 1).getCaixa("N VAI ACONTECER");
		} else if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_NAO_OCORREU)) {
			
			return (int) this.cenarios.get(cenario - 1).getCaixa("VAI ACONTECER");
		}		
		
		return 0;
	}
	
	public int getTotalRateioCenario(int cenario) {
		
		if (cenario <= 0) {
			
			throw new NullPointerException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			
			throw new NullPointerException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
		
		if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_OCORREU)) {
			int total = (this.cenarios.get(cenario - 1).getCaixa("N VAI ACONTECER") / 100);
			int rateio = (int) (this.getCaixaCenario(cenario) * this.taxa * 100) - total;

			return rateio;

		} else if (this.cenarios.get(cenario - 1).getEstadoCenario().equals(EstadoDoCenario.FINALIZADO_NAO_OCORREU)) {
			int total = (this.cenarios.get(cenario - 1).getCaixa("VAI ACONTECER") / 100);
			int rateio = (int) (this.getCaixaCenario(cenario) * this.taxa * 100) - total;
		

			return rateio;
		}
		return 0;
	}

	public String toString() {
		return "Caixa: " + this.getCaixa() + " - Taxa: " + this.getTaxa();
	}
	
}

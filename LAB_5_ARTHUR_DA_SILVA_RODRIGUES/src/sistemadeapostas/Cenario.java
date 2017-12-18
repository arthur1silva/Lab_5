package sistemadeapostas;

import java.util.HashSet;

public class Cenario {
	
	private int number;
	private String description;
	private EstadoDoCenario statusCenario;
	private HashSet<Aposta> betting;
	
	public Cenario(int number, String description) {
		if (number <= 0) {
			NullPointerException invalidNumber = new NullPointerException("Nome invalido.");
			throw invalidNumber;
		}

		if (description.equals(null) || description.trim().equals("")) {
			NullPointerException invalidDescription = new NullPointerException("Descricao invalida.");
			throw invalidDescription;
		}
		this.number = number;
		this.description = description;
		this.betting = new HashSet<Aposta>();
		this.statusCenario = EstadoDoCenario.NAO_FINALIZADO;

	}

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}

	public EstadoDoCenario getEstadoCenario() {
		return statusCenario;
	}
	
	public void setEstadoCenario(EstadoDoCenario status) {
		this.statusCenario = status;
	}
	
	public HashSet<Aposta> getApostas() {
		return this.betting;
	}
	
	public int getCaixa(String previsao) {

		int retorno = 0;

		for (Aposta bet : this.betting) {
			if (bet.getPrevisao().equals(previsao)) {
				retorno += bet.getValor();
			}
		}
		
		return retorno;
	}
	
	public int somaApostas() {
		int sum = 0;
		for (Aposta apostasDoCenario : this.betting) {
			sum += apostasDoCenario.getValor();
		}
		return sum;
	}
	
	public String toString() {
		return this.number + " - " + this.description + " - " + this.statusCenario;
	}
}

package sistemadeapostas;

public class Aposta {
	
	private String apostador;
	private int valor;
	private String previsao;
	
	public Aposta(String apostador, int valor, String previsao) {
		if (apostador.equals(null) || apostador.trim().equals("")) {
			NullPointerException emptyNumber = new NullPointerException("Apostador nao pode ser vazio ou nulo");
			throw emptyNumber;
		}

		if (valor <= 0) {
			
			NullPointerException lessZero = new NullPointerException("Valor nao pode ser menor ou igual a zero");
			throw lessZero;
		}
		if(previsao.equals(null) || previsao.trim().equals("")) {
			
			NullPointerException emptyForecast = new NullPointerException("Previsao nao pode ser vazia ou nula");
			throw emptyForecast;
		}
		if(previsao.equals("VAI ACONTECER") || previsao.equals("N VAI ACONTECER")){
			
			this.apostador = apostador;
			this.valor = valor;
			this.previsao = previsao;
		} else {
			NullPointerException invalidForecast = new NullPointerException("Previsao invalida");
			throw invalidForecast;
		}

	}

	public String getApostador() {
		return apostador;
	}

	public int getValor() {
		return valor;
	}

	public String getPrevisao() {
		return previsao;
	}
	
	public String toString() {
		return (this.apostador + " - R$" + (this.valor/100.0) + " - " + this.previsao);
	}
	
	

}

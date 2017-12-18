package sistemadeapostas;

public enum EstadoDoCenario {

	FINALIZADO_OCORREU("Finalizado (ocorreu)"), FINALIZADO_NAO_OCORREU("Finalizado (n ocorreu)"), NAO_FINALIZADO(
			"Nao finalizado");

	private String status;

	
	EstadoDoCenario(String status) {
		this.status = status;
	}

	public String toString() {
		return this.status;
	}
	
}


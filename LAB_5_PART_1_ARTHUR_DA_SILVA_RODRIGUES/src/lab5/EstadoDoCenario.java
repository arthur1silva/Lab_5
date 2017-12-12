package lab5;

public enum EstadoDoCenario {

	FINALIZADO_OCORREU("Finalizado (ocorreu)"), FINALIZADO_NAO_OCORREU("Finalizado (não ocorreu)"), NAO_FINALIZADO(
			"Não finalizado");

	private String status;

	
	EstadoDoCenario(String status) {
		this.status = status;
	}

	public String toString() {
		return this.status;
	}
	
}


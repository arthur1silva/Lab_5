package lab5;

import static org.junit.Assert.*;


import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import sistemadeapostas.Cenario;
import sistemadeapostas.EstadoDoCenario;

public class CenarioTest {
	
	Cenario cenario;
	Cenario cenarioDeErro1;
	Cenario cenarioDeErro2;

	@Before
	public void setUp() throws Exception {
		
		cenario = new Cenario(1, "Arthur vai pagar LP2");
	}
	
	@Test(expected = NullPointerException.class)
	public void testesDeExcecao() {
		
		cenarioDeErro1 = new Cenario(0, "Arthur vai pagar LP2");
		cenarioDeErro2 = new Cenario(1, "");
		
	}
	
	@Test
	public void testarToString() {
		
		assertEquals("1 - Arthur vai pagar LP2 - Nao finalizado",cenario.toString());

	}
	
	@Test
	public void testarGetEstado() {
		
		assertEquals(EstadoDoCenario.NAO_FINALIZADO, cenario.getEstadoCenario());
		cenario.setEstadoCenario(EstadoDoCenario.FINALIZADO_OCORREU);
		assertEquals(EstadoDoCenario.FINALIZADO_OCORREU, cenario.getEstadoCenario());
	}
	
	@Test
	public void testGetNumero() {
		assertEquals(1, cenario.getNumber());
	}

	
}

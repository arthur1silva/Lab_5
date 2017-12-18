package lab5;

import static org.junit.Assert.*;


import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import sistemadeapostas.Aposta;

public class ApostaTest {
	Aposta aposta;
	Aposta aposta2;
	Aposta apostaComErro1;
	Aposta apostaComErro2;
	Aposta apostaComErro3;
	
	@Before
	public void setUp() throws Exception {
		
		aposta = new Aposta("Arthur", 2988, "VAI ACONTECER");
		aposta2 = new Aposta("Tutu", 200, "N VAI ACONTECER");
	
	}

	@Test
	public void testAposta() {
		
		
		assertEquals("Arthur - R$29.88 - VAI ACONTECER",aposta.toString());
		assertEquals("Arthur",aposta.getApostador());
		assertEquals(2988,2988,aposta.getValor());
		assertEquals("VAI ACONTECER",aposta.getPrevisao());
	}
	
	@Test(expected = NullPointerException.class)
	public void testDeExcecoes() {
		
		apostaComErro1 = new Aposta("", 95, "N VAI ACONTECER");
		apostaComErro2 = new Aposta("Arthur", 0, "VAI ACONTECER");		
		apostaComErro3 = new Aposta("Tutuzão", -90, "VAIACONTECER");
	}
	
	@Test
	public void testarGetPrevisao() {
		
		assertEquals("VAI ACONTECER",aposta.getPrevisao());
		assertEquals("N VAI ACONTECER",aposta2.getPrevisao());
	}
	
	@Test
	public void testarToString() {
		
		assertEquals("Arthur - R$29.88 - VAI ACONTECER",aposta.toString());
	}
	
}

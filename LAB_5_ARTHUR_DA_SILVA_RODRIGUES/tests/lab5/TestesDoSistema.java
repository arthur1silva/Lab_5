package lab5;

import static org.junit.Assert.*;


import org.junit.Test;


import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import sistemadeapostas.Cenario;
import sistemadeapostas.EstadoDoCenario;
import sistemadeapostas.Sistema;


public class TestesDoSistema {
	
	Sistema sistema;
	Sistema sistemaErro;
	Sistema sistemaErro2;
	
	@Before
	public void setUp() throws Exception {
		
		sistema = new Sistema(0, 0.01);

	}
	
	@Test(expected = NullPointerException.class)
	public void testeDeExcecoes() {
		
		sistemaErro = new Sistema(-2, 0);
		sistemaErro2 = new Sistema(2, -0.01);
	}
	
	@Test
	public void testarGetCaixa() {
		
		sistema.cadastrarCenario("Arthur vai pagar sim, pow");
		sistema.cadastrarAposta(1, "Arthur", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Tutu", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Thuzi", 30000, "VAI ACONTECER");	
		sistema.cadastrarAposta(1, "Tuta", 20000, "VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Anonimo", 20000, "N VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Matheus", 199, "N VAI ACONTECER");		
		
		sistema.fecharAposta(1, true);
		
		assertEquals(201, sistema.getCaixa());
		
	}
	
	@Test
	public void testarGetCenarios() {
		HashSet<Cenario> cenarios = new HashSet<>();
		cenarios.add(new Cenario(2, "Fluminense sera campeao do carioca2018"));
		cenarios.add(new Cenario(1, "Fluminense > menguinho"));

		
		sistema.cadastrarCenario("Fluminense > menguinho");
		sistema.cadastrarCenario("Fluminense sera campeao do carioca2018");

		assertEquals("[1 - Fluminense > menguinho - Nao finalizado, "
				+ "2 - Fluminense sera campeao do carioca2018 - Nao finalizado]",sistema.getCenarios().toString());
	}
	
	@Test
	public void testarGetCenarioAtual() {
		
		assertEquals(1, sistema.getCenarioAtual());
		
		sistema.cadastrarCenario("lp2 é rasgado");
		assertEquals(2, sistema.getCenarioAtual());

		sistema.cadastrarCenario("lp2 é furando");
		assertEquals(3, sistema.getCenarioAtual());
	}
	
	@Test
	public void testaCadastraCenario() {
		
		assertEquals(1, sistema.cadastrarCenario("o professor vai me dar 10zao no projeto"));	
		assertEquals(2, sistema.cadastrarCenario("o minnesota vikings vai levar o superbowl"));
	}
	
	@Test
	public void testExibeCenario() {
		sistema.cadastrarCenario("o professor vai me dar 10zao no projeto");
		sistema.cadastrarCenario("o minnesota vikings vai levar o superbowl");
		assertEquals("1 - o professor vai me dar 10zao no projeto - Nao finalizado",sistema.exibeCenario(1));
		assertEquals("2 - o minnesota vikings vai levar o superbowl - Nao finalizado",sistema.exibeCenario(2));
	}

	@Test
	public void testListarCenarios() {
		
		assertEquals(1, sistema.cadastrarCenario("o professor vai me dar 10zao no projeto"));	
		assertEquals(2, sistema.cadastrarCenario("o minnesota vikings vai levar o superbowl"));
		assertEquals("1 - o professor vai me dar 10zao no projeto - Nao finalizado\n2 - o minnesota vikings vai levar o superbowl - Nao finalizado",sistema.exibirCenarios());		
	}

	@Test
	public void testCadastrarAposta() {
		sistema.cadastrarCenario("o professor vai me dar 10zao no projeto");
		sistema.cadastrarAposta(1, "Simba Mufasa", 299, "VAI ACONTECER");
		assertEquals("\nSimba Mufasa - R$2.99 - VAI ACONTECER",sistema.exibirApostas(1));
	}
	
	@Test
	public void testTotalApostas() {

		sistema.cadastrarCenario("o professor vai me dar 10zao no projeto");
		sistema.cadastrarAposta(1, "Simba Mufasa", 299, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Raaaam pá", 300, "N VAI ACONTECER");
		sistema.cadastrarAposta(1, "Miguxos de CC", 2, "N VAI ACONTECER");
		assertEquals(3,sistema.totalDeApostas(1));
	}

	@Test
	public void testValorTotalApostas() {
		
		sistema.cadastrarCenario("o professor vai me dar 10zao no projeto");
		sistema.cadastrarAposta(1, "Matheus", 100, "VAI ACONTECER");
		assertEquals(100, sistema.valorTotalDeApostas(1));

		sistema.cadastrarAposta(1, "Joana", 150, "N VAI ACONTECER");
		sistema.cadastrarAposta(1, "Juzefina", 150, "N VAI ACONTECER");
		assertEquals(400, sistema.valorTotalDeApostas(1));
	}

	@Test
	public void testExibeAposta() {

		sistema.cadastrarCenario("o professor vai me dar 10zao no projeto");
		sistema.cadastrarAposta(1, "Simba Mufasa", 299, "VAI ACONTECER");

		assertEquals("\nSimba Mufasa - R$2.99 - VAI ACONTECER", sistema.exibirApostas(1));		
	}

	@Test
	public void testFecharAposta() {
		
		sistema.cadastrarCenario("a terra é plana");
		sistema.cadastrarAposta(1, "Simba Mufasa", 299, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Poeta Galo-Cego", 800, "VAI ACONTECER");
		assertEquals(EstadoDoCenario.NAO_FINALIZADO, sistema.getCenarios().get(0).getEstadoCenario());
		sistema.fecharAposta(1, true);
		
		sistema.cadastrarCenario("flamengo vai ganhar a sulamericana");
		sistema.fecharAposta(2, false);

		assertEquals(EstadoDoCenario.FINALIZADO_OCORREU, sistema.getCenarios().get(0).getEstadoCenario());
		assertEquals(EstadoDoCenario.FINALIZADO_NAO_OCORREU, sistema.getCenarios().get(1).getEstadoCenario());	
	}

	@Test
	public void testGetCaixaCenario() {
		
		sistema.cadastrarCenario("Eu(Arthur) com toda certeza pagarei p2 e lp2");
		sistema.cadastrarAposta(1, "Matheus", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Matheus", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Livia", 30000, "VAI ACONTECER");	
		sistema.cadastrarAposta(1, "Suzete", 20000, "VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Magron", 20000, "N VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Hillary Trump", 199, "N VAI ACONTECER");		
		
		sistema.fecharAposta(1, true);
		
		assertEquals(20199, sistema.getCaixaCenario(1));	
	}

	@Test
	public void testGetTotalRateioCenario() {
		
		sistema.cadastrarCenario("Nicolas vai botar 10 nesse lab");
		sistema.cadastrarAposta(1, "Matheus", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Matheus", 10000, "VAI ACONTECER");
		sistema.cadastrarAposta(1, "Livia", 30000, "VAI ACONTECER");	
		sistema.cadastrarAposta(1, "Suzete", 20000,"VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Magron", 20000, "N VAI ACONTECER");		
		sistema.cadastrarAposta(1, "Hillary Trump", 199, "N VAI ACONTECER");		
		
		sistema.fecharAposta(1, true);
		
		assertEquals(19998,sistema.getTotalRateioCenario(1));
	}
	
	@Test(expected = NullPointerException.class)
	public void testErros() {
		
		sistema.cadastrarCenario("o professor vai aliviar na prova");
		sistema.cadastrarCenario("vai ter aula de lp2 quinta dia 21");

		sistema.cadastrarAposta(0, "matheus", 12, "N VAI ACONTECER");
		sistema.exibeCenario(0);
		sistema.exibirApostas(300);
		sistema.fecharAposta(3, false);
		sistema.getTotalRateioCenario(-2);
		sistema.totalDeApostas(3);
		sistema.valorTotalDeApostas(0);	
	}
}

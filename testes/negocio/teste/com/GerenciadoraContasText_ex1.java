package negocio.teste.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;

public class GerenciadoraContasText_ex1 {
	
	private GerenciadoraContas gc;
	private ContaCorrente conta01;
	ContaCorrente conta02;
	
	@Before
	public void setUp() {
		this.conta01 = new ContaCorrente(1, 200, true);
		this.conta02 = new ContaCorrente(2, 0, true);
		
		
		List<ContaCorrente> contasBanco = new ArrayList<ContaCorrente>();
		contasBanco.add(this.conta01);
		contasBanco.add(this.conta02);
		
		 this.gc = new GerenciadoraContas(contasBanco);
		
	}
	
	@After
	public void AfterClientes() {
		//TODO efetua apos a execucao do teste
	}

	@Test
	public void testTransfereValor() {
		
		boolean resultado  = this.gc.transfereValor(1, 100, 2);
		
		assertThat(resultado, is(true));
		assertEquals(this.conta02.getSaldo(), 100,00);
		
		
		
				
	}
	
	@Test
	public void testTransfereValor_SaldoInsuficiente() {
		
		
		boolean resultado  = this.gc.transfereValor(1, 0, 2);
		
		assertThat(resultado, is(true));
		assertEquals(this.conta01.getSaldo(), 200,00);
		assertTrue(resultado);
		
		
		
		
				
	}

}

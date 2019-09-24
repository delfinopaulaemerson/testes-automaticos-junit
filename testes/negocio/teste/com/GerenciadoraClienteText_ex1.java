package negocio.teste.com;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;
import negocio.IdadeNaoPermitidaException;

public class GerenciadoraClienteText_ex1 {
	
	private GerenciadoraClientes gc;
	
	private Cliente cliente01;
	private Cliente cliente02;
	
	@Before
	public void setUp() {
		this.cliente01 = new Cliente(1, "emerson delfino", 27, "emerson@gmail.com", 1, true);
		this.cliente02 = new Cliente(2, "Celso delfino", 20, "celso@gmail.com", 1, true);
		
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(this.cliente01);
		clientesDoBanco.add(this.cliente02);
		
		this.gc = new GerenciadoraClientes(clientesDoBanco);
		
	}
	
	@After
	public void setAfter() {
		this.gc.limpa();
	}

	@Test
	public void testPesquisaCliente() {
		
		Cliente cliente = this.gc.pesquisaCliente(1);
		
		assertThat(cliente.getId(),is(1));
		assertThat(cliente.getEmail(), is(this.cliente01.getEmail()));
		
		
	}
	
	@Test
	public void testRemoveCliente() {
		
		boolean result = this.gc.removeCliente(2);
		
		assertThat(result, is(true));
		assertThat(this.gc.getClientesDoBanco().size(), is(1));
		assertNull(this.gc.pesquisaCliente(2));
		
	}
	
	@Test
	public void testClienteIdadeAceitavel() {
		this.cliente01 = new Cliente(1,"Severino da Silva",66,"severino@gmail.com",1,true);
		
		try {
			this.gc.validaIdade(this.cliente01.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
		
	}

}

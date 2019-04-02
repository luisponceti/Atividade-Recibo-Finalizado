package br.com.db1.recibos;


import org.junit.Assert;
import org.junit.Test;



public class ClienteTest {
	
	@Test
	public void deveRetornarNomeEObrigatorio() {
		try {
			new Cliente(null, "09855171900");
		} catch (Exception e) {
			Assert.assertEquals("Campo nome é obrigatório", e.getMessage());
		}
	}
	
	
	@Test
	public void deveRetornarCpfEObrigatorio() {
		try {
			new Cliente("Luís Fernando", null);
		} catch (Exception e) {
			Assert.assertEquals("Campo CPF é obrigatório", e.getMessage());
		}
	}
	
	@Test
	public void deveRetornarCpfEInvalido() {
		try {
			new Cliente("Luís Fernando", "098");
		} catch (Exception e) {
			Assert.assertEquals("Cpf deve conter Onze caracteres ", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmCliente() {
		Cliente cliente = new Cliente("Luís Fernando", "09855171900");
		
		Assert.assertEquals("Luís Fernando", cliente.getNome());
		Assert.assertEquals("09855171900", cliente.getCpf());
		Assert.assertEquals(StatusTipoCliente.ATIVO, cliente.getStatus());
	}
	
	@Test
	public void deveRetornarClienteAtivo() {
		Cliente cliente = new Cliente("Luís Fernando", "09855171900");
		
		Assert.assertTrue(cliente.ativo());
	}
}

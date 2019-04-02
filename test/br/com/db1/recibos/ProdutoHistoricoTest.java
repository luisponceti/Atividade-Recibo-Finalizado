package br.com.db1.recibos;


import org.junit.Assert;
import org.junit.Test;



public class ProdutoHistoricoTest {

	@Test
	public void deveRetornarStatus() {
		try {
			new ProdutoHistorico(null, null);
		} catch (Exception e) {
			Assert.assertEquals("Campo status é obrigatório", e.getMessage());
		}
	}
	
}

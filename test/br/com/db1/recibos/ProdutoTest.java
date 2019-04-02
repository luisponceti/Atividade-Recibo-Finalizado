package br.com.db1.recibos;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTest {

	@Test
	public void deveRetornarCodigoProduto() {
		try {
			new Produto(null, "Computador", 2000.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo codigo é obrigatório", e.getMessage());
		}
	}

	@Test
	public void deveRetornarNomeProduto() {
		try {
			new Produto("001", null, 2000.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo nome é obrigatório", e.getMessage());
		}
	}


	@Test
	public void deveCriarUmProduto() {
		Produto produto = new Produto("001", "Computador", 2000.0);

		Assert.assertEquals("001", produto.getCodigo());
		Assert.assertEquals("Computador", produto.getNome());
		Assert.assertEquals(2000.0, produto.getValor(), 0);
		Assert.assertEquals(StatusTipoProduto.ATIVO, produto.getStatus());
	}
}

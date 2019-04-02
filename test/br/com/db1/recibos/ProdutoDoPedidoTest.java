package br.com.db1.recibos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProdutoDoPedidoTest {
	
	private Produto produto;
	
	@Before
	public void init() {
		produto = new Produto("123", "Caneta", 10.0);
	}
	
	@Test
	public void deveRetornarProdutoEObrigatorio() {
		try {
			new ProdutoDoPedido(null, 8.0);
		} catch (Exception e) {
			Assert.assertEquals("Campo produto é obrigatório", e.getMessage());
		}
	}

	@Test
	public void deveRetornarQuantidadeEObrigatorio() {
		try {
			new ProdutoDoPedido(produto, null);
		} catch (Exception e) {
			Assert.assertEquals("Campo quantidade é obrigatório", e.getMessage());
		}
	}

	@Test
	public void deveRetornarQuantidadeveSerMaiorQueZero() {
		try {
			new ProdutoDoPedido(produto, 0.0);
		} catch (Exception e) {
			Assert.assertEquals("O preço do campo quantidade não pode ser zero ou inferior a zero", e.getMessage());
		}
	}
	
	@Test
	public void deveCriarUmPedidoItem() {
		ProdutoDoPedido item = new ProdutoDoPedido(produto, 2.0);;
		Assert.assertEquals(produto, item.getProduto());
		Assert.assertEquals(2.0, item.getQuantidade(),  0);
		Assert.assertEquals(produto.getValor(), item.getValor(), 0);
		Assert.assertEquals(20.0, item.getValorTotal(), 0);
	}
}

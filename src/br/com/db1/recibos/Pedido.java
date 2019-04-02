package br.com.db1.recibos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

	private static final int Quantidade_Limite_Itens = 10;

	private StatusTipoPedido status;

	private Cliente cliente;

	private String codigo;

	private List<ProdutoHistorico> historico = new ArrayList<>();

	private List<ProdutoDoPedido> itens = new ArrayList<>();

	private LocalDateTime dataStatus;

	public Pedido(Cliente cliente, String numero) {
		Validador.naoPodeSerNulo(cliente, "cliente");
		Validador.naoPodeSerNulo(codigo, "codigo");
		this.verificarStatusClienteAtivo();
		
		this.cliente = cliente;
		this.codigo = numero;
		this.status = StatusTipoPedido.ABERTO;

	}

	public void adicionarItens(Produto produto, Double quantidade) {
		this.verificarStatusPedidoAlterar();

		if (this.itens.size() == Quantidade_Limite_Itens) {
			throw new RuntimeException("Quantidade máxima de itens excedida: " + Quantidade_Limite_Itens);
		}

		this.itens.add(new ProdutoDoPedido(produto, quantidade));
	}

	public void removerItens(Produto produto) {
		this.verificarStatusPedidoAlterar();
		this.itens.removeIf(item -> item.getProduto().getCodigo().equals(produto.getCodigo()));
	}

	public void fechar() {
		if (!StatusTipoPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está  " + this.status);
		}

		if (this.itens.size() == 0 || this.itens.size() > Quantidade_Limite_Itens) {
			throw new RuntimeException(
					"Pedido deve ter no minímo 1 item e no máximo 10 itens. Quantidade atual: " + this.itens.size());
		}

		this.verificarStatusClienteAtivo();

		this.status = StatusTipoPedido.FATURADO;
	}

	public void cancelar() {
		this.verificarStatusPedidoAlterar();
		this.status = StatusTipoPedido.CANCELADO;
	}

	public void devolucao() {
		if (!StatusTipoPedido.CANCELADO.equals(this.status)) {
			throw new RuntimeException("Pedido está " + this.status);
		}

		this.status = StatusTipoPedido.ABERTO;
	}

	public String getCodigo() {
		return codigo;
	}

	public StatusTipoPedido getStatus() {
		return status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<ProdutoHistorico> getHistoricos() {
		return Collections.unmodifiableList(historico);
	}

	public List<ProdutoDoPedido> getItens() {
		return Collections.unmodifiableList(itens);
	}

	public LocalDateTime getDataStatus() {
		return dataStatus;
	}

	private void novoHistoricoStatus() {
		ProdutoHistorico historico = new ProdutoHistorico(dataStatus, this.status);
		this.historico.add(historico);
		this.dataStatus = historico.getData();
	}

	private void verificarStatusClienteAtivo() {
		if (!cliente.ativo()) {
			throw new RuntimeException("Cliente " + cliente.getNome() + " está inativo");
		}
	}

	private void verificarStatusPedidoAlterar() {
		if (!StatusTipoPedido.ABERTO.equals(this.status)) {
			throw new RuntimeException("Pedido está  " + this.status);
		}
	}

}

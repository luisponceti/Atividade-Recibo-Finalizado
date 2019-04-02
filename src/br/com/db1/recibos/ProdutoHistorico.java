package br.com.db1.recibos;

import java.time.LocalDateTime;


public class ProdutoHistorico {

	private LocalDateTime data;

	private StatusTipoPedido status;

	public ProdutoHistorico(LocalDateTime data, StatusTipoPedido statusPedido) {
		Validador.naoPodeSerNulo(status, "status");
		this.data = LocalDateTime.now();
		statusPedido = status;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	
	public StatusTipoPedido getStatus(){
		return this.status;
	}

	public void setStatus(StatusTipoPedido status) {
		this.status = status;
	}
}

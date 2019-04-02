package br.com.db1.recibos;

public class Cliente {

	private String nome;

	private String cpf;

	private StatusTipoCliente status;

	public Cliente(String nome, String cpf) {

		Validador.naoPodeSerNulo(nome, "nome");
		Validador.naoPodeSerNulo(cpf, "CPF");
		Validador.cpfDeveTerOnzeChar(cpf);
		
		this.nome = nome;
		this.cpf = cpf;
		this.status = StatusTipoCliente.ATIVO;

	}

	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}
	
	public StatusTipoCliente getStatus() {
		return status;
	}
	
	public boolean ativo() {
		return StatusTipoCliente.ATIVO.equals(this.status);
	}

}

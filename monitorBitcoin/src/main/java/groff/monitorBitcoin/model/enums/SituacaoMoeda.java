package groff.monitorBitcoin.model.enums;

public enum SituacaoMoeda {
	
	ACIMA_MAXIMO("Valorizou acima do valor definido"),
	ABAIXO_MINIMO("Desvalorizou abaixo do valor definido"),
	NORMAL("");
	
	private String mensagem;

	SituacaoMoeda(String mensagem) {
		this.setMensagem(mensagem);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

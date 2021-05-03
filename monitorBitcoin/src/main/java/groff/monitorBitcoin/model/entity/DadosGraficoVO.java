package groff.monitorBitcoin.model.entity;

public class DadosGraficoVO {

	private Number valor;
	private Comparable<String> chaveLinha;
	private Comparable<String> chaveColuna;

	public Number getValor() {
		return valor;
	}

	public void setValor(Number valor) {
		this.valor = valor;
	}

	public Comparable<String> getChaveLinha() {
		return chaveLinha;
	}

	public void setChaveLinha(Comparable<String> chaveLinha) {
		this.chaveLinha = chaveLinha;
	}

	public Comparable<String> getChaveColuna() {
		return chaveColuna;
	}

	public void setChaveColuna(Comparable<String> chaveColuna) {
		this.chaveColuna = chaveColuna;
	}

}

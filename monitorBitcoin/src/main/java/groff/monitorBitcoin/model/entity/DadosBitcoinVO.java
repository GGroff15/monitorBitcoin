package groff.monitorBitcoin.model.entity;

import java.util.Calendar;
import java.util.Date;

public class DadosBitcoinVO {

	private String tendencia;
	private Float valor;
	private String moeda;
	private Calendar data;

	public String getTendencia() {
		return tendencia;
	}

	public void setTendencia(String tendencia) {
		this.tendencia = tendencia;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

}

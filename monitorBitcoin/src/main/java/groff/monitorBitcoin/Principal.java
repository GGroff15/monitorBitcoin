package groff.monitorBitcoin;

import java.util.Calendar;

import groff.monitorBitcoin.model.entity.DadosBitcoinVO;
import groff.monitorBitcoin.model.service.ConectorService;
import groff.monitorBitcoin.model.service.JsonService;
import groff.monitorBitcoin.model.service.NotificacaoService;
import okhttp3.Response;

public class Principal {
	
	final private static String BITCOIN_PRICE_ENDPOINT = "https://api.coindesk.com/v1/bpi/currentprice.json";

	private float valorAnterior;
	private float valorAtual;
	private String tendencia;
	
	public DadosBitcoinVO iniciar(String moeda, float valorMaximo, float valorMinimo) {
		obterDados(moeda);
		comparar(valorMaximo, valorMinimo);
		DadosBitcoinVO mensagem = montarMensagem(moeda);
		exibirNotificacao(mensagem);
		return mensagem;
	}
	
	private void comparar(float valorMaximo, float valorMinimo) {
		if (this.valorAnterior < this.valorAtual) {
			tendencia = "Valorização";
		} else if (this.valorAnterior > this.valorAtual) {
			tendencia = "Desvalorização";
		} else {
			tendencia = "Estabilidade";
		}
		
		
		
		this.valorAnterior = this.valorAtual;
	}
	
	private DadosBitcoinVO montarMensagem(String moeda) {
		
		DadosBitcoinVO bitcoinVO = new DadosBitcoinVO();
		bitcoinVO.setData(Calendar.getInstance());
		bitcoinVO.setValor(this.valorAtual);
		bitcoinVO.setTendencia(this.tendencia);
		bitcoinVO.setMoeda(moeda);
		
		return bitcoinVO;
	}
	
	private void exibirNotificacao(DadosBitcoinVO mensagem) {
		NotificacaoService notificacaoService = new NotificacaoService();
		notificacaoService.exibirNotificacao("Monitor Bitcoin", mensagem);
	}
	
	private void obterDados(String moeda) {
		ConectorService conector = new ConectorService();
		JsonService jsonService = new JsonService();
		Response response = conector.realizarRequisicaoHttp(BITCOIN_PRICE_ENDPOINT);
		float valor = jsonService.obterValorBitcoinFloat(response, moeda);
		this.valorAtual = valor;
	}
}

package groff.monitorBitcoin.model.service;

import groff.monitorBitcoin.model.ConectorHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConectorService {

	private ConectorHttp conectorHttp;

	public Response realizarRequisicaoHttp(String url) {

		conectorHttp = new ConectorHttp();
		OkHttpClient cliente = conectorHttp.getClient();
		Request request = conectorHttp.montarRequest(url);
		Response response = conectorHttp.getResponse(cliente, request);

		return response;
	}

}

package groff.monitorBitcoin.model;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConectorHttp {
	
	public Response getResponse(OkHttpClient client, Request request) {
		try {
			return client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Request montarRequest(String url) {
		return new Request.Builder().url(url).build();
	}

	public OkHttpClient getClient() {
		return new OkHttpClient();
	}
}

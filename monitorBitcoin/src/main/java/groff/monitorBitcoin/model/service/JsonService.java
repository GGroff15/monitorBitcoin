package groff.monitorBitcoin.model.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

public class JsonService {
	
	public float obterValorBitcoinFloat(Response response, String moeda) {
		
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(response.body().string());
			float valor = jsonObject.getJSONObject("bpi").getJSONObject(moeda).getFloat("rate_float");
			return valor;
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
}

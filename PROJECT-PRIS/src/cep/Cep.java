package cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import prs.controller.entity.Address;

public class Cep {
	
	public Address findCEP(String cep) throws IOException, JSONException {	
		Address ad = null;
		try {
			URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
			URLConnection connect =  url.openConnection();
			InputStream input = connect.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
		    
			int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    String jsonStr = sb.toString();
            
            JSONObject addressStr = new JSONObject(jsonStr.toString());
            
            ad = new Address();
            ad.setAddress(addressStr.getString("logradouro"));
            ad.setZipcode(addressStr.getString("cep"));
            ad.setNeighborhood(addressStr.getString("bairro"));
            ad.setCity(addressStr.getString("localidade"));
            ad.setState(addressStr.getString("uf"));

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ad;
	}


}

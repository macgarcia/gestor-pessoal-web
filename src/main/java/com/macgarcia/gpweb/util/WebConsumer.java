package com.macgarcia.gpweb.util;

import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class WebConsumer {

	private Client client;
	private WebResource web;

	public WebConsumer() {
		this.client = Client.create();
	}

	public String getDados(String endPonit) {
		this.web = client.resource(endPonit);
		ClientResponse response = this.web.type("application/json").get(ClientResponse.class);
		String result = response.getEntity(String.class);
		return result;
	}

	public String putDados(String endPoint, String json, boolean retorno) {
		this.web = client.resource(endPoint);
		ClientResponse response = this.web.type("application/json").put(ClientResponse.class, json);
		if (retorno) {
			return response.getEntity(String.class);
		}
		return null;
	}

	public String postDados(String endPoint, String json, boolean retorno) {
		this.web = client.resource(endPoint);
		ClientResponse response = this.web.type("application/json").post(ClientResponse.class, json);
		if (retorno) {
			return response.getEntity(String.class);
		}
		return null;
	}

	public void deleteDados(String endPoint) {
		this.web = client.resource(endPoint);
		this.web.delete();
	}
}

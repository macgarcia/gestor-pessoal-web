package com.macgarcia.gpweb.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.macgarcia.gpweb.model.Renda;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class RendaComponent {

	@Autowired
	private WebConsumer web;
	
	public List<Renda> buscarRendasMensal(Long idUsuario) {
		String endPoint = URL.RENDAS_MENSAIS + "/" + idUsuario;
		String result = this.web.getDados(endPoint);
		if (result != null) {
			return new Gson().fromJson(result, new TypeToken<List<Renda>>(){}.getType());
		}
		return new ArrayList<>();
	}
	
	public List<Renda> buscarRendaDoMesSelecionado(Long idUsuario, Integer mes) {
		String endPoint = URL.RENDAS_DO_MES_SELECIONADO + "/" + idUsuario + "/" + mes;
		String result = this.web.getDados(endPoint);
		if (result != null) {
			return new Gson().fromJson(result, new TypeToken<List<Renda>>(){}.getType());
		}
		return new ArrayList<>();
	}
	
}

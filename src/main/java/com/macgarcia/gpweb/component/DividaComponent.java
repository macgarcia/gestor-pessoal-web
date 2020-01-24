package com.macgarcia.gpweb.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.macgarcia.gpweb.model.Divida;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class DividaComponent {
	
	@Autowired
	private WebConsumer web;
	
	public List<Divida> buscarDividasMensais(Long idUsuario) {
		String endPoint = URL.DIVIDAS_MENSAIS + "/" + idUsuario;
		String result = this.web.getDados(endPoint);
		if (result != null) {
			return new Gson().fromJson(result, new TypeToken<List<Divida>>(){}.getType());
		}
		return new ArrayList<>();
	}
	
	public List<Divida> buscarDividasDoMesSelecionado(Long idUsuario, Integer mes) {
		String endPoint = URL.DIVIDAS_DO_MES_SELECIONADO + "/" + idUsuario + "/" + mes;
		String result = this.web.getDados(endPoint);
		if (result != null) {
			return new Gson().fromJson(result, new TypeToken<List<Divida>>(){}.getType());
		}
		return new ArrayList<>();
	}

}

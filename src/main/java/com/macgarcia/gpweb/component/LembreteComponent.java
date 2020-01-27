package com.macgarcia.gpweb.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.macgarcia.gpweb.model.Lembrete;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class LembreteComponent {

	@Autowired
	private WebConsumer web;
	
	public List<Lembrete> buscarLembretesDoUsuario(Long idUsuario) {
		String endPoint = URL.BUSCAR_LEMBRETES + "/" + idUsuario;
		String result = this.web.getDados(endPoint);
		if (result != null) {
			return new Gson().fromJson(result, new TypeToken<List<Lembrete>>() {}.getType());
		}
		return new ArrayList<>();
	}
	
}

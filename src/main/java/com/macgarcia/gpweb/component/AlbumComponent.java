package com.macgarcia.gpweb.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.macgarcia.gpweb.model.Album;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class AlbumComponent {

	@Autowired
	private WebConsumer web;
	
	public List<Album> buscarAlbuns(Long idUsuario) {
		String endPoint = URL.BUSCAR_ALBUNS + "/" + idUsuario;
		String json = this.web.getDados(endPoint);
		if (json != null) {
			List<Album> result = new Gson().fromJson(json, new TypeToken<List<Album>>() {}.getType());
			return result;
		}
		return new ArrayList<Album>();
	}
	
}

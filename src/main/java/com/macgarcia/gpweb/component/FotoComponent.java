package com.macgarcia.gpweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.macgarcia.gpweb.model.Foto;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class FotoComponent {

	@Autowired
	WebConsumer web;
	
//	public List<Foto> buscarFotosDoAlbum(Long idAlbum) {
//		String endPoint = URL.BUSCAR_FOTOS + "/" + idAlbum;
//		String json = this.web.getDados(endPoint);
//		if (json != null) {
//			List<Foto> result = new Gson().fromJson(json, new TypeToken<List<Foto>>() {}.getType());
//			return result;
//		}
//		return new ArrayList<Foto>();
//	}
	
	public void salvarFoto(Foto foto, Long idAlbum) {
		String json = new Gson().toJson(foto);
		String endPoint = URL.SALVAR_FOTO + "/" + idAlbum;
		this.web.postDados(endPoint, json, false);
	}
}

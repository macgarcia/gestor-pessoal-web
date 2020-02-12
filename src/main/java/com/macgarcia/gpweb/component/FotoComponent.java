package com.macgarcia.gpweb.component;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
	
	public void salvarFoto(Foto foto, Long idAlbum) {
		String json = new Gson().toJson(foto);
		String endPoint = URL.SALVAR_FOTO + "/" + idAlbum;
		//String endPoint = "http://localhost:9090/foto/addNovaFoto/" + idAlbum;
		this.web.postDados(endPoint, json, false);
	}
	
	public Foto buscarUnicaFoto(Long idFoto) {
		String endPoint = URL.BUSCAR_UNICA_FOTO + "/" + idFoto;
		String json = this.web.getDados(endPoint);
		if (json != null) {
			try {
				//Gson não faz parser com objetos lob
				JSONObject obj = new JSONObject(json);
				Foto foto = new Foto();
				foto.setId(Long.parseLong(obj.getString("id")));
				foto.setNome(obj.getString("nome"));
				foto.setTipo(obj.getString("tipo"));
				String s = obj.getString("arquivo");
				// parser do array que esta em string para byte, já que o conteudo da string é o array real
				byte[] b = DatatypeConverter.parseBase64Binary(s);
				foto.setArquivo(b);
				return foto;	
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		return new Foto();
	}
}



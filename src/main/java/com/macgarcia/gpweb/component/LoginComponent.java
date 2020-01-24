package com.macgarcia.gpweb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.macgarcia.gpweb.model.Usuario;
import com.macgarcia.gpweb.util.Ferramentas;
import com.macgarcia.gpweb.util.URL;
import com.macgarcia.gpweb.util.WebConsumer;

@Component
public class LoginComponent {

	@Autowired
	private WebConsumer web;
	
	public boolean validarEntrada(Usuario usuario) {
		String endPoint = URL.VALIDAR_USUARIO + "/" + usuario.getLogin() + "/" + Ferramentas.md5(usuario.getSenha());
		String result = this.web.getDados(endPoint);
		Usuario u = new Gson().fromJson(result, Usuario.class);
		if (u.getId() != null) {
			return true;
		}
		return false;
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setId(null);
		String json = new Gson().toJson(usuario);
		String endPoint = URL.NOVO_USUARIO;
		String result = this.web.postDados(endPoint, json, true);
		if (result != null) {
			return new Gson().fromJson(result, Usuario.class);
		}
		return new Usuario();
	}
	
}

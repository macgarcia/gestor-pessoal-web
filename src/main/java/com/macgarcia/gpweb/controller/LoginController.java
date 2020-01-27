package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.macgarcia.gpweb.component.LoginComponent;
import com.macgarcia.gpweb.model.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	private LoginComponent loginComponent;
	
	@GetMapping(value = "/")
	public String iniciar() {
		return "telaDeLogin";
	}

	@GetMapping(value = "/logar/{usuario}")
	public String entradaoNoSistema(@PathVariable("usuario") String usuario, HttpSession session) {
		Usuario u = new Gson().fromJson(usuario, Usuario.class);
		session.setAttribute("Usuario", u);
		return "redirect:/gestorPessoal";
	}
	
	@GetMapping(value = "/novo")
	public String novoUsuario() {
		return "telaNovoUsuario";
	}	
	
}

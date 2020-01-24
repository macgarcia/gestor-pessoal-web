package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@PostMapping(value = "/salvarUsuario")
	public String salvarNovoUsuario(Usuario usuario, RedirectAttributes ra) {
		String msg = null;
		boolean verificarApi = true;
		if (usuario.getLogin().trim().isEmpty()) {
			msg = "Informe o login";
			verificarApi = false;
		} else if (usuario.getSenha().trim().isEmpty()) {
			msg = "Informe a senha";
			verificarApi = false;
		}
		if (verificarApi) {
			Usuario novoUsuario = this.loginComponent.salvarUsuario(usuario);
			if (novoUsuario.getId() != null) {
				return "redirect:/";
			}
		}
		ra.addFlashAttribute("codigo", "2");
		ra.addFlashAttribute("mensagem", msg);
		return "redirect:/novo";
	}
}

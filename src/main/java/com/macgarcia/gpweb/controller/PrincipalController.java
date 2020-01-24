package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.macgarcia.gpweb.model.Usuario;

@Controller
public class PrincipalController {
	
	private Usuario getUsuarioDaSessao(HttpSession session) {
		return (Usuario) session.getAttribute("Usuario");
	}

	@GetMapping(value = "/gestorPessoal")
	public String telaPrincipal(HttpSession session) {
		if (this.getUsuarioDaSessao(session) != null) {
			return "telaPrincipal";
		}
		return "redirect:/";
	}
}

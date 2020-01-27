package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.macgarcia.gpweb.model.Usuario;

@Controller
public class LembreteController {
	
	private Usuario usuario;
	
	private void getUsuarioDaSessao(HttpSession session) {
		this.usuario = (Usuario) session.getAttribute("Usuario");
	}

	@GetMapping(value = "/novaAnotacao")
	public String novaAnotacao(HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (this.usuario != null) {
			return "redirect:/telaNovaAnotacao";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/telaNovaAnotacao")
	public ModelAndView telaNovaAnotacao() {
		ModelAndView mv = new ModelAndView("telaNovaAnotacao");
		mv.addObject("idUsuario", this.usuario.getId());
		return mv;
	}
}

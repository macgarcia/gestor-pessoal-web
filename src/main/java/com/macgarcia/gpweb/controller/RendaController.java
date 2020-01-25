package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.macgarcia.gpweb.model.Usuario;

@Controller
public class RendaController {
	
	private Usuario usuario;
	
	private void getUsuarioDaSessao(HttpSession session) {
		this.usuario = (Usuario) session.getAttribute("Usuario");	
	}

	@GetMapping(value = "/novaRenda")
	public String telaNovaRenda(HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (usuario != null) {
			return "redirect:/telaNovaRenda";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/telaNovaRenda")
	public ModelAndView telaNovaRenda() {
		ModelAndView mv = new ModelAndView("telaNovaRenda");
		mv.addObject("idUsuario", this.usuario.getId());
		return mv;
	}
}

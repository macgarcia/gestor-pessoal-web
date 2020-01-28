package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.macgarcia.gpweb.model.Lembrete;
import com.macgarcia.gpweb.model.Usuario;

@Controller
public class LembreteController {
	
	private Usuario usuario;
	private Lembrete lembrete;
	
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
	
	@GetMapping(value = "/updateLembrete/{json}")
	public String updateNota(@PathVariable("json") String json, HttpSession session) {
		this.lembrete = new Gson().fromJson(json, Lembrete.class);
		return "redirect:/telaAtualizarAnotacao" ;
	}
	
	@GetMapping(value = "/telaAtualizarAnotacao")
	public ModelAndView telaAtualizarAnotacao(HttpSession session) {
		this.getUsuarioDaSessao(session);
		ModelAndView mv = new ModelAndView("telaAtualizarAnotacao");
		mv.addObject("idUsuario", this.usuario.getId());
		mv.addObject("lembrete", this.lembrete); 
		return mv;
	}
}

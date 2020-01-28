package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.macgarcia.gpweb.model.Renda;
import com.macgarcia.gpweb.model.Usuario;
import com.macgarcia.gpweb.util.Ferramentas;

@Controller
public class RendaController {

	private Usuario usuario;
	private Renda renda;

	private void getUsuarioDaSessao(HttpSession session) {
		this.usuario = (Usuario) session.getAttribute("Usuario");
	}

	@GetMapping(value = "/novaRenda")
	public String telaNovaRenda(HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (this.usuario != null) {
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

	@GetMapping(value = "/updateRenda/{json}")
	public String atualizarRenda(@PathVariable("json") String json, HttpSession session) {
		this.getUsuarioDaSessao(session);
		if(this.usuario != null) {
			this.renda = new Gson().fromJson(json, Renda.class);
			String descr = new Ferramentas().decodificar(this.renda.getDescr());
			this.renda.setDescr(descr);
			return "redirect:/telaAtualizarRenda";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/telaAtualizarRenda")
	public ModelAndView telaAtualizarRenda() {
		ModelAndView mv = new ModelAndView("telaAtualizarRenda");
		mv.addObject("renda", this.renda);
		mv.addObject("idUsuario", this.usuario.getId());
		return mv;
	}
}

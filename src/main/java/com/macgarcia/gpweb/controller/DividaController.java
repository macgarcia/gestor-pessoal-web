package com.macgarcia.gpweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.macgarcia.gpweb.model.Divida;
import com.macgarcia.gpweb.model.Usuario;

@Controller
public class DividaController {
	
	private Usuario usuario;
	private Divida divida;
	
	private void getUsuarioDaSessao(HttpSession session) {
		this.usuario = (Usuario) session.getAttribute("Usuario");
	}

	@GetMapping(value = "/novaDivida")
	public String novaDivida(HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (this.usuario != null) {
			return "redirect:/telaNovaDivida";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/telaNovaDivida")
	public ModelAndView telaNovaDivida() {
		ModelAndView mv = new ModelAndView("telaNovaDivida");
		mv.addObject("idUsuario", this.usuario.getId());
		return mv;
	}
	
	// -- //
	@GetMapping(value = "/updateDivida/{json}")
	public String updateDivida(@PathVariable("json") String json, HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (this.usuario != null) {
			this.divida = new Gson().fromJson(json, Divida.class);
			return "redirect:/telaAtualizarDivida";
		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/telaAtualizarDivida")
	public ModelAndView telaAtualizarDivida() {
		ModelAndView mv = new ModelAndView("telaAtualizarDivida");
		mv.addObject("idUsuario", this.usuario.getId());
		mv.addObject("divida", this.divida);
		return mv;
	}
	// -- //
}

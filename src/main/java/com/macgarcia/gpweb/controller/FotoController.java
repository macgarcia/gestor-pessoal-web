package com.macgarcia.gpweb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.macgarcia.gpweb.component.FotoComponent;
import com.macgarcia.gpweb.model.Foto;
import com.macgarcia.gpweb.model.Usuario;
	
@Controller
public class FotoController {
	
	@Autowired
	FotoComponent dao;
	
	private Usuario usuario;
	private Long idAlbum;
	
	private void getUsuarioDaSessao(HttpSession session) {
		this.usuario = (Usuario) session.getAttribute("Usuario");
	}
	
	@GetMapping(value = "/fotosDoAlbum/{idAlbum}")
	public String telaDeFotos(@PathVariable("idAlbum") Long idAlbum, HttpSession session) {
		this.getUsuarioDaSessao(session);
		if (usuario != null) {
			this.idAlbum = idAlbum;
			return "redirect:/minhasFotos";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping(value = "/minhasFotos")
	public ModelAndView minhasFotos() {
		ModelAndView mv = new ModelAndView("telaMinhasFotos");
		mv.addObject("idAlbum", this.idAlbum);
		return mv;
	}
	
	@PostMapping(value = "/upload")
	public String uploadFoto(@PathParam("nome") String nome, @PathParam("arquivo") Part arquivo, @PathParam("tipo") String tipo) {
		boolean valido = this.validarArquivo(arquivo.getContentType(), tipo);
		if (!nome.isEmpty() && tipo != null && !(arquivo.getSize() == 0) && valido) {
			try {
				InputStream is = arquivo.getInputStream();
				int count = 0;
				int index = 0;
				byte[] b = new byte[(int) arquivo.getSize()];
				while (count < b.length && (index = is.read(b, count, b.length - count)) >= 0) {
					count += index;
				}
				Foto foto = new Foto();
				foto.setNome(nome);
				foto.setArquivo(b);
				foto.setTipo(tipo);
				this.dao.salvarFoto(foto, this.idAlbum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/fotosDoAlbum/" + this.idAlbum;
	}
	
	private boolean validarArquivo(String extensao, String tipo) {
		int index = extensao.indexOf("/");
		String tipoFoto = extensao.substring(index + 1);
		if (tipoFoto.equalsIgnoreCase(tipo)) {
			return true;
		}
		return false;
	}
	
	@GetMapping(value = "/downloadFoto/{idFoto}")
	public String pegarArquivo(@PathVariable("idFoto") Long idFoto, HttpServletResponse response) {
		Foto a = this.dao.buscarUnicaFoto(idFoto);
		if (a != null) {
			if (a.getArquivo() != null) {
				byte[] arquivo = a.getArquivo();
		        response.setContentType("image/" + a.getTipo().toLowerCase());
		        response.setHeader("Content-Disposition", "attachment; filename=Foto." + a.getTipo().toLowerCase());
				try {
					OutputStream output = response.getOutputStream();
					output.write(arquivo);
					output.flush();
					output.close();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return "redirect:/";
	}

}

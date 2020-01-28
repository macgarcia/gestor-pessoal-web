package com.macgarcia.gpweb.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.macgarcia.gpweb.component.DividaComponent;
import com.macgarcia.gpweb.component.LembreteComponent;
import com.macgarcia.gpweb.component.RendaComponent;
import com.macgarcia.gpweb.model.Divida;
import com.macgarcia.gpweb.model.Lembrete;
import com.macgarcia.gpweb.model.Mes;
import com.macgarcia.gpweb.model.Renda;
import com.macgarcia.gpweb.model.Usuario;
import com.macgarcia.gpweb.util.Ferramentas;

@Controller
public class PrincipalController {

	@Autowired
	private RendaComponent rendaComponent;
	@Autowired
	private DividaComponent dividaComponent;
	@Autowired
	private LembreteComponent lembreteComponent;

	private List<Renda> rendas;
	private List<Divida> dividas;
	private List<Lembrete> lembretes, lembretesFiltrada;
	private Integer mesSelecionado;
	private boolean filtroAtivo;

	private Usuario getUsuarioDaSessao(HttpSession session) {
		return (Usuario) session.getAttribute("Usuario");
	}

	@GetMapping(value = "/gestorPessoal")
	public String telaPrincipal(HttpSession session) {
		if (this.getUsuarioDaSessao(session) != null) {
			this.mesSelecionado = null;
			return "telaPrincipal";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/financas")
	public String telaDeFinancas(HttpSession session) {
		if (this.getUsuarioDaSessao(session) != null) {
			return "redirect:/telaDeFinancas";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/telaDeFinancas")
	public ModelAndView telaFinanceiro(HttpSession session) {
		Usuario usuario = this.getUsuarioDaSessao(session);
		ModelAndView mv = new ModelAndView("telaDeFinancas");
		if (usuario != null) {
			if (this.mesSelecionado == null) {
				this.rendas = this.rendaComponent.buscarRendasMensal(usuario.getId());
				this.dividas = this.dividaComponent.buscarDividasMensais(usuario.getId());
			} else {
				this.rendas = this.rendaComponent.buscarRendaDoMesSelecionado(usuario.getId(), this.mesSelecionado);
				this.dividas = this.dividaComponent.buscarDividasDoMesSelecionado(usuario.getId(), this.mesSelecionado);
			}
		}
		Collections.sort(this.rendas, Comparator.comparing(Renda::getId));
		Collections.sort(this.dividas, Comparator.comparing(Divida::getId));
		List<Mes> meses = new Ferramentas().getMeses();

		AtomicReference<Double> vlRendas = new AtomicReference<Double>(0d);
		this.rendas.forEach(r -> vlRendas.set(vlRendas.get() + r.getValor()));

		AtomicReference<Double> vlDividas = new AtomicReference<Double>(0d);
		AtomicReference<Double> vlPago = new AtomicReference<Double>(0d);
		this.dividas.forEach(d -> {
			vlDividas.set(vlDividas.get() + d.getValor());
			if (d.getSituacao() == 0) {
				vlPago.set(vlPago.get() + d.getValor());
			}
		});
		mv.addObject("meses", meses);
		mv.addObject("mesAtual", this.getMesAtual());
		mv.addObject("rendas", this.rendas);
		mv.addObject("totalRendas", this.rendas.size());
		mv.addObject("dividas", this.dividas);
		mv.addObject("totalDividas", this.dividas.size());
		mv.addObject("mesEscolhido", this.getMesAtual());
		mv.addObject("vlRendas", String.format("%.2f", vlRendas.get()));
		mv.addObject("vlDividas", String.format("%.2f", vlDividas.get()));
		mv.addObject("vlPago", String.format("%.2f", vlPago.get()));
		return mv;
	}

	private Mes getMesAtual() {
		Mes m = null;
		if (this.mesSelecionado != null) {
			m = new Ferramentas().getMes(this.mesSelecionado);
		} else {
			SimpleDateFormat df = new SimpleDateFormat("MM");
			Integer cod = Integer.parseInt(df.format(new Date()));
			m = new Ferramentas().getMes(cod);
		}
		return m;
	}

	@GetMapping(value = "/selecionarMes/{mes}")
	public String selecionarMes(@PathVariable("mes") Integer mes) {
		this.mesSelecionado = mes;
		return "redirect:/telaDeFinancas";
	}

	// -- ANOTACOES --//
	@GetMapping(value = "/notas")
	public String notas(HttpSession session) {
		if (this.getUsuarioDaSessao(session) != null) {
			return "redirect:/telaDeAnotacoes";
		}
		return "redirect:/";
	}

	@GetMapping(value = "/telaDeAnotacoes")
	public ModelAndView telaDeAnotacoes(HttpSession session) {
		Usuario usuario = this.getUsuarioDaSessao(session);
		if (this.lembretes == null || !this.filtroAtivo) {
			this.lembretes = this.lembreteComponent.buscarLembretesDoUsuario(usuario.getId());
		}
		ModelAndView mv = new ModelAndView("telaDeAnotacoes");
		if (!this.filtroAtivo) {
			Collections.sort(this.lembretes, Comparator.comparing(Lembrete::getId).reversed());
			mv.addObject("lembretes", this.lembretes);
		} else {
			Collections.sort(this.lembretesFiltrada, Comparator.comparing(Lembrete::getId).reversed());
			mv.addObject("lembretes", this.lembretesFiltrada);			
			this.filtroAtivo = false;
		}
		return mv;
	}

	@GetMapping(value = "/filtrar/{chave}")
	public String filtrarLista(@PathVariable("chave") String chave) {
		if (!chave.equals("null")) {
			this.lembretesFiltrada = this.lembretes.stream()
					.filter(l -> l.getTitulo().toLowerCase().contains(chave.toLowerCase()))
					.collect(Collectors.toList());
			this.filtroAtivo = true;
		} else {
			this.filtroAtivo = false;
		}
		return "redirect:/notas";
	}
	// -- //

	@GetMapping(value = "/sair")
	public String logout(HttpSession session) {
		session.setAttribute("Usuario", null);
		this.rendas = null;
		this.dividas = null;
		this.mesSelecionado = null;
		return "redirect:/";
	}
}

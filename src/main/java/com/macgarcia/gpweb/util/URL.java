package com.macgarcia.gpweb.util;

public class URL {
	
	private static final String CAMINHO_BASE = "https://api-gp.herokuapp.com";
	//private static final String CAMINHO_BASE = "http://localhost:9090";

	public static final String NO_AR = CAMINHO_BASE + "/conf/noAr";

	// -- SERVICO DE USUARIO --//
	public static final String NOVO_USUARIO = CAMINHO_BASE + "/usuario/novoUsuario";
	public static final String VALIDAR_USUARIO = CAMINHO_BASE + "/usuario/validarEntrada";
	// -- //

	// -- SERVICO DE RENDA --//
	public static final String NOVA_RENDA = CAMINHO_BASE + "/renda/novaRenda";
	public static final String ATUALIZAR_RENDA = CAMINHO_BASE + "/renda/atualizarRenda";
	public static final String APAGAR_RENDA = CAMINHO_BASE + "/renda/apagarRenda";

	public static final String PESQUISAR_RENDA = CAMINHO_BASE + "/renda/buscarRendaDoUsuarioPorPeriodo";
	public static final String RENDAS_MENSAIS = CAMINHO_BASE + "/renda/buscarRendaMensal";
	public static final String RENDAS_DO_MES_SELECIONADO = CAMINHO_BASE + "/renda/buscarRendaDoMesSelecionado";
	// -- //

	// -- SERVIÇO DE DIVIDA --//
	public static final String NOVA_DIVIDA = CAMINHO_BASE + "/divida/novaDivida";
	public static final String ATUALIZAR_DIVIDA = CAMINHO_BASE + "/divida/atualizarDivida";
	public static final String DIVIDAS_MENSAIS = CAMINHO_BASE + "/divida/buscarDividaMensal";
	public static final String DIVIDAS_DO_MES_SELECIONADO = CAMINHO_BASE + "/divida/buscarDividaDoMesSelecionado";
	public static final String APAGAR_DIVIDA = CAMINHO_BASE + "/divida/deleteDivida";
	// -- //

	// -- SERVIÇO DE LEMBRETE --//
	public static final String NOVO_LEMBRETE = CAMINHO_BASE + "/lembrete/salvarLembrete";
	public static final String BUSCAR_LEMBRETES = CAMINHO_BASE + "/lembrete/getLembretesDoUsuario";
	public static final String ATUALIZAR_LEMBRETE = CAMINHO_BASE + "/lembrete/atualizarLembrete";
	public static final String APAGAR_LEMBRETE = CAMINHO_BASE + "/lembrete/apagarLembrete";
	// -- //

	// -- SERVIÇO DE CONFIGURAÇÃO -- //
	public static final String SALVAR_CONFIGURACAO = CAMINHO_BASE + "/confUsuario/salvarConf";
	public static final String ATUALIZAR_CONFIGURACAO = CAMINHO_BASE + "/confUsuario/atualizarConf";
	public static final String BUSCAR_CONFIGURACAO = CAMINHO_BASE + "/confUsuario/getConfUsuario";
	public static final String PESQUISA_DIVIDAS = CAMINHO_BASE + "/divida/buscarDividaDoUsuarioPorPeriodo";
	// -- //
	
	// -- SERVIÇO DE ALBUNS -- //
	public static final String BUSCAR_ALBUNS = CAMINHO_BASE + "/album/pegarTodosOsAlbuns";
	
	// -- SERVIÇO DE FOTOS --//
	//public static final String BUSCAR_FOTOS = CAMINHO_BASE + "/foto/buscarFotos";
	public static final String SALVAR_FOTO = CAMINHO_BASE + "/foto/addNovaFoto";
	public static final String BUSCAR_UNICA_FOTO = CAMINHO_BASE + "/foto/buscarFoto";
}

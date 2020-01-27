var btnPesquisar = document.querySelector("#pesquisar");
function pesquisa() {
	var chave = document.getElementById("chave").value;
	if (chave.length == 0) {
		chave = null;
	}
	var url = "/filtrar/" + chave;
	window.location.replace(url);
}
btnPesquisar.onclick = pesquisa;
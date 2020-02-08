var idAlbum = document.getElementById("idAlbum").value;

//-- PROCESSO PARA MOSTRAR AS FOTOS LOGO QUANDO ABRIR A TELA --//

// -- //


function msg() {
	var nome = document.getElementById("nome").value;
	var tipo = document.getElementById("tipo").value;
	var arquivo = document.getElementById("arquivo").value;
	if (nome.length == 0) {
		alert("Informe a descrição do arquivo");
	} else if (arquivo.length == 0) {
		alert("Selecione um arquivo");
	} else if (tipo.length == 0) {
		alert("Informe o tipo do arquivo");
	}
}
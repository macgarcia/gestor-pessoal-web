var idAlbum = document.getElementById("idAlbum").value;

// -- PROCESSO PARA MOSTRAR AS FOTOS LOGO QUANDO ABRIR A TELA --//
var listaDeFotos = [];

var request = new XMLHttpRequest();
var endPoint = "https://api-gp.herokuapp.com/foto/buscarFotos/" + idAlbum;
request.open("GET", endPoint, true);
request.setRequestHeader('Content-type','application/json; charset=utf-8');
request.onload = function() {
	if (request.status == 200) {
		listaDeFotos = JSON.parse(request.responseText);
		montarTela();
		document.getElementById("loading").remove();
	}	
}
request.send();

function montarTela() {
	var tamanho = listaDeFotos.length / 12;
	var elemento = document.getElementById("box");
	for (foto of listaDeFotos) {
	    var divItem = document.createElement("a");
	    divItem.setAttribute("href","#");
	    divItem.setAttribute("class", "col s" + tamanho);
	    divItem.setAttribute("style", "width: 150px; height: 100px; margin-top: 10px; margin-left: 5px; cursor:pointer;");
	    divItem.setAttribute("onclick", "javascript:verFoto(" + foto.id + ");");

//	    var donwload = document.createElement("a");
//	    var texto = document.createTextNode("Download");
//	    donwload.appendChild(texto);
//	    donwload.setAttribute("href", "/downloadFoto/"+foto.id);
//	    donwload.setAttribute("style", "position: absolute;");

	    var img = document.createElement("img");
	    img.setAttribute("src", "data:image/png;base64," + foto.arquivo);
	    img.setAttribute("style", "width: 150px; height: 100px;");

	    divItem.appendChild(img);
	    elemento.appendChild(divItem);
	}
}
// -- //

// -- FUNLÇÃO PARA RENDERIZAR A FOTO SELECIONADA -- //
function verFoto(id) {
	var popup = document.getElementById("popup");
	var foto = pegarFotoDaLista(id);
	var imagem = document.createElement("img");
	imagem.setAttribute("id", "imagem");
	imagem.setAttribute("src", "data:image/png;base64," + foto.arquivo);
	imagem.setAttribute("style", "width: 400px;")
	
	popup.setAttribute("style", "position: absolute; margin: 0 auto; top:63%;"
			+ "left: 46%; transform: translate(-50%, -50%); "
			+ "padding-bottom: 5px;");
	
	var botaoFechar = document.createElement("button");
	botaoFechar.setAttribute("id", "fechar");
	var texto = document.createTextNode("Fechar");
	botaoFechar.setAttribute("style", "width: 100px; background-color: #cc0033; cursor:pointer;"
			+ "color: #ffffff; border: none; margin-left: -290px;");
	botaoFechar.setAttribute("onclick", "javascript:fecharImagem();");
	botaoFechar.appendChild(texto);
	
	var botaoDownload = document.createElement("button");
	var texto = document.createTextNode("Download");
	botaoDownload.setAttribute("id", "download");
	botaoDownload.setAttribute("style", "width: 100px; background-color: green; cursor:pointer;"
			+ "color: #ffffff; border: none; margin-left: 10px;");
	botaoDownload.setAttribute("onclick", "javascript:download("+id+");");
	botaoDownload.appendChild(texto);
	
	popup.appendChild(imagem);
	popup.appendChild(botaoFechar);
	popup.appendChild(botaoDownload);
}

function fecharImagem() {
	var popup = document.getElementById("popup");
	var imagem = document.getElementById("imagem").remove();
	var imagem = document.getElementById("fechar").remove();
	var imagem = document.getElementById("download").remove();
	popup.style.display = 'none';
	
}

function pegarFotoDaLista(id) {
	var obj = null;
	var index = 0;
	var achou = false;
	var tamanho = listaDeFotos.length;
	while(index < tamanho && !achou) {
		var foto = listaDeFotos[index];
		if(foto.id == id) {
			achou = true;
			obj =  foto;
		}
		index++;
	}
	return obj;
}


function download(id) {
	var url = "/downloadFoto/" + id;
	window.location.replace(url);
}
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
	} else {
		var tamanho = arquivo.length;
		var tipoArquivo = arquivo.substr(tamanho - 3, tamanho);
		if (tipoArquivo.toLowerCase() != tipo) {
			alert("Tipo do arquivo não é o mesmo que foi selecionado.")
		}
	}
}
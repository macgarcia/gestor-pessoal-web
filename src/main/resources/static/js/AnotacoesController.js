var btnSalvar = document.querySelector("#salvar");
var lembrete;
var idUsuario;

function iniciar() {
	lembrete = new Object();
	recuperarDados();
	if (validar()) {
		consumirApi();
	}
}

function recuperarDados() {
	if (document.getElementById("id").value == 0) {
		lembrete.id = null
	} else {
		lembrete.id = document.getElementById("id").value;
	}
	lembrete.titulo = document.getElementById("titulo").value;
	lembrete.conteudo = document.getElementById("conteudo").value;
	idUsuario = document.getElementById("idUsuario").value;
}

function validar() {
	if (lembrete.titulo.length == 0) {
		alert("Informe um título");
		return false;
	} else if (lembrete.conteudo.length == 0) {
		alert("Informe o conteudo");
		return false;
	} else {
		return true;
	}
}

function consumirApi() {
	var msg = "";
	var endPoint = "https://api-gp.herokuapp.com/lembrete/salvarLembrete/" + idUsuario;
	if (lembrete.id != null) {
		msg = "Anotação atualizada com sucesso";
	} else {
		msg = "Anotação salva com sucesso";
	}
	var request = new XMLHttpRequest();
	request.open("POST", endPoint, true);
	request.setRequestHeader('Content-type','application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			alert(msg);
		} else {
			alert("Erro...");
		}
		window.location.replace("/notas");
	}
	var json = JSON.stringify(lembrete);
	request.send(json);
}

btnSalvar.onclick = iniciar;

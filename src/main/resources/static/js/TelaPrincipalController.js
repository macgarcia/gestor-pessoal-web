function mesSelecionado() {
	var cod = document.getElementById("mes").value;
	url = "/selecionarMes/" + cod;
	window.location.replace(url);
}

// -- Atualizar Renda --//
function atualizarRenda(id) {
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/renda/buscarRendaPorId/" + id;
	request.open("GET", endPoint, true);
	request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			var json = JSON.parse(request.responseText);
			json.descr = escape(json.descr);
			var url = "/updateRenda/" + encodeURI(JSON.stringify(json));
			window.location.replace(url);
		}
	}
	request.send();
}
// -- //

// -- Excluir renda -- //
function excluirRenda(id) {
	if (confirm("Deseja excluir a renda selecionada?")) {
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/renda/apagarRenda/" + id;
		request.open("DELETE", endPoint, true);
		request.onload = function() {
			if (request.status == 200) {
				alert("Renda excluída com sucesso");
			} else {
				alert("Erro ao excluir a renda...");
			}
			window.location.replace("/financas");
		}
		request.send();
	}
}
// -- //

// -- Atualizar Divida //
function atualizarDivida(id) {
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/divida/buscarDividaPorId/"
			+ id;
	request.open("GET", endPoint, true);
	request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			var json = JSON.parse(request.responseText);
			json.descr = escape(json.descr);
			var url = "/updateDivida/" + encodeURI(JSON.stringify(json));
			window.location.replace(url);
		}
	}
	request.send();
}
// -- //

// -- Excluir divida -- //
function excluirDivida(id) {
	if (confirm("Deseja excluir a dívida selecionada?")) {
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/divida/deleteDivida/" + id;
		request.open("DELETE", endPoint, true);
		request.onload = function() {
			if (request.status == 200) {
				alert("Dívida excluída com sucesso");
			} else {
				alert("Erro ao excluir a dívida...");
			}
			window.location.replace("/financas");
		}
		request.send();
	}
}
// -- //

// -- Atualizar situação da divida -- //
function atualizarSituacaoDivida(id) {
	if (confirm("Deseja alterar a situação da divida selecionada?")) {
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/divida/atualizarSituacao/"
				+ id;
		request.open("PUT", endPoint, true);
		request.onload = function() {
			if (request.status == 200) {
				alert("Situação alterado com sucesso");
			} else {
				alert("Erro ao atualizar a situação");
			}
			window.location.replace("/financas");
		}
		request.send();
	}
}
// -- //

// -- Atualizar anotação -- //
function atualizarAnotacao(id) {
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/lembrete/buscarLembrete/" + id;
	request.open("GET", endPoint, true);
	request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			var json = JSON.parse(request.responseText);
			//json.conteudo = json.conteudo.replace(/\r?\n/g, " ");
			json.conteudo = escape(json.conteudo);
			var url = "/updateLembrete/" + encodeURI(JSON.stringify(json));
			window.location.replace(url);
		} else {
			alert("Anotação não encontrada...");
		}
	}
	request.send();
}
// -- //

// -- Exclir anotação -- //
function excluirAnotacao(id) {
	if (confirm("Deseja excluir a anotação selecionada?")) {
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/lembrete/apagarLembrete/" + id;
		request.open("DELETE", endPoint, true);
		request.onload = function() {
			if (request.status == 200) {
				alert("Anotação excluída com sucesso");
			} else {
				alert("Erro ao excluir a anotação...");
			}
			window.location.replace("/notas");
		}
		request.send();
	}
}
// -- //

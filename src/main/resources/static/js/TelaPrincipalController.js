function mesSelecionado() {
  var cod = document.getElementById("mes").value;
  url = "/selecionarMes/" + cod;
  window.location.replace(url);
}

//-- Atualizar Renda --//
function atualizarRenda(id) {
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/renda/buscarRendaPorId/" + id;
	request.open("GET", endPoint, true);
	request.setRequestHeader('Content-type','application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			var json = JSON.parse(request.responseText);
			var url = "/updateRenda/"+ encodeURI(JSON.stringify(json));
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
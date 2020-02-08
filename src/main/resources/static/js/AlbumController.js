function novoAlbum() {
	var nome = prompt("Digite o título do novo album");
	if (nome.length > 0) {
		var idUsuario = document.getElementById("idUsuario").value;
		salvar(nome, idUsuario);
	} else {
		alert("Nome é obrigatório");
	}
}

function salvar(nomeDoAlbum, idUsuario) {
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/album/adicionarNovoAlbum/" + idUsuario;
	request.open("POST", endPoint, true);
	request.setRequestHeader('Content-type','application/json; charset=utf-8');
	request.onload = function() {
	    if (request.status == 201) { //Código de criação
	    	alert("Album salvo com sucesso.");
	    	window.location.replace("/album");
	    } else {
	    	alert("Erro ao savar...");
	    }
	}
	var album = new Object();
	album.titulo = nomeDoAlbum;
	var json = JSON.stringify(album);
	request.send(json);
}

// -- //
// -- ATUALIZAR O NOME DO ALBUM -- //
function atualizarAlbum(idAlbum) {
	var nome = prompt("Digite o novo título do album");
	if (nome.length > 0) {
		var idUsuario = document.getElementById("idUsuario").value;
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/album/adicionarNovoAlbum/" + idUsuario;
		request.open("POST", endPoint, true);
		request.setRequestHeader('Content-type','application/json; charset=utf-8');
		request.onload = function() {
		    if (request.status == 201) { //Código de criação
		    	alert("Album salvo com sucesso.");
		    	window.location.replace("/album");
		    } else {
		    	alert("Erro ao savar...");
		    }
		}
		var album = new Object();
		album.id = idAlbum;
		album.titulo = nome;
		var json = JSON.stringify(album);
		request.send(json);
	} else {
		alert("Nome é obrigatório");
	}
}

// -- EXCLUIR O ALBUM --//
function excluirAlbum(idAlbum) {
	if (confirm("Deseja excluir o album selecionado, isso irá excluir todas as fotos contidas nele")) {
		var request = new XMLHttpRequest();
		var endPoint = "https://api-gp.herokuapp.com/album/deleteAlbum/" + idAlbum;
		request.open("DELETE", endPoint, true);
		request.onload = function() {
			if (request.status == 200) {
				alert("Album excluído com sucesso");
			} else {
				alert("Erro ao excluir o album...");
			}
			window.location.replace("/album");
		}
		request.send();
	}
}
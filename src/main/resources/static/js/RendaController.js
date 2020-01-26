// Mascara para valores monetarios
// Fonte: https://gist.github.com/antoniopassos/203181.
function moeda(a, e, r, t) {
    let n = ""
      , h = j = 0
      , u = tamanho2 = 0
      , l = ajd2 = ""
      , o = window.Event ? t.which : t.keyCode;
    if (13 == o || 8 == o)
        return !0;
    if (n = String.fromCharCode(o),
    -1 == "0123456789".indexOf(n))
        return !1;
    for (u = a.value.length,
    h = 0; h < u && ("0" == a.value.charAt(h) || a.value.charAt(h) == r); h++)
        ;
    for (l = ""; h < u; h++)
        -1 != "0123456789".indexOf(a.value.charAt(h)) && (l += a.value.charAt(h));
    if (l += n,
    0 == (u = l.length) && (a.value = ""),
    1 == u && (a.value = "0" + r + "0" + l),
    2 == u && (a.value = "0" + r + l),
    u > 2) {
        for (ajd2 = "",
        j = 0,
        h = u - 3; h >= 0; h--)
            3 == j && (ajd2 += e,
            j = 0),
            ajd2 += l.charAt(h),
            j++;
        for (a.value = "",
        tamanho2 = ajd2.length,
        h = tamanho2 - 1; h >= 0; h--)
            a.value += ajd2.charAt(h);
        a.value += r + l.substr(u - 2, u)
    }
    return !1
}

var btnSalvar = document.querySelector("#salvar");
var idUsuario;

function formatarValor() {
	var valor = document.getElementById("valor").value;
	valor = valor.replace(",", ".");
	if (isNaN(valor)) {
		alert("Existem caracteres no campo valor");
		document.getElementById("valor").value = "";
		return;
	}
	document.getElementById("valor").value = valor;
}


function iniciar() {
	var renda = new Object();
	recuperarValores(renda);
	if (validar(renda)) {
		consumirApi(renda);
	}
}

function recuperarValores(renda) {
	renda.id = document.getElementById("idRenda").value;
	renda.descr = document.getElementById("descr").value;
	renda.data = document.getElementById("data").value;
	renda.valor = document.getElementById("valor").value;
	renda.valor = renda.valor.replace(",", ".");
	
	idUsuario = document.getElementById("idUsuario").value;
	if (renda.id == 0) {
		renda.id = null;
	}
}

function validar(renda) {
	if (renda.descr.length == 0) {
		alert("Informe a descrição");
		return false;
	} else if (renda.data.length == 0) {
		alert("Informe a data");
		return false;
	} else if (renda.valor.length == 0) {
		alert("Informe o valor");
		return false;
	} else if (isNaN(renda.valor)) {
		alert("Existem caracteres no campo valor");
		return false;
	}
	return true;
}

function consumirApi(renda) {
	var msg = "";
	if (renda.id != null) {
		msg = "Renda atualizada com sucesso";
	} else {
		msg = "Renda salva com sucesso";
	}
	var request = new XMLHttpRequest();
	var endPoint = "https://api-gp.herokuapp.com/renda/novaRenda/" + idUsuario;
	request.open("POST", endPoint, true);
	request.setRequestHeader('Content-type','application/json; charset=utf-8');
	request.onload = function() {
	    if (request.status == 200) {
	    	var renda = JSON.parse(request.responseText);
	    	alert(msg);
	    	window.location.replace("/financas");
	    } else {
	    	alert("Erro ao savar...");
	    }
	}
	var json = JSON.stringify(renda);
	request.send(json);
}

btnSalvar.onclick = iniciar;

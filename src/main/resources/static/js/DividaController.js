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
var idUsuario = "";
var divida;

function iniciar() {
	divida = new Object();
	recuperarValores();
	if (validar()) {
		consumirApi();
	}
}

function recuperarValores() {
	if (document.getElementById("idDivida").value == 0) {
		divida.id = null;
	} else {
		divida.id = document.getElementById("idDivida").value;
	}
	divida.descr = document.getElementById("descr").value;
	divida.data = document.getElementById("data").value;
	divida.valor = document.getElementById("valor").value;
	var check = document.getElementById("situacao").checked;
	if (check) {
		divida.situacao = 0;
	} else {
		divida.situacao = 1;
	}
	divida.valor = divida.valor.replace(",", ".");
	idUsuario = document.getElementById("idUsuario").value;
}

function validar() {
	if (divida.descr.length == 0) {
		alert("Informe a descrição");
		return false;
	} else if (divida.data.length == 0) {
		alert("Informe a data");
		return false;
	} else if (divida.valor.length == 0) {
		alert("Informe o valor");
		return false;
	} else if (isNaN(divida.valor)) {
		alert("Valor informado é inválido");
		return false;
	} else if (divida.valor <= 0) {
		alert("Valor informado deve ser maior que 0(zero)");
		return false;
	} else {
		return true;
	}
}

function consumirApi() {
	var msg = "";
	var endPoint = "https://api-gp.herokuapp.com/divida/novaDivida/" + idUsuario;
	if (divida.id != null) {
		msg = "Dívida atualizada com sucesso";
	} else {
		msg = "Dívida salva com sucesso";
	}
	var request = new XMLHttpRequest();
	request.open("POST", endPoint, true);
	request.setRequestHeader('Content-type','application/json; charset=utf-8');
	request.onload = function() {
		if (request.status == 200) {
			alert(msg);
		} else {
			alert("Erro ao salvar...");
		}
		window.location.replace("/financas");
	}
	var json = JSON.stringify(divida);
	request.send(json);	
}

btnSalvar.onclick = iniciar;

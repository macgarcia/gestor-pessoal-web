function apagarMsgAlerta() {
	var msgAlerta = document.getElementById("alerta");
	if (msgAlerta != null) {
		window.setTimeout(function() {
			msgAlerta.remove();
		}, 2000);
	}
}

apagarMsgAlerta();

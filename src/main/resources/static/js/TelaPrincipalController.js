function mesSelecionado() {
  var cod = document.getElementById("mes").value;
  url = "/selecionarMes/" + cod;
  window.location.replace(url);
}
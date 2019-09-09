function alteraTamanhoDiv() {
    try {
        var windowWidth = window.innerWidth;
        var windowHeight = window.innerHeight;

        var screenWidth = screen.width;
        var screenHeight = screen.height;

        var umquarto = windowHeight * 0.5;
        var restante = windowHeight - umquarto;

        document.getElementById("tela1").style.height = umquarto + "px";
        document.getElementById("imagemAtual").style.maxHeight = umquarto + "px";
        document.getElementById("tela2").style.height = restante + "px";
        document.getElementById("tela4").style.height = restante + "px";
        document.getElementById("tela3").style.height = restante + "px";
        document.getElementById("video").style.height = restante-3 + "px";
    } catch (error) {
        alert("Erro!" + error);
    }
}

document.body.onresize = function () {
    if (document.body.clientWidth < 800) {
        alteraTamanhoDiv();
    }
};

alteraTamanhoDiv();
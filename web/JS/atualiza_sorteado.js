function buscaSorteado() {

    var divLista = document.getElementById("divTabela");
    var htmlDiv = "";


    var retorno = false;
    var a;
    $.ajax({
        url: 'BuscarSorteado',
        async: false,
        success: function (data)
        {
            var obj = $.parseJSON(data);

            if (obj.nome != "") {
                document.getElementById("divSorteio").style.display = "block";
                document.getElementById("divPrincipal").style.display = "none";
                document.getElementById("sorteado").innerHTML = obj.nome;
            } else {
                document.getElementById("divSorteio").style.display = "none";
                document.getElementById("divPrincipal").style.display = "block";
                document.getElementById("sorteado").innerHTML = "";
            }

        }
    }).done(function () {
        a = true;
    });
    console.log(a);
    return retorno;
}

function checkImgOnline() {
    try {
        buscaSorteado();
    } catch (error) {
        clearInterval(ponteiro6);
    }
}

buscaSorteado();
const ponteiro6 = setInterval("checkImgOnline()", 5000);
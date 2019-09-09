

function atualizaFila() {

    var divLista = document.getElementById("divTabela");
    var htmlDiv = "";


    var retorno = false;
    var a;
    $.ajax({
        url: 'BuscarFila',
        async: false,
        success: function (data)
        {
            var obj = $.parseJSON(data);

            for (var i = 0; i < obj.lista.length; i++) {
                if (i == 0) {
                    htmlDiv = "<tr style='background-color: red'><td><b style='color: white; font-size: 25px'>"+obj.lista[i].nome+"</b></td><td><b style='color: white'>"+obj.lista[i].tipo+"</b></td></tr>";
                } else {
                    htmlDiv = htmlDiv + "<tr><td><b style='font-size: 25px'>"+obj.lista[i].nome+"</b></td><td><b>"+obj.lista[i].tipo+"</b></td></tr>";
                }
            }

            divLista.innerHTML = htmlDiv;

        }
    }).done(function () {
        a = true;
    });
    console.log(a);
    return retorno;
}

function checkImgOnline() {
    try {
        atualizaFila();
    } catch (error) {
        clearInterval(ponteiro1);
    }
}

checkImgOnline();
const ponteiro1 = setInterval("atualizaFila()", 5000);

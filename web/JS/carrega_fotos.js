function atualizaFoto() {

    var idFotoAtual = document.getElementById("id_foto");
    var imagemAtual = document.getElementById("imagemAtual");

    var retorno = false;
    var a;
    $.ajax({
        url: 'BuscarFoto?idAtual=' + idFotoAtual.value,
        async: false,
        success: function (data)
        {
            var obj = $.parseJSON(data);

            if (obj.login[0].url == 1) {


            } else {
                //alert(obj.login[0].url);
                document.getElementById("coment").innerHTML = obj.login[0].comentario;
                document.getElementById("nomeAutor").innerHTML = obj.login[0].nome;
                idFotoAtual.value = obj.login[0].id;
                document.getElementById("imagemAtual").src = "fotos/" + obj.login[0].url;

            }

        }
    }).done(function () {
        a = true;
    });
    console.log(a);
    return retorno;
}



atualizaFoto();
const ponteiro = setInterval("atualizaFoto()", 25000);


function ascii_to_hexa(str) {
    var arr1 = [];
    for (var n = 0, l = str.length; n < l; n++)
    {
        var hex = Number(str.charCodeAt(n)).toString(16);
        arr1.push(hex);
    }
    return arr1.join('');
}
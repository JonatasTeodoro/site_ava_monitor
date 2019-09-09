<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    </head>
    <body style="background-image: url(https://www.dnoticias.pt/binrepository/768x433/0c1/768d432/none/11506/HORF/image_content_2211031_20190514083935.jpg); background-size: cover">
        <div id="divPrincipal">
            <input type="hidden" value="0" id="id_foto" name="id_foto" />
            <div style="margin: 0; padding: 0" class="col-sm-6">
                <div id="tela1" style="margin: 0; padding: 0" class="col-sm-12">
                    <center><img style="width:100%; height: auto; width: auto" id="imagemAtual" src=""/></center>
                </div>

                <div id="tela2" style="margin: 0; padding: 0" class="col-sm-12">
                    <video id="video" width="100%" autoplay controls loop>
                        <source  src="video/ava_patro.mp4" type="video/mp4">
                    </video>
                </div>
            </div>
            <div class="col-sm-6">
                <div id="tela3" class="col-sm-12">
                    <center><b><h2 style="color: black" id="nomeAutor"></h2></b></center>
                    <label style="font-size: 50px" id="coment"></label>
                </div>
                <div id="tela4" class="col-sm-12">
                    <div class="col-md-12" id="divLista">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>Tipo</th>
                                </tr>
                            </thead>
                            <tbody id="divTabela">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="divSorteio" style="display: none">
            <div class="col-md-12">
                <center>
                    <h1 style="margin-top: 30px;">SORTEADO:</h1>
                    <h1 style="margin-top: 10px; font-style: oblique" id="sorteado"></h1>
                </center>
            </div>
        </div>

        <script src="JS/atualiza_fila.js?version=24" type="text/javascript"></script>
        <script src="JS/manipula_telas.js?version=22" type="text/javascript"></script>
        <script src="JS/carrega_fotos.js?version=22" type="text/javascript"></script>
        <script src="JS/atualiza_sorteado.js?version=22" type="text/javascript"></script>



    </body>
</html>

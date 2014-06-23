<%-- 
    Document   : cadastroTema
    Created on : 22/06/2014, 18:53:32
    Author     : pontofrio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <style> 
        #cadTema {margin-left:550px; margin-right:550px}; 

    </style>
    <link href="bootstrap.min.css" rel="stylesheet" media="screen">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Cadastro Tema</title>
    <script type="text/javascript">

        function validaEspacoOrientador() {
            var orientador = cadform.orientador.value;
            var msg = "";
            if (orientador.search(/\s/g) != -1)
            {
                msg += "NNão é permitido iniciar espaços em branco no Campo Orientador\n";
                orientador = orientador.replace(/\s/g, "");
            }
            if (orientador.search(/[^a-z0-9]/i) != -1)
            {
                msg += "Não é permitido caracteres especiais";
                orientador = orientador.replace(/[^a-z0-9]/gi, "");
            }
            if (msg)
            {
                alert(msg);
                cadform.orientador.value = orientador;
                return false;
            }
            return true;
        }

        function validaEspacoTema() {
            var tema = cadform.tema.value;
            var msg = "";
            if (tema.search(/\s/g) != -1)
            {
                msg += "Não é permitido iniciar espaços em branco no Campo Tema\n";
                tema = tema.replace(/\s/g, "");
            }
            if (orientador.search(/[^a-z0-9]/i) != -1)
            {
                msg += "Não é permitido caracteres especiais";
                tema = tema.replace(/[^a-z0-9]/gi, "");
            }
            if (msg)
            {
                alert(msg);
                cadform.orientador.value = tema;
                return false;
            }
            return true;
        }

        //trim completo
        function trim(str) {
            return str.replace(/^\s+|\s+$/g, "");
        }
<!-- Fim do JavaScript que validará os campos obrigatórios! -->
    </script>
</head>

<body>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="bootstrap.min.js"></script>

    <form id="cadform" name="cadform" method="post" action="CadastroTemaServlet"
          onsubmit="return validaEspacoTema(), validaEspacoOrientador(), validaCampo(), validaCampo2();">
        <div id="cadTema">
            
            <legend>Cadastro Do Tema</legend>
      
            <strong>Tema:</strong><br><br/>
            <textarea rows="6" cols="40" name="tema" id="tema" maxlength="200"  onblur="validaEspaco(this)" required></textarea><br><br/>

             Orientador: <input type="text" name="orientador" id="orientador" onblur="validaEspaco(this)" placeholder="Orientador" required /><br></br>
            
            <input class="btn btn-primary" type="submit" id="enviar" name="enviar" value="Enviar">
            </form>
        </div>
        <script>
            function validaEspaco(input) {
                texto = input.value;
                textoNovo = trim(texto);
                if (textoNovo === "") {
                    input.value = textoNovo;
                    alert("Campo " + input.name + " invalido");
                }
            }
        </script>
        
        <%
            String retorno = (String) request.getAttribute("retorno");
            
            if(retorno != null && retorno.equalsIgnoreCase("Sucesso"))
            {
        %>
        
            <script> alert("Cadastro realizado com sucesso!"); </script>
        
        <%  }
            
            else if(retorno != null && retorno.equalsIgnoreCase("Professor Nao Existe"))
            {
        %>
            <script> alert("O professor digitado não existe"); </script>
        <%  }
            
            else if(retorno != null && retorno.equalsIgnoreCase("Problema"))
            {
        %>
            <script> alert("Ocorreu um problema no cadastro, tente novamente."); </script>
        <%
            }
        %>
        
</body>
</html>

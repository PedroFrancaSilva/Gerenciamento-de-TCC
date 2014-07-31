<%-- 
    Document   : formularioDeAvaliacaoAluno
    Created on : 30/07/2014, 10:58:12
    Author     : Gean
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Formulário de Avaliação de Aluno de TCC</title>

        <!-- Bootstrap core CSS -->
        <link href="../../GerenciamentoTCC/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../../GerenciamentoTCC/bootstrap/css/styles.css" rel="stylesheet">

    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <a href="menuPrincipalProfessor.jsp" class="navbar-brand"> Gerenciamento de TCC </a>
                <button class="navbar-toggle" data-toggle = "collapse" data-target = ".OpcoesMenu">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="collapse navbar-collapse OpcoesMenu">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Banca <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li> <a href="http://localhost:8080/GerenciamentoTCC/CriarTemaTCCServlet"> Criar Banca </a></li>
                                <li> <a href="http://localhost:8080/GerenciamentoTCC/MarcarBancaServlet"> Definir Horário Local e Data </a> </li>
                                <li> <a href="http://localhost:8080/GerenciamentoTCC/VerificarBancaServlet"> VerificarBanca </a> </li>
                            </ul>
                        </li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/TemasRequisitadosServlet"> Temas Requisitados  </a></li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/ConfirmarTemaServlet"> Confirmar Tema  </a></li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/cadastroPessoaExterna.jsp"> Cadastrar Pessoa Externa </a> </li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/contato.html"> Contato </a> </li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/sobre.html"> Sobre</a> </li>
                        <li> <a href="http://localhost:8080/GerenciamentoTCC/telaLogin.jsp"> Sair</a> </li>
                    </ul>
                </div>
            </div>
        </div>

        <br><br>

        <h1><center>Universidade Federal do Pampa 
                <br>Campus Alegrete
                <br>Curso de Engenharia de Software</center>
        </h1>
        <h2><center><br>FORMULÁRIO DE AVALIAÇÃO DE TRABALHO DE CONCLUSÃO DE CURSO</center></h2>

        <br><br>
        <label>Aluno:</label> <label> <c:out value="${bancaEscolhida.aluno.nome}"/> </label>
        <label>Matrícula:</label> <c:out value="${bancaEscolhida.aluno.matricula}"/><br>
        <label>Título do Trabalho:</label> <c:out value="${tema.descricao}"/><br>
        <label>Avaliador:</label> <c:out value="${avaliador.nome}"/><br><br><br>
        <form method="post" name="formularioDeAvaliacao"
              action="SalvarNotasServlet" method="post"
              onsubmit="return verificarNotaFinal();"
              >
            <div id="formAvaliacao">
                <table class="table table-hover">
                    <tr>
                        <td><label>I – TRABALHO DESENVOLVIDO (4,0)</label></td>
                        <td><label>Nota</label></td>
                    </tr>
                    <tr>
                        <td><label>Relevância (1,0)<br>
                                O problema é claramente apresentado e devidamente justificado</label></td>
                        <td>
                            <input type="text" name="notaRelevancia" id="notaRelevancia"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialTrabDesenvolvido(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Estado da Arte (1,0)<br>
                                O referêncial teórico está adequado e atualizado</label></td>
                        <td>
                            <input type="text" name="notaEstadoDaArte" id="notaEstadoDaArte"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialTrabDesenvolvido(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Corretude técnica (1,0)<br>
                                O problema foi resolvido utilizando a metodologia adequada</label></td>
                        <td>
                            <input type="text" name="notaCorretudeTecnica" id="notaCorretudeTecnica"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialTrabDesenvolvido(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Abrangência (1,0)<br>
                                O trabalho está completo e os seus objetivos específicos foram atingidos</label></td>
                        <td>
                            <input type="text" name="notaAbrangencia" id="notaAbrangencia"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialTrabDesenvolvido(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td style="text-align: right"><label>Nota Parcial</label></td>
                        <td><input type="text" name="notaParcialTrabDesenvolvido" id="notaParcialTrabDesenvolvido" ></td>           
                    </tr>
                </table>
                <br><br>
                <table class="table table-hover">
                    <tr>
                        <td><label>II – APRESENTAÇÃO (3,0)</label></td>
                        <td><label>Nota</label></td>
                    </tr>
                    <tr>
                        <td><label>Clareza (1,0)<br>
                                As ideias foram expostas de maneira crítica e em consonância com o texto</label></td>
                        <td>
                            <input type="text" name="notaClarezaApresentacao" id="notaClarezaApresentacao"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialApresentacao(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Conhecimento (1,0)<br>
                                Na abordagem do tema foi demonstrado segurança e domínio do assunto</label></td>
                        <td>
                            <input type="text" name="notaConhecimento" id="notaConhecimento"
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialApresentacao(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Planejamento (1,0)<br>
                                A apresentação teve sequência lógica e divisão equitativa do tempo</label></td>
                        <td>
                            <input type="text" name="notaPlanejamento" id="notaPlanejamento" 
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialApresentacao(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td style="text-align: right"><label>Nota Parcial</label></td>
                        <td><input type="text" name="notaParcialApresentacao" id="notaParcialApresentacao" ></td>           
                    </tr>
                </table>
                <br><br>
                <table class="table table-hover">
                    <tr>
                        <td><label>III – QUALIDADE DO TEXTO (3,0)</label></td>
                        <td><label>Nota</label></td>
                    </tr>
                    <tr>
                        <td><label>Clareza (1,0)<br>
                                As ideias foram expostas de maneira clara e o texto é legível</label></td>
                        <td>
                            <input type="text" name="notaClarezaQualidade" id="notaClarezaQualidade" 
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialQualidade(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Gramática e Ortografia (1,0)<br>
                                O texto segue as regras gramaticais e ortográficas da língua portuguesa</label></td>
                        <td>
                            <input type="text" name="notaGramaticaEOrtografia" id="notaGramaticaEOrtografia" 
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialQualidade(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td><label>Estrutura e Organização (1,0)<br>
                                O texto está bem estruturado e organizado</label></td>
                        <td>
                            <input type="text" name="notaEstruturaEOrganizacao" id="notaEstruturaEOrganizacao" 
                                   onblur="return tamanhoNumero(this.value), incrementarNotaParcialQualidade(this.value)"
                                   onkeypress='return SomenteNumero(event)'
                                   onselect="decrementaNotaParcial1(this.value)">
                        </td>           
                    </tr>
                    <tr>
                        <td style="text-align: right"><label>Nota Parcial</label></td>
                        <td><input type="text" name="notaParcialQualidade" id="notaParcialQualidade" ></td>           
                    </tr>
                </table>
                <table class="table table-hover">
                    <tr>
                        <td><label>NOTA FINAL</label></td>
                        <td><input type="text" name="notaFinal" id="notaFinal" ></td>
                        <td> <button type="button" class="bnt btn-primary" onclick="somarNotas()"> Somar notas </button> </td>
                    </tr>
                </table>
                <label>Alegrete, blablbla(data)</label>
            </div>

            <label>Comentários:</label><br>
            <textarea></textarea><br>

            <button type="submit" class="btn btn-primary" name="finalizar" id="finalizar" value="<c:out value='${bancaEscolhida.idBanca}'/>">
                Finalizar
            </button>
        </form> 
        <script>
            var flag = false;
            var valorAnterior = 0.0;
            var contNotaParcial = 0;
            function incrementarNotaParcialTrabDesenvolvido(valor) {
                var valorParcial = document.getElementById("notaParcialTrabDesenvolvido");
                if (!isNaN(valor) && valor !== "" && flag !== true) {
                    var valorAntigo = Number(valorParcial.value);
                    valorParcial.value = parseFloat(valorAntigo, 10) + parseFloat(valor, 10);
                    contNotaParcial++;
                } else {
                    if (valor !== "" && isNaN(valor)) {
                        alert("Digite somente números,\n\
         as notas parciais devem ser feitas utilizando ponto. Exemplo : 8.1");
                    }
                }
                flag = false;
            }

            function incrementarNotaParcialApresentacao(valor) {
                var valorParcial = document.getElementById("notaParcialApresentacao");
                if (!isNaN(valor) && valor !== "" && flag !== true) {
                    var valorAntigo = Number(valorParcial.value);
                    valorParcial.value = parseFloat(valorAntigo, 10) + parseFloat(valor, 10);
                    contNotaParcial++;
                } else {
                    if (valor !== "" && isNaN(valor)) {
                        alert("Digite somente números,\n\
         as notas parciais devem ser feitas utilizando ponto. Exemplo : 8.1");
                    }
                }
                flag = false;
            }

            function incrementarNotaParcialQualidade(valor) {
                var valorParcial = document.getElementById("notaParcialQualidade");
                if (!isNaN(valor) && valor !== "" && flag !== true) {
                    var valorAntigo = Number(valorParcial.value);
                    valorParcial.value = parseFloat(valorAntigo, 10) + parseFloat(valor, 10);
                    contNotaParcial++;
                } else {
                    if (valor !== "" && isNaN(valor)) {
                        alert("Digite somente números,\n\
         as notas parciais devem ser feitas utilizando ponto. Exemplo : 8.1");
                    }
                }
                flag = false;
            }

            function decrementaNotaParcial1(valor) {
                if (!isNaN(valor) && valor !== "") {
                    //valorAnterior = parseFloat(valor,10);
                    contNotaParcial = contNotaParcial - 1;
                }
            }

            function SomenteNumero(e) {
                var tecla = (window.event) ? event.keyCode : e.which;
                if ((tecla > 47 && tecla < 58))
                    return true;
                else {
                    if (tecla == 8 || tecla == 0) {
                        return true;
                    } else if (tecla == 46) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }

            function tamanhoNumero(numero) {
                if (!isNaN(numero) && numero !== "") {
                    var numeroFloat = parseFloat(numero);
                    if (numeroFloat <= 1 && numeroFloat >= 0) {
                        return true;
                    } else {
                        alert("Digite somente um valor de 0 a 1");
                        flag = true;
                        return false;
                    }
                }
                return false;
            }

            function somarNotas() {
                var notaFinal = document.getElementById("notaFinal");
                var valorParcialDesenvolvimento = document.getElementById("notaParcialTrabDesenvolvido");
                var valorParcialApresentação = document.getElementById("notaParcialApresentacao");
                var valorParcialQualidade = document.getElementById("notaParcialQualidade");
                if (contNotaParcial == 10) {
                    notaFinal.value = parseFloat(valorParcialDesenvolvimento.value)
                            + parseFloat(valorParcialApresentação.valueOf().value)
                            + parseFloat(valorParcialQualidade.valueOf().value);
                } else {
                    alert("Preencha todas as notas primeiro");
                }
            }

            function verificarNotaFinal() {
                var notaFinal = document.getElementById("notaFinal");
                if (notaFinal.value == "" || isNaN(notaFinal.value)) {
                    alert("Faça a soma da nota final primeiro");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>

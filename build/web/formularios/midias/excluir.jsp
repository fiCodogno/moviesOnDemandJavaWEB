<%-- 
    Document   : alterar
    Created on : 30 de nov. de 2021, 16:34:09
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Excluir Mídia</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
    </head>
    <body>
        <h1>Excluir Mídia</h1>
        
        <form method="post" action="${cp}/processaMidias">
            
            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.midia.id}"/>
            
            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>${requestScope.midia.titulo}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>${requestScope.midia.anoLancamento}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Código de Barras:</td>
                    <td>${requestScope.midia.codigoBarras}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração (em Minutos):</td>
                    <td>${requestScope.midia.duracaoEmMinutos}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Principal:</td>
                    <td>${requestScope.midia.atorAtrizPrincipal.nome}&nbsp${requestScope.midia.atorAtrizPrincipal.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Coadjuvante:</td>
                    <td>${requestScope.midia.atorAtrizCoadjuvante.nome}&nbsp${requestScope.midia.atorAtrizCoadjuvante.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>${requestScope.midia.genero.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Etária:</td>
                    <td>${requestScope.midia.classificacaoEtaria.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Tipo:</td>
                    <td>${requestScope.midia.tipo.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Interna:</td>
                    <td>${requestScope.midia.classificacaoInterna.descricao}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/midias/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Excluir"/>
                    </td>
                </tr>
            </table>
            
        </form>
    </body>
</html>

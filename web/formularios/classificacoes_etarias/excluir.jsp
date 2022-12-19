<%-- 
    Document   : excluir
    Created on : 30 de nov. de 2021, 16:34:18
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Excluir Classificação Etária</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
    </head>
    <body>
        <h1>Excluir Classificação Etária</h1>
        
        <form method="post" action="${cp}/processaClassificacoesEtarias">
            
            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.classificacao_etaria.id}"/>
            
            <table>
                <tr>
                    <td class="alinharDireita">Descricao:</td>
                    <td>${requestScope.classificacao_etaria.descricao}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/classificacoes_etarias/listagem.jsp">
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

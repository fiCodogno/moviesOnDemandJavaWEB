<%-- 
    Document   : excluir
    Created on : 30 de nov. de 2021, 16:34:18
    Author     : Filipe
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Excluir Ator / Atriz</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
    </head>
    <body>
        <h1>Excluir Ator / Atriz</h1>
        
        <form method="post" action="${cp}/processaAtoresAtrizes">
            
            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.atorAtriz.id}"/>
            
            <table>
                <tr>
                    <td>Nome:</td>
                    <td>${requestScope.atorAtriz.nome}</td>
                </tr>
                <tr>
                    <td>Sobrenome:</td>
                    <td>${requestScope.atorAtriz.sobrenome}</td>
                </tr>
                <tr>
                    <td>Data de Estreia (primeiro filme):</td>
                    <td>
                        <fmt:formatDate
                            pattern="dd-MM-yyyy"
                            value="${requestScope.atorAtriz.dataEstreia}"
                            var="data" scope="page"/>
                        ${data}
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/atores_atrizes/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td>
                        <input type="submit" value="Excluir"/>
                    </td>
                </tr>
            </table>   
        </form>
    </body>
</html>

<%-- 
    Document   : listagem
    Created on : 30 de nov. de 2021, 16:33:41
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaTipos?acao=preparar" />

<!DOCTYPE html>
<html>
    <head>
        <title>Tipos Cadastrados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    
    <body>
        <h1>Tipos Cadastrados</h1>
        
        <p>
            <a href="${cp}/formularios/tipos/novo.jsp">
                Novo Tipo
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaomidias.services.TipoServices" />
                
                <c:forEach items="${servicos.todos}" var="tipo">
                    <tr>
                        <td>${tipo.id}</td>
                        <td>${tipo.descricao}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${tipo.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${tipo.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                        
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/tipos/novo.jsp">
                Novo Tipo
            </a>
        </p>
        
        <p>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </p>
        
    </body>
</html>


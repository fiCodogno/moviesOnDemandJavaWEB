<%-- 
    Document   : listagem
    Created on : 30 de nov. de 2021, 16:33:41
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaAtoresAtrizes?acao=preparar" />

<!DOCTYPE html>
<html>
    <head>
        <title>Atores / Atrizes Cadastrados(as)</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    
    <body>
        <h1>Atores / Atrizes Cadastrados(as)</h1>
        
        <p>
            <a href="${cp}/formularios/atores_atrizes/novo.jsp">
                Novo(a) Ator / Atriz
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Sobrenome</th>
                    <th>Data de Estreia</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaomidias.services.AtorAtrizServices"/>
                
                <c:forEach items="${servicos.todos}" var="atorAtriz">
                    <tr>
                        <td>${atorAtriz.id}</td>
                        <td>${atorAtriz.nome}</td>
                        <td>${atorAtriz.sobrenome}</td>
                        <td>
                            <f:formatDate pattern="dd-MM-yyyy" type="date" value="${atorAtriz.dataEstreia}" />
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${atorAtriz.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${atorAtriz.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                        
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/atores_atrizes/novo.jsp">
                Novo(a) Ator / Atriz
            </a>
        </p>
        <p>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </p>
        
    </body>
</html>

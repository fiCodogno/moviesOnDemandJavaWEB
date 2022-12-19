<%-- 
    Document   : alterar
    Created on : 30 de nov. de 2021, 16:34:09
    Author     : Filipe
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaMidias?acao=preparar" />

<!DOCTYPE html>
<html>
    <head>
        <title>Mídias Cadastradas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    
    <body>
        <h1>Mídias Cadastradas</h1>
        
        <p>
            <a href="${cp}/formularios/midias/novo.jsp">
                Nova Mídia
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Ano de Lançamento</th>
                    <th>Codigo de Barras</th>
                    <th>Duração (em Minutos)</th>
                    <th>Ator Principal</th>
                    <th>Ator Coadjuvante</th>
                    <th>Gênero</th>
                    <th>Classificação Etária</th>
                    <th>Tipo</th>
                    <th>Classificação Interna</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaomidias.services.MidiaServices" />
                
                <c:forEach items="${servicos.todos}" var="midia">
                    <tr>
                        <td>${midia.id}</td>
                        <td>${midia.titulo}</td>
                        <td>${midia.anoLancamento}</td>
                        <td>${midia.codigoBarras}</td>
                        <td>${midia.duracaoEmMinutos}</td>
                        <td>${midia.atorAtrizPrincipal.nome} ${midia.atorAtrizPrincipal.sobrenome}</td>
                        <td>${midia.atorAtrizCoadjuvante.nome} ${midia.atorAtrizCoadjuvante.sobrenome}</td>
                        <td>${midia.genero.descricao}</td>
                        <td>${midia.classificacaoEtaria.descricao}</td>
                        <td>${midia.tipo.descricao}</td>
                        <td>${midia.classificacaoInterna.descricao}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${midia.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${midia.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                        
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/midias/novo.jsp">
                Nova Mídia
            </a>
        </p>
        
        <p>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </p>
        
    </body>
</html>

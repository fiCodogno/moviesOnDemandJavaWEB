<%-- 
    Document   : listagem
    Created on : 30 de nov. de 2021, 16:33:41
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaClassificacoesInternas?acao=preparar" />

<!DOCTYPE html>
<html>
    <head>
        <title>Classificações Internas Cadastradas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    
    <body>
        <h1>Classificações Internas Cadastradas</h1>
        
        <p>
            <a href="${cp}/formularios/classificacoes_internas/novo.jsp">
                Nova Classificação Interna
            </a>
        </p>
        
        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descricao</th>
                    <th>Valor do Aluguel</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            
            <tbody>
                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaomidias.services.ClassificacaoInternaServices"/>
                
                <c:forEach items="${servicos.todos}" var="classificacao_interna">
                    <tr>
                        <td>${classificacao_interna.id}</td>
                        <td>${classificacao_interna.descricao}</td>
                        <td>${classificacao_interna.valorAluguel}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${classificacao_interna.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${classificacao_interna.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                        
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/classificacoes_internas/novo.jsp">
                Nova Classificação Interna
            </a>
        </p>
        
        <p>
            <a href="${cp}/index.jsp">Tela Principal</a>
        </p>
        
    </body>
</html>

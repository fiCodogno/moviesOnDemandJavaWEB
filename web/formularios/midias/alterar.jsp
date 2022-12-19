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
        <title>Alterar Mídia</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    <body>
        <h1>Alterar Mídia</h1>
        
        <form method="post" action="${cp}/processaMidias">
            
            <input name="acao" type="hidden" value="alterar"/>
            <input name="id" type="hidden" value="${requestScope.midia.id}"/>
            
            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="100"
                               maxlength="100"
                               required
                               value="${requestScope.midia.titulo}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="100"
                               maxlength="100"
                               required
                               value="${requestScope.midia.anoLancamento}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Código de Barras:</td>
                    <td>
                        <input name="codigoBarras"
                               type="text"
                               size="20"
                               pattern="\d{13}"
                               placeholder="9999999999999"
                               required
                               value="${requestScope.midia.codigoBarras}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração (em minutos):</td>
                    <td>
                        <input name="duracao"
                               type="text"
                               size="4"
                               maxlength="4"
                               required
                               value="${requestScope.midia.duracaoEmMinutos}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Principal:</td>
                    <td>
                        <jsp:useBean
                            id="servicosAtores"
                            scope="page"
                            class="locacaomidias.services.AtorAtrizServices"/>
                        
                        <select name="idAtorPrincipal" required>
                            <c:forEach items="${servicosAtores.todos}" var="atorPrincipal">
                                <c:choose>
                                    <c:when test="${requestScope.midia.atorAtrizPrincipal.id eq atorPrincipal.id}">
                                        <option value="${atorPrincipal.id}" selected>
                                            ${atorPrincipal.nome}&nbsp${atorPrincipal.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${atorPrincipal.id}">
                                            ${atorPrincipal.nome}&nbsp${atorPrincipal.sobrenome}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Coadjuvante:</td>
                    <td>
                        <select name="idAtorCoadjuvante" required>
                            <c:forEach items="${servicosAtores.todos}" var="atorCoadjuvante">
                                <c:choose>
                                    <c:when test="${requestScope.midia.atorAtrizCoadjuvante.id eq atorCoadjuvante.id}">
                                        <option value="${atorCoadjuvante.id}" selected>
                                            ${atorCoadjuvante.nome}&nbsp${atorCoadjuvante.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${atorCoadjuvante.id}">
                                            ${atorCoadjuvante.nome}&nbsp${atorCoadjuvante.sobrenome}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>
                        <jsp:useBean
                            id="servicosGeneros"
                            scope="page"
                            class="locacaomidias.services.GeneroServices"/>
                        
                        <select name="idGenero" required>
                            <c:forEach items="${servicosGeneros.todos}" var="genero">
                                <c:choose>
                                    <c:when test="${requestScope.midia.genero.id eq genero.id}">
                                        <option value="${genero.id}" selected>
                                            ${genero.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${genero.id}">
                                            ${genero.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Etária:</td>
                    <td>
                        <jsp:useBean
                            id="servicosClassificacoesEtarias"
                            scope="page"
                            class="locacaomidias.services.ClassificacaoEtariaServices"/>
                        
                        <select name="idClassificacaoEtaria" required>
                            <c:forEach items="${servicosClassificacoesEtarias.todos}" var="classificacaoEtaria">
                                <c:choose>
                                    <c:when test="${requestScope.dvd.classificacaoEtaria.id eq classificacaoEtaria.id}">
                                        <option value="${classificacaoEtaria.id}" selected>
                                            ${classificacaoEtaria.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${classificacaoEtaria.id}">
                                            ${classificacaoEtaria.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Tipo:</td>
                    <td>
                        <jsp:useBean
                            id="servicosTipos"
                            scope="page"
                            class="locacaomidias.services.TipoServices"/>
                        
                        <select name="idTipo" required>
                            <c:forEach items="${servicosTipos.todos}" var="tipo">
                                <c:choose>
                                    <c:when test="${requestScope.midia.tipo.id eq tipo.id}">
                                        <option value="${tipo.id}" selected>
                                            ${tipo.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${tipo.id}">
                                            ${tipo.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação Interna:</td>
                    <td>
                        <jsp:useBean
                            id="servicosClassificacoesInternas"
                            scope="page"
                            class="locacaomidias.services.ClassificacaoInternaServices"/>
                        
                        <select name="idClassificacaoInterna" required>
                            <c:forEach items="${servicosClassificacoesInternas.todos}" var="classificacaoInterna">
                                <c:choose>
                                    <c:when test="${requestScope.midia.classificacaoInterna.id eq classificacaoInterna.id}">
                                        <option value="${classificacaoInterna.id}" selected>
                                            ${classificacaoInterna.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${classificacaoInterna.id}">
                                            ${classificacaoInterna.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/midias/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Salvar"/>
                    </td>
                </tr>
            </table>
            
        </form>
    </body>
</html>

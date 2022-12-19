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
        <title>Nova Mídia</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>

    </head>

    <body>
        <h1>Nova Mídia</h1>

        <form method="post" action="${cp}/processaMidias">

            <input name="acao" type="hidden" value="inserir"/>

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="100"
                               maxlength="100"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="100"
                               maxlength="100"
                               required/>
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
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração (em minutos):</td>
                    <td>
                        <input name="duracao"
                               type="text"
                               size="4"
                               maxlength="4"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator / Atriz Principal:</td>
                    <td>
                        <jsp:useBean
                            id="servicosAtores"
                            scope="page"
                            class="locacaomidias.services.AtorAtrizServices"/>

                        <select name="idAtorPrincipal" required>
                            <c:forEach items="${servicosAtores.todos}" var="atorPrincipal">
                                <option value="${atorPrincipal.id}">
                                    ${atorPrincipal.nome}&nbsp${atorPrincipal.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator / Atriz Coadjuvante:</td>
                    <td>
                        <select name="idAtorCoadjuvante" required>
                            <c:forEach items="${servicosAtores.todos}" var="atorCoadjuvante">
                                <option value="${atorCoadjuvante.id}">
                                    ${atorCoadjuvante.nome}&nbsp${atorCoadjuvante.sobrenome}
                                </option>
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
                                <option value="${genero.id}">
                                    ${genero.descricao}
                                </option>
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
                                <option value="${classificacaoEtaria.id}">
                                    ${classificacaoEtaria.descricao}
                                </option>
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
                                <option value="${tipo.id}">
                                    ${tipo.descricao}
                                </option>
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
                                <option value="${classificacaoInterna.id}">
                                    ${classificacaoInterna.descricao}
                                </option>
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

<%-- 
    Document   : novo
    Created on : 30 de nov. de 2021, 16:33:49
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>

        <title>Nova Locação</title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>

        <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
        <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
        <script src="${cp}/js/formularios/locacoes/novo.js"></script>

    </head>

    <body>

        <h1>Nova Locação</h1>

        <form id="formNovaLocacao" method="post" action="${cp}/processaLocacoes">

            <input name="acao" type="hidden" value="inserir"/>
            <input id="hiddenItensLocacao" name="itensLocacao" type="hidden"/>

            <div id="divCliente">
                <jsp:useBean 
                    id="servicosCliente" 
                    scope="page" 
                    class="locacaomidias.services.ClienteServices"/>

                Cliente:
                <br>
                <select id="selectCliente" name="idCliente" required>
                    <c:forEach items="${servicosCliente.todos}" var="cliente">
                        <option value="${cliente.id}">
                            ${cliente.nome} ${cliente.sobrenome}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div id="divItensLocacao">
                <table>
                    <tr>
                        <td>
                            
                            <jsp:useBean 
                                id="servicosLocacao" 
                                scope="page" 
                                class="locacaomidias.services.ExemplarServices"/>
                            
                            <p>
                                Exemplar (Código Interno):
                                <br>
                                <input id="codigoInternoSelect"
                                       type="text"
                                       size="11"
                                       maxlength="11"/>
                                <input id="btnVisualizar" 
                                       type="button" 
                                       value="&#10004;" 
                                       onclick="visualizar(event, '${cp}')"/>
                                <br>
                                <br>

                                Exemplar:
                                <br>
                                <input id="exemplar"
                                       type="text"
                                       size="50"
                                       disabled/>
                                <br>
                                <br>

                                Data de Devolução:
                                <br>
                                <input name="dataFim"
                                       type="date"
                                       size="8"
                                       placeholder="dd/mm/yyyy"
                                       required/>
                                <br>
                                <br>
                            </p>

                        </td>
                        <td class="btnsItensLocacao">
                            <p><input id="btnInserir" type="button" value="&#x2795;" disabled></p>
                            <p><input id="btnRemover" type="button" value="&#x2796;"></p>
                            <p><input id="btnLimpar" type="button" value="&#x274C;"></p>
                        </td>
                        <td>
                            Itens da Locação:
                            <br>
                            <select id="selectItensLocacao" size="10" multiple></select>
                            <br>
                            <div>
                                <div id="divTotal">Total: R$ 0,00</div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td></td>
                        <td class="alinharDireita">
                            <input id="btnSalvar" type="submit" value="Salvar"/>
                        </td>
                    </tr>
                </table>
            </div>
            
            <br>
            <a href="${cp}/formularios/locacoes/listagem.jsp">
                Voltar
            </a>

        </form>

    </body>

</html>
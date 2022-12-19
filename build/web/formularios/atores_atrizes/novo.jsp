<%-- 
    Document   : novo
    Created on : 30 de nov. de 2021, 16:33:49
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Novo(a) Ator / Atriz</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial_scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
        
    </head>
    
    <body>
        <h1>Novo(a) Ator / Atriz</h1>
        
        <form method="post" action="${cp}/processaAtoresAtrizes">
            
            <input name="acao" type="hidden" value="inserir"/>
            
            <table>
                <tr>
                    <td class="alinharDireita">Nome:</td>
                    <td>
                        <input name="nome"
                               type="text"
                               size="20"
                               maxlength="45"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Sobrenome:</td>
                    <td>
                        <input name="sobrenome"
                               type="text"
                               size="20"
                               maxlength="45"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de Estreia (primeiro filme):</td>
                    <td>
                        <input name="dataEstreia"
                               type="date"
                               size="10"  
                               required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/atores_atrizes/listagem.jsp">
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

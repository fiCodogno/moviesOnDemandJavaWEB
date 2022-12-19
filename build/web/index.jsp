<%-- 
    Document   : index
    Created on : 24 de nov. de 2021, 10:14:38
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Sistema para Locação de Mídias</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>Sistema para Locação de Mídias</h1>

        <p>
            <img class="icones" src="${cp}/images/ator_atriz_icone_vermelho.png" alt="ator_atriz_icone_vermelho"/>
            <a href="${cp}/formularios/atores_atrizes/listagem.jsp">
                Atores e Atrizes
            </a>
        </p>

        <p>
            <img class="icones" src="${cp}/images/cidade_icone_vermelho.png" alt="cidade_icone_vermelho"/>
            <a href="${cp}/formularios/cidades/listagem.jsp">
                Cidades
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/classificacao_etaria_icone_vermelho.png" alt="classificacao_etaria_icone_vermelho"/>
            <a href="${cp}/formularios/classificacoes_etarias/listagem.jsp">
                Classificações Etárias
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/classificacao_interna_icone_vermelho.png" alt="classificacao_interna_icone_vermelho"/>
            <a href="${cp}/formularios/classificacoes_internas/listagem.jsp">
                Classificações Internas
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/cliente_icone_vermelho.png" alt="cliente_icone_vermelho"/>
            <a href="${cp}/formularios/clientes/listagem.jsp">
                Clientes
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/estado_icone_vermelho.png" alt="estado_icone_vermelho"/>
            <a href="${cp}/formularios/estados/listagem.jsp">
                Estados
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/exemplar_icone_vermelho.png" alt="exemplar_icone_vermelho"/>
            <a href="${cp}/formularios/exemplares/listagem.jsp">
                Exemplares
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/genero_icone_vermelho.png" alt="genero_icone_vermelho"/>
            <a href="${cp}/formularios/generos/listagem.jsp">
                Gêneros
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/locacao_icone_vermelho.png" alt="locacao_icone_vermelho"/>
            <a href="${cp}/formularios/locacoes/listagem.jsp">
                Locações
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/midia_icone_vermelho.png" alt="midia_icone_vermelho"/>
            <a href="${cp}/formularios/midias/listagem.jsp">
                Mídias
            </a>
        </p>
        
        <p>
            <img class="icones" src="${cp}/images/tipo_midia_icone_vermelho.png" alt="tipo_midia_icone_vermelho"/>
            <a href="${cp}/formularios/tipos/listagem.jsp">
                Tipos de Mídias
            </a>
        </p>

    </body>

</html>

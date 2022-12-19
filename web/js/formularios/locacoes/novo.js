/**
 * Implementação das funções do formulário de locação.
 */

// Ao clicar no botão visualizar (✔️)
function visualizar(event, cp) {
    
    let $codigoInternoSelect = $("#codigoInternoSelect").val();
    
    if ($codigoInternoSelect == null || $codigoInternoSelect == "" || isNaN($codigoInternoSelect)){
        $codigoInternoSelect = 0;
    }
    
    let parametros = new URLSearchParams();
    parametros.append("acao", "visualizar");
    parametros.append("codigoInternoSelect", $codigoInternoSelect);

    fetch(`${cp}/processaExemplares`, {
        method: "POST",
        body: parametros
    }).then(response => {
        return response.json();
    }).then(exemplar => {

        let $exemplar = document.getElementById("exemplar");
        
        if(`${exemplar}` == "*****"){
            $(`#btnInserir`).prop('disabled', true);
            
            $exemplar.value = "Código Inválido!";
        } else if(`${exemplar}` == "-----"){
            $(`#btnInserir`).prop('disabled', true);
            
            $exemplar.value = "Exemplar indisponível!";
        } else{
            $(`#btnInserir`).prop('disabled', false);
            
            $exemplar.value = `${exemplar}`;
        }
        

    }).catch(error => {
        alert("Erro: " + error);
    });

};

// Document Ready (quando o documento estiver pronto)
$(() => {

    // Array para armazenar os itens da locação
    let itensLocacao = [];

    // Formatadores
    let fmtMoeda = new Intl.NumberFormat(
            "pt-BR", {
                style: "currency",
                currency: "BRL"
            }
    );

    // Ao clicar no botão inserir (+)
    $("#btnInserir").on("click", event => {       
          
        let $exemplarTxt = $("#exemplar").val();
        let arrayDadosExemplar =  $exemplarTxt.split(" - ");
        
        let codigoInternoExemplar = $("#codigoInternoSelect").val(); 
        let tituloExemplar = arrayDadosExemplar[1];
        let tipoExemplar = arrayDadosExemplar[2];
        let valorAluguelExemplar = arrayDadosExemplar[3].replace('R$', '');
        
        // se o valor da venda tem vírgula, troca por ponto
        if (valorAluguelExemplar.includes(",")) {
            valorAluguelExemplar = valorAluguelExemplar.replace(",", ".");
        }
        
        let estaNaLista = false;
        itensLocacao.some( item => {
            if ( item.codigoInternoExemplar === codigoInternoExemplar ) {
                estaNaLista = true;
                return true;
            }
        });

        if(!estaNaLista){
            itensLocacao.push({
                codigoInternoExemplar: codigoInternoExemplar,
                tituloExemplar: tituloExemplar,
                tipoExemplar: tipoExemplar,
                valorAluguelExemplar: valorAluguelExemplar,
            });
        } else{
            alert("Item já está na lista!");
        }
        
        
        atualizarGUI();

    });

    // ao clicar no botão remover
    $("#btnRemover").on("click", event => {

        // retorna um array com os values de todos os itens
        // (option) selecionados
        let selecao = $("#selectItensLocacao").val();

        // se não selecionou nenhum
        if (selecao.length === 0) {
            alert("Selecione um item da locação para remover!");

            //se há seleção
        } else if (confirm("Deseja remover o(s) item(ns) da locação selecionado(s)?")) {

            // itera pela seleção
            for (let i = 0; i < selecao.length; i++) {

                // busca sequencial nos itens de venda
                for (let j = 0; j < itensLocacao.length; j++) {

                    let item = itensLocacao[j];

                    // encontrou?
                    if (selecao[i] === item.codigoInternoExemplar) {

                        // remove da posição j
                        itensLocacao.splice(j, 1);
                        break;

                    }

                }

            }

            // remonta a lista
            atualizarGUI();

        }

    });

    // ao clicar no botão limpar
    $("#btnLimpar").on("click", event => {
        if (confirm("Deseja remover todos os itens da locação?")) {
            itensLocacao = [];
            atualizarGUI();
        }
    });

    // submissão da venda
    $("#formNovaLocacao").on("submit", event => {

        if ($("#selectItensLocacao > option").length === 0) {
            alert("Uma locação precisa conter pelo menos um item!");
            return false;
        }

        return true;

    });

    // evita que ao teclar enter dentro do campo
    // de texto o formulário seja submetido
    $("#codigoInternoSelect").on("keydown", event => {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
    });

    // constrói as opções do <select> (lista) de itens de venda;
    // atualiza o valor total da venda;
    // e prepara os dados para envio
    let atualizarGUI = () => {
        
        let $select = $("#selectItensLocacao");
        let total = new Number(0);
        
        $select.html("");

        itensLocacao.forEach(item => {
            
            let valorItem = new Number(item.valorAluguelExemplar);

            $opt = $("<option></option>").
                    html(`${item.tituloExemplar} - ` +
                            `${item.tipoExemplar} - R$` +
                            `${item.valorAluguelExemplar}`).
                    val(`${item.codigoInternoExemplar}`);

            $select.append($opt);
            total = total + valorItem;

        });

        $("#divTotal").html("Total: " + fmtMoeda.format(total));
        $("#hiddenItensLocacao").val(JSON.stringify(itensLocacao));

    };

});

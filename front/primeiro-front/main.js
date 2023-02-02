document.querySelector("#bt").addEventListener("click", function(){
    fetch(`https://economia.awesomeapi.com.br/json/last/BRL-USD,BRL-EUR`).then(response => {
        return response.json();
    }).then(body => {
        const valor = document.querySelector("#valor").value;
        document.querySelector("#euro").innerHTML = `R$ ${valor} em € ${body.BRLEUR.bid*parseFloat(valor)}`;
        document.querySelector("#dolar").innerHTML = `R$ ${valor} em $ ${body.BRLUSD.bid*parseFloat(valor)}`;
    });
});
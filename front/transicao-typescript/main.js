const estados = document.getElementById("estados");
const municipios = document.querySelector("#municipios");
const token = "ce92d6a4f98057c543d6a9aab568fd5b";
estados.addEventListener("change", function() {
    fetch(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estados.options[estados.selectedIndex].value}/municipios`).then(resquest => {
        return resquest.json();
    }).then(body => {
        municipios.innerHTML = "";
        body.forEach(m => municipios.innerHTML += `<option value="${m.nome}">${m.nome}</option>`);
        
    }).catch(error => {
        console.log(error);
    });

});


document.querySelector("#bt-sub").addEventListener("click", async function(){
    const geolocation = await getGeolocation(municipios.options[municipios.selectedIndex].value);
    const city = geolocation[0];
    fetch(`http://api.openweathermap.org/data/2.5/weather?lat=${city.lat}&lon=${city.lon}&limit=5&appid=${token}`).then(request => {
        return request.json();
    }).then(body => {
        document.querySelector("#clima").innerHTML = "Clima: " + body.weather[0].description;
        document.querySelector("#temp-min").innerHTML = `Temperatura minima: ${body.main.temp_min - 273.15}`;
        document.querySelector("#temp-max").innerHTML = `Temperatura maxima: ${body.main.temp_max - 273.15}`;
    });

});

function getGeolocation(nome){
    const cityGeo = fetch(`http://api.openweathermap.org/geo/1.0/direct?q=${nome}&limit=5&appid=${token}`).then(request => {
        return request.json();
    });
    return cityGeo;
}
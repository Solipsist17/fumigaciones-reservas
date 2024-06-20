// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
});

async function registrarPlagas() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('plagas', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const plagas = await request.json();

        console.log(plagas);

    // agregar los datos


    // agregar plaga a la tabla

}





// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
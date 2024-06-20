// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
});

async function registrarReserva() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidad= document.getElementById('txtcantidad').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('reservas', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const reservas = await request.json();

        console.log(reservas);

    // agregar los datos


    // agregar reserva a la tabla

}





// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
});

async function registrarMaquinarias() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidad= document.getElementById('txtcantidad').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('maquinarias', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const maquinarias = await request.json();

        console.log(maquinarias);

    // agregar los datos


    // agregar maquinaria a la tabla

}





// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
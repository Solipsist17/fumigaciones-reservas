// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
});

async function registrarClientes() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.nombre= document.getElementById('txtapellido').value;
    datos.nombre= document.getElementById('txtsexo').value;
    datos.nombre= document.getElementById('txtdni').value;
    datos.nombre= document.getElementById('txttelefono').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('cliente', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const cliente = await request.json();

        console.log(cliente);

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
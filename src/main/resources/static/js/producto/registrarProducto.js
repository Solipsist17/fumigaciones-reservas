// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
mostrarEditarProducto();
});

async function registrarProductos() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidad= document.getElementById('txtcantidad').value;
    datos.activo= document.getElementById('txtactivo').value;
    datos.descripcion= document.getElementById('txtdescripcion').value;

    // llamada a la API
        const request = await fetch('productos', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const productos = await request.json();

        console.log(productos);

    // agregar los datos


    // agregar producto a la tabla

}

 async function mostrarEditarProducto() {
    const nombre = localStorage.nombre;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}



// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
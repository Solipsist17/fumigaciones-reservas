$(document).ready(function() {
    mostrarEditarProducto();
    $('#formEditar').html('');

});



 async function mostrarEditarProducto() {

    let valor = localStorage.getItem('productoC');

    let coleccion = JSON.parse(valor);

    console.log(coleccion.id);

            document.getElementById("txtid").value = coleccion.id;
            document.getElementById("txtnombre").value = coleccion.nombre;
            document.getElementById("txtcantidad").value = coleccion.cantidad;
            document.getElementById("txtactivo").value = coleccion.activo;
            document.getElementById("txtdescricpcion").value = coleccion.descricpcion;

}

async function editarProducto(){

    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txtnombre').value;
        datos.cantidad= document.getElementById('txtcantidad').value;
        datos.activo= document.getElementById('txtactivo').value;
    // llamada a la API
        const request = await fetch('productos' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const productos = await request.json();

         console.log(productos);

}
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
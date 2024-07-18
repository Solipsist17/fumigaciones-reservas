$(document).ready(function() {
    mostrarEditarMaquinaria();

});



 async function mostrarEditarMaquinaria() {

    let valor = localStorage.getItem('maquinariaC');

    let coleccion = JSON.parse(valor);
    const nombre = localStorage.nombre;
    console.log(coleccion.id);

    document.getElementById("txtid").value = coleccion.id;
    document.getElementById("txtnombre").value = coleccion.nombre;
    document.getElementById("txtcantidad").value = coleccion.cantidad;
    document.getElementById("txtactivo").value = coleccion.activo;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}

async function editarMaquinaria(){

    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txtnombre').value;
        datos.cantidad= document.getElementById('txtcantidad').value;
        datos.activo= document.getElementById('txtactivo').value;
    // llamada a la API
        const request = await fetch('maquinarias' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const maquinarias = await request.json();

         console.log(maquinarias);

}
async function regresarTabla(){
    localStorage.removeItem('maquinariaC');
}
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
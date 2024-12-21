$(document).ready(function() {
    mostrarEditarMaquinaria();

});



 async function mostrarEditarMaquinaria(id) {

 // llamada a la API
         const request = await fetch('maquinarias/'+id, {
             method: 'GET',
             headers: getHeaders()
         });
   const maquinaria = await request.json();

   document.getElementById("txteditarnombre").value = maquinaria.nombre;
   document.getElementById("txteditarcantidad").value = maquinaria.cantidad;
   document.getElementById("txteditaractivo").value = maquinaria.activo;

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
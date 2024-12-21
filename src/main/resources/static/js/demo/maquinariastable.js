// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarMaquinarias();
  $('#maquinariastable').DataTable();
});

async function cargarMaquinarias() {

    // llamada a la API
        const request = await fetch('/maquinarias', {
            method: 'GET',
            headers: getHeaders()
        });
        const maquinarias = await request.json();
        const nombre = localStorage.nombre;
        console.log(maquinarias);
//../editarMaquinaria.html
    // agregar los datos
        let maquinariasHTML = '';
        for (let maquinaria of maquinarias) {
            let btnEditar = '<a onclick="mostrareditarMaquinaria('+maquinaria.id+')" class="btn btn-warning" data-toggle="modal" data-target="#editarModal"><i class="fa fa-pen"></i> Editar</a>';
            let btnEliminar = '<a href="#" onclick="eliminarMaquinaria('+maquinaria.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
            let maquinariaHTML = '<tr><td>'+maquinaria.id+'</td><td>'+maquinaria.nombre+'</td><td>'+maquinaria.cantidad+'</td><td>'+maquinaria.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';

            maquinariasHTML += maquinariaHTML;
        }

    // agregar maquinaria a la tabla
        document.querySelector('#maquinariastable tbody').outerHTML = maquinariasHTML;
        document.querySelector('#nombreUsuario').outerHTML = nombre;
        // DOM Javascript
}

async function registrarMaquinarias() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidad= document.getElementById('txtcantidad').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('/maquinarias', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const maquinarias = await request.json();

        console.log(maquinarias);
        $('#agregarModal').modal('hide');

        location.reload(true);


}
async function mostrareditarMaquinaria(id) {

 // llamada a la API
         const request = await fetch('/maquinarias/'+id, {
             method: 'GET',
             headers: getHeaders()
         });
   const maquinaria = await request.json();

   document.getElementById("txteditarid").value = maquinaria.id;
   document.getElementById("txteditarnombre").value = maquinaria.nombre;
   document.getElementById("txteditarcantidad").value = maquinaria.cantidad;
   document.getElementById("txteditaractivo").value = maquinaria.activo;

}

async function editarMaquinaria(){

    let datos = {};
        datos.id= document.getElementById('txteditarid').value;
        datos.nombre= document.getElementById('txteditarnombre').value;
        datos.cantidad= document.getElementById('txteditarcantidad').value;
        datos.activo= document.getElementById('txteditaractivo').value;
    // llamada a la API
        const request = await fetch('/maquinarias' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const maquinarias = await request.json();

         //console.log(maquinarias);
         $('#editarModal').modal('hide');
}

async function eliminarMaquinaria(id) {
    if (!confirm('¿Desea eliminar la maquinaria?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('/maquinarias/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarMaquinarias(); // cargar datos
    location.reload(true);
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarMaquinarias();
  $('#maquinariastable').DataTable();
});

async function cargarMaquinarias() {

    // llamada a la API
        const request = await fetch('maquinarias', {
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
            let btnEditar = '<a href="../editarMaquinaria.html" onclick="cargarDatosMaquinaria('+maquinaria.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
            let btnEliminar = '<a href="#" onclick="eliminarMaquinaria('+maquinaria.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
            let maquinariaHTML = '<tr><td>'+maquinaria.id+'</td><td>'+maquinaria.nombre+'</td><td>'+maquinaria.cantidad+'</td><td>'+maquinaria.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
            maquinariasHTML += maquinariaHTML;
        }

    // agregar maquinaria a la tabla
        document.querySelector('#maquinariastable tbody').outerHTML = maquinariasHTML;
        document.querySelector('#nombreUsuario').outerHTML = nombre;
        // DOM Javascript
}

async function cargarDatosMaquinaria(id){

    //localStorage.setItem('idMaquinaria', id);
// llamada a la API
        const request = await fetch('maquinarias/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const maquinarias = await request.json();

        console.log(maquinarias);

        const collectionJSON = JSON.stringify(maquinarias);

        localStorage.setItem('maquinariaC',collectionJSON);

        console.log(localStorage.getItem('maquinariaC'));
}

async function eliminarMaquinaria(id) {
    if (!confirm('¿Desea eliminar la maquinaria?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('maquinarias/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarMaquinarias(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
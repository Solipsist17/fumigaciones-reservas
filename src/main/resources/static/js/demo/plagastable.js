// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarPlagas();
  $('#plagastable').DataTable();
});

async function cargarPlagas() {
    // llamada a la API
    const request = await fetch('plagas', {
        method: 'GET',
        headers: getHeaders()
    });
    const plagas = await request.json();

    console.log(plagas);

    // agregar los datos
    let plagasHTML = '';
    for (let plaga of plagas) {
        let btnEditar = '<a href="../editarPlaga.html" onclick="cargarDatosplaga('+plaga.id+')" class="btn btn-warning btn-circle"><i class="fa fa-pen"></i></a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+btnEliminar+'</td></tr>';
        plagasHTML += plagaHTML;
    }

    // agregar el plaga a la tabla
    document.querySelector('#plagastable tbody').outerHTML = plagasHTML;
}
async function cargarDatosPlaga(id){

    //localStorage.setItem('idplaga', id);
// llamada a la API
        const request = await fetch('plagas/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const plagas = await request.json();

        console.log(plagas);

        const collectionJSON = JSON.stringify(plagas);

        localStorage.setItem('plagaC',collectionJSON);

        console.log(localStorage.getItem('plagaC'));
}
async function eliminarPlaga(id) {
    if (!confirm('¿Desea eliminar el plaga?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('plagas/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarPlagas(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

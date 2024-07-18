// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarPlagas();
  $('#plagastable').DataTable();
});

async function cargarPlagas() {
    // llamada a la API
    const request = await fetch('/plagas', {
        method: 'GET',
        headers: getHeaders()
    });
    const plagas = await request.json();
    const nombre = localStorage.nombre;
    console.log(nombre);
    console.log(plagas);

    // agregar los datos
    let plagasHTML = '';
    for (let plaga of plagas) {
<<<<<<< HEAD
        let btnEditar = '<a href="../app/editarPlaga.html" onclick="cargarDatosPlaga('+plaga.id+')" class="btn btn-warning btn-circle"><i class="fa fa-pen"></i></a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+btnEliminar+'</td></tr>';
=======
        let btnEditar = '<a href="../editarPlaga.html" onclick="cargarDatosPlaga('+plaga.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
>>>>>>> 34b8e1f943f92c2fb652ded16604a6f5a6041821
        plagasHTML += plagaHTML;
    }

    // agregar plaga a la tabla
    document.querySelector('#plagastable tbody').outerHTML = plagasHTML;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}
async function cargarDatosPlaga(id){

// llamada a la API
        const request = await fetch('plagas/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const plagas = await request.json();

        console.log(plagas);

        const plagaColeccion = JSON.stringify(plagas);

        localStorage.setItem('plagaC',plagaColeccion);

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

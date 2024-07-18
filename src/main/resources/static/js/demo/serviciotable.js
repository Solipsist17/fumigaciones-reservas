// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarServicios();
  $('#serviciostable').DataTable();
});

async function cargarServicios() {
    // llamada a la API
    const request = await fetch('/servicios', {
        method: 'GET',
        headers: getHeaders()
    });
    const paginacion = await request.json();
    const servicios = paginacion.content;
    const nombre = localStorage.nombre;
    console.log(servicios);
//../editarServicio.html
    // agregar los datos
    let serviciosHTML = '';
    for (let servicio of servicios) {
        let btnEditar = '<a href="../editarServicio.html" onclick="cargarDatosServicio('+servicio.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarServicio('+servicio.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let servicioHTML = '<tr><td>'+servicio.id+'</td><td>'+servicio.idMaquinaria+'</td><td>'+servicio.idPlaga+'</td><td>'+servicio.idProducto+'</td><td>'+servicio.nombre+'</td><td>'+servicio.cantidadMaquinaria+'</td><td>'+servicio.cantidadProducto+'</td><td>'+servicio.precioHora+'</td><td>'+servicio.descripcion+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
        serviciosHTML += servicioHTML;
    }

    // agregar el servicio a la tabla
    document.querySelector('#serviciostable tbody').outerHTML = serviciosHTML;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}
async function cargarDatosServicio(id){

    //localStorage.setItem('idservicio', id);
// llamada a la API
        const request = await fetch('servicios/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const servicios = await request.json();

        console.log(servicios);

        const collectionJSON = JSON.stringify(servicios);

        localStorage.setItem('servicioC',collectionJSON);

        console.log(localStorage.getItem('servicioC'));
}
async function eliminarServicio(id) {
    if (!confirm('¿Desea eliminar el servicio?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('servicios/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarServicios(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

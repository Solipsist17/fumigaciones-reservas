// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuariostable').DataTable();
});

async function cargarUsuarios() {
    // llamada a la API
    const request = await fetch('usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const usuarios = await request.json();

    console.log(usuarios);

    // agregar los datos
    let usuariosHTML = '';
    for (let usuario of usuarios) {
        let btnEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+'</td><td>'+usuario.estado+'</td><td>'+btnEliminar+'</td></tr>';
        usuariosHTML += usuarioHTML;
    }

    // agregar el usuario a la tabla
    document.querySelector('#usuariostable tbody').outerHTML = usuariosHTML;
}

async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar el usuario?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('usuarios/' + id, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    //location.reload(); // recargar la página
    cargarUsuarios(); // cargar datos
}

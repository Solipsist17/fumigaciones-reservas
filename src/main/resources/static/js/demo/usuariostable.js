// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuariostable').DataTable();
});

async function cargarUsuarios() {
    // llamada a la API
    const request = await fetch('usuarios', {
        method: 'GET',
        headers: getHeaders()
    });
    const usuarios = await request.json();

    console.log(usuarios);

    // agregar los datos
    let usuariosHTML = '';
    for (let usuario of usuarios) {
        let btnEditar = '<a href="../editarUsuario.html" onclick="cargarDatosUsuario('+usuario.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+'</td><td>'+usuario.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
        usuariosHTML += usuarioHTML;
    }

    // agregar el usuario a la tabla
    document.querySelector('#usuariostable tbody').outerHTML = usuariosHTML;
}
async function cargarDatosUsuarios(id){

    //localStorage.setItem('idusuario', id);
// llamada a la API
        const request = await fetch('usuarios/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const usuarios = await request.json();

        console.log(usuarios);

        const collectionJSON = JSON.stringify(usuarios);

        localStorage.setItem('usuarioC',collectionJSON);

        console.log(localStorage.getItem('usuarioC'));
}
async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar el usuario?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('usuarios/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarUsuarios(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

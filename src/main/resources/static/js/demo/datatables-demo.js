// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#dataTable').DataTable();
});

async function cargarUsuarios() {
    const request = await fetch('usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    const usuarios = await request.json();

    console.log(usuarios);

    let usuariosHTML = '';
    for (let usuario of usuarios) {
        let usuarioHTML = '<tr><td>123</td><td>'+usuario.nombre+'</td><td>'+usuario.estado+'</td><td><a href="#" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a></td></tr>';
        usuariosHTML += usuarioHTML;
    }


    document.querySelector('#dataTable tbody').outerHTML = usuariosHTML;

}

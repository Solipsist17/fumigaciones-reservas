// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarMaquinarias();
  $('#maquinariastable').DataTable();
});

async function cargarMaquinarias() {

    // llamada a la API
        const request = await fetch('maquinarias', { //checar si es pagina o tabla maquinaria
            method: 'GET',
            headers: getHeaders()
        });
        const maquinarias = await request.json();

        console.log(maquinarias);

    // agregar los datos
        let maquinariasHTML = '';
        for (let maquinaria of maquinarias) {
            let btnEliminar = '<a href="#" onclick="eliminarMaquinaria('+maquinaria.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
            let maquinariaHTML = '<tr><td>'+maquinaria.id+'</td><td>'+maquinaria.nombre+'</td><td>'+maquinaria.cantidad+'</td><td>'+maquinaria.activo+'</td><td>'+btnEliminar+'</td></tr>';
            maquinariasHTML += maquinariaHTML;
        }

    // agregar maquinaria a la tabla
        document.querySelector('#maquinariastable tbody').outerHTML = maquinariasHTML;
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
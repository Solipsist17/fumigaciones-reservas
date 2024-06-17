// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarProductos();
  $('#productostable').DataTable();
});

async function cargarProductos() {
    // llamada a la API
    const request = await fetch('productos', {
        method: 'GET',
        headers: getHeaders()
    });
    const productos = await request.json();

    console.log(productos);

    // agregar los datos
    let productosHTML = '';
    for (let producto of productos) {
        let btnEliminar = '<a href="#" onclick="eliminarproducto('+producto.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let productoHTML = '<tr><td>'+producto.id+'</td><td>'+producto.nombre+'</td><td>'+producto.cantidad+'</td><td>'+producto.activo+'</td><td>'+producto.descripcion+'</td><td>'+btnEliminar+'</td></tr>';
        productosHTML += productoHTML;
    }

    // agregar el producto a la tabla
    document.querySelector('#productostable tbody').outerHTML = productosHTML;
}

async function eliminarProducto(id) {
    if (!confirm('¿Desea eliminar el producto?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('productos/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarProductos(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

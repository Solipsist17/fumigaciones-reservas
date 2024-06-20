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
//../editarproducto.html
    // agregar los datos
        let productosHTML = '';
        for (let producto of productos) {
            let btnEditar = '<a href="../editarProducto.html" onclick="cargarDatosProducto('+producto.id+')" class="btn btn-warning btn-circle"><i class="fa fa-pencil"></i></a>';
            let btnEliminar = '<a href="#" onclick="eliminarProducto('+producto.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
            let productoHTML = '<tr><td>'+producto.id+'</td><td>'+producto.nombre+'</td><td>'+producto.cantidad+'</td><td>'+producto.activo+'</td><td>'+producto.descripcion+'</td><td>'+btnEditar+btnEliminar+'</td></tr>';
            productosHTML += productoHTML;
        }

    // agregar producto a la tabla
        document.querySelector('#productostable tbody').outerHTML = productosHTML;
        // DOM Javascript
}

async function cargarDatosProducto(id){

    //localStorage.setItem('idproducto', id);
// llamada a la API
        const request = await fetch('productos/' + id, {
                    method: 'GET',
                    headers: getHeaders()
        });
        const productos = await request.json();

        console.log(productos);

        const collectionJSON = JSON.stringify(productos);

        //localStorage.setItem('productoC',collectionJSON);

        //console.log(localStorage.getItem('productoC'));
}

async function eliminarProducto(id) {
    if (!confirm('¿Desea eliminar la producto?')) {
        return;
    }

    // llamada a la API
    const request = await fetch('productos/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    //location.reload(); // recargar la página
    cargarproductos(); // cargar datos
}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
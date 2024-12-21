// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarProductos();
  $('#productostable').DataTable();
});

async function cargarProductos() {

    // llamada a la API
        const request = await fetch('/productos', {
            method: 'GET',
            headers: getHeaders()
        });
        const productos = await request.json();
        const nombre = localStorage.nombre;
        console.log(productos);
//../editarproducto.html
    // agregar los datos
        let productosHTML = '';
        for (let producto of productos) {
            let btnEditar = '<a onclick="mostrareditarProducto('+producto.id+')" class="btn btn-warning" data-toggle="modal" data-target="#editarModal"><i class="fa fa-pen" ></i> Editar</a>';
            let btnEliminar = '<a href="#" onclick="eliminarProducto('+producto.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
            let productoHTML = '<tr><td>'+producto.id+'</td><td>'+producto.nombre+'</td><td>'+producto.cantidad+'</td><td>'+producto.activo+'</td><td>'+producto.descripcion+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
            productosHTML += productoHTML;
        }

    // agregar producto a la tabla
        document.querySelector('#productostable tbody').outerHTML = productosHTML;
        document.querySelector('#nombreUsuario').outerHTML = nombre;
        // DOM Javascript
}

async function registrarProductos() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidad= document.getElementById('txtcantidad').value;
    datos.activo= document.getElementById('txtactivo').value;
    datos.descripcion= document.getElementById('txtdescripcion').value;

    // llamada a la API
        const request = await fetch('/productos', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const productos = await request.json();

        console.log(productos);

    // agregar los datos


    // agregar producto a la tabla
    location.reload(true);
}

async function mostrareditarProducto(id) {

 // llamada a la API
         const request = await fetch('/productos/'+id, {
             method: 'GET',
             headers: getHeaders()
         });
   const producto = await request.json();

   document.getElementById("txteditarid").value = producto.id;
   document.getElementById("txteditarnombre").value = producto.nombre;
   document.getElementById("txteditarcantidad").value = producto.cantidad;
   document.getElementById("txteditaractivo").value = producto.activo;
   document.getElementById("txteditardescripcion").value = producto.descripcion;
}
async function editarProducto(){

    let datos = {};
        datos.id = document.getElementById('txteditarid').value;
        datos.nombre= document.getElementById('txteditarnombre').value;
        datos.cantidad= document.getElementById('txteditarcantidad').value;
        datos.activo= document.getElementById('txteditaractivo').value;
        datos.descripcion =document.getElementById("txteditardescripcion").value;
    // llamada a la API
        const request = await fetch('/productos' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const productos = await request.json();

        // console.log(productos);
 $('#editarModal').modal('hide');
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
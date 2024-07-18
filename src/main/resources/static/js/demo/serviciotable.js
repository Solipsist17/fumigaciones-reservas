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
        let btnEditar = '<a href="#" data-toggle="modal" data-target=".modal-Editar" onclick="cargarDatosServicio('+servicio.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarServicio('+servicio.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let servicioHTML = '<tr><td>'+servicio.id+'</td><td>'+servicio.idMaquinaria+'</td><td>'+servicio.idPlaga+'</td><td>'+servicio.idProducto+'</td><td>'+servicio.nombre+'</td><td>'+servicio.cantidadMaquinaria+'</td><td>'+servicio.cantidadProducto+'</td><td>'+servicio.precioHora+'</td><td>'+servicio.descripcion+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
        serviciosHTML += servicioHTML;
    }
        datosProducto();
        datosMaquinaria();
        datosPlaga();
    // agregar el servicio a la tabla
    document.querySelector('#serviciostable tbody').outerHTML = serviciosHTML;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}
async function cargarDatosServicio(id){

    //localStorage.setItem('idservicio', id);
// llamada a la API
        const request = await fetch('/servicios/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const servicios = await request.json();

        console.log(servicios);

        /*const collectionJSON = JSON.stringify(servicios);

        localStorage.setItem('servicioC',collectionJSON);

        console.log(localStorage.getItem('servicioC'));*/
        datosEditProducto();
        datosEditMaquinaria();
        datosEditPlaga();
        servicios.idProducto=document.getElementById("txteditarnombre").value;
        servicios.cantidadMaquinaria=document.getElementById("txteditarcantidadmaquinaria").value;
        servicios.cantidadProducto=document.getElementById("txteditarcantidadproducto").value;
        servicios.precioHora=document.getElementById("txteditarpreciohora").value;
        servicios.descipcion=document.getElementById("txteditardescripcion").value;
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
async function registrarServicios() {

    let datos = {};
    datos.idMaquinaria= document.getElementById('txtmaquinaria').value;
    datos.idPlaga= document.getElementById('txtplaga').value;
    datos.idProducto= document.getElementById('txtproducto').value;
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidadMaquinaria= document.getElementById('txtcantidadmaquinaria').value;
    datos.cantidadProducto= document.getElementById('txtcantidadproducto').value;
    datos.precioHora= document.getElementById('txtpreciohora').value;
    datos.descripcion= document.getElementById('txtdescripcion').value;

    // llamada a la API
        const request = await fetch('servicios', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const servicios = await request.json();

        console.log(servicios);

    // agregar los datos


    // agregar servicio a la tabla

}
async function editarServicio(){

    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txteditarnombre').value;
        datos.idMaquinaria=document.getElementById("txteditarmaquinaria").value;
        datos.idPlaga=document.getElementById("txteditarplaga").value;
        datos.idProducto=document.getElementById("txteditarproducto").value;
        datos.cantidadMaquinaria=document.getElementById("txteditarcantidadmaquinaria").value;
        datos.cantidadProducto=document.getElementById("txteditarcantidadproducto").value;
        datos.precioHora=document.getElementById("txteditarpreciohora").value;
        datos.descipcion=document.getElementById("txteditardescripcion").value;
    // llamada a la API
        const request = await fetch('servicios' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const servicios = await request.json();

         console.log(servicios);

}
async function datosMaquinaria(){

const request = await fetch('/maquinarias', {
            method: 'GET',
            headers: getHeaders()
        });
     const maquinarias = await request.json();

     console.log(maquinarias);
        let maquinariasHTML = '<div class="form-group"><label for="txtmaquinaria" class="col-form-label">Maquinaria:</label><select id="txtmaquinaria" name="activo" class="form-control" onchange="verificarInputsAgregar()">';
         for (let maquinaria of maquinarias) {
            maquinariasHTML += '<option value="'+maquinaria.id+'">'+maquinaria.nombre+'</option>';
         }
         maquinariasHTML+='</select></div>';
     document.querySelector('#selectmaquinaria').outerHTML = maquinariasHTML;

}

async function datosProducto(){

const request = await fetch('/productos', {
            method: 'GET',
            headers: getHeaders()
        });
     const productos = await request.json();

     console.log(productos);
         let productosHTML = '<div class="form-group"><label for="txtproducto" class="col-form-label">Producto:</label><select id="txtproducto" name="activo" class="form-control" onchange="verificarInputsAgregar()">';
         for (let producto of productos) {
            productosHTML += '<option value="'+producto.id+'">'+producto.nombre+'</option>';

         }
         productosHTML+='</select></div>';

     document.querySelector('#selectproducto').outerHTML = productosHTML;

}

async function datosPlaga(){

     const request = await fetch('/plagas', {
            method: 'GET',
            headers: getHeaders()
        });
     const plagas = await request.json();

     console.log(plagas);
     // agregar los datos
         let plagasHTML = '<div class="form-group"><label for="txtplaga" class="col-form-label">Plaga:</label><select id="txtplaga" name="activo" class="form-control" onchange="verificarInputsAgregar()">';
         for (let plaga of plagas) {
            plagasHTML += '<option value="'+plaga.id+'">'+plaga.nombre+'</option>';
         }
         plagasHTML+='</select></div>';
         document.querySelector('#selectplaga').outerHTML = plagasHTML;
}
async function datosEditMaquinaria(){

const request = await fetch('/maquinarias', {
            method: 'GET',
            headers: getHeaders()
        });
     const maquinarias = await request.json();

     console.log(maquinarias);
        let maquinariasHTML ='<div class="form-group"><label for="txteditarmaquinaria" class="col-form-label">Maquinaria:</label><select id="txteditarmaquinaria" name="activo" class="form-control" onchange="verificarInputsAgregar()">';
         for (let maquinaria of maquinarias) {
            maquinariasHTML += '<option value="'+maquinaria.id+'">'+maquinaria.nombre+'</option>';
         }
         maquinariasHTML+='</select></div>';
     document.querySelector('#selecteditmaquinaria').outerHTML = maquinariasHTML;

}

async function datosEditProducto(){

const request = await fetch('/productos', {
            method: 'GET',
            headers: getHeaders()
        });
     const productos = await request.json();

     console.log(productos);
         let productosHTML = '<div class="form-group"><label for="txteditarproducto" class="col-form-label">Producto:</label><select id="txteditarproducto" name="activo" class="form-control" onchange="verificarInputsAgregar()">';

         for (let producto of productos) {
            productosHTML += '<option value="'+producto.id+'">'+producto.nombre+'</option>';

         }
         productosHTML+='</select></div>';

     document.querySelector('#selecteditproducto').outerHTML = productosHTML;

}

async function datosEditPlaga(){

     const request = await fetch('/plagas', {
            method: 'GET',
            headers: getHeaders()
        });
     const plagas = await request.json();

     console.log(plagas);
     // agregar los datos
         let plagasHTML = '<div class="form-group"><label for="txteditarplaga" class="col-form-label">Plaga:</label><select id="txteditarplaga" name="activo" class="form-control" onchange="verificarInputsAgregar()">';
         for (let plaga of plagas) {
            plagasHTML += '<option value="'+plaga.id+'">'+plaga.nombre+'</option>';
         }
         plagasHTML+='</select></div>';
         document.querySelector('#selecteditplaga').outerHTML = plagasHTML;
}
async function verificarInputsAgregar() {
        const input1 = document.getElementById('txtmaquinaria').value;
        const input2 = document.getElementById('txtplaga').value;
        const input3 = document.getElementById('txtproducto').value;
        const input4 = document.getElementById('txtnombre').value;
        const input5 = document.getElementById('txtcantidadmaquinaria').value;
        const input6 = document.getElementById('txtcantidadproducto').value;
        const input7 = document.getElementById('txtpreciohora').value;
        const input8 = document.getElementById('txtdescripcion').value;

        const boton = document.getElementById('botonAgregar');

    if (input1 && input2) {
        boton.disabled = false;
    } else {
        boton.disabled = true;
    }
}
async function verificarInputsEditar() {
           const input1 = document.getElementById('txteditarmaquinaria').value;
           const input2 = document.getElementById('txteditarplaga').value;
           const input3 = document.getElementById('txteditarproducto').value;
           const input4 = document.getElementById('txteditarnombre').value;
           const input5 = document.getElementById('txteditarcantidadmaquinaria').value;
           const input6 = document.getElementById('txteditarcantidadproducto').value;
           const input7 = document.getElementById('txteditarpreciohora').value;
           const input8 = document.getElementById('txteditardescripcion').value;

           const boton = document.getElementById('botonEditar');

    if (input1 && input2 && input3) {
        boton.disabled = false;
    } else {
        boton.disabled = true;
    }
}
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

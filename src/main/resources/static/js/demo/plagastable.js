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

        let btnEditar = '<a href="#" data-toggle="modal" data-target="#editarModal" onclick="mostrareditarPlaga('+plaga.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
        plagasHTML += plagaHTML;
    }

    // agregar plaga a la tabla
    document.querySelector('#plagastable tbody').outerHTML = plagasHTML;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}

async function registrarPlagas() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('/plagas', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const plagas = await request.json();

        console.log(plagas);

    // agregar los datos


    // agregar plaga a la tabla

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

async function registrarPlagas() {

    let datos = {};
    datos.nombre= document.getElementById('txtnombre').value;
    datos.activo= document.getElementById('txtactivo').value;

    // llamada a la API
        const request = await fetch('plagas', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const plagas = await request.json();

        console.log(plagas);

    // agregar los datos
        cargarPlagas();

    // agregar plaga a la tabla
    location.reload(true);

}
async function mostrareditarPlaga(id) {

 // llamada a la API
         const request = await fetch('/maquinarias/'+id, {
             method: 'GET',
             headers: getHeaders()
         });
   const maquinaria = await request.json();

   document.getElementById("txteditarid").value = maquinaria.id;
   document.getElementById("txteditarnombre").value = maquinaria.nombre;
   document.getElementById("txteditaractivo").value = maquinaria.activo;

}
async function editarPlaga(){

    let datos = {};
        datos.id = document.getElementById('txteditarid').value;
        datos.nombre= document.getElementById('txteditareditarnombre').value;
        datos.activo= document.getElementById('txteditaractivo').value;
    // llamada a la API
        const request = await fetch('/plagas' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const plagas = await request.json();

        // console.log(plagas);
         $('#editarModal').modal('hide');

}

/*async function verificarInputsAgregar() {
    const input1 = document.getElementById('txtnombre').value;
    const input2 = document.getElementById('txtactivo').value;

    const boton = document.getElementById('botonAgregar');

    if (input1 && input2) {
        boton.disabled = false;
    } else {
        boton.disabled = true;
    }
}
async function verificarInputsEditar() {

    const input1 = document.getElementById('txteditarnombre').value;
    const input2 = document.getElementById('txteditaractivo').value;

    const boton = document.getElementById('botonEditar');

    if (input1 && input2) {
        boton.disabled = false;
    } else {
        boton.disabled = true;
    }
}*/

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

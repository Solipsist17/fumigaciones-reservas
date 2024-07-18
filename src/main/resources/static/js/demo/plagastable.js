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
//<<<<<<< HEAD
//<<<<<<< HEAD
/*
        let btnEditar = '<a href="../app/editarPlaga.html" onclick="cargarDatosPlaga('+plaga.id+')" class="btn btn-warning btn-circle"><i class="fa fa-pen"></i></a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+btnEliminar+'</td></tr>';
*/
//=======
//        let btnEditar = '<a href="../app/editarPlaga.html" onclick="cargarDatosPlaga('+plaga.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
//        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
//        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
//>>>>>>> 34b8e1f943f92c2fb652ded16604a6f5a6041821
//=======

        let btnEditar = '<a href="#" data-toggle="modal" data-target=".modal-Editar" onclick="cargarDatosPlaga('+plaga.id+')" class="btn btn-warning"><i class="fa fa-pen"></i> Editar</a>';
        let btnEliminar = '<a href="#" onclick="eliminarPlaga('+plaga.id+')" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</a>';
        let plagaHTML = '<tr><td>'+plaga.id+'</td><td>'+plaga.nombre+'</td><td>'+plaga.activo+'</td><td>'+btnEditar+'</td><td>'+btnEliminar+'</td></tr>';
//>>>>>>> 744e5baef6afe8dab0980e9c64a561c431c552db
        plagasHTML += plagaHTML;
    }

    // agregar plaga a la tabla
    document.querySelector('#plagastable tbody').outerHTML = plagasHTML;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}
async function cargarDatosPlaga(id){

// llamada a la API
        const request = await fetch('plagas/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const plagas = await request.json();

        console.log(plagas);

        document.getElementById("txtid").value = plagas.id;
        document.getElementById("txteditarnombre").value = plagas.nombre;
        document.getElementById("txteditaractivo").value = plagas.activo;

        //const plagaColeccion = JSON.stringify(plagas);

        //localStorage.setItem('plagaC',plagaColeccion);

        //console.log(localStorage.getItem('plagaC'));
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

}
async function editarPlaga(){

    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txteditarnombre').value;
        datos.activo= document.getElementById('txteditaractivo').value;
    // llamada a la API
        const request = await fetch('plagas' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const plagas = await request.json();

         console.log(plagas);

}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}

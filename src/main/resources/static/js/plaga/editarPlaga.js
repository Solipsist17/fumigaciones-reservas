<<<<<<< HEAD
$(document).ready(function() {
    console.log('aaaaa');
    mostrarEditarPlaga();
    console.log('aaaaa');
});
mostrarEditarPlaga();


 async function mostrarEditarPlaga() {
 console.log('editar plaga ejecutandose...');
=======

    $(document).ready(function() {
    mostrarEditarPlaga();

    });



async function mostrarEditarPlaga() {
>>>>>>> 34b8e1f943f92c2fb652ded16604a6f5a6041821

    let valor = localStorage.getItem('plagaC');

    let coleccion = JSON.parse(valor);
    const nombre = localStorage.nombre;
    console.log(nombre);
    console.log(coleccion);

    document.getElementById("txtid").value = coleccion.id;
    document.getElementById("txtnombre").value = coleccion.nombre;
    document.getElementById("txtactivo").value = coleccion.activo;

    document.querySelector('#nombreUsuario').outerHTML = nombre;
}

async function editarPlaga(){
    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txtnombre').value;
        datos.activo= document.getElementById('txtactivo').value;
    // llamada a la API
        const request = await fetch('plagas' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const plagas = await request.json();

         console.log(plagas);

}

async function regresarTabla(){

    localStorage.removeItem('plagaC');
}
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
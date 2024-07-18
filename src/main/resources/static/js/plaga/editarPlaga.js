$(document).ready(function() {
    console.log('aaaaa');
    mostrarEditarPlaga();
    console.log('aaaaa');
});
mostrarEditarPlaga();


 async function mostrarEditarPlaga() {
 console.log('editar plaga ejecutandose...');

    let valor = localStorage.getItem('plagaC');

    let coleccion = JSON.parse(valor);

    console.log(coleccion.id);

            document.getElementById("txtid").value = coleccion.id;
            document.getElementById("txtnombre").value = coleccion.nombre;
            document.getElementById("txtactivo").value = coleccion.activo;

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
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
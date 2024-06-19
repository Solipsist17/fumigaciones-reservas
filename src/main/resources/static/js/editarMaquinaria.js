$(document).ready(function() {
    cargarDatosMaquinaria();
  $('#txtnombre').;
});

async function cargarDatosMaquinaria(id){

// llamada a la API
        const request = await fetch('maquinarias', {
            id: maquinarias.id,
            method: 'GET',
            headers: getHeaders()
        });
        const maquinarias = await request.json();

        console.log(maquinarias);

}

function mostrarEditarMaquinaria(id) {
    const request = await fetch('maquinarias/' + id, {
                id: maquinarias.id,
                method: 'GET',
                headers: getHeaders()
            });
            const maquinarias = await request.json();

            console.log(maquinarias);
            maquinarias.id;

}

// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
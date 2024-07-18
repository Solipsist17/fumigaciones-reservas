/*
$(document).ready(function() {
    validarLogin();
});
*/

validarLogin();

function validarLogin() {
    var token = localStorage.getItem('token');
    console.log('Token:', token);

    // construir el header
    var headers = new Headers();
    headers.append('Authorization', 'Bearer ' + token); // Asegurarse de que el formato sea correcto
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');

    // configurar la solicitud HTTP
    var requestOptions = {
       method: 'POST',
       headers: headers
    };

    // hacer la solicitud http
    fetch('/auth/validate-token', requestOptions)
        .then(response => {
            if (response.ok) {
               return response.text();
            } else if (response.status === 401) {
               throw new Error('No autorizado');
            } else if (response.status === 403) {
               throw new Error('Acceso prohibido');
            } else {
               throw new Error('Error en la solicitud: ' + response.statusText);
            }
        })
        .then(txt => {
            if (txt == 'OK') {
                window.location.href = '/app/index.html';
            }
        })
        .catch(error => {
            console.error('Inicie sesión')
           //console.error('Error al validar login:', error);
           //alert('Error al validar login');
           //window.location.href = '/login.html'; // Redirigir al formulario de login
        });
}
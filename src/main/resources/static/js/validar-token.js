// VALIDAR TOKEN
autenticar();
setInterval(autenticar, 300000); // cada 5 minutos valida el token

function autenticar() {
    var token = localStorage.getItem('token');
    if (!token) {
       alert('No tiene acceso a esta página. Inicie sesión nuevamente.');
       window.location.href = '/login.html'; // Redirigir al formulario de login si no hay token
       return;
    }

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
        .catch(error => {
           console.error('Error al cargar la página protegida:', error);
           alert('No tiene acceso a esta página. Inicie sesión nuevamente.');
           window.location.href = '/login.html'; // Redirigir al formulario de login
        });
}
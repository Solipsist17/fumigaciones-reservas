// Call the dataTables jQuery plugin
$(document).ready(function() {
  // on ready
});

async function iniciarSesion() {
    let datos = {};
    datos.nombre = document.getElementById('txtNombre').value;
    datos.clave = document.getElementById('txtClave').value;

    // llamada a la API
    const request = await fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const response = await request.json();

    if (response != 'ERROR') {
        localStorage.token = response.jwtToken;
        localStorage.nombre = datos.nombre;
        window.location.href = '/app/index.html';//////////////////////
        //cargarPagina();
    } else {
        alert('Las credenciales son incorrectas. Por favor intente nuevamente');
    }
}

function cargarPagina() {
    // Recuperar el token JWT
    var token = localStorage.token;

    // Construir la cabecera de la solicitud con el token JWT
    var headers = new Headers();
    console.log(token);
    headers.append('Authorization', token);
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');

    // Configurar la solicitud HTTP
    var requestOptions = {
      method: 'GET', // O 'POST', 'PUT', 'DELETE', etc.
      headers: headers
      //,
      //redirect: 'follow'
    };

    // Hacer la solicitud HTTP
    fetch('usuarios.html', requestOptions)
      //.then(response => response.text())
      .then(response => {
              if (response.ok) {
                  window.history.pushState({}, '', 'usuarios.html');
                  return response.text();
              } else {
                  throw new Error('Error al cargar la página');
              }
      })
      .then(html => {
         document.open();
         document.write(html);
         document.close();
      })
      .catch(error => console.log('error', error));
}



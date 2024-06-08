function logout() {
    fetch('/auth/logout', {
       method: 'POST',
       headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
       }
       }).then(response => {
          if (response.ok) {
            localStorage.removeItem('token'); // Eliminar el token del localStorage
            localStorage.removeItem('nombre');
            window.location.href = '/login.html'; // Redirigir al formulario de login
          }
       }).catch(error => {
          console.error('Error al cerrar sesión:', error);
    });
}
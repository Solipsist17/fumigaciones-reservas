document.addEventListener('DOMContentLoaded', () => {
    mostrarPorRol();
});

function mostrarPorRol() {
    console.log("desde acceso-rol.js");
    const token = localStorage.getItem('token');
    if (!token) {
        return;
    }

    // Verificar rol del usuario
    fetch('/auth/me', {
        headers: { 'Authorization': `Bearer ${token}` }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al obtener el usuario');
        }
        return response.json();
    })
    .then(user => {
        console.log(user.rol);
        if (user.rol == 'ADMINISTRADOR') {
            console.log('bloqueando acceso');
            document.getElementById('navServicios').style.display = 'none';
            document.getElementById('navReservas').style.display = 'none';
            document.getElementById('navFacturas').style.display = 'none';

        }
        if (user.rol == 'GERENTE') {
            document.getElementById('navUsuarios').style.display = 'none';
            document.getElementById('navMaquinarias').style.display = 'none';
            document.getElementById('navPlagas').style.display = 'none';
            document.getElementById('navProductos').style.display = 'none';
        }
    })
    .catch(error => {
        console.error(error);
    });
}
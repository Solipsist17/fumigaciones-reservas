// Call the dataTables jQuery plugin
$(document).ready(function() {
//ON READY
mostrarEditarServicio();
});
async function datosMaquinaria(){

const request = await fetch('maquinarias', {
            method: 'GET',
            headers: getHeaders()
        });
     const maquinarias = await request.json();

     console.log(maquinarias);
        let maquinariasHTML = '<div class="col-sm-10"><select id="txtmaquinaria" class="form-control form-control-sm">';
         for (let maquinaria of maquinarias) {
            maquinariasHTML += '<option value="'+maquinaria.id+'">'+maquinaria.nombre+'</option>';
         }
         maquinariasHTML+='</select></div>'
     document.querySelector('#selectmaquinaria').outerHTML = maquinariasHTML;

}

async function datosProducto(){

const request = await fetch('productos', {
            method: 'GET',
            headers: getHeaders()
        });
     const productos = await request.json();

     console.log(productos);
         let productosHTML = '<div class="col-sm-10"><select id="txtproducto" class="form-control form-control-sm">';
         for (let producto of productos) {
            productosHTML += '<option value="'+producto.id+'">'+producto.nombre+'</option>';

         }
         productosHTML+='</select></div>';
     document.querySelector('#selectproducto').outerHTML = productosHTML;

}

async function datosPlaga(){

     const request = await fetch('plagas', {
            method: 'GET',
            headers: getHeaders()
        });
     const plagas = await request.json();

     console.log(plagas);
     // agregar los datos
         let plagasHTML = '<div class="col-sm-10"><select id="txtplaga" class="form-control form-control-sm">';
         for (let plaga of plagas) {
            plagasHTML += '<option value="'+plaga.id+'">'+plaga.nombre+'</option>';
         }
         plagasHTML+='</select></div>';
         document.querySelector('#selectplaga').outerHTML = plagasHTML;
}
 async function mostrarEditarServicio() {
    const nombre = localStorage.nombre;
    datosPlaga();
    datosMaquinaria();
    datosProducto();
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}
async function registrarServicios() {

    let datos = {};
    datos.idMaquinaria= document.getElementById('txtmaquinaria').value;
    datos.idPlaga= document.getElementById('txtplaga').value;
    datos.idProducto= document.getElementById('txtproducto').value;
    datos.nombre= document.getElementById('txtnombre').value;
    datos.cantidadMaquinaria= document.getElementById('txtcantidadmaquinaria').value;
    datos.cantidadProducto= document.getElementById('txtcantidadproducto').value;
    datos.precioHora= document.getElementById('txtpreciohora').value;
    datos.descripcion= document.getElementById('txtdescripcion').value;

    // llamada a la API
        const request = await fetch('servicios', {
            method: 'POST',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
        const servicios = await request.json();

        console.log(servicios);

    // agregar los datos


    // agregar servicio a la tabla

}





// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
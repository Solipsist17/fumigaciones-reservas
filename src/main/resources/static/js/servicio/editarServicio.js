$(document).ready(function() {
mostrarEditarServicio();

});

//Hay que traer los datos de las columnas plaga, maquinaria y producto

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

    let valor = localStorage.getItem('servicioC');

    let coleccion = JSON.parse(valor);
    const nombre = localStorage.nombre;
    console.log(coleccion);


    document.getElementById("txtid").value = coleccion.id;
    datosPlaga();
    datosMaquinaria();
    datosProducto();
    document.getElementById("txtnombre").value = coleccion.nombre;
    document.getElementById("txtcantidadmaquinaria").value = coleccion.cantidadMaquinaria;
    document.getElementById("txtcantidadproducto").value = coleccion.cantidadProducto;
    document.getElementById("txtpreciohora").value = coleccion.precioHora;
    document.getElementById("txtdescripcion").value = coleccion.descripcion;
    document.querySelector('#nombreUsuario').outerHTML = nombre;
}

async function editarServicio(){

    let datos = {};
        datos.id = document.getElementById('txtid').value;
        datos.nombre= document.getElementById('txtnombre').value;
        datos.idMaquinaria=document.getElementById("txtmaquinaria").value;
        datos.idPlaga=document.getElementById("txtplaga").value;
        datos.idProducto=document.getElementById("txtproducto").value;
        datos.cantidadMaquinaria=document.getElementById("txtcantidadmaquinaria").value;
        datos.cantidadProducto=document.getElementById("txtcantidadproducto").value;
        datos.precioHora=document.getElementById("txtpreciohora").value;
        datos.descipcion=document.getElementById("txtdescripcion").value;
    // llamada a la API
        const request = await fetch('servicios' , {
            method: 'PUT',
            headers: getHeaders(),
            body:JSON.stringify(datos)
        });
         const servicios = await request.json();

         console.log(servicios);

}
async function regresarTabla(){
    localStorage.removeItem('servicioC');
}
// Enviar el token en cada request
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
    }
}
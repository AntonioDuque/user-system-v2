// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function registrarUsuarios(){

    let datos = {};
    datos.nombre   = document.querySelector('#txtNombre').value;
    datos.apellido = document.querySelector('#txtApellido').value;
    datos.email    = document.querySelector('#txtEmail').value;
    datos.password = document.querySelector('#txtPassword').value;

    let repetirPassword = document.querySelector('#txtRepetirPassword').value;

    if(repetirPassword != datos.password){
    alert('La contraseña que escribiste es diferente');
    return;
    }


      const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      alert("La cuenta fue creada con exito!");

      window.location.href = 'login.html'
}


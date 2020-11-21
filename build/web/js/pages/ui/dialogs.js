$(function () {
    $('.js-sweetalert button').on('click', function () {
        var type = $(this).data('type');
        if (type === 'basic') {
            showBasicMessage();
        }
        else if (type === 'with-title') {
            showWithTitleMessage();
        }
        else if (type === 'success') {
            showSuccessMessage();
        }
        else if (type === 'confirm') {
            showConfirmMessage();
        }
        else if (type === 'cancel') {
            showCancelMessage();
        }
        else if (type === 'with-custom-icon') {
            showWithCustomIconMessage();
        }
        else if (type === 'html-message') {
            showHtmlMessage();
        }
        else if (type === 'autoclose-timer') {
            showAutoCloseTimerMessage();
        }
        else if (type === 'prompt') {
            showPromptMessage();
        }
        else if (type === 'ajax-loader') {
            showAjaxLoaderMessage();
        }
    });
});

//These codes takes from http://t4t5.github.io/sweetalert/
function showBasicMessage() {
    swal("La descripción esta vacia");
}

function showWithTitleMessage() {
    swal("Here's a message!", "It's pretty, isn't it?");
}

function showSuccessMessage() {
    swal("Good job!", "You clicked the button!", "success");
}

function showConfirmMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        closeOnConfirm: false
    }, function () {
        swal("Deleted!", "Your imaginary file has been deleted.", "success");
    });
}

function showCancelMessage() {
    swal({
        title: "Are you sure?",
        text: "You will not be able to recover this imaginary file!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel plx!",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            swal("Deleted!", "Your imaginary file has been deleted.", "success");
        } else {
            swal("Cancelled", "Your imaginary file is safe :)", "error");
        }
    });
}

function showWithCustomIconMessage() {
    swal({
        title: "Sweet!",
        text: "Here's a custom image.",
        imageUrl: "../../images/thumbs-up.png"
    });
}

function showHtmlMessage() {
    swal({
        title: "HTML <small>Title</small>!",
        text: "A custom <span style=\"color: #CC0000\">html<span> message.",
        html: true
    });
}

function showAutoCloseTimerMessage() {
    swal({
        title: "Auto close alert!",
        text: "I will close in 2 seconds.",
        timer: 2000,
        showConfirmButton: false
    });
}

function showPromptMessage() {

     Swal.fire({
  title: 'Eliminar',
  text: 'Desea eliminar la cronograma asignado',
  input: 'select',
  inputOptions: {
    'cronograma': {
      CT0004: 'CR0004',
      CT0003: 'CR0003',
      CT0002: 'CR0002',
      CT0001: 'CR0001'
    }
  },
  inputPlaceholder: 'Seleccione el id del cronograma',
  showCancelButton: true,
  inputValidator: (value) => {
    return new Promise((resolve) => {
      if (value === 'CT0001') {
        resolve()
      } else {
        resolve('Seleccione una cita')
      }
    })
  }
})

if (fruit) {
  Swal.fire(`You selected: ${fruit}`)
}

 

}

function showAjaxLoaderMessage() {
    swal({
        title: "Ajax request example",
        text: "Submit to run ajax request",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
    }, function () {
        setTimeout(function () {
            swal("Ajax request finished!");
        }, 2000);
    });
}


 var uno=document.getElementById('descripcion');
 var dos=document.getElementById('codigo');
 var tre=document.getElementById('cantidad');
 var cuatro=document.getElementById('PVP');
 var cinco=document.getElementById('costo');
 var seis=document.getElementById('categoria');

function crearproduc(){

    if (uno.value==='' && dos.value==='' && tre.value==='' && cuatro.value==='' && cinco.value==='') {
        swal("Error", "Los campos estan vacios", "warning");
        
    }
    else if (uno.value==='') {
        swal("Error", "La descripcion esta vacia", "warning");
    }
    else if (dos.value==='') {
        swal("Error", "El codigo esta vacia", "warning");
    }
    else if (tre.value==='') {
        swal("Error", "La cantidad es necesaria", "warning");
    }
    else if (cuatro.value==='') {
        swal("Error", "El PVP es necesario", "warning");
    }
    else if (cinco.value==='') {
        swal("Error", "El costo no puede estar vacio", "warning");
    }
    else{
       swal({
        title: "Estas seguro?",
        text: "Se creara el producto correspondiente en el inventario",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Si, crealo",
        cancelButtonText: "No, cancelar producto",
        closeOnConfirm: false,
        closeOnCancel: false
    }, function (isConfirm) {
        if (isConfirm) {
            swal("Creado", "Tu producto fue creado con exito", "success");
        } else {
            swal("Cancelado", "Tu producto no fue registrado", "error");
        }
    });
    }

}
function salir(){

    Swal.fire({
  icon: 'info',
  title: 'Hasta luego',
  text: 'Que vuelvas pronto',
  timer: 2000,
  timerProgressBar: true,
  onBeforeOpen: () => {
    Swal.showLoading()
    timerInterval = setInterval(() => {
      const content = Swal.getContent()
      if (content) {
        const b = content.querySelector('b')
        if (b) {
          b.textContent = Swal.getTimerLeft()
        }
      }
    }, 100)
  },
  onClose: () => {
    clearInterval(timerInterval)
  }
}).then((result) => {
  /* Read more about handling dismissals below */
  if (result.dismiss === Swal.DismissReason.timer) {
    window.location.href="../../../fullpag/Inicio.html";
  }
})


     
}
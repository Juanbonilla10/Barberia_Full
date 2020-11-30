function sumar(valor) {
    
    
    
    var total = 0;
    var traer = document.getElementById("spTotal");
    
    valor = parseInt(valor); // Convertir el valor a un entero (número).
    
    alert("El valor del total es: " +  valor);
    alert("El valor del total es: " +  traer.value);
    		
    /* Esta es la suma. */
    total = ((total) + parseFloat(valor));
    
    alert("El resultado es: " + valor + traer);
	
    // Colocar el resultado de la suma en el control "span".
    document.getElementById('spTotal').innerHTML = valor;
}
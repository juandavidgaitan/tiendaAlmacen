$(document).ready(() => {
	loadProductosCarrito();
})

loadProductosCarrito = () => {
	const productosCarrito = JSON.parse(localStorage.getItem(cedula));
	if(!productosCarrito || productosCarrito.length <= 0) {
		$('#listProductosAgregados').html('');
		return;
	}
	let html;
	let total = 0;
	productosCarrito.forEach(prod => {
		html += `<tr><td>${prod.nombre}</td><td>${prod.precioUnitario}</td> <td>${prod.cantidad}</td><td><button type="button" class="btn btn-danger" onclick="eliminar(${prod.productoId})">Eliminar</button></td></tr>`;
		total += prod.cantidad * prod.precioUnitario;
	})
	html+= `<tr><td/><td><b>TOTAL</b></td><td>${total}</td><td><button type="button" class="btn btn-success" onclick="comprar()">Comprar</button></td></tr>`;
	$('#listProductosAgregados').html('');
	$('#listProductosAgregados').html(html);
}

agregarProducto = (productoId) => {
	let producto = {};
	const cantidad = $('#'+productoId).val();
	
	if(cantidad <= 0) {
		alert('La cantidad debe ser mayor a cero');
		return;
	}
	
	const productoOriginal =  productosAsCarrito.find(x => x.productoId == productoId);
	productosCarrito = localStorage.getItem(cedula) ? JSON.parse(localStorage.getItem(cedula)) : [];
	const prod = productosCarrito.find(x => x.productoId == productoId)
	if(prod) {
		const index = productosCarrito.indexOf(prod);
		productosCarrito[index].cantidad = cantidad;
	} else {
		producto.nombre = productoOriginal.nombre;
		producto.cantidad = cantidad;
		producto.productoId = productoOriginal.productoId;
		producto.precioUnitario = productoOriginal.precioUnitario;
		producto.codigo = productoOriginal.codigo;
		productosCarrito.push(producto);		
	}
	
	localStorage.setItem(cedula, JSON.stringify(productosCarrito));
	loadProductosCarrito();
}

eliminar = (productoId) => {
	productosCarrito = localStorage.getItem(cedula) ? JSON.parse(localStorage.getItem(cedula)) : []; 
	const prod = productosCarrito.find(x => x.productoId == productoId);
	const index = productosCarrito.indexOf(prod);
	productosCarrito.splice(index, 1);
	localStorage.setItem(cedula, JSON.stringify(productosCarrito));
	loadProductosCarrito();
}

comprar = () =>  {
	const productosCarrito = localStorage.getItem(cedula) ? JSON.parse(localStorage.getItem(cedula)) : [];

	localStorage.removeItem(cedula);
	sendRequestPost(`http://localhost:9090/usuario/comprar/${cedula}` , productosCarrito).then(data => {
    	console.log(data);
  	});
  	loadProductosCarrito();
}

async function sendRequestPost(url = '', data = {}) {
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  });
  return response.json();
}

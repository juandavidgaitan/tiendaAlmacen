var facturaDespachar;

seleccionarDespachar = (facturaId) => {
	facturaDespachar = facturaId;
	console.log(facturaDespachar)
}

despachar = () => {
	sendRequestGet(`http://localhost:9090/usuario/despachar/${facturaDespachar}`).then(data => {
    	console.log(data);
  	});
}

async function sendRequestGet(url = '') {
 	const response = await fetch(url);
	$("#formTarget").submit();
}

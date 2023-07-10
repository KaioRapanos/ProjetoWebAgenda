/**
 *Validacao de formulario
 *@author Jose de Assis [professor youTube curso JavaWeb (Java EE)] 
 *@param idcon
 */

 function confirmar(idcon){
	 let resposta = confirm("Confirmar a exclusão deste contato? ")
	 if(resposta === true){
		 //alert(idcon)
		 window.location.href = "delete?idcon="+idcon
	 }
 }

var app = angular.module('myApp', []).config(function($sceProvider) {
	$sceProvider.enabled(false);
});

app.controller('myCtrl', function($scope, $http) {
	$scope.nomeCliente = "";
	$scope.documentoCliente = "";
	$scope.jsonpronto = "JSONPRONTO";
	$scope.caixaTelefonesHeight = 37;
	$scope.caixaEnderecosHeight = 37;
	$scope.caixaTelefonesHeightExt = 37;
	$scope.caixaEnderecosHeightExt = 37;
	$scope.qtdcamposTel = 1;
	$scope.qtdcamposEnd = 1;
	$scope.msgErro = "";
	$scope.statusColor = "red";
	$scope.camposTelefone = [{id: "campoTel1", titulo: "Telefone"}];
	$scope.camposEnderecos = [{id: "campoEnd1", titulo: "Endereço Principal"}];
	var telefonesValidos = [];
	var enderecosValidos = [];
	var enderecoPrincipal = true;

	
	$scope.addCamposTelefone = function() {
		$scope.qtdcamposTel ++;
		$scope.caixaTelefonesHeight += 37;
		$scope.caixaTelefonesHeightExt += 37;
		var ident = "campoTel"+$scope.qtdcamposTel;
		var tit = "Telefone("+$scope.qtdcamposTel+")";
		$scope.camposTelefone.push({id: ident, titulo: tit});
	  
	};

	
	$scope.addCamposEndereco = function() {
		$scope.qtdcamposEnd ++;
		$scope.caixaEnderecosHeight += 37;
		$scope.caixaEnderecosHeightExt += 37;
		var ident = "campoEnd"+$scope.qtdcamposEnd;
		var tit = "Endereço("+$scope.qtdcamposEnd+")";
		$scope.camposEnderecos.push({id: ident, titulo: tit});
	  
	};
	
	$scope.entradasPassamRestricoes = function() {
		
		var mensagemErro = "";
		$scope.msgErro = "";
		
		document.getElementById("inNome").style.border = "1px solid rgba(10, 176, 196,1)";
		document.getElementById("inDoc").style.border = "1px solid rgba(10, 176, 196,1)";
		document.getElementById("campoTel1").style.border = "1px solid rgba(10, 176, 196,1)";
		document.getElementById("campoEnd1").style.border = "1px solid rgba(10, 176, 196,1)";
		
		if($scope.nomeCliente==="" || $scope.documentoCliente ==="" || telefonesValidos.length == 0 || !enderecoPrincipal){
			
			
			if($scope.nomeCliente===""){
				mensagemErro += " Campo <b>Nome</b> está vazio.<br>";
				 document.getElementById("inNome").style.borderColor = "red";
			}
			if($scope.documentoCliente===""){
				mensagemErro += " Campo <b>Documento</b> está vazio.<br>";
				document.getElementById("inDoc").style.borderColor = "red";
			}
			if(telefonesValidos.length == 0){
				mensagemErro += " É necessário cadastrar pelo menos 1 <b>Telefone</b>.<br>";
				document.getElementById("campoTel1").style.borderColor = "red";
			}
			if(!enderecoPrincipal){
				mensagemErro += " É necessário cadastrar 1 <b>Endereço principal</b>.<br>";
				document.getElementById("campoEnd1").style.borderColor = "red";
			}

			$scope.statusColor = "red";
			$scope.msgErro = mensagemErro;
			return false;
		}
		return true;
	  
	};
	
	$scope.prepararEntradasExtensiveis = function(){
		telefonesValidos.length = 0;
		enderecosValidos.length = 0;
		enderecoPrincipal = true;
		
		for(var a=0; a<$scope.camposTelefone.length; a++){
			var telefoneAtual = document.getElementById($scope.camposTelefone[a].id).value;
			if (!(telefoneAtual === "")){
				telefonesValidos.push(telefoneAtual);
			}
		}
		
		for(var a=0; a<$scope.camposEnderecos.length; a++){
			var enderecoAtual = document.getElementById($scope.camposEnderecos[a].id).value; 
			
			if (!(enderecoAtual === "")){
				enderecosValidos.push(enderecoAtual);
			}else{
				if(a==0){
					enderecoPrincipal = false;
					break;
				}
			}
		}
		
		
		
	}
	
	$scope.resetCampos = function() {
		$scope.camposTelefone.length = 1;
		$scope.camposEnderecos.length = 1;
		$scope.caixaTelefonesHeight = 37;
		$scope.caixaEnderecosHeight = 37;
		$scope.caixaTelefonesHeightExt = 37;
		$scope.caixaEnderecosHeightExt = 37;
		$scope.qtdcamposTel = 1;
		$scope.qtdcamposEnd = 1;
		document.getElementById("inNome").innerHTML = "";
		document.getElementById("inDoc").innerHTML = "";
		document.getElementById("campoTel1").value = "";
		document.getElementById("campoEnd1").value = "";
		$scope.nomeCliente = "";
		$scope.documentoCliente = "";
			
	}
	
	$scope.prepararJSONReq = function() {
		var reqJ = 
			"{\"nome\": \""+$scope.nomeCliente+"\","+
			"\"documento\": \""+$scope.documentoCliente+"\","+
			"\"telefones\": [";
			
			
			for(var a=0; a<telefonesValidos.length; a++){
				var telefoneAtual = telefonesValidos[a];
				
					reqJ = reqJ + "\""+telefoneAtual+"\"";
					if(a!=(telefonesValidos.length-1)){
						reqJ += ",";
						
					}
			}
			
			reqJ += "], \"enderecos\": [";
			
			for(var a=0; a<enderecosValidos.length; a++){
				var enderecoAtual = enderecosValidos[a];
				
					reqJ = reqJ + "\""+enderecoAtual+"\"";
					if(a!=(enderecosValidos.length-1)){
						reqJ += ",";
						
					}
			}
			reqJ += "]}";
			console.log("JSON preparado "+reqJ);
			return reqJ;
	}
	
	$scope.cadastrar = function() {
		
		$scope.jsonpronto = "";
		$scope.prepararEntradasExtensiveis();
		if($scope.entradasPassamRestricoes ()){
			
			$scope.jsonpronto = $scope.prepararJSONReq();
			
			
			$http({
				method: 'POST',
				url: "http://localhost:8080/service/cadastrarcliente",
				data: $scope.jsonpronto,
				headers: {'Content-Type': 'application/json'}
			}).then(function(response) {
				$scope.httpbody = response.data;
				
				$scope.httpstatuscode = response.status;
				$scope.httpstatustext = response.statusText;
			
				
				if($scope.httpstatuscode=="200"){
					$scope.statusColor = "green";
					$scope.msgErro = $scope.nomeCliente+" cadastrado com sucesso";
					$scope.resetCampos();
					
				}else{
					$scope.statusColor = "red";
					$scope.msgErro = "Não foi possivel cadastrar "+$scope.httpstatustext;
				}
				console.log($scope.httpbody.Resposta);
			}).catch(function (erro) {
				$scope.statusColor = "red";
				$scope.msgErro = "Não foi possivel cadastrar";
				
			});
		}
		
	 };
});
	

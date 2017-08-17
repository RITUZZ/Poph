var app = angular.module('db', []);

console.log("loaded index.js");
app.controller('SubmitController',function($scope,$http,$rootScope){
	$scope.username="USERNAME";
		$scope.submit=function(){
			
			var loginDetails = {
					username:$scope.username,
					password:$scope.password
				};
			$http({
			  url: 'Login',
			  method: 'POST',
			  data:loginDetails,
			  datatype:'JSON'
			})
			.then(function success(data){
				data=data.data
				if(data.status==true){
					$scope.View.url="HTML/dashboard.html";
					getData($http);
				}
				else
					alert("Wrong Username or Password");
			},function error(data){
				console.log("error");
			});
		}
	});

var deal,instruments,counterParty,endingPosition,average,instrumentPrice,selectedInst="Astronomica",type="B",rprofit,eprofit;
function getData($http)
{
	$http({
		  url: 'Tables/Deal?offset=0&limit=1000',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			deal=data.data;
		},function error(data){
			console.log("error");
		});
	
	$http({
		  url: 'Tables/Counterparty',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			counterParty=data.data;
		},function error(data){
			console.log("error");
		});
	$http({
		  url: 'Table/RealisedProfitLoss',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			rprofit=data.data;
			console.log(rprofit);
		},function error(data){
			console.log("error");
		});
	$http({
		  url: 'Table/EffectiveProfitLoss',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			eprofit=data.data;
			console.log(eprofit);
		},function error(data){
			console.log("error");
		});
	
	$http({
		  url: 'Tables/Instruments',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			instruments=data.data;
		},function error(data){
			console.log("error");
		});
		
	$http({
		  url: 'Tables/EndingPosition',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			endingPosition=data.data;
		},function error(data){
			console.log("error");
		});
	
	$http({
		  url: 'Tables/AveragePrice',
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			average=data.data;
		},function error(data){
			console.log("error");
		});
	getInstrumentPrice($http,selectedInst,type);
}
function getInstrumentPrice($http,id,type){
	console.log(id);
	console.log(type);
	$http({
		  url: 'Data/InstrumentPrice?id='+id+'&type='+type,
		  method: 'GET',
		  datatype:'JSON'
		})
		.then(function success(data){
			instrumentPrice=data.data;
			LineGraphDrawer.start(instrumentPrice.answer);
			VolumeGraphDrawer.start(instrumentPrice.answer);
		},function error(data){
			console.log("error");
		});
}

app.controller("main-controller",function($scope,$http){

	$scope.View={};
	$scope.View.subUrl="HTML/dash-tradeoverview.html";
	$scope.View.url="HTML/login.html ";
	
	
	$scope.navoptions=function(obj){
		var temp = angular.element(".dash-nav>.navbar-nav>li").removeClass('active');
		var type = angular.element(obj.target.parentNode).attr('navtype');
		if(type=='1')
			{
				$scope.View.subUrl="HTML/dash-tradeoverview.html";
			}
		else if(type=='2')
			{
				$scope.View.subUrl="HTML/dash-tableview.html";
			}
		else if(type=='3')
		{
			$scope.View.subUrl="HTML/dash-options.html";
		}
		 $(obj.target.parentNode).addClass('active');
	};
	
	$scope.instnav=function(obj){
		selectedInst = obj.target.text;
		$('#linegraph>svg').empty();
		$('#volumegraph>svg').empty();
		var temp = angular.element(".instrument").removeClass('active');
		 $(obj.target.parentNode).addClass('active');
		getInstrumentPrice($http,selectedInst,type);
	};
	
	$scope.changeType=function(obj){
		type = obj.target.text;
		$('#linegraph>svg').empty();
		$('#volumegraph>svg').empty();
		var temp = angular.element(".instrumentnav").removeClass('active');
		 $(obj.target.parentNode).addClass('active');
		getInstrumentPrice($http,selectedInst,type);
	};
});